package testKlient.testKontroler;

import klient.komunikacja.Mediator;
import klient.kontroler.KontrolerAplikacji;
import klient.kontroler.KontrolerPokoju;
import klient.kontroler.KontrolerWidoku;
import klient.kontroler.TypyKontrolerow;
import klient.model.Model;
import klient.model.ModelPokoju;
import org.junit.Test;

public class TestKontrolerPokoju extends TestKontroler {

  @Test
  public void testPrzekazModel() {
    KontrolerPokoju kontroler = new KontrolerPokoju();
    Model model = this.utworzModel(TypyKontrolerow.KONTROLER_POKOJU, false);
    kontroler.przekazModel(model);
  }

  @Test(expected = IllegalStateException.class)
  public void testPrzekazModelError() {
    KontrolerWidoku kontroler =
        this.utworzGotowyKontroler(TypyKontrolerow.KONTROLER_POKOJU, false);
    Model model = this.utworzModel(TypyKontrolerow.KONTROLER_POKOJU, false);
    kontroler.przekazModel(model);
  }

  @Test
  public void testPrzekazGlownyKontroler() {
    KontrolerPokoju kontroler = new KontrolerPokoju();
    KontrolerAplikacji kontrolerAplikacji = new KontrolerAplikacji();
    kontroler.przekazGlownyKontroler(kontrolerAplikacji);
  }

  @Test(expected = IllegalStateException.class)
  public void testPrzekazGlownyKontrolerError() {
    KontrolerWidoku kontrolerPokoju =
        this.utworzGotowyKontroler(TypyKontrolerow.KONTROLER_POKOJU, false);
    KontrolerAplikacji kontrolerAplikacji = new KontrolerAplikacji();
    kontrolerPokoju.przekazGlownyKontroler(kontrolerAplikacji);
  }

  @Test
  public void testPrzekazWiadomosc() {
    KontrolerPokoju kontrolerPokoju = this.utworzGotowyKontrolerZWiadomoscia(false);
    kontrolerPokoju.wyslijWiadomosc();
  }

  @Test
  public void testRozpocznijGre() {
    KontrolerPokoju kontrolerPokoju =
        (KontrolerPokoju)this.utworzGotowyKontroler(
            TypyKontrolerow.KONTROLER_POKOJU,
            false);
    kontrolerPokoju.wyslijRozpocznijGre("");
  }

  private KontrolerPokoju utworzGotowyKontrolerZWiadomoscia(boolean statusPolaczenia) {
    KontrolerPokoju kontroler = new KontrolerPokoju();
    ModelPokoju model =
        (ModelPokoju)this.utworzModel(TypyKontrolerow.KONTROLER_POKOJU, statusPolaczenia);
    model.ustawTekstWiadomosci("abc");
    KontrolerAplikacji kontrolerAplikacji = new KontrolerAplikacji();
    Mediator mediator = new Mediator(kontrolerAplikacji);

    kontroler.przekazModel(model);
    kontroler.przekazGlownyKontroler(kontrolerAplikacji);
    kontroler.przekazMediator(mediator);
    return kontroler;
  }
}
