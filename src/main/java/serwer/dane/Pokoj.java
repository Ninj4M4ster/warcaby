package serwer.dane;

import serwer.Serwer;
import serwer.komendy.zasady.ZasadyGry;

public class Pokoj {
    private Gracz mistrz, gosc;
    private static int licznik = 0;
    private int id;


    private int[][] plansza;
    private ZasadyGry zasady_gry;
    private KontrolerStanuGry kontroler_stanu_gry;
    KontrolerStanuGry.StanGry stan_gry;

    public Pokoj(Gracz mistrz, ZasadyGry zasady_gry) {
        this.mistrz = mistrz;
        gosc = null;
        this.zasady_gry = zasady_gry;
        Serwer.addPokoj(this);
        kontroler_stanu_gry = new KontrolerStanuGry();
        stan_gry = KontrolerStanuGry.StanGry.NIEROZPOCZETA;
    }

    public Pokoj(Gracz mistrz, Gracz gosc, ZasadyGry zasady_gry) {
        this.mistrz = mistrz;
        mistrz.setPokoj(this);
        this.gosc = gosc;
        gosc.setPokoj(this);
        this.zasady_gry = zasady_gry;
        Serwer.addPokoj(this);
        id = licznik;
        licznik += 1;
        kontroler_stanu_gry = new KontrolerStanuGry();
        stan_gry = KontrolerStanuGry.StanGry.NIEROZPOCZETA;
    }

    public Pokoj(Gracz mistrz) {
        this.mistrz = mistrz;
        mistrz.setPokoj(this);
        Serwer.addPokoj(this);
        id = licznik;
        licznik += 1;
        kontroler_stanu_gry = new KontrolerStanuGry();
        stan_gry = KontrolerStanuGry.StanGry.NIEROZPOCZETA;
    }

    public Gracz getMistrz() {
        return mistrz;
    }

    public Gracz getGosc() {
        return gosc;
    }
    public void setGosc(Gracz gosc) {
        this.gosc = gosc;
        gosc.setPokoj(this);
    }

    public void setZasady_gry(ZasadyGry zasady_gry) {
        this.zasady_gry = zasady_gry;
    }

    public ZasadyGry getZasady_gry() {
        return zasady_gry;
    }

    public void setPlansza(int[][] plansza) {
        this.plansza = plansza;
    }

    public int[][] getPlansza() {
        return plansza;
    }

    public int getId() {
        return id;
    }

    public void setMistrz(Gracz mistrz) {
        this.mistrz = mistrz;
    }
}
