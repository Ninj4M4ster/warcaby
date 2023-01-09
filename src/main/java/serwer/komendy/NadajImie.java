package serwer.komendy;

import kontroler.KontrolerDanych;
import serwer.dane.Gracz;
import serwer.dane.Pokoj;

public class NadajImie implements Komenda{
    Gracz gracz;

    protected NadajImie(Gracz gracz) {
        this.gracz = gracz;
    }
    @Override
    public String Wykonaj(String reszta, Pokoj pokoj) {
        for(Gracz gracz_temp : KontrolerDanych.getInstance().getGracze()) {
            if(gracz_temp.getNick() != null && reszta.compareTo(gracz_temp.getNick()) == 0) {
                return "false";
            }
        }
        gracz.setNick(reszta);

        for(Gracz gracz_temp : KontrolerDanych.getInstance().getGracze()) {
            if(gracz_temp.getNick() != null && reszta.compareTo(gracz_temp.getNick()) != 0) {
                gracz_temp.getSt().Wyslij("nowy_gracz " + reszta);
                gracz.getSt().Wyslij("nowy_gracz " + gracz_temp.getNick());
            }
        }
        System.out.println("Nadano imie ");
        return "true";
    }
}