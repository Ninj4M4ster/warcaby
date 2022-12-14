package klient.kontroller;

public class TworcaKontrolera {
  public static KontrolerWidoku wybierzKontroler(TypyKontrolerow typ) {
    switch(typ) {
      case KONTROLER_GRACZY_ONLINE:
        return new KontrolerWidokuGraczyOnline();
    }
    return null;
  }
}
