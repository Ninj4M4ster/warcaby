package serwer.komendy;

import org.junit.Test;
import serwer.dane.Gracz;
import serwer.dane.Pokoj;

import static org.junit.Assert.assertEquals;

public class PodajDostepneRuchyTest {
    @Test
    public void testPodajDostepneRuchy() {
        Gracz gracz = new Gracz();
        PodajDostepneRuchy pdr = new PodajDostepneRuchy(gracz);
        WybierzTrybGry wtg = new WybierzTrybGry(gracz);
        Pokoj pokoj = new Pokoj(gracz, wtg.wybierz("0"));
        int[][] plansza = wtg.tworzPlansze();
        pokoj.setPlansza(plansza);
        pokoj.kontroler_stanu_gry.START();
        pdr.pokoj = pokoj;
        String wynik = "";
        for(PodajDostepneRuchy.Ruch ruch : pdr.wypiszRuchy(plansza)) {
            System.out.println(ruch.getRuch());
            wynik += ruch.getRuch();
            wynik += "\n";
        }

        assertEquals("0 2 1 3\n2 2 3 3\n2 2 1 3\n4 2 5 3\n4 2 3 3\n6 2 7 3\n6 2 5 3\n", wynik);
    }
}
