package serwer.komendy;

import serwer.komendy.zasady.ZasadyGry;

public class TworcaKomendy {
    ZasadyGry zasady_gry;
    public Komenda tworzKomende(String komenda, String reszta) {
        switch(komenda) {
            case "WTG":
                WybierzTrybGry wtg = new WybierzTrybGry();
                zasady_gry = wtg.wybierz(reszta);
                wtg.Wykonaj();

            case "PDR":
                //return new PodajDostepneRuchy();
            case "RP":
                new RuchPionka(zasady_gry, reszta.split(" ")[0], reszta.split(" ")[1], reszta.split(" ")[2], reszta.split(" ")[3]);
            default:
                return null;
        }
    }
}
