package klient.widoki.widgety;

import javafx.scene.layout.StackPane;

/**
 * Klasa reprezentujaca pole na planszy.
 */
public class PolePlanszy extends StackPane {
  /** Kolumna, w ktorej znajduje sie pole */
  private final int kolumna_;

  /** Rzad, w ktorym znajduje sie pole */
  private final int rzad_;

  /**
   * Konstruktor. Zapisuje wprowadzone argumenty.
   *
   * @param kolumna Kolumna, w ktorej znajduje sie pole.
   * @param rzad Rzad, w ktorym znajduje sie pole.
   */
  public PolePlanszy(int rzad, int kolumna) {
    super();
    this.kolumna_ = kolumna;
    this.rzad_ = rzad;
  }

  /**
   * Metoda zwracajaca kolumne, w ktorej znajduje sie pole.
   *
   * @return Numer kolumny, w ktorej znajduje sie pole.
   */
  public int kolumna() {
    return this.kolumna_;
  }

  /**
   * Metoda zwracajaca rzad, w ktorym znajduje sie pole.
   *
   * @return Numer rzedu, w ktorym znajduje sie pole.
   */
  public int rzad() {
    return this.rzad_;
  }
}
