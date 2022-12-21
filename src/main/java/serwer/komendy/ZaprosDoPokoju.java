package serwer.komendy;

import serwer.Serwer;
import serwer.dane.Gracz;
import serwer.dane.Pokoj;

public class ZaprosDoPokoju implements Komenda {
    Gracz gracz;
    String reszta;

    public ZaprosDoPokoju(Gracz gracz) {
        this.gracz = gracz;
    }
    @Override
    public String Wykonaj(String reszta, Pokoj pokoj) {
        for(Gracz gracz_temp : Serwer.getGracze()) {
            if(reszta.equals(gracz_temp.getNick())) {
                gracz_temp.getSt().Wyslij("Zaproszenie " + gracz.getNick());
                Pokoj pokoj_temp = new Pokoj(gracz);
                gracz.setPokoj(pokoj_temp);
                return "true";
            }
        }
        return "false";
    }
}
