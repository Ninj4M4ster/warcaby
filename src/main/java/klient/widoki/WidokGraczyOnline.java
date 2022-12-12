package klient.widoki;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

public class WidokGraczyOnline implements Widok {

  @Override
  public Scene utworzWidok(boolean statusPolaczenia) {
    //TODO(Jakub Drzewiecki) przerobić w późniejszym etapie całość na
    // StackPane i dodać w tle więcej elementów (np pionki)

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
    System.out.println(Font.getFontNames());
    glownyOpis.setFont(new Font("Book Antiqua", 60));
    glownyOpis.setTextFill(Color.valueOf("#ffffff"));

    DropShadow cien = new DropShadow();
    cien.setRadius(5.0);
    cien.setOffsetX(3.0);
    cien.setOffsetY(3.0);
    cien.setColor(Color.valueOf("#000000"));
    glownyOpis.setEffect(cien);

    poleGlownegoOpisu.getChildren().add(glownyOpis);

    // kontener na liste graczy online
    BorderPane oknoGracze = new BorderPane();
    oknoGracze.setPrefSize(400, 600);
    oknoGracze.setMaxSize(600, 600);
    oknoGracze.setBorder(
        new Border(
            new BorderStroke(
                Color.valueOf("#ffffff2a"),
                BorderStrokeStyle.SOLID,
                new CornerRadii(15),
                new BorderWidths(1)
            )
        )
    );
    oknoGracze.setBackground(
        new Background(
            new BackgroundFill(
                Color.valueOf("#ffffff7a"),
                new CornerRadii(15),
                null
            )
        )
    );

    // kontener na opis listy graczy online
    VBox poleOpisuOkna = new VBox();
    poleOpisuOkna.setAlignment(Pos.CENTER);
    poleOpisuOkna.setPadding(new Insets(5, 0, 5, 0));

    // opis listy graczy online
    Label opisOkna = new Label("Gracze online");
    opisOkna.setTextFill(
        Color.valueOf("ffffff")
    );
    opisOkna.setFont(new Font("Book Antiqua", 20));
    poleOpisuOkna.getChildren().add(opisOkna);

    // linia oddzielajaca opis okna listy graczy i liste
    Rectangle linia = new Rectangle();
    linia.setArcHeight(5);
    linia.setArcWidth(5);
    linia.setHeight(2);
    linia.setWidth(300);
    linia.setFill(Color.valueOf("ffffff"));

    poleOpisuOkna.getChildren().add(linia);

    oknoGracze.setTop(poleOpisuOkna);

    // kontener na pola z graczami online
    VBox listaGraczy = new VBox();
    oknoGracze.setCenter(listaGraczy);

    // TODO(Jakub Drzewiecki) Stworzyć kustomowy obiekt prezentujacy gracza,
    //  który można nacisnąć i zaprosić gracza do wybranego trybu

    okno.getChildren().addAll(poleGlownegoOpisu, oknoGracze);
    return new Scene(okno);
  }
}
