package klient.komunikacja;

import java.io.IOException;
import klient.komunikacja.wiadomosci.Wiadomosc;
import klient.kontroler.KontrolerAplikacji;
import klient.kontroler.KontrolerWidoku;

/**
 * Klasa mediatora. Posredniczy w komunikacji pomiedzy serwerem oraz aplikacja.
 * Konwertuje przesylane wiadomosci.
 */
public class Mediator {
  /** Kontroler aplikacji */
  private KontrolerAplikacji kontrolerAplikacji_;
  /** Kontroler aktualnego widoku aplikacji */
  private KontrolerWidoku aktualnyKontroler_;
  /** Watek polaczenia z serwerem */
  private Polaczenie polaczenie_;
  /** Status polaczenia z serwerem */
  private boolean czyPolaczono_;
  /** Zmienna przedstawiajaca wyslana wiadomosc, na ktorej odpowiedz aplikacja aktualnie oczekuje */
  private Wiadomosc aktualnaWiadomosc_;
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
    if(!oczekiwanieNaOdpowiedz_) {
      this.polaczenie_.wyslijWiadomosc(wiadomosc);
      this.oczekiwanieNaOdpowiedz_ = true;
    }
    // TODO(Jakub Drzewiecki): Tutaj mozna byloby dodac kolejkowanie wiadomosci jesli aplikacja czeka na odpowiedz oraz aplikacja jest polaczona z serwerem
  }

  /**
   * Metoda przekazujaca informacje od serwera do aplikacji.
   * Na podstawie poprzednio wyslanej wiadomosci dostosowuje ona swoje zachowanie
   * @param napis
   */
  public void przekazWiadomoscDoAplikacji(String napis) {
    // TODO(Jakub Drzewiecki): Utworzyc rozne rodzaje wiadomosci oraz dostosowac zachowanie na podstawie klasy poprzednio wyslanej wiadomosci
    this.oczekiwanieNaOdpowiedz_ = false;
  }

  /**
   * Metoda konczaca prace watku polaczenia z serwerem.
   */
  public void zakonczPolaczenie() {
    this.polaczenie_.interrupt();
  }
}
