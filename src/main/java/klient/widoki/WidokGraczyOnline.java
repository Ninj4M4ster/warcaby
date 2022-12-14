package klient.widoki;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import klient.kontroller.KontrolerWidoku;
import klient.kontroller.KontrolerWidokuGraczyOnline;
import klient.model.ModelGraczyOnline;
import klient.model.ModelWidoku;

public class WidokGraczyOnline implements Widok {

  private VBox listaGraczy_;
  private BorderPane oknoGlowne_;
  private TextField poleWprowadzaniaNazwy_;
  private VBox kontenerOpisuListyGraczy_;
  private ScrollPane kontenerListyGraczy_;

  /** Zmienna kontrolera, pozwala zapobiec przypisaniu dwoch kontrolerow do jednego widoku */
  private KontrolerWidokuGraczyOnline kontroler_;
  private ModelGraczyOnline model_;


  @Override
  public Scene utworzWidok(KontrolerWidoku kontroler, ModelWidoku model, boolean statusPolaczenia) {
    //TODO(Jakub Drzewiecki) przerobić w późniejszym etapie całość na
    // StackPane i dodać w tle więcej elementów (np pionki)
    if(this.model_ != null || this.kontroler_ != null)
      throw new IllegalStateException("Nie mozna utworzyc juz utworzonego widoku");
    this.kontroler_ = (KontrolerWidokuGraczyOnline) kontroler;
    this.model_ = (ModelGraczyOnline) model;

    // glowne okno, w nim znajduja sie wszystkie elementy
    VBox okno = new VBox();
    okno.setPadding(new Insets(20, 200, 20, 200));
    okno.setAlignment(Pos.CENTER);
    okno.setBackground(Background.fill(Color.valueOf("#242424")));

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

    // kontener w centrum, z nim klient prowadzi interakcje
    oknoGlowne_ = new BorderPane();
    oknoGlowne_.setPrefSize(400, 200);
    oknoGlowne_.setMaxSize(600, 600);
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

    this.utworzWidokWprowadzaniaNazwy();
    this.utworzWidokPoWprowadzeniuNazwy();

    // TODO(Jakub Drzewiecki) Stworzyć kustomowy obiekt prezentujacy gracza,
    //  który można nacisnąć i zaprosić gracza do wybranego trybu

    okno.getChildren().addAll(poleGlownegoOpisu, oknoGlowne_);
    return new Scene(okno);
  }

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

    kontenerWprowadzaniaNazwy.getChildren().addAll(opisWprowadzaniaNazwy, poleWprowadzaniaNazwy_, przyciskZatwierdzeniaNazwy);
    oknoGlowne_.setCenter(kontenerWprowadzaniaNazwy);
  }

  private void utworzWidokPoWprowadzeniuNazwy() {
    // kontener na opis listy graczy online, zostanie wyswietlony po wprowadzeniu swojej nazwy
    kontenerOpisuListyGraczy_ = new VBox();
    kontenerOpisuListyGraczy_.setAlignment(Pos.CENTER);
    kontenerOpisuListyGraczy_.setPadding(new Insets(5, 0, 5, 0));

    // opis listy graczy online
    Label opisOkna = new Label("Gracze online");
    opisOkna.setTextFill(
        Color.valueOf("ffffff")
    );
    opisOkna.setFont(new Font("Book Antiqua", 20));
    kontenerOpisuListyGraczy_.getChildren().add(opisOkna);

    // linia oddzielajaca opis okna listy graczy i liste
    Rectangle linia = new Rectangle();
    linia.setArcHeight(5);
    linia.setArcWidth(5);
    linia.setHeight(2);
    linia.setWidth(300);
    linia.setFill(Color.valueOf("ffffff"));

    kontenerOpisuListyGraczy_.getChildren().add(linia);

    // kontener ze scrollem na pola z graczami online, zostanie wyswietlone po wprowadzeniu nazwy
    kontenerListyGraczy_ = new ScrollPane();
    kontenerListyGraczy_.setVbarPolicy(ScrollBarPolicy.NEVER);
    kontenerListyGraczy_.setPadding(new Insets(10, 2, 15, 2));
    kontenerListyGraczy_.setStyle("-fx-background: rgba(255,255,255,0);"
        + "-fx-background-color: rgba(255,255,255,0);");

    listaGraczy_ = new VBox();
    listaGraczy_.setSpacing(5);
    kontenerListyGraczy_.setContent(listaGraczy_);
  }
}
