package testKlient.testKontroler;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import klient.komunikacja.Mediator;
import klient.kontroler.KontrolerAplikacji;
import klient.kontroler.KontrolerWidoku;
import klient.kontroler.TworcaKontrolera;
import klient.kontroler.TypyKontrolerow;
import klient.model.Model;
import klient.model.ModelGraczyOnline;
import klient.model.ModelGry;
import klient.model.ModelPokoju;

/**
 * Abstrakcyjna klasa do testu kontrolerow, posiadajaca gotowa metode do
 * stworzenia kontrolera o wybranym typie.
 */
public abstract class TestKontroler {
  public KontrolerWidoku utworzGotowyKontroler(TypyKontrolerow typ, boolean statusPolaczenia) {
    KontrolerWidoku kontroler = TworcaKontrolera.wybierzKontroler(typ);
    Model model = this.utworzModel(typ, statusPolaczenia);
    KontrolerAplikacji kontrolerAplikacji = new KontrolerAplikacji();
    Mediator mediator = new Mediator(kontrolerAplikacji);

    assert kontroler != null;
    kontroler.przekazMediator(mediator);
    kontroler.przekazGlownyKontroler(kontrolerAplikacji);
    kontroler.przekazModel(model);

    return kontroler;
  }

  public Model utworzModel(TypyKontrolerow typ, boolean statusPolaczenia) {
    BooleanProperty czyPolaczono = new SimpleBooleanProperty();
    czyPolaczono.set(statusPolaczenia);
    StringProperty nazwaGracza = new SimpleStringProperty();
    nazwaGracza.set("");
    if(typ == TypyKontrolerow.KONTROLER_GRACZY_ONLINE)
      return new ModelGraczyOnline(nazwaGracza, czyPolaczono);
    else if(typ == TypyKontrolerow.KONTROLER_POKOJU)
      return new ModelPokoju(czyPolaczono);
    else
      return new ModelGry(czyPolaczono);
  }
}