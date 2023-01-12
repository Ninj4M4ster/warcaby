package klient.model;

import javafx.beans.property.BooleanProperty;

/**
 * Interfejs modelu widoku.
 * Kazdy model widoku musi go implementowac.
 */
public interface Model {
  /**
   * Metoda przypisujaca stan aktualnego polaczenia.
   *
   * @param czyPolaczono Stan aktualnego polaczenia.
   */
  void ustawCzyPolaczono(BooleanProperty czyPolaczono);

  /**
   * Metoda zwracajaca stan aktualnego polaczenia.
   *
   * @return Stan aktualnego polaczenia.
   */
  BooleanProperty czyPolaczono();
}
