package klient.widoki.widgety;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import klient.kontroler.KontrolerWidokuGraczyOnline;

/**
 * Klasa reprezentujaca powiadomienie w widoku graczy online.
 */
public class Powiadomienie extends VBox {
  /** Nazwa gracza ktory wyslal zaproszenie lub '' jesli powiadomienie nie jest zaproszeniem */
  private String gracz_ = "";

  /**
   * Konstruktor. Tworzy powiadomienie oraz przycisk akceptacji i odrzucenia w zaleznosci,
   * czy powiadomienie jest zaproszeniem.
   *
   * @param powiadomienie Gracz wysylajacy zaproszenie lub powiadomienie do wyswietlenia.
   * @param kontroler Kontroler widoku.
   * @param czyZaproszenie Czy powiadomienie jest zaproszeniem do pokoju?
   */
  public Powiadomienie(String powiadomienie, KontrolerWidokuGraczyOnline kontroler, boolean czyZaproszenie) {
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

    // wybor opisu na podstawie argumentu
    String opisPowiadomienia;
    if(czyZaproszenie)
      opisPowiadomienia = powiadomienie + " zaprasza cie do gry";
    else
      opisPowiadomienia = powiadomienie;

    // stworzenie wdigetow
    this.stworzOpisPowiadomienia(opisPowiadomienia);
    if(czyZaproszenie) {
      this.stworzPrzyciski(kontroler, powiadomienie);
      this.gracz_ = powiadomienie;
    } else {
      // TODO(Jakub Drzewiecki): Dodac usuniecie powiadomienia po 5 sekundach
    }
  }

  /**
   * Metoda tworzaca opis powiadomienia.
   *
   * @param opisPowiadmienia Opis powiadomienia.
   */
  private void stworzOpisPowiadomienia(String opisPowiadmienia) {
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
   * Metoda tworzaca przycisk do akceptacji lub odrzucenia zaproszenia do pokoju.
   *
   * @param kontroler Kontroler widoku.
   * @param wlasciciel Wlasciciel pokoju, ktory zaprosil klienta.
   */
  private void stworzPrzyciski(KontrolerWidokuGraczyOnline kontroler, String wlasciciel) {
    // kontener na przyciski
    HBox kontenerPrzyciskow = new HBox();
    kontenerPrzyciskow.setAlignment(Pos.CENTER);
    kontenerPrzyciskow.setSpacing(15);
    this.getChildren().add(kontenerPrzyciskow);

    // przyciski do akceptacji i odrzucenia zaproszenia
    Button przyciskPotwierdz = new Button("Akceptuj");
    przyciskPotwierdz.setPrefSize(70, 25);
    przyciskPotwierdz.setFont(new Font("Book Antiqua", 12));
    przyciskPotwierdz.setBackground(Background.fill(Color.valueOf("#cccccc")));
    przyciskPotwierdz.setOnMouseClicked(mouseEvent -> kontroler.dolaczDoPokoju(wlasciciel));

    Button przyciskOdrzuc = new Button("Odrzuc");
    przyciskOdrzuc.setPrefSize(70, 25);
    przyciskOdrzuc.setFont(new Font("Book Antiqua", 12));
    przyciskOdrzuc.setBackground(Background.fill(Color.valueOf("#cccccc")));
    przyciskOdrzuc.setOnMouseClicked(mouseEvent -> kontroler.odrzucZaproszenie(wlasciciel));

    kontenerPrzyciskow.getChildren().addAll(przyciskPotwierdz, przyciskOdrzuc);
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
