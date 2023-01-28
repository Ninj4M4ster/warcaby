package serwer.baza_danych;

import java.util.ArrayList;
import kontrolerDanych.KontrolerDanych;

public class MenadzerBazyDanych {
  private static volatile MenadzerBazyDanych instancja;
  private ArrayList<BazaDanych> listaBazDanych = new ArrayList<>();
  private MenadzerBazyDanych() {
    BazaDanychMysql bazaDanych = new BazaDanychMysql();
    listaBazDanych.add(bazaDanych);
  }

  public static MenadzerBazyDanych instancja() {
    if(instancja != null) {
      return instancja;
    }
    synchronized(KontrolerDanych.class) {
      if(instancja == null) {
        instancja = new MenadzerBazyDanych();
      }
      return instancja;
    }
  }


}
