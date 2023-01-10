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
    new GlownyKontroler();
    ServerSocket serwer = this.stworzSztucznySerwer();
    new GlownyKontroler();
  }

  @Test
  public void testZakonczPolaczenie() {
    ServerSocket serwer = this.stworzSztucznySerwer();
    GlownyKontroler kontroler = new GlownyKontroler();
    kontroler.zakonczPolaczenie();
  }

  @Test
  public void testUtworzPodstawowaScene() {
    GlownyKontroler kontroler = new GlownyKontroler();
    kontroler.utworzPodstawowaScene();
  }

  @Test
  public void testUtworzPokoj() {
    GlownyKontroler kontroler = new GlownyKontroler();
    kontroler.utworzPokoj("",
        true,
        new Wiadomosc("", TypyWiadomosci.ZAPROSZENIE));
  }

  @Test
  public void testPowiadomDolaczyl() {
    GlownyKontroler kontroler = new GlownyKontroler();
    kontroler.powiadomDolaczyl();
  }

  @Test
  public void testPoiadomOdrzucil() {
    GlownyKontroler kontroler = new GlownyKontroler();
    kontroler.powiadomOdrzucil();
  }

  @Test
  public void testDolaczDoPokoju() {
    GlownyKontroler kontroler = new GlownyKontroler();
    kontroler.utworzPokoj("",
        false,
        new Wiadomosc("", TypyWiadomosci.ODPOWIEDZ));
  }

  @Test
  public void testOdrzucZaproszenie() {
    GlownyKontroler kontroler = new GlownyKontroler();
    kontroler.odrzucZaproszenie("");
  }

  @Test(expected = NullPointerException.class)
  public void testZaktualizujListeGraczyException() {
    GlownyKontroler glownyKontroler = new GlownyKontroler();
    glownyKontroler.zaktualizujListeGraczy("", true);
  }

  @Test
  public void testZaktualizujListeGraczy() {
    GlownyKontroler glownyKontroler = new GlownyKontroler();
    glownyKontroler.utworzPodstawowaScene();
    glownyKontroler.zaktualizujListeGraczy("", true);
    glownyKontroler.zaktualizujListeGraczy("", false);
  }

  @Test
  public void testRozpocznijGre() {
    GlownyKontroler glownyKontroler = new GlownyKontroler();
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
