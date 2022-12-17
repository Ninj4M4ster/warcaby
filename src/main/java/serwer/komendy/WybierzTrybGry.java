package serwer.komendy;

import serwer.komendy.zasady.Zasady1;
import serwer.komendy.zasady.ZasadyGry;

public class WybierzTrybGry implements Komenda{
    ZasadyGry zasady_gry;
    private int plansza_rozmiar;
    /**
     * plansza - tablica zawierająca informacje o umiejscowieniu pionkow<br>
     * plansza[x][y]<br>
     *  x+<br>
     * y<br>
     * +<br>
     *  0 = puste<br>
     *  1 = biale<br>
     *  2 = czarne
     */
    int[][] plansza;
    public ZasadyGry wybierz(String rodzaj) {
        switch (rodzaj) {
            case "0":
                setPlansza_rozmiar(8);
                zasady_gry = new Zasady1(plansza);
                return zasady_gry;
            case "1":
                setPlansza_rozmiar(10);
                zasady_gry = new Zasady1(plansza);
                return zasady_gry;
            case "2":
                setPlansza_rozmiar(12);
                zasady_gry = new Zasady1(plansza);
                return zasady_gry;
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
                if(y != ((plansza_rozmiar/2)-1) && y != (plansza_rozmiar/2) && (x + y) % 2 == 0) {
                    if(y < (plansza_rozmiar/2)) {
                        plansza[x][y] = 1;
                    }
                    else {
                        plansza[x][y] = 2;
                    }
                }
                else {
                    plansza[x][y] = 0;
                }
            }
        }
        return true;
    }

    public int[][] getPlansza() {
        return plansza;
    }

    private void setPlansza_rozmiar(int plansza_rozmiar) {
        this.plansza_rozmiar = plansza_rozmiar;
    }
}
