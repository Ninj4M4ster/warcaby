package serwer.komendy.zasady;

public class Zasady1 implements ZasadyGry{
    @Override
    public boolean ruchPionem() {
        return false;
    }

    @Override
    public boolean ruchKrolowa() {
        return false;
    }

    @Override
    public boolean bicie() {
        return false;
    }

    @Override
    public boolean promocja() {
        return false;
    }
}
