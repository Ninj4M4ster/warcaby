package klient.widoki;

/**
 * Klasa odpowiedzialna za tworzenie widoku.
 */
public class TworcaWidoku {

  /**
   * Prywatny konstruktor uniemozliwiajacy utworzenie instancji tej klasy.
   */
  private TworcaWidoku() {}

  /**
   * Metoda tworzaca widok na podstawie wyboru.
   *
   * @param typ Typ widoku, ktory chcemy utworzyc.
   * @return Nowo utworzony widok.
   */
  public static Widok wybierzWidok(TypyWidokow typ) {
    switch(typ) {
      case WIDOK_GRACZY_ONLINE:
        return new WidokGraczyOnline();
      case WIDOK_POKOJU:
        return new WidokPokoju();
      case WIDOK_GRY:
        return new WidokGry();
      case WIDOK_OGLADANIA_GRY:
        return new WidokOgladaniaGry();
      default:
        throw new IllegalStateException("Unexpected value: " + typ);
    }
  }
}
