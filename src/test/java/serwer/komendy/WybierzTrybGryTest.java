package serwer.komendy;

import serwer.SerwerThread;
import serwer.dane.Gracz;
import serwer.dane.Pokoj;

import java.net.Socket;

public class WybierzTrybGryTest {
    public static void main(String args[]) {
        WybierzTrybGry wbg = new WybierzTrybGry();

        Gracz gracz1 = new Gracz(1);
        Gracz gracz2 = new Gracz(2);
        String prawda = wbg.Wykonaj("0", new Pokoj(gracz1, gracz2));

        int[][] plansza;
        plansza = wbg.getPlansza();

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