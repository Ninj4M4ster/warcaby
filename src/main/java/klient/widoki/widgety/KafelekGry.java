package klient.widoki.widgety;

import entities.Gra;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Widget reprezentujacy rozegrana wczesniej gre, umozliwiajacy przejscie do widoku,
 * w ktorym mozna ja obejrzec.
 */
public class KafelekGry extends HBox {
  /** Gra, ktora reprezentuje widget */
  private final Gra gra_;
  /** Przycisk sluzacy do przejscia do ogladania gry */
  private final Button przycisk_;

  /**
   * Konstruktor. Tworzy opis gry i przycisk sluzacy do przejscia do ogladania tej gry.
   *
   * @param gra Gra, ktora reprezentuje widget.
   */
  public KafelekGry(Gra gra) {
    super();
    this.gra_ = gra;

    // ustawienia widgetu
    this.setPadding(new Insets(5,0,5,0));
    this.setAlignment(Pos.CENTER);
    this.setSpacing(50);
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

    // opis gry
    Label opis = new Label(gra.getGracz1() + " vs " + gra.getGracz2());
    opis.setFont(new Font("Book Antiqua", 18));
    this.getChildren().add(opis);

    // przycisk do przejscia do ogladania gry
    this.przycisk_ = new Button("Obejrzyj gre");
    this.przycisk_.setPrefSize(150, 30);
    this.przycisk_.setFont(new Font("Book Antiqua", 16));
    this.przycisk_.setBackground(
        Background.fill(Color.valueOf("#cccccc")));
    cien = new DropShadow();
    cien.setRadius(5.0);
    cien.setOffsetX(3.0);
    cien.setOffsetY(3.0);
    cien.setColor(Color.valueOf("#2e2e2e"));
    this.przycisk_.setEffect(cien);

    this.getChildren().add(this.przycisk_);
  }

  /**
   * Metoda zwracajaca przycisk sluzacy do przejscia do ogladania gry, ktora przedstawia ten widget.
   *
   * @return Przycisk sluzacy do przejscia do ogladania gry.
   */
  public Button przycisk() {
    return this.przycisk_;
  }

  /**
   * Metoda zwracajaca gre, ktora przedstawia ten widget.
   *
   * @return Gra, ktora przedstawia ten widget.
   */
  public Gra gra() {
    return this.gra_;
  }
}
