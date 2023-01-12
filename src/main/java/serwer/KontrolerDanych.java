package serwer;

import serwer.dane.Gracz;
import serwer.dane.Pokoj;

import java.util.ArrayList;
import java.util.List;

public class KontrolerDanych {
    /**
     * Przechwuje instancje kontrolera
     */
    private static volatile KontrolerDanych instancja;
    /**
     * Przechowuje wszystkich graczy na serwerze
     */
    private List<Gracz> gracze = new ArrayList<Gracz>();
    /**
     * Przechowuje listę wszystkich pokoji na serwerze
     */
    private volatile List<Pokoj> pokoje = new ArrayList<Pokoj>();

    /**
     * Zwraca istniejącą instancję lub tworzy nową jeśli jeszce nie powstała
     * @return
     */
    public static KontrolerDanych getInstance() {
        if(instancja != null) {
            return instancja;
        }
        synchronized(KontrolerDanych.class) {
            if(instancja == null) {
                instancja = new KontrolerDanych();
            }
            return instancja;
        }
    }

    /**
     * Zwraca liste pokoi
     * @return
     */
    public List<Pokoj> getPokoje() {
        return pokoje;
    }

    /**
     * Zwraca listę graczy
     * @return
     */
    public List<Gracz> getGracze() {
        return gracze;
    }

    /**
     * Dodaje pokoj do listy pokoi
     * @param pokoj
     */
    public synchronized void addPokoj(Pokoj pokoj) {
        pokoje.add(pokoj);
    }

    /**
     * Dodaje gracza do listy graczy
     * @param gracz
     */
    public synchronized void addGracz(Gracz gracz) {
        gracze.add(gracz);
    }

    /**
     * Usuwa gracza z listy graczy
     * @param gracz
     */
    public synchronized void removeGracz(Gracz gracz) {
        gracze.remove(gracz);
    }

    /**
     * Usuwa pokoje z listy pokoi
     * @param pokoj
     */
    public synchronized void removePokoj(Pokoj pokoj) {
        pokoje.remove(pokoj);
    }
}
