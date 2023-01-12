package klient.widoki.widgety;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Klasa reprezentujaca dymek czatu wyswietlany po wyslaniu lub odebraniu wiadomosci w pokoju.
 */
public class DymekCzatu extends HBox {

  /**
   * Konstruktor. Tworzy dymek, wybiera jego kolor i polozenie w zaleznosci,
   * czy wiadomosc wyslana zostala przez uzytkownika, czy otrzymana od serwera.
   *
   * @param tekst Tekst wiadomosci do wyswietlenia.
   * @param wiadomoscKlienta Czy wiadomosc zostala wyslana przez klienta,
   *                         czy otrzymana od serwera?
   */
  public DymekCzatu(String tekst, boolean wiadomoscKlienta) {
    // wybierz kolor i przyleganie dymku
    Color kolorWiadomosci;
    if(wiadomoscKlienta) {
      this.setAlignment(Pos.CENTER_RIGHT);
      kolorWiadomosci = Color.BLUE;
    }
    else {
      this.setAlignment(Pos.CENTER_LEFT);
      kolorWiadomosci = Color.LIGHTBLUE;
    }

    // utworz dymek z wiadomoscia
    Label etykietaTekstu = new Label(tekst);
    etykietaTekstu.setFont(new Font("Book Antiqua", 20));
    etykietaTekstu.maxWidthProperty().bind(this.widthProperty().multiply(0.7));
    etykietaTekstu.setPadding(new Insets(5, 10, 5, 10));
    etykietaTekstu.setBackground(
        new Background(
            new BackgroundFill(
                kolorWiadomosci,
                new CornerRadii(10),
                null)
        )
    );

    this.getChildren().add(etykietaTekstu);
  }
}
