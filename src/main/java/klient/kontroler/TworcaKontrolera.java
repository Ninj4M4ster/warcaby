package klient.kontroler;

import klient.komunikacja.Mediator;
import klient.model.Model;

/**
 * Klasa odpowiedzialna za tworzenie kontrolera.
 */
public class TworcaKontrolera {

  /**
   * Prywatny konstruktor.
   * Klasa ta sluzy tylko do tworzenia kontrolera.
   */
  private TworcaKontrolera() {}

  /**
   * Metoda tworzaca nowy kontroler widoku na podstawie podanego typu.
   *
   * @param typ Typ kontrolera.
   * @return Nowy kontroler widoku.
   */
  public static KontrolerWidoku wybierzKontroler(TypyKontrolerow typ,
      Model model,
      Mediator mediator) {
    KontrolerWidoku kontroler;
    switch(typ) {
      case KONTROLER_GRACZY_ONLINE:
        kontroler = new KontrolerWidokuGraczyOnline();
        break;
      case KONTROLER_POKOJU:
        kontroler = new KontrolerPokoju();
        break;
      case KONTROLER_GRY:
        kontroler = new KontrolerGry();
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + typ);
    }
    kontroler.przekazModel(model);
    kontroler.przekazMediator(mediator);
    mediator.ustawAktualnyKontroler(kontroler);
    return kontroler;
  }
}
