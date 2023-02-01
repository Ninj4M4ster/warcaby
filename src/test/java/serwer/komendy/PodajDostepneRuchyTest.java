package serwer.komendy;

import org.junit.Test;
import serwer.dane.Gracz;
import serwer.dane.Pokoj;

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
        for(PodajDostepneRuchy.Ruch ruch : pdr.wypiszRuchy(plansza)) {
            System.out.println(ruch.getRuch());
        }

        for(int y = plansza.length - 1; y >= 0; y -= 1) {
            for(int x = 0; x < plansza.length; x += 1) {
                System.out.print(plansza[x][y]);
                if(x == plansza.length - 1) {
                    System.out.print("\n");
                }
            }
        }
    }
}
