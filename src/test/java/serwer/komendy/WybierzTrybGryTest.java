package serwer.komendy;

import serwer.komendy.WybierzTrybGry;
import serwer.komendy.zasady.ZasadyGry;

public class WybierzTrybGryTest {
    public static void main(String args[]) {
        WybierzTrybGry wbg = new WybierzTrybGry();
        ZasadyGry zg = wbg.wybierz("1");
        boolean prawda = wbg.Wykonaj();

        int[][] plansza;
        plansza = wbg.getPlansza();

        for(int y = 0; y < 10; y += 1) {
            for(int x = 0; x < 10; x += 1) {
                System.out.print(plansza[x][y]);
                if(x == 9) {
                    System.out.print("\n");
                }
            }
        }
    }
}