package klient.widoki;

import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import klient.kontroler.KontrolerWidoku;
import klient.kontroler.KontrolerWidokuGraczyOnline;
import klient.model.ModelGraczyOnline;
import klient.model.Model;
import klient.widoki.eventy.OknoKlikniete;

/**
 * Klasa reprezentujaca widok menu glownego, w ktorym mozliwe jest
 * zaproszenie jednego z dostepnych graczy do pokoju.
 */
public class WidokGraczyOnline extends Widok {

  /** Kontener wszystkich elementow widoku */
  private StackPane okno_;

  /** Element widoku z ktorym uzytkownik wchodzi w interakcje */
  private BorderPane oknoGlowne_;

  /** Pole sluzace do wprowadzenia swojej nazwy, ktora potem beda widziec inni gracze */
  private TextField poleWprowadzaniaNazwy_;

  /** Kontener przechowujacy widgety z nazwami dostepnych graczy */
  private VBox listaGraczy_;

  /** Kontroler widoku */
  private KontrolerWidokuGraczyOnline kontroler_;

  /** Model widoku */
  private ModelGraczyOnline model_;

  /**
   * Metoda tworzaca caly widok oraz zwracajaca kontener przechowujacy wszystkie widgety.
   *
   * @param kontroler Kontroler widoku.
   * @param model Model widoku.
   * @return Kontener wszystkich elementow widoku.
   */
  @Override
  public Parent utworzWidok(KontrolerWidoku kontroler, Model model) {
    if(this.model_ != null || this.kontroler_ != null)
      throw new IllegalStateException("Nie mozna utworzyc juz utworzonego widoku");
    this.kontroler_ = (KontrolerWidokuGraczyOnline) kontroler;
    this.model_ = (ModelGraczyOnline) model;

    this.okno_ = new StackPane();
    this.okno_.setAlignment(Pos.CENTER);
    this.okno_.setOnKeyPressed(keyEvent -> {
      if(keyEvent.getCode() == KeyCode.ENTER
          && this.model_.centrumMenu().get() != this.model_.kontenerListyGraczy()) {
        this.kontroler_.zapiszNazweGracza(poleWprowadzaniaNazwy_.getText());
      }
    });

    // layout, gdzie centrum to glowne elementy aplikacji, a dolny element to pasek statusu
    BorderPane layoutMenu = new BorderPane();
    layoutMenu.setBottom(this.utworzPasekStatusu(this.model_, this.kontroler_));

    this.okno_.getChildren().add(layoutMenu);

    this.utworzMenu(layoutMenu);
    this.utworzWidokWprowadzaniaNazwy();
    if(this.model_.nazwaGracza() == null)
      this.utworzWidokPoWprowadzeniuNazwy();
    else
      this.listaGraczy_ = this.model_.listaGraczy();
    this.utworzPasekPowiadomien();

    this.utworzListeRozegranychGier();

    okno_.setOnMouseClicked(mouseEvent ->
        listaGraczy_.fireEvent(new OknoKlikniete((Node)mouseEvent.getTarget(),
            mouseEvent.getTarget())));

    return okno_;
  }

  /**
   * Metoda odpowiedzialna za utworzenie menu,
   * czyli napisu z nazwa aplikacji oraz konteneru z ktorym uzytkownik wchodzi w interakcje.
   */
  private void utworzMenu(BorderPane layoutMenu) {
    // glowne okno, w nim znajduja sie wszystkie elementy
    VBox kontenerElementowOkna = new VBox();
    kontenerElementowOkna.setPadding(new Insets(20, 200, 20, 200));
    kontenerElementowOkna.setAlignment(Pos.CENTER);
    kontenerElementowOkna.setBackground(Background.fill(Color.valueOf("#242424")));

    // kontener napisu z nazwa aplikacji
    HBox poleGlownegoOpisu = new HBox();
    poleGlownegoOpisu.setPrefSize(400, 200);
    poleGlownegoOpisu.setAlignment(Pos.CENTER);

    // napis z nazwa aplikacji
    Label glownyOpis = new Label("Warcaby");
    glownyOpis.setFont(new Font("Book Antiqua", 60));
    glownyOpis.setTextFill(Color.valueOf("#ffffff"));

    DropShadow cien = new DropShadow();
    cien.setRadius(5.0);
    cien.setOffsetX(3.0);
    cien.setOffsetY(3.0);
    cien.setColor(Color.valueOf("#000000"));
    glownyOpis.setEffect(cien);

    poleGlownegoOpisu.getChildren().add(glownyOpis);

    // kontener w centrum, z nim klient wchodzi w interakcje
    oknoGlowne_ = this.model_.oknoGlowne();
    oknoGlowne_.setBorder(
        new Border(
            new BorderStroke(
                Color.valueOf("#ffffff2a"),
                BorderStrokeStyle.SOLID,
                new CornerRadii(15),
                new BorderWidths(1)
            )
        )
    );
    oknoGlowne_.setBackground(
        new Background(
            new BackgroundFill(
                Color.valueOf("#ffffff7a"),
                new CornerRadii(15),
                null
            )
        )
    );
    // zmien rozmiar okna jesli nastapi zmiana na kontener listy graczy
    oknoGlowne_.centerProperty().addListener((observableValue, node, t1) -> {
      if(t1.equals(model_.kontenerListyGraczy())) {
        oknoGlowne_.setPrefHeight(600);
      }
    });

    kontenerElementowOkna.getChildren().addAll(poleGlownegoOpisu, oknoGlowne_);
    layoutMenu.setCenter(kontenerElementowOkna);
  }

  /**
   * Utworzenie elementow menu sluzacych do
   * wprowadzenia nazwy uzytkownika i wyslania jej do serwera.
   */
  private void utworzWidokWprowadzaniaNazwy() {
    // kontener na wszystkie elementy przy wprowadzaniu nazwy
    VBox kontenerWprowadzaniaNazwy = new VBox();
    kontenerWprowadzaniaNazwy.setAlignment(Pos.CENTER);
    kontenerWprowadzaniaNazwy.setSpacing(20);
    kontenerWprowadzaniaNazwy.setMaxSize(300, 400);

    // opis wprowadzania nazwy
    Label opisWprowadzaniaNazwy = new Label("Wprowadz swoja nazwę:");
    opisWprowadzaniaNazwy.setTextFill(Color.valueOf("ffffff"));
    opisWprowadzaniaNazwy.setFont(new Font("Book Antiqua", 22));

    // pole tekstowe na nazwe
    poleWprowadzaniaNazwy_ = new TextField();
    poleWprowadzaniaNazwy_.setBackground(Background.fill(Color.valueOf("#ffffff")));

    // przycisk zatwierdzenia nazwy gracza
    Button przyciskZatwierdzeniaNazwy = new Button("Zatwierdź");
    przyciskZatwierdzeniaNazwy.setPrefSize(200, 30);
    przyciskZatwierdzeniaNazwy.setFont(new Font("Book Antiqua", 18));
    przyciskZatwierdzeniaNazwy.setBackground(Background.fill(Color.valueOf("#cccccc")));
    DropShadow cien = new DropShadow();
    cien.setRadius(5.0);
    cien.setOffsetX(3.0);
    cien.setOffsetY(3.0);
    cien.setColor(Color.valueOf("#2e2e2e"));
    przyciskZatwierdzeniaNazwy.setEffect(cien);

    // zatwierdzenie nazwy gracza i przejscie do widoku graczy online
    przyciskZatwierdzeniaNazwy.setOnMouseClicked((event) ->
        kontroler_.zapiszNazweGracza(poleWprowadzaniaNazwy_.getText()));

    kontenerWprowadzaniaNazwy.getChildren().addAll(
        opisWprowadzaniaNazwy,
        poleWprowadzaniaNazwy_,
        przyciskZatwierdzeniaNazwy);

    // ustawienie centum menu jesli jest to robione po raz pierwszy
    if(this.model_.centrumMenu().get() == null)
      this.model_.ustawCentrumMenu(kontenerWprowadzaniaNazwy);
  }

  /**
   * Utworzenie elementow sluzacych do wyswietlenia dostepnych graczy i
   * umozliwiajacych zaproszenie jednego z nich do pokoju.
   */
  private void utworzWidokPoWprowadzeniuNazwy() {
    // kontener na opis listy graczy online, zostanie wyswietlony po wprowadzeniu swojej nazwy
    VBox kontenerOpisuListyGraczy = new VBox();
    kontenerOpisuListyGraczy.setAlignment(Pos.CENTER);
    kontenerOpisuListyGraczy.setPadding(new Insets(5, 0, 5, 0));

    // opis listy graczy online
    Label opisOkna = new Label("Gracze online");
    opisOkna.setTextFill(
        Color.valueOf("ffffff")
    );
    opisOkna.setFont(new Font("Book Antiqua", 20));
    kontenerOpisuListyGraczy.getChildren().add(opisOkna);

    // linia oddzielajaca opis okna listy graczy i liste
    Rectangle linia = new Rectangle();
    linia.setArcHeight(5);
    linia.setArcWidth(5);
    linia.setHeight(2);
    linia.setWidth(300);
    linia.setFill(Color.valueOf("ffffff"));

    kontenerOpisuListyGraczy.getChildren().add(linia);
    this.model_.ustawKontenerOpisuListyGraczy(kontenerOpisuListyGraczy);

    // kontener ze scrollem na pola z graczami online, zostanie wyswietlone po wprowadzeniu nazwy
    ScrollPane kontenerListyGraczy = new ScrollPane();
    kontenerListyGraczy.setVbarPolicy(ScrollBarPolicy.NEVER);
    kontenerListyGraczy.setHbarPolicy(ScrollBarPolicy.NEVER);
    kontenerListyGraczy.setFitToWidth(true);
    kontenerListyGraczy.setFitToHeight(true);
    kontenerListyGraczy.setPadding(new Insets(10, 2, 15, 2));
    kontenerListyGraczy.setStyle("-fx-background: rgba(255,255,255,0);"
        + "-fx-background-color: rgba(255,255,255,0);");

    // lista graczy online
    listaGraczy_ = new VBox();
    listaGraczy_.setSpacing(5);
    listaGraczy_.setAlignment(Pos.TOP_CENTER);
    listaGraczy_.setMaxHeight(600);
    listaGraczy_.addEventHandler(OknoKlikniete.OKNO_KLIKNIETE,
        event -> kontroler_.uruchomWydarzenieNaKazdymDziecku(event));
    listaGraczy_.getChildren().addListener((ListChangeListener<Node>) change ->
        kontroler_.przypiszFunkcjeKafelkowi(change));
    this.model_.ustawListeGraczy(listaGraczy_);

    kontenerListyGraczy.setContent(listaGraczy_);
    this.model_.ustawKontenerListyGraczy(kontenerListyGraczy);

    // kontener przycisku przejscia do widoku rozegranych gier
    VBox kontenerPrzyciskuPrzejsciaDoRozegranychGier;
    if(this.model_.kontenerPrzyciskuPrzejsciaDoRozegranychGier() == null) {
      kontenerPrzyciskuPrzejsciaDoRozegranychGier = new VBox();
      kontenerPrzyciskuPrzejsciaDoRozegranychGier.setAlignment(Pos.CENTER);
      kontenerPrzyciskuPrzejsciaDoRozegranychGier.setPadding(new Insets(5, 0, 5, 0));
      kontenerPrzyciskuPrzejsciaDoRozegranychGier.setSpacing(5);

      linia = new Rectangle();
      linia.setArcHeight(5);
      linia.setArcWidth(5);
      linia.setHeight(2);
      linia.setWidth(300);
      linia.setFill(Color.valueOf("ffffff"));

      kontenerPrzyciskuPrzejsciaDoRozegranychGier.getChildren().add(linia);

      Button przyciskPrzejsciaDoRozegranychGier =
          new Button("Przejdz do listy rozegranych gier");
      przyciskPrzejsciaDoRozegranychGier.setPrefSize(300, 30);
      przyciskPrzejsciaDoRozegranychGier.setFont(new Font("Book Antiqua", 18));
      przyciskPrzejsciaDoRozegranychGier.setBackground(
          Background.fill(Color.valueOf("#cccccc")));
      DropShadow cien = new DropShadow();
      cien.setRadius(5.0);
      cien.setOffsetX(3.0);
      cien.setOffsetY(3.0);
      cien.setColor(Color.valueOf("#2e2e2e"));
      przyciskPrzejsciaDoRozegranychGier.setEffect(cien);

      przyciskPrzejsciaDoRozegranychGier.setOnMouseClicked((mouseEvent ->
          this.kontroler_.przejdzDoListyRozegranychGier()));

      kontenerPrzyciskuPrzejsciaDoRozegranychGier.getChildren()
          .add(przyciskPrzejsciaDoRozegranychGier);

      this.model_.ustawKontenerPrzyciskuPrzejsciaDoRozegranychGier(
          kontenerPrzyciskuPrzejsciaDoRozegranychGier);
    }
  }

  /**
   * Metoda odpowiedzialna za utworzenie widgetow sluzacych do przegladania i obejrzenia
   * jednej z poprzednio rozegranych przez graczy gier.
   */
  private void utworzListeRozegranychGier() {
    // kontener z opisem menu
    VBox kontenerOpisuListyRozegranychGier;
    if(this.model_.kontenerOpisuListyRozegranychGier() == null) {
      kontenerOpisuListyRozegranychGier = new VBox();
      kontenerOpisuListyRozegranychGier.setAlignment(Pos.CENTER);
      kontenerOpisuListyRozegranychGier.setPadding(new Insets(5, 0, 5, 0));

      // opis listy graczy online
      Label opisOkna = new Label("Lista rozegranych gier");
      opisOkna.setTextFill(
          Color.valueOf("ffffff")
      );
      opisOkna.setFont(new Font("Book Antiqua", 20));
      kontenerOpisuListyRozegranychGier.getChildren().add(opisOkna);

      // linia oddzielajaca opis okna listy graczy i liste
      Rectangle linia = new Rectangle();
      linia.setArcHeight(5);
      linia.setArcWidth(5);
      linia.setHeight(2);
      linia.setWidth(300);
      linia.setFill(Color.valueOf("ffffff"));

      kontenerOpisuListyRozegranychGier.getChildren().add(linia);
      this.model_.ustawKontenerOpisuListyRozegranychGier(kontenerOpisuListyRozegranychGier);
    }

    // kontener z lista rozegranych gier
    ScrollPane kontenerListyRozegranychGier;
    if(this.model_.kontenerListyRozegranychGier() == null) {
      kontenerListyRozegranychGier = new ScrollPane();
      kontenerListyRozegranychGier.setVbarPolicy(ScrollBarPolicy.NEVER);
      kontenerListyRozegranychGier.setHbarPolicy(ScrollBarPolicy.NEVER);
      kontenerListyRozegranychGier.setFitToWidth(true);
      kontenerListyRozegranychGier.setFitToHeight(true);
      kontenerListyRozegranychGier.setPadding(new Insets(10, 2, 15, 2));
      kontenerListyRozegranychGier.setStyle("-fx-background: rgba(255,255,255,0);"
          + "-fx-background-color: rgba(255,255,255,0);");

      // lista graczy online
      VBox listaGier = new VBox();
      listaGier.setSpacing(5);
      listaGier.setAlignment(Pos.TOP_CENTER);
      listaGier.setMaxHeight(600);
      this.model_.ustawListeGier(listaGier);

      kontenerListyRozegranychGier.setContent(listaGier);
      this.model_.ustawKontenerListyRozegranychGier(kontenerListyRozegranychGier);
    }

    // kontener przycisku przejscia do widoku listy graczy
    VBox kontenerPrzyciskuPrzejsciaDoListyGraczy;
    if(this.model_.kontenerPrzyciskuPrzejsciaDoListyGraczy() == null) {
      kontenerPrzyciskuPrzejsciaDoListyGraczy = new VBox();
      kontenerPrzyciskuPrzejsciaDoListyGraczy.setAlignment(Pos.CENTER);
      kontenerPrzyciskuPrzejsciaDoListyGraczy.setPadding(new Insets(5, 0, 5, 0));
      kontenerPrzyciskuPrzejsciaDoListyGraczy.setSpacing(5);

      Rectangle linia = new Rectangle();
      linia.setArcHeight(5);
      linia.setArcWidth(5);
      linia.setHeight(2);
      linia.setWidth(300);
      linia.setFill(Color.valueOf("ffffff"));

      kontenerPrzyciskuPrzejsciaDoListyGraczy.getChildren().add(linia);

      Button przyciskPrzejsciaDoListyGraczy =
          new Button("Przejdz do listy graczy online");
      przyciskPrzejsciaDoListyGraczy.setPrefSize(280, 30);
      przyciskPrzejsciaDoListyGraczy.setFont(new Font("Book Antiqua", 18));
      przyciskPrzejsciaDoListyGraczy.setBackground(
          Background.fill(Color.valueOf("#cccccc")));
      DropShadow cien = new DropShadow();
      cien.setRadius(5.0);
      cien.setOffsetX(3.0);
      cien.setOffsetY(3.0);
      cien.setColor(Color.valueOf("#2e2e2e"));
      przyciskPrzejsciaDoListyGraczy.setEffect(cien);

      przyciskPrzejsciaDoListyGraczy.setOnMouseClicked((mouseEvent ->
          this.kontroler_.przejdzDoListyGraczy()));

      kontenerPrzyciskuPrzejsciaDoListyGraczy.getChildren()
          .add(przyciskPrzejsciaDoListyGraczy);

      this.model_.ustawKontenerPrzyciskuPrzejsciaDoListyGraczy(
          kontenerPrzyciskuPrzejsciaDoListyGraczy);
    }
  }

  /**
   * Metoda tworzaca pasek powiadomien, w ktorym beda pojawiac sie
   * powiadomienia o zaproszeniach do pokojow.
   */
  private void utworzPasekPowiadomien() {
    VBox kontenerPowiadomien = this.model_.kontenerPowiadomien();
    kontenerPowiadomien.setAlignment(Pos.TOP_CENTER);
    kontenerPowiadomien.setPadding(new Insets(5, 5, 5, 5));
    kontenerPowiadomien.setSpacing(5);
    kontenerPowiadomien.maxWidthProperty().bind(this.okno_.widthProperty().multiply(0.2));

    this.okno_.setAlignment(Pos.CENTER_RIGHT);
    this.okno_.getChildren().add(kontenerPowiadomien);
  }
}
