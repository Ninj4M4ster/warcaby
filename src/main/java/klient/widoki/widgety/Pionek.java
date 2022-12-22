package klient.widoki.widgety;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import klient.kontroller.KontrolerGry;

/**
 * Klasa reprezentujaca kontener z pionkiem na planszy.
 */
public class Pionek extends StackPane {
  /** Startowa pozycja x myszki podczas przesuwania pionka */
  private double startowaPozycjaX_ = 0.0;

  /** Startowa pozycja y myszki podczas przesuwania pionka */
  private double startowaPozycjaY_ = 0.0;

  /** Zmienna reprezentujaca kolor pionka - 'bialy' lub 'czarny' */
  private final String kolorPionka_;

  /**
   * Konstruktor, tworzy kolo reprezentujace pionek i dodaje do niego wszystkie wymagane wydarzenia.
   * @param kolor Kolor kola.
   * @param kolorObramowki Kolor obramowania kola.
   * @param propertySzerokoscPola Szerokosc pola, w ktorym znajduje sie kolo.
   * @param kontroler Kontroler widoku gry.
   */
  public Pionek(Color kolor,
      Color kolorObramowki,
      ReadOnlyDoubleProperty propertySzerokoscPola,
      KontrolerGry kontroler,
      String kolorPionka) {
    super();
    this.kolorPionka_ = kolorPionka;

    Circle kolo = new Circle();
    kolo.setFill(kolor);
    kolo.radiusProperty().bind(propertySzerokoscPola.multiply(0.35));
    kolo.setStroke(kolorObramowki);
    kolo.setStrokeWidth(5);

    kolo.setOnMousePressed(
        mouseEvent -> kontroler.zacznijPrzesuwacPionek(mouseEvent, this));
    kolo.setOnMouseDragged(
        mouseEvent -> kontroler.przesunPionek(mouseEvent, this));
    kolo.setOnMouseReleased(mouseEvent -> kontroler.skonczPrzesuwacPionek());
    this.getChildren().add(kolo);
  }

  /**
   * Metoda zwracajaca startowa pozycje x myszki podczas przesuwania pionka.
   *
   * @return Startowa pozycja x myszki podczas przesuwania pionka.
   */
  public double startowaPozycjaX() {
    return this.startowaPozycjaX_;
  }

  /**
   * Metoda ustawiajaca startowa pozycje x myszki uzywana do przesuwania pionka.
   *
   * @param x Startowa pozycja myszki w osi X.
   */
  public void ustawStartowaPozycjaX(double x) {
    this.startowaPozycjaX_ = x;
  }

  /**
   * Metoda zwracajaca startowa pozycje y myszki podczas przesuwania pionka.
   *
   * @return Startowa pozycja y myszki podczas przesuwania pionka.
   */
  public double startowaPozycjaY() {
    return this.startowaPozycjaY_;
  }

  /**
   * Metoda ustawiajaca startowa pozycje y myszki uzywana do przesuwania pionka.
   *
   * @param y Startowa pozycja myszki w osi Y.
   */
  public void ustawStartowaPozycjaY(double y) {
    this.startowaPozycjaY_ = y;
  }

  /**
   * Metoda zwracajaca napis reprezentujacy kolor pionka.
   *
   * @return Kolor pionka - 'bialy' lub 'czarny'.
   */
  public String kolorPionka() {
    return this.kolorPionka_;
  }
}
