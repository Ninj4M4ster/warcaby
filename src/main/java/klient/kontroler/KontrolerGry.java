package klient.kontroler;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import klient.komunikacja.Mediator;
import klient.komunikacja.wiadomosci.TypyWiadomosci;
import klient.komunikacja.wiadomosci.Wiadomosc;
import klient.model.Model;
import klient.model.ModelGry;
import klient.widoki.widgety.Pionek;
import klient.widoki.widgety.PolePlanszy;

/**
 * Klasa kontrolera widoku gry.
 */
public class KontrolerGry implements KontrolerWidoku {
  /** Model widoku */
  private ModelGry model_;
  /** Mediator pomiedzy aplikacja oraz polaczeniem z serwerem */
  private Mediator mediator_;

  /** Kontener aktualnie przesuwanego pionka */
  private Pionek kontenerAktualniePrzesuwanegoPionka_;

  /** Czy jakis pionek jest aktualnie przesuwany? */
  private boolean pionekPrzesuwany_;

  /**
   * Metoda odpowiedzialna za przechowanie modelu widoku gry.
   * @param model Model widoku gry.
   */
  @Override
  public void przekazModel(Model model) {
    if(this.model_ != null)
      throw new IllegalStateException("Nie mozna dwa razy przekazac modelu do kontrolera widoku");
    this.model_ = (ModelGry) model;
  }

  /**
   * Metoda odpowiedzialna za przekazanie instancji mediatora do aplikacji.
   *
   * @param mediator Mediator miedzy aplikacja oraz polaczeniem z serwerem.
   */
  @Override
  public void przekazMediator(Mediator mediator) {
    this.mediator_ = mediator;
  }

  /**
   * Metoda odpowiedzialna za podjecie proby odnowienia polaczenia.
   */
  @Override
  public void odnowPolaczenie() {
    this.mediator_.odnowPolaczenie();
  }

  /**
   * Metoda odpowiedzialna za rozpoczecie przesuwania pionka po planszy.
   *
   * @param mouseEvent Wydarzenie nacisniecia na pionek.
   * @param kontenerPrzesuwanegoPionka Kontener przesuwanego pionka.
   */
  public void zacznijPrzesuwacPionek(MouseEvent mouseEvent, Pionek kontenerPrzesuwanegoPionka) {
    if(kontenerPrzesuwanegoPionka.kolorPionka().equals(this.model_.kolorPionkow())) {
      kontenerPrzesuwanegoPionka.ustawStartowaPozycjaX(mouseEvent.getSceneX());
      kontenerPrzesuwanegoPionka.ustawStartowaPozycjaY(mouseEvent.getSceneY());
      kontenerPrzesuwanegoPionka.getParent().toFront();

      this.kontenerAktualniePrzesuwanegoPionka_ = kontenerPrzesuwanegoPionka;
      this.pionekPrzesuwany_ = true;
    }
  }

  /**
   * Metoda odpowiedzialna za przesuwanie pionka za myszka.
   *
   * @param mouseEvent                 Wydarzenie przesuniecia myszka po ekranie.
   * @param kontenerPrzesuwanegoPionka Kontener przesuwanego pionka.
   */
  public void przesunPionek(MouseEvent mouseEvent, Pionek kontenerPrzesuwanegoPionka) {
    if(this.pionekPrzesuwany_) {
      kontenerPrzesuwanegoPionka.setTranslateX(
          mouseEvent.getSceneX() - kontenerPrzesuwanegoPionka.startowaPozycjaX());
      kontenerPrzesuwanegoPionka.setTranslateY(
          mouseEvent.getSceneY() - kontenerPrzesuwanegoPionka.startowaPozycjaY());
    }
  }

  /**
   * Metoda odpowiedzialna za zakonczenie przesuwania pionka po planszy.
   * Ustawia przesuniecie pionka wzgledem aktualnego pola na zero.
   */
  public void skonczPrzesuwacPionek() {
    if(this.pionekPrzesuwany_) {
      kontenerAktualniePrzesuwanegoPionka_.setTranslateX(0);
      kontenerAktualniePrzesuwanegoPionka_.setTranslateY(0);
    }
  }

  /**
   * Metoda odpowiedzialna za wyslanie wiadomosci o przesuniecie pionka po
   * puszczeniu go na wybrane pole.
   *
   * @param pole Pole nad ktorym znajdowala sie myszka po puszczeniu pionka.
   * @param wynikWydarzenia Wynik wydarzenia.
   */
  public void puszczonoMyszkeNadPolem(PolePlanszy pole, PickResult wynikWydarzenia) {
    // sprawdz czy pionek przesuwany jest na inne pole niz to na ktorym stoi
    if(this.pionekPrzesuwany_
        && wynikWydarzenia.getIntersectedNode() instanceof Circle) {
      PolePlanszy startowePole =
          (PolePlanszy) this.kontenerAktualniePrzesuwanegoPionka_.getParent();
      int kolumnaStartowa = startowePole.kolumna();
      int rzadStartowy = startowePole.rzad();

      int docelowaKolumna = pole.kolumna();
      int docelowyRzad = pole.rzad();

      Wiadomosc wiadomosc =
          new Wiadomosc(kolumnaStartowa,
              rzadStartowy,
              docelowaKolumna,
              docelowyRzad,
              TypyWiadomosci.RUCH_PIONKA);
      this.mediator_.wyslijWiadomoscDoSerwera(wiadomosc);
    }
    this.pionekPrzesuwany_ = false;
  }

  /**
   * Metoda aktualizujaca wszystkie pola planszy na podstawie wiadomosci otrzymanej od serwera.
   *
   * @param wiadmoscPlansza Wiadomosc z plansza otrzymana od serwera.
   */
  public void zaktualizujPlansze(String wiadmoscPlansza) {
    String[] rzedy = wiadmoscPlansza.split("\n");
    Parent[][] polaPlanszy = this.model_.polaPlanszy();
    for(int i = 0; i < polaPlanszy.length; i++) {
      String[] otrzymaneRzedy = rzedy[i].split("");
      for(int j=0; j < polaPlanszy[i].length; j++) {
        probujUsunPionek(i, j); // sprobowac usunac pionek z aktualnego pola
        if(otrzymaneRzedy[j].compareTo("1") == 0) {  // dodac bialy pionek
          int finalI1 = i;
          int finalJ1 = j;
          Platform.runLater(() -> ((StackPane) polaPlanszy[finalI1][finalJ1]).getChildren().add(
              new Pionek(Color.valueOf("#dbdbdb"),
                  Color.valueOf("#a3a3a3"),
                  ((StackPane) polaPlanszy[finalI1][finalJ1]).widthProperty(),
                  this,
                  "bialy")));
        } else if(otrzymaneRzedy[j].compareTo("2") == 0) {  // dodac czarny pionek
          int finalI = i;
          int finalJ = j;
          Platform.runLater(() -> ((StackPane) polaPlanszy[finalI][finalJ]).getChildren().add(
              new Pionek(Color.valueOf("#363636"),
                  Color.valueOf("#424242"),
                  ((StackPane) polaPlanszy[finalI][finalJ]).widthProperty(),
                  this,
                  "czarny")));
        }
      }
    }
  }

  /**
   * Metoda usuwajaca pionek z pola o podanych wspolrzednych,
   * jesli pionek znajduje sie na tym polu.
   *
   * @param rzad Rzad pola.
   * @param kolumna Kolumna pola.
   */
  private void probujUsunPionek(int rzad, int kolumna) {

    Parent[][] polaPlanszy = this.model_.polaPlanszy();
    ObservableList<Node> elementyPola = ((StackPane)polaPlanszy[rzad][kolumna]).getChildren();
    if(elementyPola.size() != 0) {
      Platform.runLater(() -> {
        Node pionek = elementyPola.get(0);
        elementyPola.remove(pionek);
      });
    }
  }
}