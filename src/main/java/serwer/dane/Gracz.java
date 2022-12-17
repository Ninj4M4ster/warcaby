package serwer.dane;

public class Gracz {
    private int id_gracza;
    private Pokoj pokoj;

    public Gracz(int id_gracza) {
        this.id_gracza = id_gracza;
    }

    public void setPokoj(Pokoj pokoj) {
        this.pokoj = pokoj;
    }

    public Pokoj getPokoj() {
        return pokoj;
    }
}
