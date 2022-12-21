package klient.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * Klasa reprezentujaca model widoku pokoju.
 */
public class ModelPokoju implements Model {
  /** Kontener przechowujacy wszystkie wyslane wczesniej wiadomosci */
  private final VBox historiaChatu_ = new VBox();

  /** Zmienna reprezentujaca kontener przechowujacy liste dostepnych zasad gry */
  private final ObjectProperty<ObservableList<String>> dostepneTryby_ =
      new SimpleObjectProperty<>();

  /** Zmienna przechowujaca domyslne zasady gry */
  private String domyslnyTryb_;

  /** Element, w ktorym uzytkownik wpisuje wiadomosc, ktora potem moze wyslac */
  private final StringProperty tekstWiadomosci_ = new SimpleStringProperty();

  /**
   * Konstruktor.
   */
  public ModelPokoju() {
    ObservableList<String> listaTrybow =
        FXCollections.observableArrayList("Warcaby klasyczne (brazylijskie)",
        "Warcaby polskie",
        "Warcaby kanadyjskie");
    this.dostepneTryby_.set(listaTrybow);
    this.domyslnyTryb_ = listaTrybow.get(0);
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
   * Metoda ustawiajaca dostepne zasady gry.
   *
   * @param lista Lista dostepnych zasad gry.
   */
  public void ustawDostepneTryby(ObservableList<String> lista) {
    this.dostepneTryby_.set(lista);
    this.ustawDomyslnyTryb(lista.get(0));
  }

  /**
   * Metoda ustawiajaca dostepne zasady gry.
   *
   * @param tryby Lista dostepnych zasad gry.
   */
  public void ustawDostepneTryby(String[] tryby) {
    ObservableList<String> listaTrybow = FXCollections.observableArrayList(tryby);
    this.dostepneTryby_.set(listaTrybow);
    this.ustawDomyslnyTryb(listaTrybow.get(0));
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
   * Metoda zmieniajaca domyslne zasady gry.
   *
   * @param tryb Zasady gry.
   * TODO(Jakub Drzewiecki): Dodac sprawdzenie czy zasady znajduja sie w liscie wszystkich zasad.
   */
  public void ustawDomyslnyTryb(String tryb) {
    this.domyslnyTryb_ = tryb;
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
}
