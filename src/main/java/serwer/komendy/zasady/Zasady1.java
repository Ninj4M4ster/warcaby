package serwer.komendy.zasady;

import serwer.dane.KontrolerStanuGry;

public class Zasady1 extends ZasadyGry {
    boolean mozliweBicie() {
        int max_bicie = 0;
        if(stan_gry == KontrolerStanuGry.StanGry.RUCH_BIALYCH) {
            for(int x = 0; x < plansza.length; x += 1) {
                for(int y = 0; y < plansza.length; y += 1) {
                    if(plansza[x][y] == 1) {
                        biciePion(x, y, plansza);
                    }
                    else if(plansza[x][y] == 3) {

                    }
                }
            }
        }
        else if(stan_gry == KontrolerStanuGry.StanGry.RUCH_CZARNYCH) {

        }
        return false;
    }

    int biciePion(int x, int y, int[][] plansza) {
        int[][] plansza_temp = plansza;
        int pionek = plansza_temp[x][y];
        int max = 0;
        int temp;
        if(x+1 < plansza_temp.length && y+1 < plansza_temp.length && plansza_temp[x+1][y+1] != 0 && plansza_temp[x+1][y+1] != pionek && plansza_temp[x+1][y+1] != pionek+2) {
            if(x+2 < plansza_temp.length && y+2 < plansza_temp.length && plansza_temp[x+2][y+2] == 0) {
                plansza_temp[x+1][y+1] = 0;
                plansza_temp[x][y] = 0;
                plansza_temp[x+2][y+2] = pionek;
                max = ((temp = biciePion(x+2, y+2, plansza_temp)) > max ? temp : max);
            }
        }
        plansza_temp = plansza;
        if(x-1 < plansza_temp.length && y+1 < plansza_temp.length && plansza_temp[x-1][y+1] != 0 && plansza_temp[x-1][y+1] != pionek && plansza_temp[x-1][y+1] != pionek+2) {
            if(x-2 < plansza_temp.length && y+2 < plansza_temp.length && plansza_temp[x-2][y+2] == 0) {
                plansza_temp[x-1][y+1] = 0;
                plansza_temp[x][y] = 0;
                plansza_temp[x-2][y+2] = pionek;
                max = ((temp = biciePion(x-2, y+2, plansza_temp)) > max ? temp : max);
            }
        }
        plansza_temp = plansza;
        if(x+1 < plansza_temp.length && y-1 < plansza_temp.length && plansza_temp[x+1][y-1] != 0 && plansza_temp[x+1][y-1] != pionek && plansza_temp[x+1][y-1] != pionek+2) {
            if(x+2 < plansza_temp.length && y-2 < plansza_temp.length && plansza_temp[x+2][y-2] == 0) {
                plansza_temp[x+1][y-1] = 0;
                plansza_temp[x][y] = 0;
                plansza_temp[x+2][y-2] = pionek;
                max = ((temp = biciePion(x+2, y-2, plansza_temp)) > max ? temp : max);
            }
        }
        plansza_temp = plansza;
        if(x-1 < plansza_temp.length && y-1 < plansza_temp.length && plansza_temp[x-1][y-1] != 0 && plansza_temp[x-1][y-1] != pionek && plansza_temp[x-1][y-1] != pionek+2) {
            if(x-2 < plansza_temp.length && y-2 < plansza_temp.length && plansza_temp[x-2][y-2] == 0) {
                plansza_temp[x-1][y-1] = 0;
                plansza_temp[x][y] = 0;
                plansza_temp[x-2][y-2] = pionek;
                max = ((temp = biciePion(x-2, y-2, plansza_temp)) > max ? temp : max);
            }
        }
        return max;
    }

    boolean dlugoscBicia(int x, int nowy_x) {
        if(pionek < 3 && Math.abs(nowy_x - x) == 2) {
            return true;
        }
        if(pionek >= 3 && Math.abs(nowy_x - x) >= 2) {
            return true;
        }
        return false;
    }
}