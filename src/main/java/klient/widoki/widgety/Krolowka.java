package klient.widoki.widgety;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.paint.Color;
import klient.kontroler.KontrolerGry;
import klient.kontroler.KontrolerWidoku;

/**
 * Klasa reprezentujaca krolowa. Z zalozenia jest tym samym co pionek, ale ma inny wyglad.
 */
public class Krolowka extends NieaktywnaKrolowka {

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
      KontrolerWidoku kontroler, String kolorPionka) {
    super(kolor, kolorObramowki, propertySzerokoscPola, kontroler, kolorPionka);

    if(kontroler instanceof KontrolerGry) {
      this.srodkoweKolo_.setOnMousePressed(
          mouseEvent -> ((KontrolerGry) kontroler)
              .zacznijPrzesuwacPionek(mouseEvent, this));
      this.srodkoweKolo_.setOnMouseDragged(
          mouseEvent -> ((KontrolerGry) kontroler)
              .przesunPionek(mouseEvent, this));
      this.srodkoweKolo_.setOnMouseReleased(mouseEvent -> ((KontrolerGry) kontroler)
          .skonczPrzesuwacPionek());
    }
  }
}
