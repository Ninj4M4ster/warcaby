package serwer.dane;

import org.junit.Test;
import serwer.komendy.WybierzTrybGry;

public class PokojTest {
    @Test
    public void testPlanszaToString() {
        Gracz gracz = new Gracz();
        WybierzTrybGry wbg = new WybierzTrybGry(gracz);

        Pokoj pokoj = new Pokoj(gracz);
        pokoj.setZasadyGry(wbg.wybierz("0"));
        pokoj.setPlansza(wbg.tworzPlansze());

        System.out.println(pokoj.planszaToString());
    }
}
