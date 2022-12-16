package serwer.dane;

public class Pokoj {
    Gracz mistrz, gosc;
    public Pokoj(Gracz mistrz) {
        this.mistrz = mistrz;
        this.gosc = gosc;
    }

    public Gracz getMistrz() {
        return mistrz;
    }

    public Gracz getGosc() {
        return gosc;
    }
}
