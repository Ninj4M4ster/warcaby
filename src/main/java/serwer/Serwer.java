package serwer;

import serwer.dane.Gracz;
import serwer.dane.Pokoj;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Serwer {
  private List<Gracz> gracze;
  private List<Pokoj> pokoje;
  private static Socket socket;

  public static void main(String[] args) {
  ServerSocket ss = null;
    
    try {
      ss = new ServerSocket(6666);
    } catch (IOException ioe) {
      ioe.printStackTrace();
      System.out.println("IO exception");
    }


    while(true) {
      try {
        Socket socket = ss.accept();

        SerwerThread st = new SerwerThread(socket);
        st.start();
      } catch(Exception e) {
        e.printStackTrace();
        System.out.println("Problem z połączeniem");
      }
    }
  }
}
