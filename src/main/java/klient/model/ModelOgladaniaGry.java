package klient.model;

import entities.Gra;
import entities.StanPlanszy;
import java.util.ArrayList;
import java.util.Comparator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

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

  /** Zmienna przechowujaca plansze gry */
  private final GridPane planszaGry_ = new GridPane();

  /** Zmienna przechowujaca ilosc pol w rzedzie i kolumnie planszy. */
  private int iloscPol_ = 8;

  /** Zmienna przechowujaca znaki wyswietlane na obramowaniu planszy */
  private final String[] znakiIndeksow_ = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};

  /** Dwuwymiarowa lista przechowujaca wszystkie pola na planszy */
  private Parent[][] polaPlanszy_;

  private String nazwaGracza1_;
  private int kolorGracza1_;

  private String nazwaGracza2_;

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
   * Metoda konfigurujaca informacje o ogladanej grze.
   *
   * @param gra Gra do obejrzenia.
   */
  public void ustawGre(Gra gra) {
    this.listaRuchow = new ArrayList<>(gra.getStanyPlanszy());
    this.listaRuchow.sort(Comparator.comparingInt(StanPlanszy::getNumerRuchu));
    this.iloscPol_ = (int)Math.sqrt(this.listaRuchow.get(0).getPlansza().length());
    this.nazwaGracza1_ = gra.getGracz1();
    this.kolorGracza1_ = gra.getKolorGracz1();
    this.nazwaGracza2_ = gra.getGracz2();
  }

  public GridPane planszaGry() {
    return this.planszaGry_;
  }

  public int iloscPol() {
    return this.iloscPol_;
  }

  public String[] znakiIndeksow() {
    return this.znakiIndeksow_;
  }

  public void ustawPolaPlanszy(Parent[][] polaPlanszy) {
    this.polaPlanszy_ = polaPlanszy;
  }

  public String nazwaGracza1() {
    return this.nazwaGracza1_;
  }

  public int kolorGracza1() {
    return this.kolorGracza1_;
  }

  public String nazwaGracza2() {
    return this.nazwaGracza2_;
  }
}
