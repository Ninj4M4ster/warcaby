package klient.widoki.widgety;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import klient.widoki.eventy.OknoKlikniete;

public class KafelekGraczaOnline extends VBox {
  private final String nazwaGracza_;
  private final HBox pasekOpcje_;
  public KafelekGraczaOnline(String nazwaGracza) {
    super();
    this.nazwaGracza_ = nazwaGracza;
    this.setPadding(new Insets(5,0,5,0));
    this.setAlignment(Pos.CENTER);
    this.setBackground(Background.fill(Color.valueOf("#ffffffaa")));

    HBox pasekNazwaGracza = new HBox();
    pasekNazwaGracza.setAlignment(Pos.CENTER);

    Label plakietkaNazwaGracza = new Label(nazwaGracza);
    plakietkaNazwaGracza.setFont(new Font("Book Antiqua", 18));

    pasekNazwaGracza.getChildren().add(plakietkaNazwaGracza);

    pasekOpcje_ = new HBox();
    pasekOpcje_.setAlignment(Pos.CENTER);
    pasekOpcje_.setManaged(false);
    pasekOpcje_.setVisible(false);

    Label opcje = new Label("Opcje");
    opcje.setFont(new Font("Book Antiqua", 18));

    pasekOpcje_.getChildren().add(opcje);

    this.getChildren().addAll(pasekNazwaGracza, pasekOpcje_);
    this.setOnMouseClicked((event) -> pokazOpcje());
    this.addEventHandler(OknoKlikniete.OKNO_KLIKNIETE, this::ukryjOpcje);
  }

  public String nazwaGracza() {
    return nazwaGracza_;
  }

  private void pokazOpcje() {
   pasekOpcje_.setVisible(true);
   pasekOpcje_.setManaged(true);
  }

  private void ukryjOpcje(Event event) {
    OknoKlikniete oknoKlikniete = (OknoKlikniete) event;
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
