package serwer.baza_danych;

import entities.Gra;
import entities.StanPlanszy;

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
}
