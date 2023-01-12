package testKlient.testModel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import klient.model.GlownyModel;
import org.junit.Test;

public class TestGlownyModel {
  @Test
  public void testGlownyModel() {
    BooleanProperty czyPolaczono = new SimpleBooleanProperty();
    czyPolaczono.set(false);
    new GlownyModel(czyPolaczono);
  }

  @Test
  public void testPodmodele() {
    GlownyModel model = this.utworzModel();
    model.modelGraczyOnline();
    model.modelPokoju();
    model.modelGry();
  }

  @Test
  public void testCzyPolaczono() {
    GlownyModel model = this.utworzModel();
    boolean czyPolaczono = model.czyPolaczono();
    assert(!czyPolaczono);
  }

  private GlownyModel utworzModel() {
    BooleanProperty czyPolaczono = new SimpleBooleanProperty();
    czyPolaczono.set(false);
    return new GlownyModel(czyPolaczono);
  }
}
