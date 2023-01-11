package serwer.dane;

import kontroler.KontrolerDanych;
import serwer.komendy.zasady.ZasadyGry;

public class Pokoj {
    /**
     * Gracze bedacy w pokoju
     */
    private Gracz mistrz, gosc;
    private static int licznik = 0;
    private int id;

    /**
     * plansza - tablica zawierająca informacje o umiejscowieniu pionkow<br>
     * plansza[x][y]<br>
     *  x+<br>
     * y<br>
     * +<br>
     *  0 = puste<br>
     *  1 = bialy pion   3 = bialy krol<br>
     *  2 = czarne       4 = czrany krol
     */
    private int[][] plansza;

    /**
     * Zasady ktorymi pokoj bedzie sie poslugiwal by walidowac ruch
     */
    private ZasadyGry zasady_gry;

    /**
     * Kontroler sprawdzajacy stan gry
     */
    public KontrolerStanuGry kontroler_stanu_gry;

    public Pokoj(Gracz mistrz, ZasadyGry zasady_gry) {
        this.mistrz = mistrz;
        gosc = null;
        this.zasady_gry = zasady_gry;
        KontrolerDanych.getInstance().addPokoj(this);
        mistrz.setPokoj(this);
        kontroler_stanu_gry = new KontrolerStanuGry();
        id = licznik;
        licznik += 1;
    }

    public Pokoj(Gracz mistrz, Gracz gosc, ZasadyGry zasady_gry) {
        this.mistrz = mistrz;
        mistrz.setPokoj(this);
        this.gosc = gosc;
        gosc.setPokoj(this);
        this.zasady_gry = zasady_gry;
        KontrolerDanych.getInstance().addPokoj(this);
        id = licznik;
        licznik += 1;
        kontroler_stanu_gry = new KontrolerStanuGry();
    }

    public Pokoj(Gracz mistrz, Gracz gosc) {
        this.mistrz = mistrz;
        mistrz.setPokoj(this);
        this.gosc = gosc;
        gosc.setPokoj(this);
        KontrolerDanych.getInstance().addPokoj(this);
        id = licznik;
        licznik += 1;
        kontroler_stanu_gry = new KontrolerStanuGry();
    }

    public Pokoj(Gracz mistrz) {
        this.mistrz = mistrz;
        mistrz.setPokoj(this);
        KontrolerDanych.getInstance().addPokoj(this);
        id = licznik;
        licznik += 1;
        kontroler_stanu_gry = new KontrolerStanuGry();
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

    public void setZasadyGry(ZasadyGry zasady_gry) {
        this.zasady_gry = zasady_gry;
    }

    public ZasadyGry getZasadyGry() {
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

    /**
     * Metoda zmieniajaca plansze danego pokoju na jeden dlugi string (np. 01010101...202020)
     * @return
     */
    public String planszaToString() {
        StringBuilder plansza_temp = new StringBuilder();

        for(int y = 0; y < plansza.length; y += 1) {
            for(int x = 0; x < plansza.length; x += 1) {
                plansza_temp.append(plansza[x][y]);
            }
        }
        return plansza_temp.toString();
    }
}