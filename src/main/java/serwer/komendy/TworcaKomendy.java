package serwer.komendy;

import serwer.dane.Gracz;
import serwer.komendy.zasady.ZasadyGry;

public class TworcaKomendy {
    ZasadyGry zasady_gry;
    public Komenda tworzKomende(String komenda, String reszta, Gracz gracz) {
        switch(komenda) {
            case "WTG":
                WybierzTrybGry wtg = new WybierzTrybGry();
                zasady_gry = wtg.wybierz(reszta);
                wtg.Wykonaj();
                return wtg;
            case "PDR":
                //return new PodajDostepneRuchy();
                return null;
            case "RP":
                RuchPionka rp = new RuchPionka(zasady_gry, gracz.getPokoj().getPlansza(), Integer.parseInt(reszta.split(" ")[0]), Integer.parseInt(reszta.split(" ")[1]), Integer.parseInt(reszta.split(" ")[2]), Integer.parseInt(reszta.split(" ")[3]));
                return rp;
            case "Join":
                WejdzDoPokoju wdp = new WejdzDoPokoju();
                return wdp;
            default:
                return null;
        }
    }
}
