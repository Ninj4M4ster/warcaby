package serwer.komendy;

public class TworcaKomendy {
    public Komenda tworzKomende(String komenda, String reszta) {
        switch(komenda) {
            case "WTG":
                return new WybierzTrybGry();
            case "PDR":
                //return new PodajDostepneRuchy();
            case "RP":
                //return new RuchPionka();
            default:
                return null;
        }
    }
}
