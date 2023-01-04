package serwer.dane;

import serwer.SerwerThread;

public class Gracz {
    private String nick;
    private int id_gracza;
    private Pokoj pokoj;
    private SerwerThread st;
    private int kolor = 0;

    public Gracz(int id_gracza, SerwerThread st) {
        this.id_gracza = id_gracza;
        this.st = st;
    }

    public Gracz(int id_gracza) {
        this.id_gracza = id_gracza;
        st = null;
    }

    public void setPokoj(Pokoj pokoj) {
        this.pokoj = pokoj;
    }

    public Pokoj getPokoj() {
        return pokoj;
    }


    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public SerwerThread getSt() {
        return st;
    }

    public int getKolor() {
        return kolor;
    }

    public void setKolor(int kolor) {
        this.kolor = kolor;
    }
}