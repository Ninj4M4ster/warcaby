package testKlient.testWidoki;

import javafx.scene.layout.StackPane;
import klient.widoki.eventy.OknoKlikniete;
import org.junit.Test;

public class TestOknoKlikniete {
  @Test
  public void testOknoKlikniete() {
    new OknoKlikniete(new StackPane(), new StackPane());
  }

  @Test
  public void testZrodlo() {
    StackPane zrodlo = new StackPane();
    OknoKlikniete event = new OknoKlikniete(zrodlo, new StackPane());
    assert event.zrodlo() == zrodlo;
  }
}
