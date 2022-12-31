package testKlient.testKontroler;

import klient.komunikacja.Mediator;
import klient.kontroler.KontrolerAplikacji;
import klient.kontroler.KontrolerPokoju;
import klient.model.ModelPokoju;
import org.junit.Test;

public class TestKontrolerPokoju {

  @Test
  public void testPrzekazModel() {
    KontrolerPokoju kontroler = new KontrolerPokoju();
    ModelPokoju model = new ModelPokoju();
    kontroler.przekazModel(model);
  }

  @Test(expected = IllegalStateException.class)
  public void testPrzekazModelError() {
    KontrolerPokoju kontroler = this.utworzGotowyKontroler();
    ModelPokoju model = new ModelPokoju();
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
    KontrolerPokoju kontrolerPokoju = this.utworzGotowyKontroler();
    KontrolerAplikacji kontrolerAplikacji = new KontrolerAplikacji();
    kontrolerPokoju.przekazGlownyKontroler(kontrolerAplikacji);
  }

  @Test
  public void testPrzekazWiadomosc() {
    KontrolerPokoju kontrolerPokoju = this.utworzGotowyKontrolerZWiadomoscia();
    kontrolerPokoju.wyslijWiadomosc();
  }

  @Test
  public void testRozpocznijGre() {
    KontrolerPokoju kontrolerPokoju = this.utworzGotowyKontroler();
    kontrolerPokoju.wyslijRozpocznijGre("");
  }

  private KontrolerPokoju utworzGotowyKontroler() {
    KontrolerPokoju kontroler = new KontrolerPokoju();
    ModelPokoju model = new ModelPokoju();
    KontrolerAplikacji kontrolerAplikacji = new KontrolerAplikacji();
    Mediator mediator = new Mediator(kontrolerAplikacji);

    kontroler.przekazModel(model);
    kontroler.przekazGlownyKontroler(kontrolerAplikacji);
    kontroler.przekazMediator(mediator);
    return kontroler;
  }

  private KontrolerPokoju utworzGotowyKontrolerZWiadomoscia() {
    KontrolerPokoju kontroler = new KontrolerPokoju();
    ModelPokoju model = new ModelPokoju();
    model.ustawTekstWiadomosci("abc");
    KontrolerAplikacji kontrolerAplikacji = new KontrolerAplikacji();
    Mediator mediator = new Mediator(kontrolerAplikacji);

    kontroler.przekazModel(model);
    kontroler.przekazGlownyKontroler(kontrolerAplikacji);
    kontroler.przekazMediator(mediator);
    return kontroler;
  }
}
