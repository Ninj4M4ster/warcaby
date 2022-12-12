package klient.widoki;

public class TworcaWidoku {
  public static Widok wybierzWidok(TypyWidokow typ) {
    switch(typ) {
      case WIDOK_GRACZY_ONLINE:
        return new WidokGraczyOnline();
      case WIDOK_POKOJU:
        return new WidokPokoju();
      case WIDOK_GRY:
        return new WidokGry();
    }
    return null;
  }
}
