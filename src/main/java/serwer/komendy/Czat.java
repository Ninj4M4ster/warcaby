package serwer.komendy;

import serwer.dane.Gracz;
import serwer.dane.Pokoj;

public class Czat implements Komenda {
    private Gracz gracz;

    public Czat(Gracz gracz) {
        this.gracz = gracz;
    }

    @Override
    public String Wykonaj(String reszta, Pokoj pokoj) {
        Gracz gracz2 = (gracz.getPokoj().getMistrz().equals(gracz) ? gracz.getPokoj().getGosc() : gracz.getPokoj().getMistrz());
        gracz2.getSt().Wyslij("Czat " + reszta);
        return "true";
    }
}