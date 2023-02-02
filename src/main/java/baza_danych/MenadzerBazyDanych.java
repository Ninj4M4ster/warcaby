package baza_danych;

import entities.Gra;
import entities.StanPlanszy;
import java.util.ArrayList;
import java.util.List;
import kontrolerDanych.KontrolerDanych;

/**
 * Klasa obslugujaca interakcje serwera z baza danych.
 */
public class MenadzerBazyDanych {
  /** Instancja tej klasy. */
  private static volatile MenadzerBazyDanych instancja;
  private ArrayList<BazaDanych> listaBazDanych = new ArrayList<>();

  /**
   * Prywatny konstruktor.
   */
  private MenadzerBazyDanych() {}

  /**
   * Metoda odpowiedzialna za konfiguracje.
   * Tworzy bazy danych, ktore zapisuja gry oraz ruchy.
   *
   * @param plikiDostepu Nazwy plikow z uprawnieniami dla poszczegolnych baz danych.
   */
  public void konfiguruj(String[] plikiDostepu) {
    BazaDanychMysql bazaDanych = new BazaDanychMysql(plikiDostepu[0]);
    listaBazDanych.add(bazaDanych);
  }

  /**
   * Metoda dajaca dostep do instancji menadzera baz danych.
   *
   * @return Instancja menadzera baz danych.
   */
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

  /**
   * Metoda odpowiedzialna za utworzenie instancji Gry i przeslanie jej do bazy danych.
   *
   * @param gracz1 Nazwa pierwszego gracza.
   * @param gracz2 Nazwa drugiego gracza.
   * @param kolorGracz1 Kolor pierwszego gracza.
   * @param kolorGracz2 Kolor drugiego gracza.
   * @return Instancja klasy Gra przekazana do bazy danyc.
   */
  public Gra wprowadzNowaGre(String gracz1, String gracz2, int kolorGracz1, int kolorGracz2) {
    Gra gra = new Gra(gracz1, gracz2, kolorGracz1, kolorGracz2);
    for(BazaDanych bazaDanych: listaBazDanych) {
      if(bazaDanych.czyPolaczono())
        bazaDanych.wprowadzGre(gra);
    }
    return gra;
  }

  /**
   * Metoda odpowiedzialna za utworzenie instancji klasy StanPlanszy i wyslanie jej do bazy danych.
   *
   * @param gra Gra, w ktorej odbyl sie ruch.
   * @param numerRuchu Numer ruchu.
   * @param plansza Stan planszy.
   */
  public void wprowadzNowyRuch(Gra gra, int numerRuchu, String plansza) {
    StanPlanszy stanPlanszy = new StanPlanszy(numerRuchu, plansza);
    gra.dodajRuch(stanPlanszy);
    for(BazaDanych bazaDanych: listaBazDanych) {
      if(bazaDanych.czyPolaczono())
        bazaDanych.wprowadzRuch(stanPlanszy);
    }
  }

  /**
   * Metoda odpowiedzialna za pobranie listy gier z baz danych.
   *
   * @return Lista gier.
   */
  public ArrayList<Gra> pobierzGry() {
    ArrayList<Gra> listaGier = new ArrayList<>();
    for(BazaDanych bazaDanych: listaBazDanych) {
      if(bazaDanych.czyPolaczono()) {
        List<Gra> lista = bazaDanych.pobierzGry();
        listaGier.addAll(lista);
      }
    }
    return listaGier;
  }

}
