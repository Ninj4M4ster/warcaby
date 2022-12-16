package serwer.komendy.zasady;

public class Zasady1 extends ZasadyGry {
    @Override
    boolean ruchPionem(int x, int y) {
        return false;
    }

    @Override
    public boolean ruchKrolowa(int x, int y) {
        return false;
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
