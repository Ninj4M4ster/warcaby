package testKlient.testKontroler;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import klient.komunikacja.Mediator;
import klient.kontroler.GlownyKontroler;
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
    Model model = this.utworzModel(typ, statusPolaczenia);
    GlownyKontroler glownyKontroler = new GlownyKontroler();
    Mediator mediator = new Mediator(glownyKontroler);

    return TworcaKontrolera.wybierzKontroler(typ,
    model,
        glownyKontroler,
    mediator);
  }

  public Model utworzModel(TypyKontrolerow typ, boolean statusPolaczenia) {
    BooleanProperty czyPolaczono = new SimpleBooleanProperty();
    czyPolaczono.set(statusPolaczenia);
    StringProperty nazwaGracza = new SimpleStringProperty();
    nazwaGracza.set("");
    if(typ == TypyKontrolerow.KONTROLER_GRACZY_ONLINE)
      return new ModelGraczyOnline(nazwaGracza, czyPolaczono);
    else if(typ == TypyKontrolerow.KONTROLER_POKOJU)
      return new ModelPokoju(nazwaGracza, czyPolaczono);
    else
      return new ModelGry(czyPolaczono);
  }
}
