package klient.komunikacja;

import java.io.IOException;
import klient.kontroller.KontrolerAplikacji;

/**
 * Klasa mediatora. Posredniczy w komunikacji pomiedzy serwerem oraz aplikacja.
 * Konwertuje przesylane wiadomosci.
 */
public class Mediator {
  /** Kontroler aplikacji */
  private KontrolerAplikacji kontrolerAplikacji_;
  /** Watek polaczenia z serwerem */
  private Polaczenie polaczenie_;
  /** Status polaczenia z serwerem */
  private boolean czyPolaczono_;

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
   * Metoda konczaca prace watku polaczenia z serwerem.
   */
  public void zakonczPolaczenie() {
    this.polaczenie_.interrupt();
  }
}
