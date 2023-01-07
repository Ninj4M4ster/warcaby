package kontroler;

import serwer.dane.Gracz;
import serwer.dane.Pokoj;

import java.util.ArrayList;
import java.util.List;

public class KontrolerDanych {
    private static volatile KontrolerDanych instancja;

    private volatile List<Gracz> gracze = new ArrayList<Gracz>();
    private volatile List<Pokoj> pokoje = new ArrayList<Pokoj>();

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

    public List<Pokoj> getPokoje() {
        return pokoje;
    }

    public List<Gracz> getGracze() {
        return gracze;
    }

    public synchronized void addPokoj(Pokoj pokoj) {
        pokoje.add(pokoj);
    }

    public synchronized void addGracz(Gracz gracz) {
        gracze.add(gracz);
    }

    public synchronized void removeGracz(Gracz gracz) {
        gracze.remove(gracz);
    }

    public synchronized void removePokoj(Pokoj pokoj) {
        pokoje.remove(pokoj);
    }
}
