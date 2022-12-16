package serwer.komendy.zasady;

public abstract class ZasadyGry {
    abstract boolean ruchPionem(int x, int y);

    abstract boolean ruchKrolowa(int x, int y);

    abstract boolean bicie(int x, int y);

    abstract boolean promocja();
}
