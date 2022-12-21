package testKlient.testKontroler;

import klient.kontroller.KontrolerGry;
import klient.kontroller.KontrolerPokoju;
import klient.kontroller.KontrolerWidoku;
import klient.kontroller.KontrolerWidokuGraczyOnline;
import klient.kontroller.TworcaKontrolera;
import klient.kontroller.TypyKontrolerow;
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
