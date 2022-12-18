package serwer.komendy;

import serwer.dane.Gracz;
import serwer.dane.Pokoj;

public class WybierzTrybGryTest {
    public static void main(String args[]) {
        WybierzTrybGry wbg = new WybierzTrybGry();
        boolean prawda = wbg.Wykonaj("2", new Pokoj(new Gracz(1)));

        int[][] plansza;
        plansza = wbg.getPlansza();

        for(int y = 0; y < plansza.length; y += 1) {
            for(int x = 0; x < plansza.length; x += 1) {
                System.out.print(plansza[x][y]);
                if(x == plansza.length - 1) {
                    System.out.print("\n");
                }
            }
        }
    }
}