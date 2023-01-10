package serwer.komendy;

import serwer.SerwerThread;
import serwer.dane.Gracz;
import serwer.dane.Pokoj;

import java.net.Socket;

public class WybierzTrybGryTest {
    public static void main(String args[]) {
        WybierzTrybGry wbg = new WybierzTrybGry();

        Pokoj pokoj = new Pokoj(new Gracz());
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