package serwer.komendy;

import serwer.dane.Gracz;
import serwer.dane.Pokoj;

public class WejdzDoPokoju implements Komenda {
    Gracz gracz;
    Pokoj pokoj;
    @Override
    public boolean Wykonaj() {
           if(pokoj == null) {
               pokoj = new Pokoj(gracz);
               return true;
           }
           else if (pokoj != null && pokoj.getMistrz() != null){
               pokoj.setGosc(gracz);
               return true;
           }
           return false;
    }

    public void setGracz(Gracz gracz) {
        this.gracz = gracz;
    }

    public void setPokoj(Pokoj pokoj) {
        this.pokoj = pokoj;
    }
}
