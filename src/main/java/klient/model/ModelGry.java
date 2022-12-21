package klient.model;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

public class ModelGry implements Model {
  private int iloscPol_ = 8;
  private String kolorPionkow_ = "bialy";
  private GridPane planszaGry_ = new GridPane();
  private Parent[][] polaPlanszy_;
  private String[] znakiIndeksow_ = {"A", "B", "C", "D", "E", "F", "G", "H"};

  public int iloscPol() {
    return this.iloscPol_;
  }

  public void ustawIloscPol(int ilosc) {
    this.iloscPol_ = ilosc;
    String[] indeksy = new String[this.iloscPol_];
    for(int i=0; i < this.iloscPol_; i++) {
      indeksy[i] = String.valueOf('A' + i);
    }
    this.znakiIndeksow_ = indeksy;
  }

  public String kolorPionkow() {
    return this.kolorPionkow_;
  }

  public void ustawKolorPionkow(String kolor) {
    if(!kolor.equals("bialy") && !kolor.equals("czarny"))
      throw new IllegalArgumentException("Jedyne mozliwe kolory pionkow to 'bialy' oraz 'czarny'");
    this.kolorPionkow_ = kolor;
  }

  public GridPane planszaGry() {
    return this.planszaGry_;
  }

  public void ustawPolaPlanszy(Parent[][] listaPol) {
    this.polaPlanszy_ = listaPol;
  }

  public String[] znakiIndeksow() {
    return this.znakiIndeksow_;
  }
}
