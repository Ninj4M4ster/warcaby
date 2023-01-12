package klient.widoki.widgety.powiadomienie;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * Klasa reprezentujaca powiadomienie w widoku graczy online.
 */
public abstract class Powiadomienie extends VBox {
  /** Nazwa gracza ktory wyslal zaproszenie lub '' jesli powiadomienie nie jest zaproszeniem */
  protected String gracz_ = "";

  /**
   * Konstruktor. Tworzy widget sluzacy do powiadomienia uzytkownika.
   */
  public Powiadomienie() {
    // ustawienie wygladu powiadomienia
    this.setBackground(
        new Background(
            new BackgroundFill(
                Color.valueOf("#737373"),
                new CornerRadii(8),
                null
            )
        )
    );
    this.setPadding(new Insets(5, 5, 5, 5));
    this.setSpacing(5);
  }

  /**
   * Metoda tworzaca opis powiadomienia.
   *
   * @param opisPowiadmienia Opis powiadomienia.
   */
  protected void stworzOpisPowiadomienia(String opisPowiadmienia) {
    // kontener na opis
    HBox kontenerPowiadomienia = new HBox();
    kontenerPowiadomienia.setAlignment(Pos.CENTER);
    this.getChildren().add(kontenerPowiadomienia);

    // opis o osobie zapraszajacej
    Label opis = new Label(opisPowiadmienia);
    opis.setFont(new Font("Book Antiqua", 18));
    opis.setWrapText(true);
    opis.setTextAlignment(TextAlignment.CENTER);
    kontenerPowiadomienia.getChildren().add(opis);
  }

  /**
   * Metoda zwracajaca nazwe gracza, ktory zaprosil klienta do pokoju,
   * lub pusty napis jesli powiadomienie nie jest zaproszeniem.
   *
   * @return Nazwa gracza, ktory zaprosil klienta do pokoju, lub pusty napis.
   */
  public String gracz() {
    return this.gracz_;
  }
}
