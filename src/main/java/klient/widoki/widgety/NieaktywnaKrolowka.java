package klient.widoki.widgety;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import klient.kontroler.KontrolerWidoku;

public class NieaktywnaKrolowka extends Pionek {
  protected final Circle srodkoweKolo_;

  /**
   * Konstruktor, tworzy kolo reprezentujace pionek i dodaje do niego wszystkie wymagane wydarzenia.
   *
   * @param kolor                 Kolor kola.
   * @param kolorObramowki        Kolor obramowania kola.
   * @param propertySzerokoscPola Szerokosc pola, w ktorym znajduje sie kolo.
   * @param kontroler             Kontroler widoku gry.
   * @param kolorPionka           Napis przedstawiajacy kolor pionka.
   */
  public NieaktywnaKrolowka(Color kolor, Color kolorObramowki,
      ReadOnlyDoubleProperty propertySzerokoscPola,
      KontrolerWidoku kontroler, String kolorPionka) {
    super(kolor, kolorObramowki, propertySzerokoscPola, kontroler, kolorPionka);
    this.srodkoweKolo_ = new Circle();
    this.srodkoweKolo_.setFill(kolor);
    this.srodkoweKolo_.radiusProperty().bind(propertySzerokoscPola.multiply(0.2));
    this.srodkoweKolo_.setStroke(Color.YELLOW);
    this.srodkoweKolo_.setStrokeWidth(4);

    this.getChildren().add(this.srodkoweKolo_);
  }

}
