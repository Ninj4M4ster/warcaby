package serwer.baza_danych;

import entities.Gra;
import entities.StanPlanszy;
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

  public Gra wprowadzNowaGre(String gracz1, String gracz2, int kolorGracz1, int kolorGracz2) {
    Gra gra = new Gra(gracz1, gracz2, kolorGracz1, kolorGracz2);
    for(BazaDanych bazaDanych: listaBazDanych) {
      if(bazaDanych.czyPolaczono())
        bazaDanych.wprowadzGre(gra);
    }
    return gra;
  }

  public void wprowadzNowyRuch(Gra gra, int numerRuchu, String plansza) {
    StanPlanszy stanPlanszy = new StanPlanszy(numerRuchu, plansza);
    gra.dodajRuch(stanPlanszy);
    for(BazaDanych bazaDanych: listaBazDanych) {
      if(bazaDanych.czyPolaczono())
        bazaDanych.wprowadzRuch(stanPlanszy);
    }
  }

}
