package testKlient.testModel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.VBox;
import klient.model.ModelGraczyOnline;
import org.junit.Test;

public class TestModelWidokuGraczyOnline {
  @Test
  public void testModelGraczyOnline() {
    StringProperty nazwaGracza = new SimpleStringProperty();
    nazwaGracza.set("");

    BooleanProperty czyPolaczono = new SimpleBooleanProperty();
    czyPolaczono.set(false);

    new ModelGraczyOnline(nazwaGracza, czyPolaczono);
  }

  @Test
  public void testUstawCzyPolaczono() {
    ModelGraczyOnline model = this.utworzModel();
    BooleanProperty status = new SimpleBooleanProperty();
    status.set(true);
    model.ustawCzyPolaczono(status);
  }

  @Test
  public void testCzyPolaczono() {
    ModelGraczyOnline model = this.utworzModel();
    BooleanProperty status = model.czyPolaczono();
    assert(!status.get());
  }

  @Test
  public void testUstawNazweGracza() {
    ModelGraczyOnline model = this.utworzModel();
    model.ustawNazweGracza("");
  }

  @Test
  public void testUstawGoreMenu() {
    ModelGraczyOnline model = this.utworzModel();
    model.ustawGoreMenu(new VBox());
  }

  @Test
  public void testListaGraczy() {
    ModelGraczyOnline model = this.utworzModel();
    VBox lista = new VBox();
    model.ustawListeGraczy(lista);
    assert model.listaGraczy() == lista;
  }

  @Test
  public void testKontenerOpisuListyGraczy() {
    ModelGraczyOnline model = this.utworzModel();
    VBox kontener = new VBox();
    model.ustawKontenerOpisuListyGraczy(kontener);
    assert model.kontenerOpisuListyGraczy() == kontener;
  }

  private ModelGraczyOnline utworzModel() {
    StringProperty nazwaGracza = new SimpleStringProperty();
    nazwaGracza.set("");

    BooleanProperty czyPolaczono = new SimpleBooleanProperty();
    czyPolaczono.set(false);

    return new ModelGraczyOnline(nazwaGracza, czyPolaczono);
  }

}
