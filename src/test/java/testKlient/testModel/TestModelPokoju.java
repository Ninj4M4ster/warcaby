package testKlient.testModel;

import java.util.Objects;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import klient.ZasadyGry;
import klient.model.ModelPokoju;
import org.junit.Test;

public class TestModelPokoju {
  @Test
  public void testModelPokoju() {
    StringProperty nazwa = new SimpleStringProperty();
    nazwa.set("");

    BooleanProperty czyPolaczono = new SimpleBooleanProperty();
    czyPolaczono.set(false);
    new ModelPokoju(nazwa, czyPolaczono);
  }

  @Test
  public void testCzyPolaczono() {
    ModelPokoju model = this.utworzModel();
    BooleanProperty czyPolaczono = new SimpleBooleanProperty();
    czyPolaczono.set(true);
    model.ustawCzyPolaczono(czyPolaczono);
    czyPolaczono = model.czyPolaczono();
    assert(czyPolaczono.get());
  }

  @Test
  public void testNazwaGracza() {
    ModelPokoju model = this.utworzModel();
    StringProperty nazwa = new SimpleStringProperty();
    nazwa.set("abc");
    model.ustawNazwaGracza(nazwa);
    nazwa = model.nazwaGracza();
    assert(nazwa.get().compareTo("abc") == 0);
  }

  @Test
  public void testDostepneTryby() {
    ModelPokoju model = this.utworzModel();
    ObservableList<String> listaTrybow =
        FXCollections.observableArrayList(ZasadyGry.KLASYCZNE.toString(),
            ZasadyGry.POLSKIE.toString(),
            ZasadyGry.KANADYJSKIE.toString());
    assert(Objects.equals(model.dostepneTryby().get(), listaTrybow));
  }

  @Test
  public void testTekstWiadomosci() {
    ModelPokoju model = this.utworzModel();
    model.ustawTekstWiadomosci("abc");
    assert(model.tekstWiadomosci().get().compareTo("abc") == 0);
  }

  @Test
  public void testCzyWlasciciel() {
    ModelPokoju model = this.utworzModel();
    model.ustawCzyWlasciciel(true);
    assert(model.czyWlasciciel());
  }

  private ModelPokoju utworzModel() {
    StringProperty nazwa = new SimpleStringProperty();
    nazwa.set("");

    BooleanProperty czyPolaczono = new SimpleBooleanProperty();
    czyPolaczono.set(false);
    return new ModelPokoju(nazwa, czyPolaczono);
  }

}
