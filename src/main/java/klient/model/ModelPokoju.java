package klient.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import klient.ZasadyGry;

/**
 * Klasa reprezentujaca model widoku pokoju.
 */
public class ModelPokoju implements Model {
  /** Status polaczenia z serwerem */
  private final BooleanProperty czyPolaczono_ = new SimpleBooleanProperty();
  /** Nazwa wprowadzona przez klienta */
  private final StringProperty nazwaGracza_ = new SimpleStringProperty();
  /** Kontener przechowujacy wszystkie wyslane wczesniej wiadomosci */
  private final VBox historiaChatu_ = new VBox();

  /** Zmienna reprezentujaca kontener przechowujacy liste dostepnych zasad gry */
  private final ObjectProperty<ObservableList<String>> dostepneTryby_ =
      new SimpleObjectProperty<>();

  /** Zmienna przechowujaca domyslne zasady gry */
  private final String domyslnyTryb_;

  /** Element, w ktorym uzytkownik wpisuje wiadomosc, ktora potem moze wyslac */
  private final StringProperty tekstWiadomosci_ = new SimpleStringProperty();

  /** Status czy klient jest wlascicielem pokoju */
  private boolean czyWlasciciel_ = false;
  /** Nazwa drugiego gracza w pokoju */
  private final StringProperty nazwaDrugiegoGracza_ = new SimpleStringProperty();

  /**
   * Konstruktor.
   */
  public ModelPokoju(StringProperty nazwaGracza, BooleanProperty czyPolaczono) {
    ObservableList<String> listaTrybow =
        FXCollections.observableArrayList(ZasadyGry.KLASYCZNE.toString(),
            ZasadyGry.POLSKIE.toString(),
            ZasadyGry.KANADYJSKIE.toString());
    this.dostepneTryby_.set(listaTrybow);
    this.domyslnyTryb_ = listaTrybow.get(0);
    this.ustawCzyPolaczono(czyPolaczono);
    this.ustawNazwaGracza(nazwaGracza);
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
   * Metoda zwracajaca nazwe uzytkownika.
   *
   * @return Nazwa uzytkownika.
   */
  public StringProperty nazwaGracza() {
    return this.nazwaGracza_;
  }

  /**
   * Metoda przypisujaca nazwe uzytkownika do zmiennej.
   *
   * @param nazwa Nazwa uzytkownika.
   */
  public void ustawNazwaGracza(StringProperty nazwa) {
    this.nazwaGracza_.bind(nazwa);
  }

  /**
   * Metoda zwracajaca kontener przechowujacy dotychczas wyslane wiadomosci.
   *
   * @return Kontener przechowujacy dotychczas wyslane wiadomosci.
   */
  public VBox historiaChatu() {
    return this.historiaChatu_;
  }

  /**
   * Metoda dodajaca do historii wiadomosci nowa wiadomosc.
   *
   * @param kafelekWiadomosc Kontener z nowa wiadomoscia.
   */
  public void dodajWiadomoscDoHistorii(Node kafelekWiadomosc) {
    this.historiaChatu_.getChildren().add(kafelekWiadomosc);
  }

  /**
   * Metoda zwracajaca element widgetu zawierajacy wszystkie dostepne zasady gry.
   *
   * @return Element widgetu zawierajacy wszystkie dostepne zasady gry.
   */
  public ObjectProperty<ObservableList<String>> dostepneTryby() {
    return this.dostepneTryby_;
  }

  /**
   * Metoda zwracajcaca domyslne zasady gry.
   *
   * @return Domyslne zasady gry.
   */
  public String domyslnyTryb() {
    return this.domyslnyTryb_;
  }

  /**
   * Metoda zwracajaca element widgetu, w ktorym uzytkownik wpisuje wiadomosc.
   *
   * @return Element widgetu, w ktorym uzytkownik wpisuje wiadomosc.
   */
  public StringProperty tekstWiadomosci() {
    return this.tekstWiadomosci_;
  }

  /**
   * Metoda ustawiajaca zawartosc pola tekstowego, w ktore uzytkownik wpisuje wiadomosc.
   *
   * @param wiadomosc Zawartosc pola tekstowego.
   */
  public void ustawTekstWiadomosci(String wiadomosc) {
    this.tekstWiadomosci_.set(wiadomosc);
  }

  /**
   * Metoda zwracajaca status czy klient jest wlascicielem pokoju.
   *
   * @return Czy klient jest wlascicielem pokoju?
   */
  public boolean czyWlasciciel() {
    return this.czyWlasciciel_;
  }

  /**
   * Metoda ustawiajaca status, czy klient jest wlascicielem pokoju.
   *
   * @param status Status czy klient jest wlascicielem pokoju.
   */
  public void ustawCzyWlasciciel(boolean status) {
    this.czyWlasciciel_ = status;
  }

  /**
   * Metoda zwracajaca nazwe drugiego gracza w pokoju.
   *
   * @return Nazwa drugiego gracza w pokoju.
   */
  public StringProperty nazwaDrugiegoGracza() {
    return this.nazwaDrugiegoGracza_;
  }

  /**
   * Metoda ustawiajaca nazwe drugiego gracza w pokoju.
   *
   * @param nazwa Nazwa drugiego gracza w pokoju.
   */
  public void ustawNazweDrugiegoGracza(String nazwa) {
    this.nazwaDrugiegoGracza_.set(nazwa);
  }
}
