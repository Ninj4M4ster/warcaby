package serwer.komendy;

import serwer.Serwer;
import serwer.dane.Gracz;
import serwer.dane.Pokoj;

public class NadajImie implements Komenda{
    Gracz gracz;

    protected NadajImie(Gracz gracz) {
        this.gracz = gracz;
    }
    @Override
    public boolean Wykonaj(String reszta, Pokoj pokoj) {
        for(Gracz gracz_temp : Serwer.getGracze()) {
            if(reszta.equals(gracz_temp.getNick())) {
                return false;
            }
        }
        gracz.setNick(reszta);
        return true;
    }
}
