package serwer.komendy;

import kontroler.KontrolerDanych;
import serwer.Serwer;
import serwer.dane.Gracz;
import serwer.dane.Pokoj;

public class AkceptujZaproszenie implements Komenda {
    private Gracz gracz;

    public AkceptujZaproszenie(Gracz gracz) {
        this.gracz = gracz;
    }

    @Override
    public String Wykonaj(String reszta, Pokoj pokoj) {
        if(reszta.split(" ")[0].equals("Akceptuje")) {
            for(Gracz gracz_temp : KontrolerDanych.getInstance().getGracze()) {
                if(reszta.split(" ")[1].equals(gracz_temp.getNick())) {
                    gracz_temp.getSt().Wyslij("Zaakceptowano");
                    gracz_temp.getPokoj().setGosc(gracz);
                    gracz.setPokoj(gracz_temp.getPokoj());
                    return "true";
                }
            }
        }
        else {
            for(Gracz gracz_temp : KontrolerDanych.getInstance().getGracze()) {
                if(reszta.split(" ")[1].equals(gracz_temp.getNick())) {
                    gracz_temp.getSt().Wyslij("Odrzucono");
                    gracz_temp.getPokoj().setMistrz(null);
                    gracz_temp.setPokoj(null);
                    gracz.setPokoj(gracz_temp.getPokoj());
                    return "false";
                }
            }
        }
        return "false";
    }
}