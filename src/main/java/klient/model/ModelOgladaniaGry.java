package klient.model;

import entities.Gra;
import entities.StanPlanszy;
import java.util.ArrayList;
import java.util.Comparator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Model widoku ogladania gry.
 * Przechowuje liste wszystkich ruchow oraz informacje o ogladanej grze.
 */
public class ModelOgladaniaGry implements Model {
  /** Status polaczenia z serwerem */
  private final BooleanProperty czyPolaczono_ = new SimpleBooleanProperty();
  /** Posortowana lista wszystkich ruchow ogladanej gry */
  private ArrayList<StanPlanszy> listaRuchow;
  /** Aktualny numer ruchu */
  private int aktualnyNumerRuchu_ = 0;

  /**
   * Konstruktor. Przypisuje stan polaczenia z serwerem.
   *
   * @param czyPolaczono Stan polaczenia z serwerem.
   */
  public ModelOgladaniaGry(BooleanProperty czyPolaczono) {
    this.ustawCzyPolaczono(czyPolaczono);
  }

  /**
   * Metoda przypisujaca stan aktualnego polaczenia.
   *
   * @param czyPolaczono Stan aktualnego polaczenia.
   */
  @Override
  public void ustawCzyPolaczono(BooleanProperty czyPolaczono) {
    this.czyPolaczono_.bind(czyPolaczono);
  }

  /**
   * Metoda zwracajaca stan aktualnego polaczenia.
   *
   * @return Stan aktualnego polaczenia.
   */
  @Override
  public BooleanProperty czyPolaczono() {
    return this.czyPolaczono_;
  }

  /**
   * Metoda
   * @param gra
   */
  public void ustawGre(Gra gra) {
    this.listaRuchow = new ArrayList<>(gra.getStanyPlanszy());
    this.listaRuchow.sort(Comparator.comparingInt(StanPlanszy::getNumerRuchu));
  }
}
