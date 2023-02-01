package baza_danych;

import entities.Gra;
import entities.StanPlanszy;
import java.util.List;

/**
 * Abstrakcyjna klasa reprezentujaca baze danych.
 * Kazda obslugiwana przez serwer baza danych musi dziedziczyc po tej klasie.
 */
public abstract class BazaDanych {
  private boolean czyPolaczono_;

  public boolean czyPolaczono() {
    return this.czyPolaczono_;
  }

  public void ustawCzyPolaczono(boolean status) {
    this.czyPolaczono_ = status;
  }

  public abstract void wprowadzGre(Gra gra);

  public abstract void wprowadzRuch(StanPlanszy stanPlanszy);

  public abstract List<Gra> pobierzGry();
}
