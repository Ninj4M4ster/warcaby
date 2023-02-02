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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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

  /** Element reprezentujacy gorna czesc widgetu, z ktorym uzytkownik wchodzi w interakcje */
  private final ObjectProperty<Node> goraMenu_ = new SimpleObjectProperty<>();

  /** Element reprezentujacy dolna czesc widgetu, z ktorym uzytkownik wchodzi w interakcje */
  private final ObjectProperty<Node> dolMenu_ = new SimpleObjectProperty<>();

  /** Glowne okno menu, z nim uzytkownik wchodzi w interakcje */
  private final BorderPane oknoGlowne_ = new BorderPane();

  /** Kontener przechowujacy opis widoczny, gdy widoczni sa aktualnie dostepni gracze */
  private VBox kontenerOpisuListyGraczy_;

  /** Kontener przechowujacy kolejny kontener z kafelkami reprezentujacymi dostepnych graczy */
  private ScrollPane kontenerListyGraczy_;

  /** Kontener przechowujacy kafelki z dostepnymi graczami */
  private VBox listaGraczy_ = null;
  /** Kontener na powiadomienia dla klienta */
  private final VBox kontenerPowiadomien_ = new VBox();

  /** Kontener na przycisk przejscia do widoku listy rozegranych gier */
  private VBox kontenerPrzyciskuPrzejsciaDoRozegranychGier_ = null;

  /** Kontener przechowujacy opis menu, gdy widoczne sa wszystkie rozegrane gry */
  private VBox kontenerOpisuListyRozegranychGier_ = null;

  /** Kontener przechowujacy liste zawierajaca wszystkie rozegrane gry */
  private ScrollPane kontenerListyRozegranychGier_ = null;

  /** Kontener przechowujacy wszystkie rozegrane gry */
  private VBox listaGier_ = null;

  /** Kontener przechowujacy przycisk sluzacy do przejscia do listy graczy online */
  private VBox kontenerPrzyciskuPrzejsciaDoListyGraczy_ = null;

  /** Kontener na przyciski do gry z botem */
  private HBox kontenerZagrajBot_ = null;

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
    this.oknoGlowne_.centerProperty().bind(this.centrumMenu_);
    this.oknoGlowne_.topProperty().bind(this.goraMenu_);
    this.oknoGlowne_.bottomProperty().bind(this.dolMenu_);
    this.oknoGlowne_.setPrefSize(400, 200);
    this.oknoGlowne_.setMaxSize(600, 600);
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
   * Metoda zwracajaca wprowadzona przez uzytkownika nazwe.
   *
   * @return Wprowadzona przez uzytkownika nazwa.
   */
  public String nazwaGracza() {
    return this.nazwaGracza_.get();
  }

  /**
   * Metoda zwracajaca glowne okno menu, z ktorym uzytkownik wchodzi w interakcje.
   *
   * @return Menu, z ktorym uzytkownik wchodzi w interakcje.
   */
  public BorderPane oknoGlowne() {
    return this.oknoGlowne_;
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
   * Metoda zmieniajaca zawartosc elementu reprezentujacego dolna czesc widgetu,
   * z ktorym uzytkownik wchodzi w interakcje.
   *
   * @param widget Widget, ktory wyswietlony bedzie na dole.
   */
  public void ustawDolMenu(Node widget) {this.dolMenu_.set(widget);}

  /**
   * Metoda zwracajaca kontener przechowujacy przycisk sluzacy do przejscia
   * do listy rozegranych gier.
   *
   * @return Kontener przycisku przejscia do rozegranych gier.
   */
  public VBox kontenerPrzyciskuPrzejsciaDoRozegranychGier() {
    return this.kontenerPrzyciskuPrzejsciaDoRozegranychGier_;
  }

  /**
   * Metoda zmienajaca wartosc zmiennej przechowujacej kontener przycisku
   * sluzacego do przejscia do listy rozegranych gier.
   *
   * @param kontener Kontener przycisku przejscia do rozegranych gier.
   */
  public void ustawKontenerPrzyciskuPrzejsciaDoRozegranychGier(VBox kontener) {
    this.kontenerPrzyciskuPrzejsciaDoRozegranychGier_ = kontener;
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

  public VBox listaGraczy() {
    return this.listaGraczy_;
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

  /**
   * Metoda zwracajaca kontener opisu menu, gdy widoczna jest lista rozegranych gier.
   *
   * @return Kontener opisu listy rozegranych gier.
   */
  public VBox kontenerOpisuListyRozegranychGier() {
    return this.kontenerOpisuListyRozegranychGier_;
  }

  /**
   * Metoda zmieniajaca wartosc zmiennej przedstawiajacej opis menu,
   * gdy widoczna jest lista rozegranych gier.
   *
   * @param kontener Kontener opisu listy rozegranych gier.
   */
  public void ustawKontenerOpisuListyRozegranychGier(VBox kontener) {
    this.kontenerOpisuListyRozegranychGier_ = kontener;
  }

  /**
   * Metoda zwracajaca kontener przechowujacy liste wszystkich rozegranych gier.
   *
   * @return Kontener listy rozegranych gier.
   */
  public ScrollPane kontenerListyRozegranychGier() {
    return this.kontenerListyRozegranychGier_;
  }

  /**
   * Metoda ustawiajaca wartosc zmiennej przedstawiajacej kontener przechowujacy
   * liste wszystkich rozegranych gier.
   *
   * @param kontener Kontener przechowujacy liste wszystkich rozegranych gier.
   */
  public void ustawKontenerListyRozegranychGier(ScrollPane kontener) {
    this.kontenerListyRozegranychGier_ = kontener;
  }

  /**
   * Metoda zwracajaca kontener przechowujacy wszystkie rozegrane gry.
   *
   * @return Kontener przechowujacy wszystkie rozegrane gry.
   */
  public VBox listaGier() {
    return this.listaGier_;
  }

  /**
   * Metoda ustawiajaca wartosc zmiennej przechowujacej wszystkie rozegrane gry.
   *
   * @param listaGier Kontener przechowujacy wszystkie rozegrane gry.
   */
  public void ustawListeGier(VBox listaGier) {
    this.listaGier_ = listaGier;
  }

  /**
   * Metoda zwracajaca kontener przechowujacy przycisk sluzacy
   * do przejscia do listy graczy aktualnie online.
   *
   * @return Kontener przycisku przejscia do listy graczy online.
   */
  public VBox kontenerPrzyciskuPrzejsciaDoListyGraczy() {
    return this.kontenerPrzyciskuPrzejsciaDoListyGraczy_;
  }

  /**
   * Metoda ustawiajaca wartosc zmiennej przechowujacej kontener na przycisk sluzacy
   * do przejscia do listy graczy aktualnie online.
   *
   * @param kontener Kontener przycisku przejscia do listy graczy online.
   */
  public void ustawKontenerPrzyciskuPrzejsciaDoListyGraczy(VBox kontener) {
    this.kontenerPrzyciskuPrzejsciaDoListyGraczy_ = kontener;
  }

  /**
   * Metoda ustawiajaca wartosc zmiennej przechowujacej kontener na przyciski
   * do gry z botem.
   *
   * @param kontener Kontener na przyciski do gry z botem.
   */
  public void ustawKontenerZagrajBot(HBox kontener) {
    this.kontenerZagrajBot_ = kontener;
  }

  /**
   * Metoda zwracajaca kontener przechoujacy przyciski do gry z botem.
   *
   * @return Kontener przechowujacy przyciski do gry z botem.
   */
  public HBox kontenerZagrajBot() {
    return this.kontenerZagrajBot_;
  }
}
