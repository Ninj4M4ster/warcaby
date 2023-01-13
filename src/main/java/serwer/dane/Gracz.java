package serwer.dane;

import kontrolerDanych.KontrolerDanych;
import serwer.SerwerThread;

public class Gracz {
    /**
     * Nazwa i identyfikator gracza
     */
    private String nick;
    /**
     * Pokoj w ktorym gra gracz
     */
    private Pokoj pokoj;
    /**
     * Watek serwera obslugujacy gracza
     */
    private SerwerThread st;
    /**
     * kolor bierek gracz
     */
    private int kolor = 0;

    /**
     * Konstruktor gracza
     * @param st - watek serwera obslugujacy gracza
     */
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