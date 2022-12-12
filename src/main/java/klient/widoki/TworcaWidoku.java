package klient.widoki;

public class TworcaWidoku {
  public static Widok wybierzWidok(String typ) {
    switch(typ) {
      case "gracze-online":
        return new WidokGraczyOnline();
      case "pokoj":
        return new WidokPokoju();
      case "gra":
        return new WidokGry();
    }
    return null;
  }
}
