package serwer.komendy;

import serwer.dane.Pokoj;

public interface Komenda {

    /**
     * Funkcja wykonujaca dana komende
     * @param reszta - parametry komendy
     * @param pokoj - pokoj w ktorym jest gracz wysylajacy komende
     * @return czy poprawnie wykonano komende
     */
    String Wykonaj(String reszta, Pokoj pokoj);
}