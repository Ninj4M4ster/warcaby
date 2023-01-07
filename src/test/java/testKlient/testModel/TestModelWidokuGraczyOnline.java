package testKlient.testModel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

  private ModelGraczyOnline utworzModel() {
    StringProperty nazwaGracza = new SimpleStringProperty();
    nazwaGracza.set("");

    BooleanProperty czyPolaczono = new SimpleBooleanProperty();
    czyPolaczono.set(false);

    return new ModelGraczyOnline(nazwaGracza, czyPolaczono);
  }

}
