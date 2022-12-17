package serwer.dane;

import serwer.komendy.zasady.ZasadyGry;

public class Pokoj {
    private Gracz mistrz, gosc;
    private static int licznik = 0;
    private int id;


    private int[][] plansza;
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
        id = licznik;
        licznik += 1;
    }

    public Pokoj(Gracz mistrz) {
        this.mistrz = mistrz;
        id = licznik;
        licznik += 1;
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

    public void setPlansza(int[][] plansza) {
        this.plansza = plansza;
    }

    public int[][] getPlansza() {
        return plansza;
    }
}
