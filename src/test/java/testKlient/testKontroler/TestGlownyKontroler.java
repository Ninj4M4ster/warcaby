package testKlient.testKontroler;

import java.io.IOException;
import java.net.ServerSocket;
import javafx.application.Platform;
import klient.komunikacja.wiadomosci.TypyWiadomosci;
import klient.komunikacja.wiadomosci.Wiadomosc;
import klient.kontroler.GlownyKontroler;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestGlownyKontroler {
  @BeforeClass
  public static void inicjalizujJavaFX() {
    Platform.startup(() -> {});
  }
  @Test
  public void testKontrolerAplikacji() {
    GlownyKontroler.instancja();
    ServerSocket serwer = this.stworzSztucznySerwer();
    GlownyKontroler.instancja();
  }

  @Test
  public void testZakonczPolaczenie() {
    ServerSocket serwer = this.stworzSztucznySerwer();
    GlownyKontroler kontroler = GlownyKontroler.instancja();
    kontroler.zakonczPolaczenie();
  }

  @Test
  public void testUtworzPodstawowaScene() {
    GlownyKontroler kontroler = GlownyKontroler.instancja();
    kontroler.utworzPodstawowaScene();
  }

  @Test
  public void testUtworzPokoj() {
    GlownyKontroler kontroler = GlownyKontroler.instancja();
    kontroler.utworzPokoj("",
        true,
        new Wiadomosc("", TypyWiadomosci.ZAPROSZENIE));
  }

  @Test
  public void testPowiadomDolaczyl() {
    GlownyKontroler kontroler = GlownyKontroler.instancja();
    kontroler.powiadomDolaczyl();
  }

  @Test
  public void testPoiadomOdrzucil() {
    GlownyKontroler kontroler = GlownyKontroler.instancja();
    kontroler.powiadomOdrzucil();
  }

  @Test
  public void testDolaczDoPokoju() {
    GlownyKontroler kontroler = GlownyKontroler.instancja();
    kontroler.utworzPokoj("",
        false,
        new Wiadomosc("", TypyWiadomosci.ODPOWIEDZ));
  }

  @Test
  public void testOdrzucZaproszenie() {
    GlownyKontroler kontroler = GlownyKontroler.instancja();
    kontroler.odrzucZaproszenie("");
  }

  @Test(expected = NullPointerException.class)
  public void testZaktualizujListeGraczyException() {
    GlownyKontroler glownyKontroler = GlownyKontroler.instancja();
    glownyKontroler.zaktualizujListeGraczy("", true);
  }

  @Test
  public void testZaktualizujListeGraczy() {
    GlownyKontroler glownyKontroler = GlownyKontroler.instancja();
    glownyKontroler.utworzPodstawowaScene();
    glownyKontroler.zaktualizujListeGraczy("", true);
    glownyKontroler.zaktualizujListeGraczy("", false);
  }

  @Test
  public void testRozpocznijGre() {
    GlownyKontroler glownyKontroler = GlownyKontroler.instancja();
    glownyKontroler.rozpocznijGre(new String[]{"1", "8"});
    glownyKontroler.rozpocznijGre(new String[]{"2", "10"});
    glownyKontroler.rozpocznijGre(new String[]{"1", "12"});
  }


  private ServerSocket stworzSztucznySerwer() {
    try {
      return new ServerSocket(6666);
    } catch (IOException e) {
      return null;
    }
  }
}
