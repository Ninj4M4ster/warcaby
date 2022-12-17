package serwer.komendy.zasady;

public abstract class ZasadyGry {
    private int[][] plansza;

    public ZasadyGry(int[][] plansza) {
        this.plansza = plansza;
    }

    public void setPlansza(int[][] plansza) {
        this.plansza = plansza;
    }

    public int[][] getPlansza() {
        return plansza;
    }

    public boolean ruchPionem(int x, int y, int przesuniecie_x, int przesuniecie_y) {
        int kolor = plansza[x][y];
        if(x+przesuniecie_x < 0 || x + przesuniecie_x > plansza.length || y + przesuniecie_y < 0 || y + przesuniecie_y > plansza.length) {
            return false;
        }
        if(kolor == 0 || plansza[x+przesuniecie_x][y+przesuniecie_y] != 0 || Math.abs(przesuniecie_x) != Math.abs(przesuniecie_y)) {
            return false;
        }
        if(x+przesuniecie_x < 0 || x + przesuniecie_x > plansza.length || y + przesuniecie_y < 0 || y + przesuniecie_y > plansza.length) {
            return false;
        }
        if(plansza[x][y] == 1 && przesuniecie_y == 1) {
            return true;
        }
        else if(plansza[x][y] == 2 && przesuniecie_y == -1) {
            plansza[x][y] = 0;
            plansza[x+przesuniecie_x][y+przesuniecie_y] = 2;
            return true;
        }
        return false;
    }

    public boolean ruchKrolowa(int x, int y, int przesuniecie_x, int przesuniecie_y) {
        int kolor = plansza[x][y];
        if(x+przesuniecie_x < 0 || x + przesuniecie_x > plansza.length || y + przesuniecie_y < 0 || y + przesuniecie_y > plansza.length) {
            return false;
        }
        if(kolor == 0 || plansza[x+przesuniecie_x][y+przesuniecie_y] != 0 || Math.abs(przesuniecie_x) != Math.abs(przesuniecie_y)) {
            return false;
        }
        int licznik = 0;
        for(int i = 0; i < przesuniecie_x; i += 1) {
            if(plansza[x + (i * przesuniecie_x/Math.abs(przesuniecie_x))][y + (i * przesuniecie_y/Math.abs(przesuniecie_y))] != 0) {
                return false;
            }
        }
        return true;
    }

    abstract public boolean bicie(int x, int y);

    abstract public boolean promocja();
}
