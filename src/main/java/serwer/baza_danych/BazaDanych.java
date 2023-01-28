package serwer.baza_danych;

public abstract class BazaDanych {
  private boolean czyPolaczono_;

  public boolean czyPolaczono() {
    return this.czyPolaczono_;
  }

  public void ustawCzyPolaczono(boolean status) {
    this.czyPolaczono_ = status;
  }
}
