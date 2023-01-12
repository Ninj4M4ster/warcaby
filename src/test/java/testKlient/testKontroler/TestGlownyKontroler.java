package testKlient.testKontroler;

import java.io.IOException;
import java.net.ServerSocket;
import klient.komunikacja.wiadomosci.TypyWiadomosci;
import klient.komunikacja.wiadomosci.Wiadomosc;
import klient.kontroler.GlownyKontroler;
import org.junit.Test;

public class TestGlownyKontroler {
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
    glownyKontroler.rozpocznijGre("110101010010101011010101000000000"
        + "00000000020202022020202002020202");
    glownyKontroler.rozpocznijGre("210101010100101010101101010101001010101010000000000000"
        + "00000002020202020020202020220202020200202020202");
    glownyKontroler.rozpocznijGre("110101010101001010101010110101010101001010101010110101"
        + "0101010000000000000000000000000202020202020020202020202202020202020020202020202202020202"
        + "020");
  }


  private ServerSocket stworzSztucznySerwer() {
    try {
      return new ServerSocket(6666);
    } catch (IOException e) {
      return null;
    }
  }
}
