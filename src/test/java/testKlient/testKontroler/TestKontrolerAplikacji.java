package testKlient.testKontroler;

import java.io.IOException;
import java.net.ServerSocket;
import javafx.application.Platform;
import klient.komunikacja.wiadomosci.TypyWiadomosci;
import klient.komunikacja.wiadomosci.Wiadomosc;
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
  public void testZakonczPolaczenie() {
    ServerSocket serwer = this.stworzSztucznySerwer();
    KontrolerAplikacji kontroler = new KontrolerAplikacji();
    kontroler.zakonczPolaczenie();
  }

  @Test
  public void testUtworzPodstawowaScene() {
    KontrolerAplikacji kontroler = new KontrolerAplikacji();
    kontroler.utworzPodstawowaScene();
  }

  @Test
  public void testUtworzPokoj() {
    KontrolerAplikacji kontroler = new KontrolerAplikacji();
    kontroler.utworzPokoj("",
        true,
        new Wiadomosc("", TypyWiadomosci.ZAPROSZENIE));
  }

  @Test
  public void testPowiadomDolaczyl() {
    KontrolerAplikacji kontroler = new KontrolerAplikacji();
    kontroler.powiadomDolaczyl();
  }

  @Test
  public void testPoiadomOdrzucil() {
    KontrolerAplikacji kontroler = new KontrolerAplikacji();
    kontroler.powiadomOdrzucil();
  }

  @Test
  public void testDolaczDoPokoju() {
    KontrolerAplikacji kontroler = new KontrolerAplikacji();
    kontroler.utworzPokoj("",
        false,
        new Wiadomosc("", TypyWiadomosci.ODPOWIEDZ));
  }

  @Test
  public void testOdrzucZaproszenie() {
    KontrolerAplikacji kontroler = new KontrolerAplikacji();
    kontroler.odrzucZaproszenie("");
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
    kontrolerAplikacji.rozpocznijGre(new String[]{"1", "8"});
    kontrolerAplikacji.rozpocznijGre(new String[]{"2", "10"});
    kontrolerAplikacji.rozpocznijGre(new String[]{"1", "12"});
  }


  private ServerSocket stworzSztucznySerwer() {
    try {
      return new ServerSocket(6666);
    } catch (IOException e) {
      return null;
    }
  }
}
