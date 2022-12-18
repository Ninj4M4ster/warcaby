package serwer.komendy;

import serwer.dane.Pokoj;

public class PodajDostepneRuchy implements Komenda{
    Pokoj pokoj;
    @Override
    public boolean Wykonaj(String reszta, Pokoj pokoj) {
        return false;
    }
}
