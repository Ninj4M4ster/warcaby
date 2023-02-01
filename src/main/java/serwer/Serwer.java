package serwer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import baza_danych.MenadzerBazyDanych;

public class Serwer {

  /**
   * Główna funkcja serwera
   * Łączy się z nowymi graczami i dla każdego tworzy osobny wątek
   * @param args
   */
  public static void main(String[] args) {
    ServerSocket ss = null;
    MenadzerBazyDanych menadzerBazyDanych = MenadzerBazyDanych.instancja();
    menadzerBazyDanych.konfiguruj(new String[]{"serwer.warcaby"});
    
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