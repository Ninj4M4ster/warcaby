package serwer.komendy.zasady;

import serwer.dane.Gracz;
import serwer.dane.KontrolerStanuGry;
import serwer.dane.Pokoj;

import java.util.ArrayList;

public abstract class ZasadyGry {
    int[][] plansza;
    ArrayList<Integer> ruchy;
    int pionek;
    Pokoj pokoj;

    KontrolerStanuGry.StanGry stan_gry;
    Gracz gracz;

    public boolean sprawdz() {
        if(!czyWToku() || !czyKolor()) {
            return false;
        }
        if(sprawdzBicie()) {
            return true;
        }
        return (pionek < 3 && ruchPionem()) || (pionek > 2 && ruchKrol());
    }

    boolean czyWToku() {
        return stan_gry == KontrolerStanuGry.StanGry.RUCH_BIALYCH || stan_gry == KontrolerStanuGry.StanGry.RUCH_CZARNYCH;
    }

    boolean czyKolor() {
        if((pionek == 1 || pionek == 3) && stan_gry == KontrolerStanuGry.StanGry.RUCH_BIALYCH && 1 == gracz.getKolor()) {
            return true;
        }
        return (pionek == 2 || pionek == 4) && stan_gry == KontrolerStanuGry.StanGry.RUCH_CZARNYCH && 2 == gracz.getKolor();
    }

    boolean ruchPionem() {
        if(!czyWPlanszy(ruchy.get(2), ruchy.get(3)) || !czyWPlanszy(ruchy.get(0), ruchy.get(1))) {
            return false;
        }
        if(pionek == 0 || plansza[ruchy.get(2)][ruchy.get(3)] != 0 || Math.abs(ruchy.get(2) - ruchy.get(0)) != 1) {
            return false;
        }
        if(pionek == 1 && ruchy.get(3) - ruchy.get(1) != 1) {
            return false;
        }
        else return !(pionek == 2 && ruchy.get(3) - ruchy.get(1) != -1);
    }

    boolean ruchKrol() {
        if(!czyWPlanszy(ruchy.get(2), ruchy.get(3))) {
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
        return (plansza[x][y] == 1 && y == plansza.length - 1) || (plansza[x][y] == 2 && y == 0);
    }

    public int czyWygrana() {
        int biale = 0, czarne = 0;
        for(int x = 0; x < plansza.length; x += 1) {
            for(int y = 0; y < plansza.length; y += 1) {
                if(plansza[x][y] % 2 == 1) {
                    biale += 1;
                }
                else if(plansza[x][y] % 2 == 0 && plansza[x][y] != 0) {
                    czarne += 1;
                }
            }
        }
        if(biale == 0) {
            return 1;
        }
        if(czarne == 0) {
            return 2;
        }
        return 0;
    }

    boolean dlugoscBicia(int x, int y, int nowy_x, int nowy_y) {
        if(pionek < 3 && Math.abs(nowy_x - x) == 2 && Math.abs(nowy_y - y) == 2) {
            return true;
        }
        return pionek >= 3 && Math.abs(nowy_x - x) >= 2 && Math.abs(nowy_x - x) == Math.abs(nowy_y - y);
    }

    boolean czyWPlanszy(int x, int y) {
        return x < plansza.length && x >= 0 && y < plansza.length && y >= 0;
    }

    public void setPlansza(int[][] plansza) {
        this.plansza = new int[plansza.length][plansza.length];
        for(int x = 0; x < plansza.length; x +=1) {
            for(int y = 0; y < plansza.length; y += 1) {
                this.plansza[x][y] = plansza[x][y];
            }
        }
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

    public void setPokoj(Pokoj pokoj) {
        this.pokoj = pokoj;
    }
}