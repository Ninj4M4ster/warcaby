package serwer.komendy;

import serwer.SerwerThread;
import serwer.dane.Gracz;
import serwer.dane.Pokoj;

import java.net.Socket;

public class RuchPionkaTest {
    public static void main(String args[]) {
        WybierzTrybGry wbg = new WybierzTrybGry();

        Gracz gracz = new Gracz(1);
        gracz.setKolor(1);
        Pokoj pokoj = new Pokoj(gracz, new Gracz(2, new SerwerThread(new Socket(), 2)));
        pokoj.setZasadyGry(wbg.wybierz("2"));
        pokoj.setPlansza(wbg.tworzPlansze());
        pokoj.kontroler_stanu_gry.START();

        RuchPionka rp = new RuchPionka(gracz);
        System.out.println(rp.Wykonaj("0 4 1 1", pokoj));

        int[][] plansza = pokoj.getPlansza();
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
