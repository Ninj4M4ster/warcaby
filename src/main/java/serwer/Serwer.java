package serwer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Serwer {
  private List<Gracz> gracze;
  private List<Pokoj> pokoje;
  private ServerSocket ss;

  public void main(String[] args) {

    try {
      ss = new ServerSocket(6666);

      while(true) {
        Socket socket = ss.accept();

        InputStream input = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        OutputStream output = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(output, true);
      }
    } catch (IOException ioe) {
      System.out.println("IO exception");
    }
  }
}
