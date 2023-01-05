package klient.komunikacja;

import java.io.IOException;
import klient.komunikacja.wiadomosci.TypyWiadomosci;
import klient.komunikacja.wiadomosci.Wiadomosc;
import klient.kontroler.KontrolerAplikacji;
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
  private final KontrolerAplikacji kontrolerAplikacji_;
  /** Kontroler aktualnego widoku aplikacji */
  private KontrolerWidoku aktualnyKontroler_;
  /** Watek polaczenia z serwerem */
  private Polaczenie polaczenie_;
  /** Status polaczenia z serwerem */
  private boolean czyPolaczono_;
  /** Zmienna przedstawiajaca typ ostatniej wyslanej wiadomosci,
   * na ktorej odpowiedz aplikacja aktualnie oczekuje */
  private TypyWiadomosci typOstatniejWiadomosci_;
  /** Czy aplikacja oczekuje aktualnie na odpowiedz z serwera? */
  private boolean oczekiwanieNaOdpowiedz_ = false;

  /**
   * Konstruktor. Tworzy polaczenie z serwerem i zapisuje status polaczenia.
   *
   * @param kontrolerAplikacji Kontroler aplikacji.
   */
  public Mediator(KontrolerAplikacji kontrolerAplikacji) {
    this.kontrolerAplikacji_ = kontrolerAplikacji;
    try {
      this.polaczenie_ = new Polaczenie(this);
      this.czyPolaczono_ = true;
      this.polaczenie_.start();
    } catch (IOException e) {
      this.czyPolaczono_ = false;
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
  public boolean czyPolaczono() {
    return this.czyPolaczono_;
  }

  /**
   * Metoda ustawiajaca status polaczenia z serwerem.
   *
   * @param status Status polaczenia z serwerem.
   */
  public void ustawCzyPolaczono(boolean status) {
    this.czyPolaczono_ = status;
  }

  /**
   * Metoda przekazujaca wiadomosc z aplikacji do serwera.
   *
   * @param wiadomosc Wiadomosc wyslana przez aplikacje.
   */
  public void wyslijWiadomoscDoSerwera(Wiadomosc wiadomosc) {
    System.out.println(wiadomosc);
    if(!oczekiwanieNaOdpowiedz_ && this.czyPolaczono_) {
      this.polaczenie_.wyslijWiadomosc(wiadomosc);
      this.typOstatniejWiadomosci_ = wiadomosc.typWiadomosci();
      this.oczekiwanieNaOdpowiedz_ = true;
    }
    // TODO(Jakub Drzewiecki): Tutaj mozna byloby dodac kolejkowanie wiadomosci jesli aplikacja czeka na odpowiedz oraz aplikacja jest polaczona z serwerem
  }

  /**
   * Metoda przekazujaca informacje od serwera do aplikacji.
   * Na podstawie poprzednio wyslanej wiadomosci dostosowuje ona swoje zachowanie.
   *
   * @param wiadomosc Wiadomosc zwrotna otrzymana od serwera.
   */
  public void przekazWiadomoscDoAplikacji(String wiadomosc) {
    // TODO(Jakub Drzewiecki): Utworzyc rozne rodzaje wiadomosci oraz dostosowac zachowanie na podstawie klasy poprzednio wyslanej wiadomosci
    // sprawdzic, czy otrzymana wiadomosc jest odpowiedzia
    if(!czyOdpowiedz(wiadomosc)) {
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
      this.kontrolerAplikacji_.rozpocznijGre(this.wydobadzArgumenty(wiadomosc));
    } else if(typOstatniejWiadomosci_ == TypyWiadomosci.RUCH_PIONKA) {
      this.wyslijAktualizacjePlanszy(this.wydobadzArgumenty(wiadomosc));
    }
    this.oczekiwanieNaOdpowiedz_ = false;
  }

  /**
   * Metoda sprawdzajaca czy podana wiadomosc jest odpowiedzia na poprzednio
   * wyslana informacje do serwera, czy niezalezna, nowa wiadomoscia.
   * @param wiadomosc Wiadomosc otrzymana od serwera.
   *
   * @return Czy podana wiadomosc jest odpowiedzia na poprzednio wyslane informacje?
   * TODO(Jakub Drzewiecki): Sprawdzic jakie komendy, ktore nie sa odpowiedziami, wysyla serwer.
   */
  private boolean czyOdpowiedz(String wiadomosc) {
    if(wiadomosc.startsWith("Zaproszenie")) {
      String[] argumenty = this.wydobadzArgumenty(wiadomosc);
      if(this.aktualnyKontroler_ instanceof KontrolerWidokuGraczyOnline) {
        ((KontrolerWidokuGraczyOnline)this.aktualnyKontroler_)
            .wyswietlZaproszenieOdGracza(argumenty[0]);
      }
    } else if(wiadomosc.startsWith("nowy_gracz")) {
      String[] argumenty = this.wydobadzArgumenty(wiadomosc);
      StringBuilder gracz = new StringBuilder();
      for(int i=0; i < argumenty.length; i++) {
        gracz.append(argumenty[i]);
        if(i != argumenty.length - 1)
          gracz.append(" ");
      }
      this.kontrolerAplikacji_.zaktualizujListeGraczy(gracz.toString(), true);
    } else if(wiadomosc.startsWith("Zaakceptowano")) {
      // TODO(Jakub Drzewiecki): Dolaczyc do pokoju gracza ktory zaakceptowal zaproszenie
    } else if(wiadomosc.startsWith("Plansza")) {
      this.wyslijAktualizacjePlanszy(this.wydobadzArgumenty(wiadomosc));
    } else if(wiadomosc.startsWith("Czat")) {
      if(aktualnyKontroler_ instanceof KontrolerPokoju)
        ((KontrolerPokoju) aktualnyKontroler_).odbierzWiadomosc(this.wydobadzArgumenty(wiadomosc));
    } else if(wiadomosc.startsWith("plansza")) { // tutaj wiadomosc o rozpoczeciu gry powinna sie inaczej nazywac
      this.kontrolerAplikacji_.rozpocznijGre(this.wydobadzArgumenty(wiadomosc));
    } else {
      return false;
    }
    return true;
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

  /**
   * Metoda konczaca prace watku polaczenia z serwerem.
   */
  public void zakonczPolaczenie() {
    if(this.czyPolaczono_)
      this.polaczenie_.interrupt();
  }
}
