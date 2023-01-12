package serwer.komendy;

import serwer.KontrolerDanych;
import serwer.dane.Gracz;
import serwer.dane.Pokoj;

public class ZaprosDoPokoju implements Komenda {
    Gracz gracz;

    public ZaprosDoPokoju(Gracz gracz) {
        this.gracz = gracz;
    }
    @Override
    public String Wykonaj(String reszta, Pokoj pokoj) {
        for(Gracz gracz_temp : KontrolerDanych.getInstance().getGracze()) {
            if(reszta.compareTo(gracz_temp.getNick()) == 0) {
                gracz_temp.getSt().wyslij("Zaproszenie " + gracz.getNick());
                new Pokoj(gracz);
                return "true";
            }
        }
        return "false";
    }
}
