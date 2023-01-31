package serwer.komendy;

import serwer.dane.Gracz;
import serwer.dane.KontrolerStanuGry;
import serwer.dane.Pokoj;
import serwer.komendy.zasady.ZasadyGry;

import java.util.ArrayList;
import java.util.List;

public class PodajDostepneRuchy extends ZasadyGry implements Komenda {
    Gracz gracz;

    List<Ruch> bez_bicia = new ArrayList<Ruch>();
    List<Ruch> bicia = new ArrayList<Ruch>();

    public PodajDostepneRuchy(Gracz gracz) {
        this.gracz = gracz;
    }

    class Ruch implements Comparable<Ruch> {
        private int ilosc_bic = 0;
        private String ruch = "";

        void addRuch(int x, int y) {
            if(ruch.length() == 0) {
                this.ruch.concat(x + " " + y);
            }
            else {
                this.ruch.concat(" " + x + " " + y);
            }
        }

        public Ruch kopiujRuch() {
            Ruch ruch_temp = new Ruch();
            ruch_temp.ruch = this.ruch;
            ruch_temp.ilosc_bic = this.ilosc_bic;
            return ruch_temp;
        }

        public Ruch kopiujRuch(int x, int y) {
            Ruch ruch_temp = new Ruch();
            ruch_temp.ruch = this.ruch;
            ruch_temp.addRuch(x, y);
            ruch_temp.ilosc_bic = this.ilosc_bic;
            return ruch_temp;
        }

        @Override
        public int compareTo(Ruch o) {
            return this.ilosc_bic - o.ilosc_bic;
        }

        public int getIlosc_bic() {
            return ilosc_bic;
        }

        public String getRuch() {
            return ruch;
        }
    }
    @Override
    public String Wykonaj(String reszta, Pokoj pokoj) {
        int[][] plansza = kopiuj(pokoj.getPlansza());
        if(pokoj.kontroler_stanu_gry.getStan() == KontrolerStanuGry.StanGry.RUCH_BIALYCH) {
            for (int x = 0; x < plansza.length; x += 1) {
                for (int y = 0; y < plansza.length; y += 1) {
                    if(plansza[x][y] % 2 == 1) {
                        sprawdzRuchy(plansza, x, y);
                    }
                }
            }
        }
        else if (pokoj.kontroler_stanu_gry.getStan() == KontrolerStanuGry.StanGry.RUCH_CZARNYCH) {
            for (int x = 0; x < pokoj.getPlansza().length; x += 1) {
                for (int y = 0; y < pokoj.getPlansza().length; y += 1) {
                    if(plansza[x][y] != 0 && plansza[x][y] % 2 == 0) {
                        sprawdzRuchy(plansza, x, y);
                    }
                }
            }
        }
        return "true";
    }

    private void sprawdzRuchy(int[][] plansza_do_kopii, int x, int y) {
        int pionek = plansza_do_kopii[x][y];
        int[][] plansza = kopiuj(plansza_do_kopii);

        if(czyWPlanszy(x + 1, y + 1)) {

        }
        if(czyWPlanszy(x - 1, y + 1)) {

        }
        if(czyWPlanszy(x + 1, y - 1)) {

        }
        if(czyWPlanszy(x - 1, y - 1)) {

        }
    }

    private void sprawdzRuchy(int[][] plansza_do_kopii, int x, int y, Ruch ruch) {
        int pionek = plansza_do_kopii[x][y];
        int[][] plansza = kopiuj(plansza_do_kopii);

        if(czyWPlanszy(x + 1, y + 1)) {

        }
        if(czyWPlanszy(x - 1, y + 1)) {

        }
        if(czyWPlanszy(x + 1, y - 1)) {

        }
        if(czyWPlanszy(x - 1, y - 1)) {

        }
    }

    private int[][] kopiuj(int[][] plansza_do_kopii) {
        int[][] plansza = new int[plansza_do_kopii.length][plansza_do_kopii.length];
        for (int x = 0; x < plansza_do_kopii.length; x += 1) {
            for (int y = 0; y < plansza_do_kopii.length; y += 1) {
                plansza[x][y] = plansza_do_kopii[x][y];
            }
        }
        return plansza;
    }
}