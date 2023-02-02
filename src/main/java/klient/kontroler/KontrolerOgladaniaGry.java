package klient.kontroler;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import klient.komunikacja.Mediator;
import klient.model.Model;
import klient.model.ModelOgladaniaGry;
import klient.widoki.widgety.NieaktywnaKrolowka;
import klient.widoki.widgety.NieaktywnyPionek;

/**
 * Klasa kontrolera widoku ogladania gry.
 */
public class KontrolerOgladaniaGry implements KontrolerWidoku {
  /** Model widoku ogladania gry */
  private ModelOgladaniaGry model_;
  /** Mediator pomiedzy aplikacja oraz polaczeniem z serwerem */
  private Mediator mediator_;

  /**
   * Metoda odpowiedzialna za przechowanie modelu.
   *
   * @param model Model widoku pokoju.
   */
  @Override
  public void przekazModel(Model model) {
    this.model_ = (ModelOgladaniaGry) model;
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
   * Metoda odpowiedzialna za przedstawienie na planszy poprzedniego ruchu.
   */
  public void poprzedniRuch() {
    this.model_.obnizAktualnyNumerRuchu();
    String plansza = this.model_.aktualnyStanPlanszy();
    this.zaktualizujPlansze(plansza);
  }

  /**
   * Metoda odpowiedzialna za przedstawienie na planszy kolejnego ruchu.
   */
  public void nastepnyRuch() {
    this.model_.podniesAktualnyNumerRuchu();
    String plansza = this.model_.aktualnyStanPlanszy();
    this.zaktualizujPlansze(plansza);
  }

  /**
   * Metoda aktualizujaca wszystkie pola planszy na podstawie napisu.
   *
   * @param wiadomoscPlansza Napis z plansza.
   */
  public void zaktualizujPlansze(String wiadomoscPlansza) {
    String[] rzedy = wiadomoscPlansza.split("");
    Parent[][] polaPlanszy = this.model_.polaPlanszy();
    for(int i = 0; i < rzedy.length; i++) {
      int rzad = i / this.model_.iloscPol();
      int kolumna = i % this.model_.iloscPol();
      probujUsunPionek(rzad, kolumna); // sprobowac usunac pionek z aktualnego pola
      if(rzedy[i].compareTo("1") == 0) {  // dodac bialy pionek
        Platform.runLater(() -> ((StackPane) polaPlanszy[rzad][kolumna]).getChildren().add(
            new NieaktywnyPionek(Color.valueOf("#dbdbdb"),
                Color.valueOf("#a3a3a3"),
                ((StackPane) polaPlanszy[rzad][kolumna]).widthProperty(),
                this,
                "bialy")));
      } else if(rzedy[i].compareTo("2") == 0) {  // dodac czarny pionek
        Platform.runLater(() -> ((StackPane) polaPlanszy[rzad][kolumna]).getChildren().add(
            new NieaktywnyPionek(Color.valueOf("#363636"),
                Color.valueOf("#424242"),
                ((StackPane) polaPlanszy[rzad][kolumna]).widthProperty(),
                this,
                "czarny")));
      } else if(rzedy[i].compareTo("3") == 0) {  // dodac biala krolowa
        Platform.runLater(() -> ((StackPane) polaPlanszy[rzad][kolumna]).getChildren().add(
            new NieaktywnaKrolowka(Color.valueOf("#dbdbdb"),
                Color.valueOf("#a3a3a3"),
                ((StackPane) polaPlanszy[rzad][kolumna]).widthProperty(),
                this,
                "bialy")));
      } else if(rzedy[i].compareTo("4") == 0) {  // dodac czarna krolowa
        Platform.runLater(() -> ((StackPane) polaPlanszy[rzad][kolumna]).getChildren().add(
            new NieaktywnaKrolowka(Color.valueOf("#363636"),
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
    Platform.runLater(() -> {
      if(elementyPola.size() != 0) {
        Node pionek = elementyPola.get(0);
        elementyPola.remove(pionek);
      }
    });
  }
}
