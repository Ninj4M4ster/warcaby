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
    Mediator mediator = new Mediator();
    KontrolerWidoku kontroler =
        TworcaKontrolera.wybierzKontroler(TypyKontrolerow.KONTROLER_POKOJU,
            model.modelPokoju(),
            mediator);
    assert(kontroler instanceof KontrolerPokoju);

    kontroler =
        TworcaKontrolera.wybierzKontroler(TypyKontrolerow.KONTROLER_GRY,
            model.modelGry(),
            mediator);
    assert(kontroler instanceof KontrolerGry);

    kontroler =
        TworcaKontrolera.wybierzKontroler(TypyKontrolerow.KONTROLER_GRACZY_ONLINE,
            model.modelGraczyOnline(),
            mediator);
    assert(kontroler instanceof KontrolerWidokuGraczyOnline);
  }
}
