package serwer.komendy;

import serwer.Serwer;
import serwer.dane.Gracz;
import serwer.dane.Pokoj;

public class WejdzDoPokoju implements Komenda {
    Gracz gracz;
    @Override
    public boolean Wykonaj(String reszta, Pokoj pokoj) {
        if(pokoj == null && reszta.equals("nowy")) {
            new Pokoj(gracz);
            return true;
        }
        else if(pokoj == null) {
            for(Pokoj pokoj_temp : Serwer.getPokoje()) {
                int id;

                try {
                    id = Integer.parseInt(reszta);
                }
                catch(NumberFormatException nfe) {
                    return false;
                }

                if(pokoj_temp.getId() == id) {
                    pokoj_temp.setGosc(gracz);
                    return true;
                }
            }
        }
        return false;
    }

    public void setGracz(Gracz gracz) {
        this.gracz = gracz;
    }
}
