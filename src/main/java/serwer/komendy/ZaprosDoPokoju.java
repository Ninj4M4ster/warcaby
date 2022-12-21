package serwer.komendy;

import serwer.Serwer;
import serwer.dane.Gracz;
import serwer.dane.Pokoj;

public class ZaprosDoPokoju implements Komenda {
    Gracz gracz;
    String reszta;

    public ZaprosDoPokoju(Gracz gracz) {
    }
    @Override
    public boolean Wykonaj(String reszta, Pokoj pokoj) {
        for(Gracz gracz_temp : Serwer.getGracze()) {
            if(reszta.equals(gracz_temp.getNick())) {
                gracz_temp.getSt().Wyslij("Zaproszenie " + gracz);
            }
        }
        return false;
    }
}
