package serwer.komendy;

import serwer.komendy.WybierzTrybGry;

public class WybierzTrybGryTest {
    public static void main(String args[]) {
        WybierzTrybGry wbg = new WybierzTrybGry();
        wbg.setPlansza_rozmiar(8);
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