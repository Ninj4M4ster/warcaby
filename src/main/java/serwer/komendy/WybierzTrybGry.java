package serwer.komendy;

import serwer.komendy.zasady.Zasady1;
import serwer.komendy.zasady.ZasadyGry;

public class WybierzTrybGry implements Komenda{
    private int plansza_rozmiar;
    /**
     * plansza - tablica zawierająca informacje o umiejscowieniu pionkow<br>
     * plansza[x][y]<br>
     *  x+<br>
     * y<br>
     * +<br>
     *  0 = puste<br>
     *  1 = biale<br>
     *  2 = biale
     */
    int[][] plansza;
    public ZasadyGry wybierz(String rodzaj) {
        switch (rodzaj) {
            case "0":
                return new Zasady1();
            default:
                return null;
        }
    }

    @Override
    public boolean Wykonaj() {
        //x, y
        plansza = new int[plansza_rozmiar][plansza_rozmiar];
        for(int x = 0; x < plansza_rozmiar; x += 1) {
            for(int y = 0; y < plansza_rozmiar; y += 1) {
                if(y != 3 && y !=4 && (x + y) % 2 == 1) {
                    if(y > 4) {
                        plansza[x][7 - y] = 1;
                    }
                    else {
                        plansza[x][7 - y] = 2;
                    }
                }
                else {
                    plansza[x][7 - y] = 0;
                }
            }
        }
        return true;
    }

    public int[][] getPlansza() {
        return plansza;
    }

    public void setPlansza_rozmiar(int plansza_rozmiar) {
        this.plansza_rozmiar = plansza_rozmiar;
    }
}
