package klient.komunikacja;

import java.io.IOException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import klient.komunikacja.wiadomosci.TypyWiadomosci;
import klient.komunikacja.wiadomosci.Wiadomosc;
import klient.kontroler.GlownyKontroler;
import klient.kontroler.KontrolerGry;
import klient.kontroler.KontrolerPokoju;
import klient.kontroler.KontrolerWidoku;
import klient.kontroler.KontrolerWidokuGraczyOnline;

/**
 * Klasa mediatora. Posredniczy w komunikacji pomiedzy serwerem oraz aplikacja.
 * Konwertuje przesylane wiadomosci.
 */
public class Mediator {
  /** Kontroler aplikacji */
  private final GlownyKontroler glownyKontroler_;
  /** Kontroler aktualnego widoku aplikacji */
  private KontrolerWidoku aktualnyKontroler_;
  /** Watek polaczenia z serwerem */
  private Polaczenie polaczenie_;
  /** Status polaczenia z serwerem */
  private final BooleanProperty czyPolaczono_ = new SimpleBooleanProperty();
  /** Zmienna przedstawiajaca typ ostatniej wyslanej wiadomosci,
   * na ktorej odpowiedz aplikacja aktualnie oczekuje */
  private TypyWiadomosci typOstatniejWiadomosci_;
  /** Czy aplikacja oczekuje aktualnie na odpowiedz z serwera? */
  private boolean oczekiwanieNaOdpowiedz_ = false;

  /**
   * Konstruktor. Tworzy polaczenie z serwerem i zapisuje status polaczenia.
   */
  public Mediator(GlownyKontroler kontroler) {
    this.glownyKontroler_ = kontroler;
    try {
      this.polaczenie_ = new Polaczenie(this);
      this.czyPolaczono_.set(true);
      this.polaczenie_.start();
    } catch (IOException e) {
      this.czyPolaczono_.set(false);
    }
  }

  /**
   * Metoda ustawiajaca kontroler aktualnego widoku aplikacji.
   *
   * @param kontroler Kontroler aktualnego widoku aplikacji.
   */
  public void ustawAktualnyKontroler(KontrolerWidoku kontroler) {
    this.aktualnyKontroler_ = kontroler;
  }

  /**
   * Metoda ta zwraca status polaczenia z serwerem.
   *
   * @return Czy aplikacja polaczona jest z serwerem?
   */
  public BooleanProperty czyPolaczono() {
    return this.czyPolaczono_;
  }

  /**
   * Metoda ustawiajaca status polaczenia z serwerem.
   *
   * @param status Status polaczenia z serwerem.
   */
  public void ustawCzyPolaczono(boolean status) {
    this.czyPolaczono_.set(status);
  }

  /**
   * Metoda przekazujaca wiadomosc z aplikacji do serwera.
   *
   * @param wiadomosc Wiadomosc wyslana przez aplikacje.
   */
  public void wyslijWiadomoscDoSerwera(Wiadomosc wiadomosc) {
    System.out.println(wiadomosc);
    if(!oczekiwanieNaOdpowiedz_ && this.czyPolaczono_.get()) {
      this.polaczenie_.wyslijWiadomosc(wiadomosc);
      this.typOstatniejWiadomosci_ = wiadomosc.typWiadomosci();
      this.oczekiwanieNaOdpowiedz_ = true;
    }
  }

  /**
   * Metoda przekazujaca informacje od serwera do aplikacji.
   * Na podstawie poprzednio wyslanej wiadomosci dostosowuje ona swoje zachowanie.
   *
   * @param wiadomosc Wiadomosc zwrotna otrzymana od serwera.
   */
  public void przekazWiadomoscDoAplikacji(String wiadomosc) {
    // sprawdzic, czy otrzymana wiadomosc jest odpowiedzia
    if(czyOdpowiedz(wiadomosc)) {
      return;
    }
    // zareagowac na odpowiedz od serwera
    if(typOstatniejWiadomosci_ == TypyWiadomosci.IMIE) {
      if(wiadomosc.startsWith("true"))
        ((KontrolerWidokuGraczyOnline)this.aktualnyKontroler_).przejdzDoListyGraczy();
      else {
        ((KontrolerWidokuGraczyOnline)this.aktualnyKontroler_)
            .wyswietlPowiadomienie("Wprowadzona nazwa jest juz zajeta");
      }
    } else if(typOstatniejWiadomosci_ == TypyWiadomosci.ROZPOCZECIE_GRY) {
      if(wiadomosc.startsWith("true"))
        this.glownyKontroler_.rozpocznijGre(this.wydobadzArgumenty(wiadomosc));
    } else if(typOstatniejWiadomosci_ == TypyWiadomosci.RUCH_PIONKA) {
      this.wyslijAktualizacjePlanszy(this.wydobadzArgumenty(wiadomosc));
    } else if(typOstatniejWiadomosci_ == TypyWiadomosci.ZAPROSZENIE) {
      if(wiadomosc.startsWith("Odrzucono"))
        this.glownyKontroler_.powiadomOdrzucil();
    }
    this.oczekiwanieNaOdpowiedz_ = false;
  }

  /**
   * Metoda sprawdzajaca czy podana wiadomosc jest odpowiedzia na poprzednio
   * wyslana informacje do serwera, czy niezalezna, nowa wiadomoscia.
   * @param wiadomosc Wiadomosc otrzymana od serwera.
   *
   * @return Czy podana wiadomosc jest odpowiedzia na poprzednio wyslane informacje?
   */
  private boolean czyOdpowiedz(String wiadomosc) {
    if(wiadomosc.startsWith("Zaproszenie")) {
      String[] argumenty = this.wydobadzArgumenty(wiadomosc);
      if(this.aktualnyKontroler_ instanceof KontrolerWidokuGraczyOnline) {
        ((KontrolerWidokuGraczyOnline)this.aktualnyKontroler_)
            .wyswietlZaproszenieOdGracza(argumenty[0]);
      }
    } else if(wiadomosc.startsWith("nowy_gracz")) {
      String gracz = stworzNazwe(wiadomosc);
      this.glownyKontroler_.zaktualizujListeGraczy(gracz, true);
    } else if(wiadomosc.startsWith("Zaakceptowano")) {
      this.glownyKontroler_.powiadomDolaczyl();
    } else if(wiadomosc.startsWith("Plansza")) {
      this.wyslijAktualizacjePlanszy(this.wydobadzArgumenty(wiadomosc));
    } else if(wiadomosc.startsWith("Czat")) {
      if(aktualnyKontroler_ instanceof KontrolerPokoju)
        ((KontrolerPokoju) aktualnyKontroler_).odbierzWiadomosc(this.wydobadzArgumenty(wiadomosc));
    } else if(wiadomosc.startsWith("plansza")) { // tutaj wiadomosc o rozpoczeciu gry powinna sie inaczej nazywac
      this.glownyKontroler_.rozpocznijGre(this.wydobadzArgumenty(wiadomosc));
    } else if(wiadomosc.startsWith("Rozlaczono")) {
      String gracz = stworzNazwe(wiadomosc);
      this.glownyKontroler_.zaktualizujListeGraczy(gracz, false);
    } else {
      return false;
    }
    return true;
  }

  /**
   * Metoda wydobywajaca nazwe gracza z otrzymanej wiadomosci.
   *
   * @param wiadomosc Otrzymana wiadomosc.
   * @return Nazwa gracza.
   */
  private String stworzNazwe(String wiadomosc) {
    String[] argumenty = this.wydobadzArgumenty(wiadomosc);
    StringBuilder gracz = new StringBuilder();
    for(int i=0; i < argumenty.length; i++) {
      gracz.append(argumenty[i]);
      if(i != argumenty.length - 1)
        gracz.append(" ");
    }
    return gracz.toString();
  }

  /**
   * Metoda odpowiedzialna za przesuniecie pionka oraz ewentualne
   * usuniecie pionka na podstawie informacji otrzymanych od serwera.
   *
   * @param argumenty Wiadomosc otrzymana od serwera.
   */
  private void wyslijAktualizacjePlanszy(String[] argumenty) {
    StringBuilder plansza = new StringBuilder();
    for(int i=0; i < argumenty.length; i++) {
      plansza.append(argumenty[i]);
      if(i != argumenty.length - 1)
        plansza.append(" ");
    }
    ((KontrolerGry)this.aktualnyKontroler_).zaktualizujPlansze(plansza.toString());
  }

  /**
   * Metoda rozdzielajaca wiadomosc na osobne wyrazy i zwracajaca liste z argumentami bez komendy.
   *
   * @param wiadomosc Wiadomosc otrzymana od serwera.
   * @return Argumenty polecenia.
   */
  private String[] wydobadzArgumenty(String wiadomosc) {
    String[] rozdzieloneWyrazy = wiadomosc.split( " ");
    String[] argumenty = new String[rozdzieloneWyrazy.length-1];
    System.arraycopy(rozdzieloneWyrazy,
        1, argumenty,
        0,
        rozdzieloneWyrazy.length - 1);
    return argumenty;
  }

  public void odnowPolaczenie() {
    try {
      this.polaczenie_ = new Polaczenie(this);
      this.czyPolaczono_.set(true);
      this.polaczenie_.start();
    } catch (IOException e) {
      this.czyPolaczono_.set(false);
    }
  }

  /**
   * Metoda konczaca prace watku polaczenia z serwerem.
   */
  public void zakonczPolaczenie() {
    if(this.czyPolaczono_.get())
      this.polaczenie_.interrupt();
  }
}
