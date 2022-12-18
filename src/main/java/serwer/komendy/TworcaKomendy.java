package serwer.komendy;

import serwer.dane.Gracz;
import serwer.komendy.zasady.ZasadyGry;

public class TworcaKomendy {
    ZasadyGry zasady_gry;
    public Komenda tworzKomende(String komenda, Gracz gracz) {
        switch(komenda) {
            case "WTG":
                return new WybierzTrybGry();
            case "PDR":
                //return new PodajDostepneRuchy();
                return null;
            case "RP":
                return new RuchPionka();
            case "Dolacz":
                WejdzDoPokoju wdp = new WejdzDoPokoju();
                wdp.setGracz(gracz);
                return wdp;
            default:
                return null;
        }
    }
}
