package serwer.komendy;

import serwer.komendy.zasady.Zasady1;
import serwer.komendy.zasady.Zasady2;
import serwer.komendy.zasady.Zasady3;
import serwer.komendy.zasady.ZasadyGry;

public class WybierzTrybGry implements Komenda{
    public ZasadyGry wybierz(String rodzaj) {
        switch (rodzaj) {
            case "0":
                return new Zasady1();
            case "1":
                return new Zasady2();
            case "2":
                return new Zasady3();
            default:
                return null;
        }
    }
}
