package klient.model;

import entities.Gra;
import entities.StanPlanszy;
import java.util.ArrayList;
import java.util.Comparator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
  private final IntegerProperty aktualnyNumerRuchu_ = new SimpleIntegerProperty(0);

  /** Laczna ilosc ruchow w grze */
  private final IntegerProperty lacznaIloscRuchow_ = new SimpleIntegerProperty();

  /** Zmienna przechowujaca plansze gry */
  private final GridPane planszaGry_ = new GridPane();

  /** Zmienna przechowujaca ilosc pol w rzedzie i kolumnie planszy. */
  private int iloscPol_ = 8;

  /** Zmienna przechowujaca znaki wyswietlane na obramowaniu planszy */
  private final String[] znakiIndeksow_ = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};

  /** Dwuwymiarowa lista przechowujaca wszystkie pola na planszy */
  private Parent[][] polaPlanszy_;

  /** Nazwa pierwszego gracza */
  private String nazwaGracza1_;

  /** Kolor pierwszego gracza */
  private int kolorGracza1_;

  /** Nazwa drugiego gracza */
  private String nazwaGracza2_;

  /** Zmienna sygnalizujaca czy aktualny numer ruchu jest rowny zero */
  private final BooleanProperty numerRuchuRownyZero_ = new SimpleBooleanProperty();

  /** Zmienna sygnalizujaca czy aktualny numer ruchu jest maksymalnym numerem */
  private final BooleanProperty numerRuchuMaksymalny_ = new SimpleBooleanProperty();

  /**
   * Konstruktor. Przypisuje stan polaczenia z serwerem.
   *
   * @param czyPolaczono Stan polaczenia z serwerem.
   */
  public ModelOgladaniaGry(BooleanProperty czyPolaczono) {
    this.ustawCzyPolaczono(czyPolaczono);
    this.numerRuchuRownyZero_.bind(aktualnyNumerRuchu_.isEqualTo(0));
    this.numerRuchuMaksymalny_.bind(
        aktualnyNumerRuchu_.isEqualTo(this.lacznaIloscRuchow_.subtract(1)));
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
    this.lacznaIloscRuchow_.set(this.listaRuchow.size());
    this.iloscPol_ = (int)Math.sqrt(this.listaRuchow.get(0).getPlansza().length());
    this.nazwaGracza1_ = gra.getGracz1();
    this.kolorGracza1_ = gra.getKolorGracz1();
    this.nazwaGracza2_ = gra.getGracz2();
  }

  /**
   * Metoda zwracajaca napis z aktualnym stanem planszy.
   *
   * @return Napis z aktualnym stanem planszy.
   */
  public String aktualnyStanPlanszy() {
    return this.listaRuchow.get(this.aktualnyNumerRuchu_.get()).getPlansza();
  }

  /**
   * Metoda zwracajaca kontener na plansze gry.
   *
   * @return Kontener na plansze gry.
   */
  public GridPane planszaGry() {
    return this.planszaGry_;
  }

  /**
   * Metoda zwracajaca ilosc pol na planszy w poziomie i pionie.
   *
   * @return Ilosc pol na planszy w poziomie i pionie.
   */
  public int iloscPol() {
    return this.iloscPol_;
  }

  /**
   * Metoda zwracajaca znaki indeksow sluzace do oznaczenia planszy.
   *
   * @return Znaki indeksow sluzace do oznaczenia planszy.
   */
  public String[] znakiIndeksow() {
    return this.znakiIndeksow_;
  }

  /**
   * Metoda sluzaca do ustawienia zmiennej przechowujacej wszystkie pola na planszy.
   *
   * @param polaPlanszy Wszystkie pola na planszy.
   */
  public void ustawPolaPlanszy(Parent[][] polaPlanszy) {
    this.polaPlanszy_ = polaPlanszy;
  }

  /**
   * Metoda zwracajaca napis z nazwa pierwszego gracza.
   *
   * @return Napis z nazwa pierwszego gracza.
   */
  public String nazwaGracza1() {
    return this.nazwaGracza1_;
  }

  /**
   * Metoda zwracajaca kolor pierwszego gracza.
   *
   * @return Kolor pierwszego gracza (1 - bialy, 2 - czarny).
   */
  public int kolorGracza1() {
    return this.kolorGracza1_;
  }

  /**
   * Metoda zwracajaca nazwe drugiego gracza.
   *
   * @return Napis z nazwa drugiego gracza.
   */
  public String nazwaGracza2() {
    return this.nazwaGracza2_;
  }

  /**
   * Metoda podnoszaca wartosc aktualnego numeru ruchu o jeden.
   */
  public void podniesAktualnyNumerRuchu() {
    if(this.aktualnyNumerRuchu_.get() < this.lacznaIloscRuchow_.subtract(1).get())
      this.aktualnyNumerRuchu_.set(this.aktualnyNumerRuchu_.get() + 1);
  }

  /**
   * Metoda obnizajaca wartosc aktualnego numeru ruchu o jeden.
   */
  public void obnizAktualnyNumerRuchu() {
    if(this.aktualnyNumerRuchu_.get() > 0)
      this.aktualnyNumerRuchu_.set(this.aktualnyNumerRuchu_.get() - 1);
  }

  /**
   * Metoda zwracajaca zmienna wyznaczajaca, czy numer ruchu jest rowny 0.
   *
   * @return Zmienna wyznaczajaca czy numer ruchu jest rowny 0.
   */
  public BooleanProperty numerRuchuRownyZero() {
    return this.numerRuchuRownyZero_;
  }

  /**
   * Metoda zwracajaca zmienna wyznaczajaca, czy numer ruchu jest maksymalnym numerem.
   *
   * @return Zmienna wyznaczajca, czy numer ruchu jest rowny lacznej liczbie ruchow.
   */
  public BooleanProperty numerRuchuMaksymalny() {
    return this.numerRuchuMaksymalny_;
  }

  /**
   * Metoda zwracajaca wszystkie pola planszy.
   *
   * @return Wszystkie pola planszy.
   */
  public Parent[][] polaPlanszy() {
    return this.polaPlanszy_;
  }
}
