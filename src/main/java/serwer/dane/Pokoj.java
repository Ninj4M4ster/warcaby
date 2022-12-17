package serwer.dane;

import serwer.komendy.zasady.ZasadyGry;

public class Pokoj {
    Gracz mistrz, gosc;

    int[][] plansza;
    ZasadyGry zasady_gry;

    public Pokoj(Gracz mistrz, ZasadyGry zasady_gry) {
        this.mistrz = mistrz;
        gosc = null;
        this.zasady_gry = zasady_gry;
    }

    public Pokoj(Gracz mistrz, Gracz gosc, ZasadyGry zasady_gry) {
        this.mistrz = mistrz;
        this.gosc = gosc;
        this.zasady_gry = zasady_gry;
    }

    public Gracz getMistrz() {
        return mistrz;
    }

    public Gracz getGosc() {
        return gosc;
    }
    public void setGosc(Gracz gosc) {
        this.gosc = gosc;
    }

    public void setZasady_gry(ZasadyGry zasady_gry) {
        this.zasady_gry = zasady_gry;
    }
}
