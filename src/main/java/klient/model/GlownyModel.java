package klient.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Klasa reprezentujaca model calej aplikacji.
 */
public class GlownyModel {
  /** Zmienna przechowujaca informacje czy uzytkownik jest polaczony z serwerem */
  private final BooleanProperty czyPolaczono_ = new SimpleBooleanProperty();

  /** Zmienna przechowujaca nazwe gracza */
  private final StringProperty nazwaGracza_ = new SimpleStringProperty();

  /** Zmienna przechowujaca model widoku graczy online */
  private final ModelGraczyOnline modelGraczyOnline_;

  /** Zmienna przechowujaca model widoku pokoju */
  private final ModelPokoju modelPokoju_;

  /** Zmienna przechowujaca model widoku gry */
  private final ModelGry modelGry_;

  /** Zmienna przechowujaca model widoku ogladania gry */
  private final ModelOgladaniaGry modelOgladaniaGry_;

  /**
   * Konstruktor, tworzy wszystkie modele widokow.
   *
   * @param czyPolaczono Czy uzytkownik jest aktualnie polaczony z serwerem?
   */
  public GlownyModel(BooleanProperty czyPolaczono) {
    this.ustawCzyPolaczono(czyPolaczono);
    this.modelGraczyOnline_ = new ModelGraczyOnline(nazwaGracza_, czyPolaczono_);
    this.modelPokoju_ = new ModelPokoju(nazwaGracza_, czyPolaczono_);
    this.modelGry_ = new ModelGry(czyPolaczono_);
    this.modelOgladaniaGry_ = new ModelOgladaniaGry(czyPolaczono);
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
   * Metoda zwracajaca model widoku ogladania gry.
   *
   * @return Model widoku ogladania gry.
   */
  public ModelOgladaniaGry modelOgladaniaGry() {
    return this.modelOgladaniaGry_;
  }

  /**
   * Metoda zwracajaca informacje czy uzytkownik jest polaczony z serwerem.
   *
   * @return Czy uzytkownik jest polaczony z serwerem?
   */
  public boolean czyPolaczono() {
    return this.czyPolaczono_.get();
  }

  /**
   * Metoda ta zmienia aktualny status polaczenia z serwerem.
   *
   * @param czyPolaczono Czy uzytkownik jest polaczony z serwerem?
   */
  public void ustawCzyPolaczono(BooleanProperty czyPolaczono) {
    this.czyPolaczono_.bind(czyPolaczono);
  }
}
