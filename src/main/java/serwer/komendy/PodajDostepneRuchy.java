package serwer.komendy;

import serwer.dane.Pokoj;

public class PodajDostepneRuchy implements Komenda{
    Pokoj pokoj;
    @Override
    public String Wykonaj(String reszta, Pokoj pokoj) {
        return "false";
    }
}
