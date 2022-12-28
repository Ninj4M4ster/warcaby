package testKlient.testKontroler;

import klient.kontroler.KontrolerGry;
import klient.kontroler.KontrolerPokoju;
import klient.kontroler.KontrolerWidoku;
import klient.kontroler.KontrolerWidokuGraczyOnline;
import klient.kontroler.TworcaKontrolera;
import klient.kontroler.TypyKontrolerow;
import org.junit.Test;

public class TestTworcaKontrolera {
  @Test
  public void testWybierzKontroler() {
    KontrolerWidoku kontroler = TworcaKontrolera.wybierzKontroler(TypyKontrolerow.KONTROLER_POKOJU);
    assert(kontroler instanceof KontrolerPokoju);

    kontroler = TworcaKontrolera.wybierzKontroler(TypyKontrolerow.KONTROLER_GRY);
    assert(kontroler instanceof KontrolerGry);

    kontroler = TworcaKontrolera.wybierzKontroler(TypyKontrolerow.KONTROLER_GRACZY_ONLINE);
    assert(kontroler instanceof KontrolerWidokuGraczyOnline);
  }
}
