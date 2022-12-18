package klient.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class ModelPokoju implements Model {
  private VBox historiaChatu_ = new VBox();
  private ObjectProperty<ObservableList<String>> dostepneTryby_ =
      new SimpleObjectProperty<>();
  private String domyslnyTryb_;
  private StringProperty tekstWiadomosci_ = new SimpleStringProperty();

  public ModelPokoju() {
    ObservableList<String> listaTrybow =
        FXCollections.observableArrayList("Warcaby klasyczne (brazylijskie)",
        "Warcaby polskie",
        "Warcaby kanadyjskie");
    this.dostepneTryby_.set(listaTrybow);
    this.domyslnyTryb_ = listaTrybow.get(0);
  }

  public VBox historiaChatu() {
    return this.historiaChatu_;
  }

  public void dodajWiadomoscDoHistorii(Node kafelekWiadomosc) {
    this.historiaChatu_.getChildren().add(kafelekWiadomosc);
  }

  public ObjectProperty<ObservableList<String>> dostepneTryby() {
    return this.dostepneTryby_;
  }

  public void ustawDostepneTryby(ObservableList<String> lista) {
    this.dostepneTryby_.set(lista);
    this.ustawDomyslnyTryb(lista.get(0));
  }

  public void ustawDostepneTryby(String[] tryby) {
    ObservableList<String> listaTrybow = FXCollections.observableArrayList(tryby);
    this.dostepneTryby_.set(listaTrybow);
    this.ustawDomyslnyTryb(listaTrybow.get(0));
  }

  public String domyslnyTryb() {
    return this.domyslnyTryb_;
  }

  public void ustawDomyslnyTryb(String tryb) {
    this.domyslnyTryb_ = tryb;
  }

  public StringProperty tekstWiadomosci() {
    return this.tekstWiadomosci_;
  }

  public void ustawTekstWiadomosci(String wiadomosc) {
    this.tekstWiadomosci_.set(wiadomosc);
  }
}
