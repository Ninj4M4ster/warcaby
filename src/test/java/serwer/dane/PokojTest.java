package serwer.dane;

import serwer.komendy.WybierzTrybGry;

public class PokojTest {
    public static void main(String args[]) {
        Gracz gracz = new Gracz();
        WybierzTrybGry wbg = new WybierzTrybGry(gracz);

        Pokoj pokoj = new Pokoj(gracz);
        pokoj.setZasadyGry(wbg.wybierz("1"));
        pokoj.setPlansza(wbg.tworzPlansze());

        System.out.println(pokoj.planszaToString());
    }
}
