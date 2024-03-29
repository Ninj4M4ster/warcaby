package klient.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

/**
 * Klasa reprezentujaca model gry.
 */
public class ModelGry implements Model {
  /** Status aktualnego polaczenia */
  private BooleanProperty czyPolaczono_ = new SimpleBooleanProperty();
  /** Zmienna przechowujaca ilosc pol w rzedzie i kolumnie planszy. */
  private int iloscPol_ = 8;

  /** Zmienna przechowujaca kolor pionkow, ktorymi moze poruszac sie gracz */
  private String kolorPionkow_ = "bialy";

  /** Zmienna przechowujaca plansze gry */
  private GridPane planszaGry_ = new GridPane();

  /** Dwuwymiarowa lista przechowujaca wszystkie pola na planszy */
  private Parent[][] polaPlanszy_;

  /** Zmienna przechowujaca znaki wyswietlane na obramowaniu planszy */
  private String[] znakiIndeksow_ = {"A", "B", "C", "D", "E", "F", "G", "H"};

  public ModelGry(BooleanProperty czyPolaczono) {
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
   * Metoda zwracajaca ilosc pol w rzedzie i kolumnie planszy.
   *
   * @return Ilosc pol w rzedzie i kolumnie graczy.
   */
  public int iloscPol() {
    return this.iloscPol_;
  }

  /**
   * Metoda ustawiajaca ilosc pol w rzedzie i kolumnie planszy.
   * Aktualizuje ona rowniez liste zawierajaca znaki indeksow planszy.
   *
   * @param ilosc Ilosc pol w rzedzie i kolumnie planszy.
   */
  public void ustawIloscPol(int ilosc) {
    this.iloscPol_ = ilosc;
    // aktualizuj znaki indeksow planszy
    String[] indeksy = new String[this.iloscPol_];
    for(int i=0; i < this.iloscPol_; i++) {
      indeksy[i] = Character.toString('A' + i);
    }
    this.znakiIndeksow_ = indeksy;
  }

  /**
   * Metoda zwracajaca kolor pionow, ktorymi moze poruszac sie uzytkownik.
   *
   * @return Kolor pionkow, ktorymi moze poruszac sie gracz.
   */
  public String kolorPionkow() {
    return this.kolorPionkow_;
  }

  /**
   * Metoda ustawiajaca kolor pionkow, ktorymi moze poruszac sie gracz.
   *
   * @param kolor Kolor pionkow, ktorymi moze poruszac sie gracz.
   *              Dopuszczalne wartosci to 'bialy' i 'czarny'.
   */
  public void ustawKolorPionkow(String kolor) {
    if(!kolor.equals("bialy") && !kolor.equals("czarny"))
      throw new IllegalArgumentException("Jedyne mozliwe kolory pionkow to 'bialy' oraz 'czarny'");
    this.kolorPionkow_ = kolor;
  }

  /**
   * Metoda zwracajaca kontener wszystkich pol i obramowania planszy.
   *
   * @return Kontener wszystkich pol i obramowania planszy.
   */
  public GridPane planszaGry() {
    return this.planszaGry_;
  }

  /**
   * Metoda zwracajaca dwuwymiarowa liste przedstawiajaca wszystkie pola na planszy.
   *
   * @return Pola planszy.
   */
  public Parent[][] polaPlanszy() {
    return this.polaPlanszy_;
  }

  /**
   * Metoda ustawiajaca wartosc zmiennej przechowujacej liste wszystkich pol na planszy.
   *
   * @param listaPol Lista wszystkich pol na planszy.
   */
  public void ustawPolaPlanszy(Parent[][] listaPol) {
    this.polaPlanszy_ = listaPol;
  }

  /**
   * Metoda zwracajaca znaki indeksow planszy.
   *
   * @return Znaki indeksow planszy.
   */
  public String[] znakiIndeksow() {
    return this.znakiIndeksow_;
  }
}
