package klient.widoki;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import klient.kontroller.KontrolerPokoju;
import klient.kontroller.KontrolerWidoku;
import klient.model.Model;
import klient.model.ModelPokoju;

public class WidokPokoju implements Widok {
  private KontrolerPokoju kontroler_;
  private ModelPokoju model_;
  private StackPane okno_;
  @Override
  public Parent utworzWidok(KontrolerWidoku kontroler, Model model) {
    if(this.model_ != null || this.kontroler_ != null)
      throw new IllegalStateException("Nie mozna utworzyc juz utworzonego widoku");
    this.kontroler_ = (KontrolerPokoju) kontroler;
    this.model_ = (ModelPokoju) model;

    // TODO(Jakub Drzewiecki): Mozliwe dodać więcej elementów graficznych do okna? Jesli nie, trzeba usunac i ustawic kontenerWidoku jako korzen
    // stackpane jako korzen widoku
    this.okno_ = new StackPane();
    this.okno_.setAlignment(Pos.CENTER);

    // kontener elementy dotyczace bezposrednio pokoju
    BorderPane kontenerWidoku = new BorderPane();


    // kontener na chat
    BorderPane kontenerChatu = new BorderPane();
    kontenerChatu.setBackground(Background.fill(Color.valueOf("#525252")));

    // kontener na wyswietlanie historii chatu
    ScrollPane kontenerHistoriiChatu = new ScrollPane();
    kontenerHistoriiChatu.setStyle("-fx-background: #525252; -fx-background-color: #525252;");
    HBox historiaChatu = new HBox();

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

    // przycisk do wyslania wiadomosci
    Button przyciskWyslijWiadomosc = new Button("Wyślij");
    przyciskWyslijWiadomosc.setMaxWidth(Double.MAX_VALUE);
    przyciskWyslijWiadomosc.setPrefHeight(30);

    kontenerPolaTekstowego.add(poleTekstowe, 0, 0);
    kontenerPolaTekstowego.add(przyciskWyslijWiadomosc, 1, 0);

    kontenerChatu.setBottom(kontenerPolaTekstowego);

    // kontener opcji gry oraz informacji o graczach w lobby
    GridPane kontenerInformacji = new GridPane();
    RowConstraints rzad1 = new RowConstraints();
    rzad1.setPercentHeight(50);
    RowConstraints rzad2 = new RowConstraints();
    rzad2.setPercentHeight(50);
    kontenerInformacji.getRowConstraints().addAll(rzad1, rzad2);
    kontenerInformacji.setPrefWidth(300);
//    kontenerInformacji.setAlignment(Pos.CENTER);

    // kontener informacji o graczach w lobby
    VBox kontenerGraczy = new VBox();
    kontenerGraczy.setAlignment(Pos.CENTER);
    kontenerGraczy.getChildren().add(new Label("Gracz 1"));

    kontenerInformacji.add(kontenerGraczy, 0, 0);

    // kontener opcji gry
    VBox kontenerOpcjiGry = new VBox();
    kontenerOpcjiGry.setBackground(Background.fill(Color.valueOf("#292929")));
    kontenerOpcjiGry.setPrefWidth(300);
    kontenerOpcjiGry.setAlignment(Pos.CENTER);

    // wybor trybu gry
    ComboBox<String> trybyGry = new ComboBox<>();
    trybyGry.getItems().addAll(
        "Warcaby klasyczne (brazylijskie)",
        "Warcaby polskie",
        "Warcaby kanadyjskie");
    trybyGry.setValue("Warcaby klasyczne (brazylijskie)");

    // przejscie do rozgrywki
    Button przyciskRozpocznijGre = new Button("Start");

    kontenerOpcjiGry.getChildren().addAll(trybyGry, przyciskRozpocznijGre);
    kontenerInformacji.add(kontenerOpcjiGry, 0, 1);

    // ustawienie widgetow w kontenerze
    kontenerWidoku.setRight(kontenerInformacji);
    kontenerWidoku.setCenter(kontenerChatu);

    this.okno_.getChildren().add(kontenerWidoku);

    return this.okno_;
  }
}
