package testKlient.testKontroler;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import klient.komunikacja.Mediator;
import klient.kontroler.KontrolerAplikacji;
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
    KontrolerAplikacji kontrolerAplikacji = new KontrolerAplikacji();
    Mediator mediator = new Mediator(kontrolerAplikacji);
    KontrolerWidoku kontroler =
        TworcaKontrolera.wybierzKontroler(TypyKontrolerow.KONTROLER_POKOJU,
            model.modelPokoju(),
            kontrolerAplikacji,
            mediator);
    assert(kontroler instanceof KontrolerPokoju);

    kontroler =
        TworcaKontrolera.wybierzKontroler(TypyKontrolerow.KONTROLER_GRY,
            model.modelGry(),
            kontrolerAplikacji,
            mediator);
    assert(kontroler instanceof KontrolerGry);

    kontroler =
        TworcaKontrolera.wybierzKontroler(TypyKontrolerow.KONTROLER_GRACZY_ONLINE,
            model.modelGraczyOnline(),
            kontrolerAplikacji,
            mediator);
    assert(kontroler instanceof KontrolerWidokuGraczyOnline);
  }
}
