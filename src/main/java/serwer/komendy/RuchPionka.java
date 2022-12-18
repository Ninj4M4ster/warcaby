package serwer.komendy;

import serwer.dane.Pokoj;

public class RuchPionka implements Komenda{
    int x, y, przesuniecie_x, przesuniecie_y;
    int[][] plansza;

    @Override
    public boolean Wykonaj(String reszta, Pokoj pokoj) {
        plansza = pokoj.getPlansza();

        try {
            x = Integer.parseInt(reszta.split(" ")[0]);
            y = Integer.parseInt(reszta.split(" ")[1]);
            przesuniecie_x = Integer.parseInt(reszta.split(" ")[2]);
            przesuniecie_y = Integer.parseInt(reszta.split(" ")[3]);
        } catch(NumberFormatException nfe) {
            return false;
        }
        pokoj.getZasady_gry().setPlansza(plansza);
        if(pokoj.getZasady_gry().ruchPionem(x, y, przesuniecie_x, przesuniecie_y) || pokoj.getZasady_gry().ruchKrolowa(x, y, przesuniecie_x, przesuniecie_y)) {      // || zasady_gry.bicie()
            int pionek = plansza[x][y];
            plansza[x][y] = 0;
            plansza[x + przesuniecie_x][y + przesuniecie_y] = pionek;
            pokoj.getZasady_gry().setPlansza(plansza);
            return true;
        }

        return false;
    }
}
