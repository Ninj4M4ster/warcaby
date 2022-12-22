package klient.kontroller;

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
  public static KontrolerWidoku wybierzKontroler(TypyKontrolerow typ) {
    switch(typ) {
      case KONTROLER_GRACZY_ONLINE:
        return new KontrolerWidokuGraczyOnline();
      case KONTROLER_POKOJU:
        return new KontrolerPokoju();
      case KONTROLER_GRY:
        return new KontrolerGry();
    }
    return null;
  }
}
