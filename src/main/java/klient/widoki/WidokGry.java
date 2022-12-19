package klient.widoki;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import klient.kontroller.KontrolerGry;
import klient.kontroller.KontrolerWidoku;
import klient.model.Model;
import klient.model.ModelGry;

public class WidokGry implements Widok {
  private ModelGry model_;
  private KontrolerGry kontroler_;
  private StackPane okno_;

  @Override
  public Parent utworzWidok(KontrolerWidoku kontroler, Model model) {
    if(this.model_ != null || this.kontroler_ != null)
      throw new IllegalStateException("Nie mozna utworzyc juz utworzonego widoku");

    this.model_ = (ModelGry) model;
    this.kontroler_ = (KontrolerGry) kontroler;

    this.okno_ = new StackPane();
    this.okno_.setAlignment(Pos.CENTER);

    GridPane planszaGry = new GridPane();
    planszaGry.setStyle("-fx-background-color: #222222;");
    planszaGry.maxHeightProperty().bind(this.okno_.heightProperty().multiply(0.98));
    planszaGry.maxWidthProperty().bind(planszaGry.maxHeightProperty());
    planszaGry.setAlignment(Pos.CENTER);

    this.okno_.getChildren().add(planszaGry);

    return this.okno_;
  }
}
