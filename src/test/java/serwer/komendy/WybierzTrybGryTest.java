package serwer.komendy;

import org.junit.Test;
import serwer.dane.Gracz;
import serwer.dane.Pokoj;

public class WybierzTrybGryTest {
    @Test
    public void testWybierzTrybGry() {
        Gracz gracz = new Gracz();
        WybierzTrybGry wbg = new WybierzTrybGry(gracz);

        Pokoj pokoj = new Pokoj(gracz);
        pokoj.setZasadyGry(wbg.wybierz("0"));

        int[][] plansza;
        plansza = wbg.tworzPlansze();

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