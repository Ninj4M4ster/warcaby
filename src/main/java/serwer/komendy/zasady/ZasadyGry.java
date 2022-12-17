package serwer.komendy.zasady;

public abstract class ZasadyGry {
    int[][] plansza;

    public ZasadyGry(int[][] plansza) {
        this.plansza = plansza;
    }

    private boolean ruchPionem(int x, int y, int przesuniecie_x, int przesuniecie_y) {
        int kolor = plansza[x][y];
        if(kolor == 0 || plansza[x+przesuniecie_x][y+przesuniecie_y] != 0 || (przesuniecie_x != przesuniecie_y && przesuniecie_y != -przesuniecie_x)) {
            return false;
        }
        else if(plansza[x][y] == 1 && przesuniecie_y == -1) {
            plansza[x][y] = 0;
            plansza[x+przesuniecie_x][y+przesuniecie_y] = 1;
            return true;
        }
        else if(plansza[x][y] == 2 && przesuniecie_y == 1) {
            plansza[x][y] = 0;
            plansza[x+przesuniecie_x][y+przesuniecie_y] = 1;
            return true;
        }
        return false;
    }

    abstract boolean ruchKrolowa(int x, int y);

    abstract boolean bicie(int x, int y);

    abstract boolean promocja();
}
