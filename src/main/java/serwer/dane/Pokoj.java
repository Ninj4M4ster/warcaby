package serwer.dane;

import serwer.dane.Gracz;

public class Pokoj {
    Gracz mistrz, gosc;
    public Pokoj(Gracz mistrz, Gracz gosc) {
        this.mistrz = mistrz;
        this.gosc = gosc;
    }

    public Gracz getMistrz() {
        return mistrz;
    }

    public Gracz getGosc() {
        return gosc;
    }
}
