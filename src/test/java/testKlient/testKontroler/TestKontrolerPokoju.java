package testKlient.testKontroler;

import klient.ZasadyGry;
import klient.komunikacja.Mediator;
import klient.kontroler.GlownyKontroler;
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
  public void testOdnowPolaczenie() {
    KontrolerPokoju kontrolerPokoju =
        (KontrolerPokoju)this.utworzGotowyKontroler(TypyKontrolerow.KONTROLER_POKOJU, false);
    kontrolerPokoju.odnowPolaczenie();
  }

  @Test
  public void testWyslijWiadomosc() {
    KontrolerPokoju kontrolerPokoju = this.utworzGotowyKontrolerZWiadomoscia(false);
    kontrolerPokoju.wyslijWiadomosc();
  }

  @Test
  public void testOdbierzWiadomosc() {
    KontrolerPokoju kontrolerPokoju =
        (KontrolerPokoju)this.utworzGotowyKontroler(TypyKontrolerow.KONTROLER_POKOJU, false);
    kontrolerPokoju.odbierzWiadomosc(new String[]{"a", "b", "c"});
  }

  @Test
  public void testRozpocznijGre() {
    KontrolerPokoju kontrolerPokoju =
        (KontrolerPokoju)this.utworzGotowyKontroler(
            TypyKontrolerow.KONTROLER_POKOJU,
            false);
    kontrolerPokoju.wyslijRozpocznijGre("");
  }

  @Test
  public void testWybierzNumerTrybu() {
    KontrolerPokoju kontroler =
        (KontrolerPokoju)this.utworzGotowyKontroler(TypyKontrolerow.KONTROLER_POKOJU, false);
    kontroler.wyslijRozpocznijGre(ZasadyGry.KLASYCZNE.toString());
    kontroler.wyslijRozpocznijGre(ZasadyGry.POLSKIE.toString());
    kontroler.wyslijRozpocznijGre(ZasadyGry.KANADYJSKIE.toString());
  }

  private KontrolerPokoju utworzGotowyKontrolerZWiadomoscia(boolean statusPolaczenia) {
    KontrolerPokoju kontroler = new KontrolerPokoju();
    ModelPokoju model =
        (ModelPokoju)this.utworzModel(TypyKontrolerow.KONTROLER_POKOJU, statusPolaczenia);
    model.ustawTekstWiadomosci("abc");
    Mediator mediator = new Mediator(GlownyKontroler.instancja());

    kontroler.przekazModel(model);
    kontroler.przekazMediator(mediator);
    return kontroler;
  }
}
