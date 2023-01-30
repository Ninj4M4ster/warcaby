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

        @Override
        public int compareTo(Ruch o) {
            return this.ilosc_bic - o.ilosc_bic;
        }
    }
    @Override
    public String Wykonaj(String reszta, Pokoj pokoj) {
        if(pokoj.kontroler_stanu_gry.getStan() == KontrolerStanuGry.StanGry.RUCH_BIALYCH) {
            for (int x = 0; x < pokoj.getPlansza().length; x += 1) {
                for (int y = 0; y < pokoj.getPlansza().length; y += 1) {

                }
            }
        }
        else if (pokoj.kontroler_stanu_gry.getStan() == KontrolerStanuGry.StanGry.RUCH_CZARNYCH) {
            for (int x = 0; x < pokoj.getPlansza().length; x += 1) {
                for (int y = 0; y < pokoj.getPlansza().length; y += 1) {

                }
            }
        }
        return "true";
    }

    private void sprawdzRuchy(int[][] plansza_do_kopii) {

    }

    int[][] kopiuj(int[][] plansza_do_kopii) {
        int length = plansza_do_kopii.length;
        int[][] plansza = new int[length][length];
        for (int x = 0; x < length; x += 1) {
            for (int y = 0; y < pokoj.getPlansza().length; y += 1) {

            }
        }
    }
}