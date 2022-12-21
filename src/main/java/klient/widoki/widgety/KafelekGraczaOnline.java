package klient.widoki.widgety;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import klient.widoki.eventy.OknoKlikniete;

/**
 * Klasa reprezentujaca widget majacy na celu przechowywac dane o graczu,
 * ktory jest polaczony z serwerem oraz dajacy mozliwosc zaproszenia go do pokoju.
 */
public class KafelekGraczaOnline extends VBox {

  /** Nazwa dostepnego gracza */
  private final String nazwaGracza_;

  /** Kontener na przycisk do zaproszenia gracza */
  private final HBox pasekOpcje_;

  /** Przycisk zapraszajacy gracza do pokoju */
  private final Button przyciskZapros_;

  /**
   * Konstruktor, tworzy wszystkie widoczne elementy.
   *
   * @param nazwaGracza Nazwa dostepnego gracza.
   */
  public KafelekGraczaOnline(String nazwaGracza) {
    super();
    // ustaw wyglad calego kontenera
    this.nazwaGracza_ = nazwaGracza;
    this.setPadding(new Insets(5,0,5,0));
    this.setAlignment(Pos.CENTER);
    this.setBackground(
        new Background(
            new BackgroundFill(
                Color.valueOf("#d1d1d1"),
                new CornerRadii(10),
                null)
        )
    );
    // dodaj cien do kontenera
    DropShadow cien = new DropShadow();
    cien.setRadius(10.0);
    cien.setOffsetX(1.0);
    cien.setOffsetY(1.0);
    cien.setColor(Color.valueOf("#303030"));
    this.setEffect(cien);

    // kontener na nazwe gracza
    HBox pasekNazwaGracza = new HBox();
    pasekNazwaGracza.setAlignment(Pos.CENTER);

    Label plakietkaNazwaGracza = new Label(nazwaGracza);
    plakietkaNazwaGracza.setFont(new Font("Book Antiqua", 18));

    pasekNazwaGracza.getChildren().add(plakietkaNazwaGracza);

    // kontener na przycisk sluzacy do zaproszenia gracza do pokoju
    pasekOpcje_ = new HBox();
    pasekOpcje_.setAlignment(Pos.CENTER);
    pasekOpcje_.setManaged(false);
    pasekOpcje_.setVisible(false);

    przyciskZapros_ = new Button("Zaproś gracza do gry");
    przyciskZapros_.setFont(new Font("Book Antiqua", 16));
    przyciskZapros_.setPrefWidth(300);
    przyciskZapros_.setStyle("-fx-background-color: '#4f4f4f';");
    przyciskZapros_.setTextFill(Color.valueOf("#ffffff"));
    przyciskZapros_.setEffect(cien);

    pasekOpcje_.getChildren().add(przyciskZapros_);

    this.getChildren().addAll(pasekNazwaGracza, pasekOpcje_);

    // przypisz wydarzenia
    this.setOnMouseClicked((event) -> pokazOpcje());
    this.addEventHandler(OknoKlikniete.OKNO_KLIKNIETE, this::ukryjOpcje);
  }

  /**
   * Zwroc nazwe dostepnego gracza
   *
   * @return Nazwa dostepnego gracza.
   */
  public String nazwaGracza() {
    return nazwaGracza_;
  }

  /**
   * Zwroc przycisk sluzacy do zaproszenia gracza od pokoju.
   *
   * @return Przycisk sluzacy do zaproszenia gracza do pokoju.
   */
  public Button przyciskZapros() {
    return przyciskZapros_;
  }

  /**
   * Metoda ta ujawnia przycisk sluzacy do zaproszenia gracza do pokoju.
   */
  private void pokazOpcje() {
   pasekOpcje_.setVisible(true);
   pasekOpcje_.setManaged(true);
  }

  /**
   * Metoda ta chowa przycisk sluzacy do zaproszenia gracza do pokoju.
   *
   * @param event Wydarzenie wywolane kliknieciem elementu widoku.
   */
  private void ukryjOpcje(Event event) {
    OknoKlikniete oknoKlikniete = (OknoKlikniete) event;
    // sprawdz, czy ten kafelek nie byl zrodlem wydarzenia
    if(oknoKlikniete.zrodlo() != null && oknoKlikniete.zrodlo().equals(this)) {
      return;
    } else {
      ObservableList<Node> lista = this.getChildren();
      if(lista.size() > 0) {
        boolean wynik;
        for(Node node: lista) {
          wynik = czyDziecko(node, oknoKlikniete.zrodlo());
          if(wynik)
            return;
        }
      }
    }
    pasekOpcje_.setVisible(false);
    pasekOpcje_.setManaged(false);
  }

  /**
   * Metoda pomocnicza, rekursywnie sprawdza czy
   * wszytkie elementy tego widgetu nie sa zrodlem wydarzenia.
   *
   * @param node Sprawdzany widget.
   * @param zrodlo Zrodlo wydarzenia.
   * @return Czy ktorys z elementow tego kontenera byl zrodlem wydarzenia?
   */
  private boolean czyDziecko(Node node, Node zrodlo) {
    if(node.equals(zrodlo))
      return true;
    boolean wynik;
    if(node instanceof Parent) {
      ObservableList<Node> lista = ((Parent) node).getChildrenUnmodifiable();
      for(Node dziecko: lista) {
        wynik = czyDziecko(dziecko, zrodlo);
        if(wynik)
          return true;
      }
    }
    return false;
  }
}
