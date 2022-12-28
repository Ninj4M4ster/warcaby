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
  private Socket polaczenie_;
  /** Deskryptor wyjscia */
  private PrintWriter out_;
  /** Deskryptor wejscia */
  private BufferedReader in_;
  /** Mediator miedzy polaczeniem z serwerem oraz aplikaca */
  private Mediator mediator_;

  /**
   * Konstruktor, tworzy polaczenie z serwerem oraz deskryptory wejscia i wyjscia.
   *
   * @param mediator Mediator miedzy serwerem a aplikacja.
   * @throws IOException Blad polaczenia z serwerem.
   */
  public Polaczenie(Mediator mediator) throws IOException {
    this.polaczenie_ = new Socket("127.0.0.1", 6666);
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
    while(true) {
      try {
        String wiadomosc = this.in_.readLine();
        this.mediator_.przekazWiadomoscDoAplikacji(wiadomosc);
      } catch (IOException e) {
        this.mediator_.ustawCzyPolaczono(false);
        throw new RuntimeException(e);
      }
    }
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
