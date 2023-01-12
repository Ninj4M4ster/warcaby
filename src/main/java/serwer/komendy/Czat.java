package serwer.komendy;

import serwer.dane.Gracz;
import serwer.dane.Pokoj;

public class Czat implements Komenda {
    private Gracz gracz;

    public Czat(Gracz gracz) {
        this.gracz = gracz;
    }

    /**
     * Przesyla czat wyslany przez jednego gracz do drugiego gracz
     * @param reszta - tresc wiadomosci
     * @param pokoj - pokoj w ktorym jest gracz
     * @return czy pomyslnie wyslano czat
     */
    @Override
    public String Wykonaj(String reszta, Pokoj pokoj) {
        Gracz gracz2 = (gracz.equals(pokoj.getMistrz()) ? pokoj.getGosc() : pokoj.getMistrz());
        gracz2.getSt().wyslij("Czat " + reszta);
        return "true";
    }
}