import serwer.dane.Gracz;
import serwer.dane.Pokoj;

import java.util.ArrayList;
import java.util.List;

public class KontrolerDanych {
    private static volatile KontrolerDanych instancja;

    private static volatile List<Gracz> gracze = new ArrayList<Gracz>();
    private static volatile List<Pokoj> pokoje = new ArrayList<Pokoj>();

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

    public static List<Pokoj> getPokoje() {
        return pokoje;
    }

    public static List<Gracz> getGracze() {
        return gracze;
    }

    public synchronized static void addPokoj(Pokoj pokoj) {
        pokoje.add(pokoj);
    }

    public synchronized static void addGracz(Gracz gracz) {
        gracze.add(gracz);
    }

    public synchronized static void removeGracz(Gracz gracz) {
        gracze.remove(gracz);
    }

    public synchronized static void removePokoj(Pokoj pokoj) {
        pokoje.remove(pokoj);
    }
}
