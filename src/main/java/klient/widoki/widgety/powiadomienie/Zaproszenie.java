package klient.widoki.widgety.powiadomienie;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import klient.kontroler.KontrolerWidokuGraczyOnline;

/**
 * Konkretna klasa powiadomienia, jest to
 * powiadomienie o oczekujacym zaproszeniu do pokoju.
 */
public class Zaproszenie extends Powiadomienie {

  /**
   * Konstruktor. Tworzy powiadomienie o zaproszeniu oraz przyciski
   * akceptacji i odrzucenia w zaleznosci, czy powiadomienie jest zaproszeniem.
   *
   * @param powiadomienie Gracz wysylajacy zaproszenie lub powiadomienie do wyswietlenia.
   * @param kontroler     Kontroler widoku.
   */
  public Zaproszenie(String powiadomienie, KontrolerWidokuGraczyOnline kontroler) {
    super();
    this.stworzOpisPowiadomienia(powiadomienie + " zaprasza cie do gry");
    this.stworzPrzyciski(kontroler, powiadomienie);
    this.gracz_ = powiadomienie;
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
}
