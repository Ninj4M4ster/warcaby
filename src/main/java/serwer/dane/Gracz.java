package serwer.dane;

public class Gracz {
    private int id_gracza;
    /**
     * Kolor:
     * 0=czarne;
     * 1=białe;
     */
    private boolean kolor;

    public Gracz(int id_gracza, boolean kolor) {
        this.id_gracza = id_gracza;
        this.kolor = kolor;
    }

    boolean getKolor() {
        return kolor;
    }
}
