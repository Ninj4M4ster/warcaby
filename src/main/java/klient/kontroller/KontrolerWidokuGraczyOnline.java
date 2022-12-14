package klient.kontroller;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import klient.model.ModelWidoku;

public class KontrolerWidokuGraczyOnline implements KontrolerWidoku {
  private VBox listaGraczy_;
  private BorderPane oknoGlowne_;
  private TextField poleWprowadzaniaNazwy_;
  private VBox kontenerOpisuListyGraczy_;
  private ScrollPane kontenerListyGraczy_;
  private ModelWidoku model_;

  @Override
  public void przekazModel(ModelWidoku model) {
    this.model_ = model;
  }
}
