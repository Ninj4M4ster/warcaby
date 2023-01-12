package testKlient.testModel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import klient.model.ModelGry;
import org.junit.Test;

public class TestModelGry {
  @Test
  public void testModelGry() {
    BooleanProperty czyPolaczono = new SimpleBooleanProperty();
    czyPolaczono.set(false);
    new ModelGry(czyPolaczono);
  }

  @Test
  public void testUstawCzyPolaczono() {
    ModelGry model = this.utworzModel();
    BooleanProperty czyPolaczono = new SimpleBooleanProperty();
    czyPolaczono.set(true);
    model.ustawCzyPolaczono(czyPolaczono);
  }

  @Test
  public void testCzyPolaczono() {
    ModelGry model = this.utworzModel();
    BooleanProperty czyPolaczono = model.czyPolaczono();
    assert(!czyPolaczono.get());
  }

  @Test
  public void testIloscPol() {
    ModelGry model = this.utworzModel();
    int iloscPol = model.iloscPol();
    assert(iloscPol == 8);
  }

  @Test
  public void testUstawIloscPol() {
    ModelGry model = this.utworzModel();
    model.ustawIloscPol(12);
  }

  @Test
  public void testUstawKolorPionkow() {
    ModelGry model = this.utworzModel();
    model.ustawKolorPionkow("czarny");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUstawKolorPionkowError() {
    ModelGry model = this.utworzModel();
    model.ustawKolorPionkow("");
  }

  private ModelGry utworzModel() {
    BooleanProperty czyPolaczono = new SimpleBooleanProperty();
    czyPolaczono.set(false);
    return new ModelGry(czyPolaczono);
  }
}
