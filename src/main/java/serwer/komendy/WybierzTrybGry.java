package serwer.komendy;

import serwer.dane.Pokoj;
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
     *  2 = czarne
     */
    private int[][] plansza;

    public ZasadyGry wybierz(String rodzaj) {
        switch (rodzaj) {
            case "0":
                plansza_rozmiar = 8;
                return new Zasady1();
            case "1":
                plansza_rozmiar = 10;
                return new Zasady1();
            case "2":
                plansza_rozmiar = 12;
                return new Zasady1();
            default:
                return null;
        }
    }

    @Override
    public String Wykonaj(String reszta, Pokoj pokoj) {
        if(pokoj != null) {
            pokoj.setZasadyGry(wybierz(reszta));

            plansza = tworzPlansze();

            pokoj.setPlansza(plansza);

            pokoj.getMistrz().setKolor((int) (Math.random() * (2 - 1) + 1));
            pokoj.getGosc().setKolor(3 - pokoj.getMistrz().getKolor());

            pokoj.getGosc().getSt().Wyslij("plansza " + String.valueOf(pokoj.getGosc().getKolor()) + pokoj.planszaToString());
            pokoj.kontroler_stanu_gry.START();

            return "true " + String.valueOf(pokoj.getMistrz().getKolor()) + pokoj.planszaToString();
        }
        return "false";
    }

    public int[][] tworzPlansze() {
        int[][] plansza_temp = new int[plansza_rozmiar][plansza_rozmiar];

        for (int x = 0; x < plansza_rozmiar; x += 1) {
            for (int y = 0; y < plansza_rozmiar; y += 1) {
                if (y != ((plansza_rozmiar / 2) - 1) && y != (plansza_rozmiar / 2) && (x + y) % 2 == 0) {
                    if (y < (plansza_rozmiar / 2)) {
                        plansza_temp[x][y] = 1;
                    } else {
                        plansza_temp[x][y] = 2;
                    }
                } else {
                    plansza_temp[x][y] = 0;
                }
            }
        }
        return plansza_temp;
    }

    public int[][] getPlansza() {
        return plansza;
    }
}