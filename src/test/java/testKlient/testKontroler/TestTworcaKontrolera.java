package testKlient.testKontroler;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import klient.komunikacja.Mediator;
import klient.kontroler.GlownyKontroler;
import klient.kontroler.KontrolerGry;
import klient.kontroler.KontrolerPokoju;
import klient.kontroler.KontrolerWidoku;
import klient.kontroler.KontrolerWidokuGraczyOnline;
import klient.kontroler.TworcaKontrolera;
import klient.kontroler.TypyKontrolerow;
import klient.model.GlownyModel;
import org.junit.Test;

public class TestTworcaKontrolera {
  @Test
  public void testWybierzKontroler() {
    BooleanProperty czyPolaczono = new SimpleBooleanProperty(false);
    GlownyModel model = new GlownyModel(czyPolaczono);
    GlownyKontroler glownyKontroler = new GlownyKontroler();
    Mediator mediator = new Mediator(glownyKontroler);
    KontrolerWidoku kontroler =
        TworcaKontrolera.wybierzKontroler(TypyKontrolerow.KONTROLER_POKOJU,
            model.modelPokoju(),
            glownyKontroler,
            mediator);
    assert(kontroler instanceof KontrolerPokoju);

    kontroler =
        TworcaKontrolera.wybierzKontroler(TypyKontrolerow.KONTROLER_GRY,
            model.modelGry(),
            glownyKontroler,
            mediator);
    assert(kontroler instanceof KontrolerGry);

    kontroler =
        TworcaKontrolera.wybierzKontroler(TypyKontrolerow.KONTROLER_GRACZY_ONLINE,
            model.modelGraczyOnline(),
            glownyKontroler,
            mediator);
    assert(kontroler instanceof KontrolerWidokuGraczyOnline);
  }
}
