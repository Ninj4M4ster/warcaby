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
import klient.widoki.widgety.Krolowka;
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

  private int kolumnaStartowa_ = -1;
  private int kolumnaDocelowa_ = -1;
  private int rzadStartowy_ = -1;
  private int rzadDocelowy_ = -1;

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
      kolumnaStartowa_ = startowePole.kolumna();
      rzadStartowy_ = startowePole.rzad();

      kolumnaDocelowa_ = pole.kolumna();
      rzadDocelowy_ = pole.rzad();

      Wiadomosc wiadomosc =
          new Wiadomosc(kolumnaStartowa_,
              rzadStartowy_,
              kolumnaDocelowa_,
              rzadDocelowy_,
              TypyWiadomosci.RUCH_PIONKA);
      this.mediator_.wyslijWiadomoscDoSerwera(wiadomosc);
    }
    this.pionekPrzesuwany_ = false;
  }

  /**
   * Metoda odpowiedzialna za zatwierdzenie ruchu, ktory chcial wykonac gracz.
   */
  public void zatwierdzRuch() {
    if(kolumnaStartowa_ != -1) {
      Parent[][] polaPlanszy = this.model_.polaPlanszy();
      probujUsunPionek(rzadStartowy_, kolumnaStartowa_);
      if(Math.abs(rzadStartowy_ - rzadDocelowy_) == 2)
        probujUsunPionek(Math.abs(rzadStartowy_ - rzadDocelowy_) / 2,
            Math.abs(kolumnaDocelowa_ - kolumnaStartowa_) / 2);
      if(rzadDocelowy_ == 0 || rzadDocelowy_ == 7) {
        if (this.model_.kolorPionkow().compareTo("bialy") == 0)
            Platform.runLater(() ->
            ((StackPane) polaPlanszy[rzadDocelowy_][kolumnaDocelowa_]).getChildren().add(
                new Krolowka(Color.valueOf("#dbdbdb"),
                    Color.valueOf("#a3a3a3"),
                    ((StackPane) polaPlanszy[rzadDocelowy_][kolumnaDocelowa_]).widthProperty(),
                    this,
                    "bialy")));
        else
          Platform.runLater(() ->
            ((StackPane) polaPlanszy[rzadDocelowy_][kolumnaDocelowa_]).getChildren().add(
                new Krolowka(Color.valueOf("#363636"),
                    Color.valueOf("#424242"),
                    ((StackPane) polaPlanszy[rzadDocelowy_][kolumnaDocelowa_]).widthProperty(),
                    this,
                    "czarny")));
      } else {
        if (this.model_.kolorPionkow().compareTo("bialy") == 0)
          Platform.runLater(() ->
              ((StackPane) polaPlanszy[rzadDocelowy_][kolumnaDocelowa_]).getChildren().add(
                  new Pionek(Color.valueOf("#dbdbdb"),
                      Color.valueOf("#a3a3a3"),
                      ((StackPane) polaPlanszy[rzadDocelowy_][kolumnaDocelowa_]).widthProperty(),
                      this,
                      "bialy")));
        else
          Platform.runLater(() ->
              ((StackPane) polaPlanszy[rzadDocelowy_][kolumnaDocelowa_]).getChildren().add(
                  new Pionek(Color.valueOf("#363636"),
                      Color.valueOf("#424242"),
                      ((StackPane) polaPlanszy[rzadDocelowy_][kolumnaDocelowa_]).widthProperty(),
                      this,
                      "czarny")));
      }
    }
  }

  /**
   * Metoda aktualizujaca wszystkie pola planszy na podstawie wiadomosci otrzymanej od serwera.
   *
   * @param wiadomoscPlansza Wiadomosc z plansza otrzymana od serwera.
   */
  public void zaktualizujPlansze(String wiadomoscPlansza) {
    System.out.println(wiadomoscPlansza);
    String[] rzedy = wiadomoscPlansza.split("");
    Parent[][] polaPlanszy = this.model_.polaPlanszy();
    for(int i = 0; i < rzedy.length; i++) {
      int rzad = i / 8;
      int kolumna = i % 8;
      System.out.println(rzad + " " + kolumna);
      probujUsunPionek(rzad, kolumna); // sprobowac usunac pionek z aktualnego pola
      if(rzedy[i].compareTo("1") == 0) {  // dodac bialy pionek
        Platform.runLater(() -> ((StackPane) polaPlanszy[rzad][kolumna]).getChildren().add(
            new Pionek(Color.valueOf("#dbdbdb"),
                Color.valueOf("#a3a3a3"),
                ((StackPane) polaPlanszy[rzad][kolumna]).widthProperty(),
                this,
                "bialy")));
      } else if(rzedy[i].compareTo("2") == 0) {  // dodac czarny pionek
        Platform.runLater(() -> ((StackPane) polaPlanszy[rzad][kolumna]).getChildren().add(
            new Pionek(Color.valueOf("#363636"),
                Color.valueOf("#424242"),
                ((StackPane) polaPlanszy[rzad][kolumna]).widthProperty(),
                this,
                "czarny")));
      } else if(rzedy[i].compareTo("3") == 0) {  // dodac biala krolowa
        Platform.runLater(() -> ((StackPane) polaPlanszy[rzad][kolumna]).getChildren().add(
            new Krolowka(Color.valueOf("#dbdbdb"),
                Color.valueOf("#a3a3a3"),
                ((StackPane) polaPlanszy[rzad][kolumna]).widthProperty(),
                this,
                "bialy")));
      } else if(rzedy[i].compareTo("4") == 0) {  // dodac czarna krolowa
        Platform.runLater(() -> ((StackPane) polaPlanszy[rzad][kolumna]).getChildren().add(
            new Krolowka(Color.valueOf("#363636"),
                Color.valueOf("#424242"),
                ((StackPane) polaPlanszy[rzad][kolumna]).widthProperty(),
                this,
                "czarny")));
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
