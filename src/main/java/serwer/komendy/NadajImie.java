package serwer.komendy;

import serwer.KontrolerDanych;
import serwer.dane.Gracz;
import serwer.dane.Pokoj;

public class NadajImie implements Komenda{
    Gracz gracz;

    protected NadajImie(Gracz gracz) {
        this.gracz = gracz;
    }

    /**
     * Nadaje imie graczowi, sprawda czy nikt takiego imienia nie ma i rozsyla informacje do wszystkich ze jest nowy gracz
     * @param reszta - nick gracza
     * @param pokoj - pokoj w ktorym jest gracz wysylajacy komende
     * @return czy poprawnie wykonano komende
     */
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
                gracz_temp.getSt().wyslij("nowy_gracz " + reszta);
                gracz.getSt().wyslij("nowy_gracz " + gracz_temp.getNick());
            }
        }
        System.out.println("Nadano imie ");
        return "true";
    }
}