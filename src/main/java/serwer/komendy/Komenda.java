package serwer.komendy;

import serwer.dane.Pokoj;

public interface Komenda {
    String Wykonaj(String reszta, Pokoj pokoj);

}
