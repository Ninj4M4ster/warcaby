package serwer.komendy;

import serwer.dane.Pokoj;

public interface Komenda {
    boolean Wykonaj(String reszta, Pokoj pokoj);

}
