package klient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Klasa odpowiedzialna za polaczenie z serwerem oraz
 * wysylanie sformatowanych i specjalnie zakodowanych wiadomosci.
 */
public class Klient {
  /** Polaczenie z serwerem */
  private Socket polaczenie_;
  /** Deskryptor wyjscia */
  private PrintWriter out_;
  /** Deskryptor wejscia */
  private BufferedReader in_;

  /**
   * Konstruktor, tworzy polaczenie z serwerem oraz deskryptory wejscia i wyjscia.
   * @throws IOException
   */
  public Klient() throws IOException {
    polaczenie_ = new Socket("127.0.0.1", 6666);
    out_ = new PrintWriter(polaczenie_.getOutputStream(), true);
    in_ = new BufferedReader(new InputStreamReader(polaczenie_.getInputStream()));
  }
}
