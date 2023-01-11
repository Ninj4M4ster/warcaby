package serwer.komendy;

import serwer.dane.Gracz;
import serwer.komendy.zasady.ZasadyGry;

public class TworcaKomendy {
    public Komenda tworzKomende(String komenda, Gracz gracz) {
        switch(komenda) {
            case "WTG":
                return new WybierzTrybGry(gracz);
            case "PDR":
                //return new PodajDostepneRuchy();
                return null;
            case "RP":
                return new RuchPionka(gracz);
            case "Zapros":
                return new ZaprosDoPokoju(gracz);
            case "Odpowiedz":
                return new AkceptujZaproszenie(gracz);
            case "Imie":
                return new NadajImie(gracz);
            case "Czat":
                return new Czat(gracz);
            default:
                return null;
        }
    }
}
