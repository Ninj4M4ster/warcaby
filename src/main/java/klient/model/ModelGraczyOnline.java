package klient.model;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import klient.widoki.widgety.KafelekGraczaOnline;

/**
 * Model widoku graczy online.
 * Przechowuje informacje takie jak aktualnie aktywni gracze, czy nazwa uzytkownika.
 */
public class ModelGraczyOnline implements Model {
  /** Status polaczenia z serwerem */
  private final BooleanProperty czyPolaczono_ = new SimpleBooleanProperty();
  /** Wprowadzona nazwa gracza */
  private final StringProperty nazwaGracza_ = new SimpleStringProperty();

  /** Element reprezentujacy centralna czesc widgetu, z ktorym uzytkownik wchodzi w interakcje */
  private final ObjectProperty<Node> centrumMenu_ = new SimpleObjectProperty<>();

  /** Element reprezentujacy gorna czesc widgetu, z ktorym uzytkownik wchodzi w interkacje */
  private final ObjectProperty<Node> goraMenu_ = new SimpleObjectProperty<>();

  /** Kontener przechowujacy opis widoczny, gdy widoczni sa aktualnie dostepni gracze */
  private VBox kontenerOpisuListyGraczy_;

  /** Kontener przechowujacy kolejny kontener z kafelkami reprezentujacymi dostepnych graczy */
  private ScrollPane kontenerListyGraczy_;

  /** Kontener przechowujacy kafelki z dostepnymi graczami */
  private VBox listaGraczy_;
  /** Kontener na powiadomienia dla klienta */
  private final VBox kontenerPowiadomien_ = new VBox();

  /**
   * Konstruktor.
   *
   * @param nazwaGracza Nazwa podana przez uzytkownika.
   * @param czyPolaczono Stan aktualnego polaczenia.
   */
  public ModelGraczyOnline(StringProperty nazwaGracza, BooleanProperty czyPolaczono) {
    nazwaGracza.bind(nazwaGracza_);
    nazwaGracza_.set(nazwaGracza.get());
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
  public BooleanProperty czyPolaczono() {
    return this.czyPolaczono_;
  }

  /**
   * Metoda przechowujaca nazwe wprowadzona przez uzytkownika.
   *
   * @param nazwa Nazwa wprowadzona przez uzytkownika.
   */
  public void ustawNazweGracza(String nazwa) {
    nazwaGracza_.set(nazwa);
  }

  /**
   * Metoda zwracajaca element reprezentujacy srodkowa czesc widgetu,
   * z ktorym uzytkownik wchodzi w interakcje.
   *
   * @return Element reprezentujacy srodkowa czesc widgetu.
   */
  public ObjectProperty<Node> centrumMenu() {
    return this.centrumMenu_;
  }

  /**
   * Metoda zwracajaca element reprezentujacy gorna czesc widgetu,
   * z ktorym uzytkownik wchodzi w interakcje.
   *
   * @return Element reprezentujacy gorna czesc widgetu.
   */
  public ObjectProperty<Node> goraMenu() {
    return this.goraMenu_;
  }

  /**
   * Metoda zmieniajaca zawartosc elementu reprezentujacego srodkowa czesc widgetu,
   * z ktorym uzytkownik wchodzi w interakcje.
   *
   * @param widget Widget, ktory wyswietlony bedzie w centrum.
   */
  public void ustawCentrumMenu(Node widget) {
    this.centrumMenu_.set(widget);
  }

  /**
   * Metoda zmieniajaca zawartosc elementu reprezentujacego gorna czesc widgetu,
   * z ktorym uzytkownik wchodzi w interakcje.
   *
   * @param widget Widget, ktory wyswietlony bedzie na gorze.
   */
  public void ustawGoreMenu(Node widget) {
    this.goraMenu_.set(widget);
  }

  /**
   * Metoda zmieniajaca wartosc zmiennej reprezentujacej kontener na opis wyswietlany,
   * gdy widoczni sa aktualnie dostepni gracze.
   *
   * @param kontener Kontener z opisem wyswietlanym, gdy widoczni sa aktualnie dostepni gracze.
   */
  public void ustawKontenerOpisuListyGraczy(VBox kontener) {
    this.kontenerOpisuListyGraczy_ = kontener;
  }

  /**
   * Metoda zmieniajaca wartosc zmiennej reprezentujacej kontener na liste graczy.
   *
   * @param kontener Kontener z lista graczy.
   */
  public void ustawKontenerListyGraczy(ScrollPane kontener) {
    this.kontenerListyGraczy_ = kontener;
  }

  /**
   * Metoda zmieniajaca wartosc zmiennej reprezentujacej
   * kontener na kafelki dostepnych aktualnie graczy.
   *
   * @param listaGraczy Kontener zawierajacy kafelki aktualnie dostepnych graczy.
   */
  public void ustawListeGraczy(VBox listaGraczy) {
    this.listaGraczy_ = listaGraczy;
  }

  /**
   * Metoda zwracajaca kontener zawierajacy opis wyswietlany,
   * gdy widoczni sa aktualnie dostepni gracze.
   *
   * @return Kontener zawierajacy opis wyswietlany, gdy widoczni sa aktualnie dostepni gracze.
   */
  public VBox kontenerOpisuListyGraczy() {
    return this.kontenerOpisuListyGraczy_;
  }

  /**
   * Metoda zwracajaca kontener przechowujacy liste graczy.
   *
   * @return Kontener przechowujacy liste graczy.
   */
  public ScrollPane kontenerListyGraczy() {
    return this.kontenerListyGraczy_;
  }

  /**
   * Metoda dodajaca nowego gracza do listy aktualnie dostepnych graczy.
   *
   * @param nazwaGracza Nazwa dostepnego gracza.
   */
  public void dodajGraczaDoListy(String nazwaGracza) {
    Platform.runLater(() -> listaGraczy_.getChildren().add(new KafelekGraczaOnline(nazwaGracza)));
  }

  /**
   * Metoda usuwajaca gracza z listy aktualne dostepnych graczy.
   *
   * @param nazwaGracza Nazwa gracza, ktory nie jest juz dluzej dostepny.
   */
  public void usunGraczaLista(String nazwaGracza) {
    Platform.runLater(() -> {
      ObservableList<Node> lista = listaGraczy_.getChildren();
      lista.removeIf(node -> node instanceof KafelekGraczaOnline &&
          ((KafelekGraczaOnline) node).nazwaGracza().equals(nazwaGracza));
    });
  }

  /**
   * Metoda zwracajaca kontener powiadomien.
   *
   * @return Kontener powiadomien.
   */
  public VBox kontenerPowiadomien() {
    return this.kontenerPowiadomien_;
  }

}
