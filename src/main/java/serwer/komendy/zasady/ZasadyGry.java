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

    public boolean sprawdz() {
        if(!czyWToku() || !czyKolor()) {
            return false;
        }
        if(sprawdzBicie()) {
            return true;
        }
        if((pionek < 3 && ruchPionem()) || (pionek > 2 && ruchKrol())) {
            return true;
        }
        return false;
    }

    boolean czyWToku() {
        if(stan_gry == KontrolerStanuGry.StanGry.RUCH_BIALYCH || stan_gry == KontrolerStanuGry.StanGry.RUCH_CZARNYCH) {
            return true;
        }
        return false;
    }

    boolean czyKolor() {
        if((pionek == 1 || pionek == 3) && stan_gry == KontrolerStanuGry.StanGry.RUCH_BIALYCH && pionek % 2 == gracz.getKolor()) {
            return true;
        }
        if((pionek == 2 || pionek == 4) && stan_gry == KontrolerStanuGry.StanGry.RUCH_CZARNYCH && pionek % 2 == gracz.getKolor()) {
            return true;
        }
        return false;
    }

    boolean ruchPionem() {
        if(czyWPlanszy(ruchy.get(2), ruchy.get(3))) {
            return false;
        }
        if(pionek == 0 || plansza[ruchy.get(2)][ruchy.get(3)] != 0 || Math.abs(ruchy.get(2) - ruchy.get(0)) != 1) {
            return false;
        }
        if(pionek == 1 && ruchy.get(3) - ruchy.get(1) != 1) {
            return false;
        }
        else if(pionek == 2 && ruchy.get(3) - ruchy.get(1) != -1) {
            return false;
        }
        return true;
    }

    boolean ruchKrol() {
        if(czyWPlanszy(ruchy.get(2), ruchy.get(3))) {
            return false;
        }
        if(pionek == 0 || plansza[ruchy.get(2)][ruchy.get(3)] != 0 || Math.abs(ruchy.get(2) - ruchy.get(0)) != Math.abs(ruchy.get(3) - ruchy.get(1))) {
            return false;
        }

        for(int i = 1; i < Math.abs(ruchy.get(2) - ruchy.get(0)) - 1; i += 1) {
            if(plansza[ruchy.get(0) + ((ruchy.get(2) - ruchy.get(0))/Math.abs(ruchy.get(2) - ruchy.get(0)) * i)][ruchy.get(1) + ((ruchy.get(3) - ruchy.get(1))/Math.abs(ruchy.get(3) - ruchy.get(1)) * i)] != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean promocja(int x, int y) {
        if((plansza[x][y] == 1 && y == 7) || (plansza[x][y] == 2 && y == 0)) {
            return true;
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

    abstract boolean sprawdzBicie();
}