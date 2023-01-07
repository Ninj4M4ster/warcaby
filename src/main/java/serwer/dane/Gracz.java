package serwer.dane;

import kontroler.KontrolerDanych;
import serwer.SerwerThread;

public class Gracz {
    private String nick;
    private Pokoj pokoj;
    private SerwerThread st;
    private int kolor = 0;

    public Gracz(SerwerThread st) {
        this.st = st;
        KontrolerDanych.getInstance().addGracz(this);
    }

    public Gracz() {
        st = null;
        KontrolerDanych.getInstance().addGracz(this);
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