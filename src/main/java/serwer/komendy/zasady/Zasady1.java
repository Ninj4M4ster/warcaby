package serwer.komendy.zasady;

public class Zasady1 extends ZasadyGry {
    public Zasady1(int[][] plansza) {
        super(plansza);
    }

    @Override
    public boolean bicie(int x, int y) {
        return false;
    }

    @Override
    public boolean promocja() {
        return false;
    }
}
