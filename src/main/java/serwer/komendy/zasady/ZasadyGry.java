package serwer.komendy.zasady;

import serwer.dane.Gracz;
import serwer.dane.KontrolerStanuGry;

import java.util.ArrayList;

public abstract class ZasadyGry {
    int[][] plansza;
    ArrayList<Integer> ruchy;
    int pionek;

    KontrolerStanuGry.StanGry stan_gry;
    Gracz gracz;

    boolean czyWToku() {
        if(stan_gry == KontrolerStanuGry.StanGry.RUCH_BIALYCH || stan_gry == KontrolerStanuGry.StanGry.RUCH_CZARNYCH) {
            return true;
        }
        return false;
    }

    boolean czyKolor() {
        if((pionek == 1 || pionek == 3) && stan_gry == KontrolerStanuGry.StanGry.RUCH_BIALYCH) {
            return true;
        }
        if((pionek == 2 || pionek == 4) && stan_gry == KontrolerStanuGry.StanGry.RUCH_CZARNYCH) {
            return true;
        }
        return false;
    }

    private boolean ruchPionem() {
        if(gracz.getKolor() != pionek) {
            return false;
        }
        if(pionek == 0 || plansza[x+przesuniecie_x][y+przesuniecie_y] != 0 || Math.abs(przesuniecie_x) != Math.abs(przesuniecie_y)) {
            return false;
        }
        if((pionek == 1 && stan_gry == KontrolerStanuGry.StanGry.RUCH_CZARNYCH) || (pionek == 2 && stan_gry == KontrolerStanuGry.StanGry.RUCH_BIALYCH)) {
            return false;
        }
        if(x+przesuniecie_x < 0 || x + przesuniecie_x > plansza.length || y + przesuniecie_y < 0 || y + przesuniecie_y > plansza.length) {
            return false;
        }
        if(pionek == 1 && przesuniecie_y == 1) {
            return true;
        }
        else if(pionek == 2 && przesuniecie_y == -1) {
            plansza[x][y] = 0;
            plansza[x+przesuniecie_x][y+przesuniecie_y] = 2;
            return true;
        }
        return false;
    }

    private boolean ruchKrol() {
        if(gracz.getKolor() != pionek) {
            return false;
        }
        if(pionek == 0 || plansza[x+przesuniecie_x][y+przesuniecie_y] != 0 || Math.abs(przesuniecie_x) != Math.abs(przesuniecie_y)) {
            return false;
        }
        if(stan_gry == KontrolerStanuGry.StanGry.NIEROZPOCZETA || stan_gry == KontrolerStanuGry.StanGry.SKONCZONA || stan_gry == KontrolerStanuGry.StanGry.PRZERWANA || (pionek == 1 && stan_gry == KontrolerStanuGry.StanGry.RUCH_CZARNYCH) || (pionek == 2 && stan_gry == KontrolerStanuGry.StanGry.RUCH_BIALYCH)) {
            return false;
        }
        if(x+przesuniecie_x < 0 || x + przesuniecie_x > plansza.length || y + przesuniecie_y < 0 || y + przesuniecie_y > plansza.length) {
            return false;
        }
        int licznik = 0;
        for(int i = 0; i < przesuniecie_x; i += 1) {
            if(plansza[x + (i * przesuniecie_x/Math.abs(przesuniecie_x))][y + (i * przesuniecie_y/Math.abs(przesuniecie_y))] != 0) {
                licznik += 1;
            }
            if(licznik > 1) {
                return false;
            }
        }
        return true;
    }

    boolean promocja(int x, int y) {
        if((plansza[x][y] == 1 && y == 7) || (plansza[x][y] == 2 && y == 0)) {
            return true;
        }
        return false;
    }

    public boolean sprawdz(int[] ruchy) {
        if(!czyWToku() || !czyKolor()) {
            return false;
        }
        return false;
    }

    boolean dlugoscBicia(int x, int y, int nowy_x, int nowy_y) {
        if(pionek < 3 && Math.abs(nowy_x - x) == 2 && Math.abs(nowy_y - y) == 2) {
            return true;
        }
        if(pionek >= 3 && Math.abs(nowy_x - x) >= 2 && Math.abs(nowy_x - x) == Math.abs(nowy_y - y) ) {
            return true;
        }
        return false;
    }

    boolean czyWPlanszy(int x, int y) {
        if(x < plansza.length && x >= 0 && y < plansza.length && y >= 0) {
            return true;
        }
        return false;
    }

    public void setPlansza(int[][] plansza) {
        this.plansza = plansza;
    }

    public void setRuchy(ArrayList<Integer> ruchy) {
        this.ruchy = ruchy;
        pionek = plansza[ruchy.get(0)][ruchy.get(1)];
    }

    public void setStanGry(KontrolerStanuGry.StanGry stan_gry) {
        this.stan_gry = stan_gry;
    }

    public void setGracz(Gracz gracz) {
        this.gracz = gracz;
    }
}