package klient.widoki;

import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import klient.kontroler.KontrolerPokoju;
import klient.kontroler.KontrolerWidoku;
import klient.model.Model;
import klient.model.ModelPokoju;

/**
 * Klasa reprezentujaca widok pokoju, w ktorym mozna
 * komunikowac sie z drugim graczem oraz wybrac zasady gry.
 */
public class WidokPokoju extends Widok {
  /** Kontroler widoku */
  private KontrolerPokoju kontroler_;

  /** Model widoku */
  private ModelPokoju model_;

  /** Kontener wszystkich elementow widoku */
  private BorderPane okno_;

  /** Kontener chatu, informacji o graczach w pokoju oraz opcji rozgrywki */
  private BorderPane kontenerWidoku_;

  /**
   * Metoda odpowiedzialna za utworzenie wszystkich elementow
   * widoku i zwrocenie konteneru zawierajacego te elementy.
   *
   * @param kontroler Kontroler widoku.
   * @param model Model widoku.
   * @return Kontener zawierajacy wszystkie elementy widoku.
   */
  @Override
  public Parent utworzWidok(KontrolerWidoku kontroler, Model model) {
    if(this.model_ != null || this.kontroler_ != null)
      throw new IllegalStateException("Nie mozna utworzyc juz utworzonego widoku");
    this.kontroler_ = (KontrolerPokoju) kontroler;
    this.model_ = (ModelPokoju) model;

    this.okno_ = new BorderPane();
    this.okno_.setOnKeyPressed(keyEvent -> {
      if(keyEvent.getCode() == KeyCode.ENTER) {
        this.kontroler_.wyslijWiadomosc();
      }
    });

    // kontener elementow dotyczacych bezposrednio pokoju
    kontenerWidoku_ = new BorderPane();

    // utworz widgety i dodaj je do widoku
    this.okno_.setBottom(this.utworzPasekStatusu(this.model_, this.kontroler_));
    this.utworzChat();
    this.utworzInformacjeOpcje();

    this.okno_.setCenter(kontenerWidoku_);

    return this.okno_;
  }

  /**
   * Metoda odpowiedzialna za utworzenie chatu.
   */
  private void utworzChat() {
    // kontener na chat
    BorderPane kontenerChatu = new BorderPane();
    kontenerChatu.setBackground(Background.fill(Color.valueOf("#525252")));

    // kontener na wyswietlanie historii chatu
    ScrollPane kontenerHistoriiChatu = new ScrollPane();
    kontenerHistoriiChatu.setStyle("-fx-background: #525252; -fx-background-color: #525252;");
    kontenerHistoriiChatu.setFitToWidth(true);
    kontenerHistoriiChatu.setFitToHeight(true);
    kontenerHistoriiChatu.setHbarPolicy(ScrollBarPolicy.NEVER);
    kontenerHistoriiChatu.setVbarPolicy(ScrollBarPolicy.NEVER);

    // kontener na dymki chatu
    VBox historiaChatu = this.model_.historiaChatu();
    historiaChatu.setAlignment(Pos.BOTTOM_CENTER);
    historiaChatu.setFillWidth(true);
    historiaChatu.heightProperty().addListener(
        (observable) -> kontenerHistoriiChatu.setVvalue(kontenerHistoriiChatu.getVmax()));

    kontenerHistoriiChatu.setContent(historiaChatu);
    kontenerChatu.setCenter(kontenerHistoriiChatu);

    // kontener na pole tekstowe i przycisk
    GridPane kontenerPolaTekstowego = new GridPane();

    ColumnConstraints kolumna1 = new ColumnConstraints();
    kolumna1.setPercentWidth(90);
    ColumnConstraints kolumna2 = new ColumnConstraints();
    kolumna2.setPercentWidth(10);
    kontenerPolaTekstowego.getColumnConstraints().addAll(kolumna1, kolumna2);

    // pole tekstowe
    TextField poleTekstowe = new TextField();
    poleTekstowe.setPrefHeight(30);
    poleTekstowe.textProperty().bindBidirectional(this.model_.tekstWiadomosci());

    // przycisk do wyslania wiadomosci
    Button przyciskWyslijWiadomosc = new Button("Wyślij");
    przyciskWyslijWiadomosc.setMaxWidth(Double.MAX_VALUE);
    przyciskWyslijWiadomosc.setPrefHeight(30);
    przyciskWyslijWiadomosc.setOnMouseClicked(mouseEvent -> this.kontroler_.wyslijWiadomosc());

    kontenerPolaTekstowego.add(poleTekstowe, 0, 0);
    kontenerPolaTekstowego.add(przyciskWyslijWiadomosc, 1, 0);

    kontenerChatu.setBottom(kontenerPolaTekstowego);

    kontenerWidoku_.setCenter(kontenerChatu);
  }

  /**
   * Metoda odpowiedzialna za utworzenie informacji o graczach
   * znajdujacych sie w tym pokoju oraz opcji rozgrywki.
   */
  private void utworzInformacjeOpcje() {
    // kontener opcji gry oraz informacji o graczach w lobby
    GridPane kontenerInformacji = new GridPane();
    RowConstraints rzad1 = new RowConstraints();
    rzad1.setPercentHeight(50);
    RowConstraints rzad2 = new RowConstraints();
    rzad2.setPercentHeight(50);
    kontenerInformacji.getRowConstraints().addAll(rzad1, rzad2);
    kontenerInformacji.setPrefWidth(300);

    // uklad konteneru informacji o graczach w lobby
    BorderPane ukladKonteneruGraczy = new BorderPane();
    Label opisUkladu = new Label("Gracze");
    opisUkladu.setFont(new Font("Book Antiqua", 18));
    opisUkladu.setTextAlignment(TextAlignment.CENTER);
    opisUkladu.setAlignment(Pos.CENTER);
    opisUkladu.minWidthProperty().bind(ukladKonteneruGraczy.widthProperty());

    ukladKonteneruGraczy.setTop(opisUkladu);

    // kontener informacji o graczach w lobby
    VBox kontenerGraczy = new VBox();
    kontenerGraczy.setAlignment(Pos.CENTER);
    kontenerGraczy.setSpacing(60);
    kontenerGraczy.setBackground(Background.fill(Color.valueOf("#6e6e6e")));

    ukladKonteneruGraczy.setCenter(kontenerGraczy);

    // nazwy graczy do wyswietlenia
    Label nazwaKlienta = this.utworzEtykieteGracza(this.model_.nazwaGracza());
    if(this.model_.czyWlasciciel())
      nazwaKlienta.setTextFill(Color.YELLOW);

    Label nazwaDrugiegoGracza = this.utworzEtykieteGracza(this.model_.nazwaDrugiegoGracza());
    if(!this.model_.czyWlasciciel())
      nazwaDrugiegoGracza.setTextFill(Color.YELLOW);

    kontenerGraczy.getChildren().addAll(nazwaKlienta, nazwaDrugiegoGracza);

    kontenerInformacji.add(ukladKonteneruGraczy, 0, 0);

    // kontener opcji gry
    VBox kontenerOpcjiGry = new VBox();
    kontenerOpcjiGry.setBackground(Background.fill(Color.valueOf("#292929")));
    kontenerOpcjiGry.setPrefWidth(300);
    kontenerOpcjiGry.setAlignment(Pos.CENTER);

    // wybor trybu gry
    ComboBox<String> trybyGry = new ComboBox<>();
    trybyGry.itemsProperty().bind(this.model_.dostepneTryby());
    trybyGry.setValue(this.model_.domyslnyTryb());

    // przejscie do rozgrywki
    Button przyciskRozpocznijGre = new Button("Start");
    przyciskRozpocznijGre.setOnMouseClicked(
        mouseEvent -> this.kontroler_.wyslijRozpocznijGre(trybyGry.getValue()));

    kontenerOpcjiGry.getChildren().addAll(trybyGry, przyciskRozpocznijGre);
    kontenerInformacji.add(kontenerOpcjiGry, 0, 1);

    // ustawienie widgetow w kontenerze
    kontenerWidoku_.setRight(kontenerInformacji);
  }

  /**
   * Metoda ta tworzy etykiete nazwy gracza.
   *
   * @param nazwa Nazwa gracza do wyswietlenia.
   * @return Etykieta nazwy gracza.
   */
  private Label utworzEtykieteGracza(StringProperty nazwa) {
    Label etykieta = new Label();
    etykieta.textProperty().bind(nazwa);
    etykieta.setFont(new Font("Book Antiqua", 25));
    etykieta.setPadding(new Insets(5, 5, 5, 5));
    etykieta.setBorder(
        new Border(
            new BorderStroke(Color.valueOf("#1c1c1c"), BorderStrokeStyle.SOLID,
                new CornerRadii(3), new BorderWidths(1))
        )
    );
    etykieta.setBackground(
        new Background(
            new BackgroundFill(Color.valueOf("#949494"), new CornerRadii(3), null)
        )
    );
    return etykieta;
  }
}
