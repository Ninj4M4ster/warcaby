package klient.widoki.widgety;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import klient.kontroler.KontrolerGry;

/**
 * Klasa reprezentujaca krolowa. Z zalozenia jest tym samym co pionek, ale ma inny wyglad.
 */
public class Krolowka extends Pionek {

  /**
   * Konstruktor, tworzy kolo reprezentujace pionek i dodaje do niego wszystkie wymagane wydarzenia.
   *
   * @param kolor                 Kolor kola.
   * @param kolorObramowki        Kolor obramowania kola.
   * @param propertySzerokoscPola Szerokosc pola, w ktorym znajduje sie kolo.
   * @param kontroler             Kontroler widoku gry.
   * @param kolorPionka           Napis przedstawiajacy kolor pionka.
   */
  public Krolowka(Color kolor, Color kolorObramowki,
      ReadOnlyDoubleProperty propertySzerokoscPola,
      KontrolerGry kontroler, String kolorPionka) {
    super(kolor, kolorObramowki, propertySzerokoscPola, kontroler, kolorPionka);
    Circle srodkoweKolo = new Circle();
    srodkoweKolo.setFill(kolor);
    srodkoweKolo.radiusProperty().bind(propertySzerokoscPola.multiply(0.2));
    srodkoweKolo.setStroke(Color.YELLOW);
    srodkoweKolo.setStrokeWidth(4);

    srodkoweKolo.setOnMousePressed(
        mouseEvent -> kontroler.zacznijPrzesuwacPionek(mouseEvent, this));
    srodkoweKolo.setOnMouseDragged(
        mouseEvent -> kontroler.przesunPionek(mouseEvent, this));
    srodkoweKolo.setOnMouseReleased(mouseEvent -> kontroler.skonczPrzesuwacPionek());

    this.getChildren().add(srodkoweKolo);
  }
}
