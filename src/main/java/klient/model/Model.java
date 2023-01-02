package klient.model;

import javafx.beans.property.BooleanProperty;

/**
 * Interfejs modelu widoku.
 * Kazdy model widoku musi go implementowac.
 */
public interface Model {
  void ustawCzyPolaczono(BooleanProperty czyPolaczono);
}
