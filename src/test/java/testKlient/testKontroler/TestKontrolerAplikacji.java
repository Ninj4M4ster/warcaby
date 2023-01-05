package testKlient.testKontroler;

import java.io.IOException;
import java.net.ServerSocket;
import javafx.application.Platform;
import klient.kontroler.KontrolerAplikacji;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestKontrolerAplikacji {
  @BeforeClass
  public static void inicjalizujJavaFX() {
    Platform.startup(() -> {});
  }
  @Test
  public void testKontrolerAplikacji() {
    new KontrolerAplikacji();
    ServerSocket serwer = this.stworzSztucznySerwer();
    new KontrolerAplikacji();
  }

  @Test
  public void testUtworzPodstawowaScene() {
    KontrolerAplikacji kontroler = new KontrolerAplikacji();
    kontroler.utworzPodstawowaScene();
  }

  @Test
  public void testUtworzPokoj() {
    KontrolerAplikacji kontroler = new KontrolerAplikacji();
    kontroler.utworzPokoj("");
  }

  @Test(expected = NullPointerException.class)
  public void testZaktualizujListeGraczyException() {
    KontrolerAplikacji kontrolerAplikacji = new KontrolerAplikacji();
    kontrolerAplikacji.zaktualizujListeGraczy("", true);
  }

  @Test
  public void testZaktualizujListeGraczy() {
    KontrolerAplikacji kontrolerAplikacji = new KontrolerAplikacji();
    kontrolerAplikacji.utworzPodstawowaScene();
    kontrolerAplikacji.zaktualizujListeGraczy("", true);
    kontrolerAplikacji.zaktualizujListeGraczy("", false);
  }

  @Test
  public void testRozpocznijGre() {
    KontrolerAplikacji kontrolerAplikacji = new KontrolerAplikacji();
    kontrolerAplikacji.rozpocznijGre(new String[]{"bialy", "8"});
  }


  private ServerSocket stworzSztucznySerwer() {
    try {
      return new ServerSocket(6666);
    } catch (IOException e) {
      return null;
    }
  }
}
