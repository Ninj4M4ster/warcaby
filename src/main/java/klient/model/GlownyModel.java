package klient.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// TODO(Jakub Drzewiecki): Ta klasa mogłaby implementować wzorzec singleton

/**
 * Klasa reprezentujaca model calej aplikacji.
 */
public class GlownyModel {
  // TODO(Jakub Drzewiecki): Trzeba zmienić ta zmienna na BooleanProperty i bindować ją w widoku do odpowiedniego widgetu
  /** Zmienna przechowujaca informacje czy uzytkownik jest polaczony z serwerem */
  private boolean czyPolaczono_;

  /** Zmienna przechowujaca nazwe gracza */
  private final StringProperty nazwaGracza_ = new SimpleStringProperty();

  /** Zmienna przechowujaca model widoku graczy online */
  private final ModelGraczyOnline modelGraczyOnline_;

  /** Zmienna przechowujaca model widoku pokoju */
  private final ModelPokoju modelPokoju_;

  /** Zmienna przechowujaca model widoku gry */
  private final ModelGry modelGry_;

  /**
   * Konstruktor, tworzy wszystkie modele widokow.
   *
   * @param czyPolaczono Czy uzytkownik jest aktualnie polaczony z serwerem?
   */
  public GlownyModel(boolean czyPolaczono) {
    this.czyPolaczono_ = czyPolaczono;
    this.modelGraczyOnline_ = new ModelGraczyOnline(nazwaGracza_);
    this.modelPokoju_ = new ModelPokoju();
    this.modelGry_ = new ModelGry();
  }

  /**
   * Metoda zwracajaca model widoku graczy online.
   *
   * @return Model widoku graczy online.
   */
  public ModelGraczyOnline modelGraczyOnline() {
    return this.modelGraczyOnline_;
  }

  /**
   * Metoda zwracajaca model widoku pokoju.
   *
   * @return Model widoku pokoju.
   */
  public ModelPokoju modelPokoju() {
    return this.modelPokoju_;
  }

  /**
   * Metoda zwracajaca model widoku gry.
   *
   * @return Model widoku gry.
   */
  public ModelGry modelGry() {
    return this.modelGry_;
  }

  /**
   * Metoda zwracajaca informacje czy uzytkownik jest polaczony z serwerem.
   *
   * @return Czy uzytkownik jest polaczony z serwerem?
   */
  public boolean czyPolaczono() {
    return this.czyPolaczono_;
  }

  /**
   * Metoda ta zmienia aktualny status polaczenia z serwerem.
   *
   * @param czyPolaczono Czy uzytkownik jest polaczony z serwerem?
   */
  public void ustawCzyPolaczono(boolean czyPolaczono) {
    this.czyPolaczono_ = czyPolaczono;
  }
}
