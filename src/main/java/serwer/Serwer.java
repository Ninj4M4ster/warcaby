package serwer;

import serwer.dane.Gracz;
import serwer.dane.Pokoj;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Serwer {
  private static List<Gracz> gracze = new ArrayList<Gracz>();
  private static List<Pokoj> pokoje = new ArrayList<Pokoj>();
  private Socket socket;

  public static void main(String[] args) {
  ServerSocket ss = null;
    
    try {
      ss = new ServerSocket(6666);
    } catch (IOException ioe) {
      ioe.printStackTrace();
      System.out.println("IO exception");
    }

    int gracze_id = 1;
    while(true) {
      try {
        Socket socket = ss.accept();

        SerwerThread st = new SerwerThread(socket, gracze_id);
        gracze_id += 1;
        st.start();
      } catch(Exception e) {
        e.printStackTrace();
        System.out.println("Problem z połączeniem");
      }
    }
  }

  public static List<Pokoj> getPokoje() {
    return pokoje;
  }

  public static void addPokoj(Pokoj pokoj) {
    pokoje.add(pokoj);
  }

  public static List<Gracz> getGracze() {
    return gracze;
  }

  public static void addGracz(Gracz gracz) {
    gracze.add(gracz);
  }

  public static void removeGracz(Gracz gracz) {
    gracze.remove(gracz);
  }
}