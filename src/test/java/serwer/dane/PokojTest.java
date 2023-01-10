package serwer.dane;

import serwer.komendy.WybierzTrybGry;

public class PokojTest {
    public static void main(String args[]) {
        WybierzTrybGry wbg = new WybierzTrybGry();

        Pokoj pokoj = new Pokoj(new Gracz());
        pokoj.setZasadyGry(wbg.wybierz("1"));
        pokoj.setPlansza(wbg.tworzPlansze());

        System.out.println(pokoj.planszaToString());
    }
}
