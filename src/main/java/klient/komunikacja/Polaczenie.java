package klient.komunikacja;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import klient.komunikacja.wiadomosci.Wiadomosc;

/**
 * Klasa odpowiedzialna za polaczenie z serwerem oraz
 * wysylanie sformatowanych i specjalnie zakodowanych wiadomosci.
 */
public class Polaczenie extends Thread {
  /** Polaczenie z serwerem */
  private final Socket polaczenie_;
  /** Deskryptor wyjscia */
  private final PrintWriter out_;
  /** Deskryptor wejscia */
  private final BufferedReader in_;
  /** Mediator miedzy polaczeniem z serwerem oraz aplikaca */
  private final Mediator mediator_;

  /**
   * Konstruktor, tworzy polaczenie z serwerem oraz deskryptory wejscia i wyjscia.
   *
   * @param mediator Mediator miedzy serwerem a aplikacja.
   * @throws IOException Blad polaczenia z serwerem.
   */
  public Polaczenie(Mediator mediator) throws IOException {
    this.polaczenie_ = new Socket("127.0.0.1", 6666);
    this.polaczenie_.setSoTimeout(1);
    this.out_ = new PrintWriter(polaczenie_.getOutputStream(), true);
    this.in_ = new BufferedReader(new InputStreamReader(polaczenie_.getInputStream()));
    this.mediator_ = mediator;
  }

  /**
   * Metoda uruchamiana po uruchomieniu watku.
   * Jej celem jest nasluchiwanie wiadomosci wysylanych przez serwer i wysylanie ich do aplikacji.
   */
  @Override
  public void run() {
    while(!Thread.currentThread().isInterrupted()) {
      try {
        String wiadomosc = this.in_.readLine();
        System.out.println(wiadomosc);
        this.mediator_.przekazWiadomoscDoAplikacji(wiadomosc);
      } catch (IOException ignored) {}
    }
    this.mediator_.ustawCzyPolaczono(false);
  }

  /**
   * Metoda przekazujaca wiadomosc z aplikacji do serwera.
   *
   * @param wiadomosc Wiadomosc przekazywana do serwera.
   */
  public void wyslijWiadomosc(Wiadomosc wiadomosc) {
    this.out_.println(wiadomosc);
  }
}
