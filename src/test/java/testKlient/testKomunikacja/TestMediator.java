package testKlient.testKomunikacja;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import klient.komunikacja.Mediator;
import klient.komunikacja.wiadomosci.TypyWiadomosci;
import klient.komunikacja.wiadomosci.Wiadomosc;
import klient.kontroler.GlownyKontroler;
import klient.kontroler.KontrolerWidoku;
import klient.kontroler.TypyKontrolerow;
import klient.model.GlownyModel;
import org.junit.BeforeClass;
import org.junit.Test;
import serwer.Serwer;
import testKlient.testKontroler.TestKontroler;

public class TestMediator extends TestKontroler {
  @BeforeClass
  public static void inicjalizujJavaFX() {
    Platform.startup(() -> {});
  }
  @Test
  public void testMediator() {
    new Mediator(GlownyKontroler.instancja());
    Serwer serwer = new Serwer();
    new Mediator(GlownyKontroler.instancja());
  }

  @Test
  public void ustawAktualnyKontroler() {
    Mediator mediator = new Mediator(GlownyKontroler.instancja());
    BooleanProperty booleanProperty = new SimpleBooleanProperty(false);
    GlownyModel model = new GlownyModel(booleanProperty);
    KontrolerWidoku kontroler =
        this.utworzGotowyKontroler(TypyKontrolerow.KONTROLER_GRY, false);

    mediator.ustawAktualnyKontroler(kontroler);
  }

  @Test
  public void testCzyPolaczono() {
    Mediator mediator = new Mediator(GlownyKontroler.instancja());
    mediator.ustawCzyPolaczono(false);
    assert !mediator.czyPolaczono().get();
  }

  @Test
  public void testWyslijWiadomoscDoSerwera() {
    Mediator mediator = new Mediator(GlownyKontroler.instancja());
    mediator.wyslijWiadomoscDoSerwera(new Wiadomosc("", TypyWiadomosci.WIADOMOSC));
  }

  @Test
  public void przekazWiadomoscDoAplikacji() {
    Mediator mediator = new Mediator(GlownyKontroler.instancja());
    mediator.przekazWiadomoscDoAplikacji("nowy_gracz abc");

    mediator.ustawAktualnyKontroler(
        this.utworzGotowyKontroler(TypyKontrolerow.KONTROLER_GRACZY_ONLINE, true));

    mediator.przekazWiadomoscDoAplikacji("");
  }

  @Test
  public void testCzyOdpowiedz() {
    Mediator mediator = new Mediator(GlownyKontroler.instancja());
    mediator.przekazWiadomoscDoAplikacji("Zaakceptowano");
    mediator.przekazWiadomoscDoAplikacji("Zaproszenie a");
    mediator.przekazWiadomoscDoAplikacji("nowy_gracz a");
    mediator.ustawAktualnyKontroler(
        this.utworzGotowyKontroler(TypyKontrolerow.KONTROLER_GRY, false));
    mediator.przekazWiadomoscDoAplikacji("Czat 1");
    mediator.przekazWiadomoscDoAplikacji("Rozlaczono a");
  }


}
