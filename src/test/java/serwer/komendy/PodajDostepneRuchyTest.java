package serwer.komendy;

import org.junit.Test;
import serwer.dane.Gracz;
import serwer.dane.Pokoj;

import static java.lang.Math.sqrt;
import static org.junit.Assert.assertEquals;

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
        String wynik = "";
        for(String plansza2 : pdr.planszePoRuchach(pokoj.getPlansza()).split(" ")) {
            System.out.println(plansza2);
            wynik += plansza2;
            wynik += "\n";
        }

        int[][] plansza10 = stringToPlansza("1010101001010101101010000000010000000000020202022020202002020202");
        for(int y = plansza10.length - 1; y >= 0; y -= 1) {
            for(int x = 0; x < plansza10.length; x += 1) {
                System.out.print(plansza10[x][y]);
                if(x == plansza10.length - 1) {
                    System.out.print("\n");
                }
            }
        }
        assertEquals("0 2 1 3\n2 2 3 3\n2 2 1 3\n4 2 5 3\n4 2 3 3\n6 2 7 3\n6 2 5 3\n", wynik);
    }

    private int[][] stringToPlansza(String plansza) {
        int dlugosc = (int) sqrt(plansza.length());
        int[][] plansza2 = new int[dlugosc][dlugosc];
        for(int y = 0; y < dlugosc; y += 1) {
            for(int x = 0; x < dlugosc; x += 1) {
                plansza2[x][y] = Integer.parseInt(String.valueOf(plansza.charAt(y * dlugosc + x)));
            }
        }
        return plansza2;
    }
}
