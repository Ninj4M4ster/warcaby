package serwer.komendy;

import serwer.komendy.zasady.ZasadyGry;

public class RuchPionka implements Komenda{

    ZasadyGry zasady_gry;
    int x, y, przesuniecie_x, przesuniecie_y;
    int[][] plansza;

    public RuchPionka(ZasadyGry zasady_gry, int[][] plansza, int x, int y, int przesuniecie_x, int przesuniecie_y) {
        this.zasady_gry = zasady_gry;
        this.plansza = plansza;
        this.x = x;
        this.y = y;
        this.przesuniecie_x = przesuniecie_x;
        this.przesuniecie_y = przesuniecie_y;
    }

    @Override
    public boolean Wykonaj() {
        if(zasady_gry.ruchPionem(x, y, przesuniecie_x, przesuniecie_y) || zasady_gry.ruchKrolowa(x, y, przesuniecie_x, przesuniecie_y)) {      // || zasady_gry.bicie()
            int pionek = plansza[x][y];
            plansza[x][y] = 0;
            plansza[x + przesuniecie_x][y + przesuniecie_y] = pionek;
            zasady_gry.setPlansza(plansza);
            return true;
        }
        return false;
    }
}
