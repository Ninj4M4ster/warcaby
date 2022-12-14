package klient.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ModelGraczyOnline implements Model {
  private final StringProperty nazwaGracza_ = new SimpleStringProperty();
  private final ObjectProperty<Node> centrumMenu_ = new SimpleObjectProperty<>();
  private final ObjectProperty<Node> goraMenu_ = new SimpleObjectProperty<>();
  private VBox kontenerOpisuListyGraczy_;
  private ScrollPane kontenerListyGraczy_;
  private VBox listaGraczy_;

  public ModelGraczyOnline(StringProperty nazwaGracza) {
    nazwaGracza.bind(nazwaGracza_);
    nazwaGracza_.set(nazwaGracza.get());
  }

  public void ustawNazweGracza(String nazwa) {
    nazwaGracza_.set(nazwa);
  }

  public ObjectProperty<Node> centrumMenu() {
    return this.centrumMenu_;
  }

  public ObjectProperty<Node> goraMenu() {
    return this.goraMenu_;
  }

  public void ustawCentrumMenu(Node widget) {
    this.centrumMenu_.set(widget);
  }

  public void ustawGoreMenu(Node widget) {
    this.goraMenu_.set(widget);
  }

  public void ustawKontenerOpisuListyGraczy(VBox kontener) {
    this.kontenerOpisuListyGraczy_ = kontener;
  }

  public void ustawKontenerListyGraczy(ScrollPane kontener) {
    this.kontenerListyGraczy_ = kontener;
  }

  public void ustawListeGraczy(VBox listaGraczy) {
    this.listaGraczy_ = listaGraczy;
  }

  public VBox kontenerOpisuListyGraczy() {
    return this.kontenerOpisuListyGraczy_;
  }

  public ScrollPane kontenerListyGraczy() {
    return this.kontenerListyGraczy_;
  }

}
