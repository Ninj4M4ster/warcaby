package serwer.komendy;

import serwer.komendy.WybierzTrybGry;
import serwer.komendy.zasady.ZasadyGry;

public class WybierzTrybGryTest {
    public static void main(String args[]) {
        WybierzTrybGry wbg = new WybierzTrybGry();
        ZasadyGry zg = wbg.wybierz("0");
        boolean prawda = wbg.Wykonaj();

        int[][] plansza;
        plansza = wbg.getPlansza();

        for(int y = 0; y < 8; y += 1) {
            for(int x = 0; x < 8; x += 1) {
                System.out.print(plansza[x][7 - y]);
                if(x == 7) {
                    System.out.print("\n");
                }
            }
        }
    }
}