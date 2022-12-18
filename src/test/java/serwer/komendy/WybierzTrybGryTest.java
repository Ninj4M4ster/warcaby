package serwer.komendy;

import serwer.dane.Gracz;
import serwer.dane.Pokoj;

public class WybierzTrybGryTest {
    public static void main(String args[]) {
        WybierzTrybGry wbg = new WybierzTrybGry();
        boolean prawda = wbg.Wykonaj("1", new Pokoj(new Gracz(1)));

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