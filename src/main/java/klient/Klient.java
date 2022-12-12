package klient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Klient {
  private Socket polaczenie_;
  private PrintWriter out_;
  private BufferedReader in_;
  public Klient() throws IOException {
    polaczenie_ = new Socket("127.0.0.1", 6666);
    out_ = new PrintWriter(polaczenie_.getOutputStream(), true);
    in_ = new BufferedReader(new InputStreamReader(polaczenie_.getInputStream()));
  }
}
