package serwer.komendy;

import serwer.dane.Gracz;
import serwer.dane.Pokoj;

import java.util.ArrayList;

public class RuchPionka implements Komenda{
    int[][] plansza;
    ArrayList<Integer> ruchy;
    Gracz gracz;

    public RuchPionka(Gracz gracz) {
        this.gracz = gracz;
    }

    @Override
    public String Wykonaj(String reszta, Pokoj pokoj) {
        plansza = pokoj.getPlansza();

        String[] ruchy_str = reszta.split(" ");
        ruchy = new ArrayList<Integer>();

        try {
            for(String koord : ruchy_str) {
                ruchy.add(Integer.parseInt(koord));
            }
        } catch(NumberFormatException nfe) {
            return "false";
        }

        pokoj.getZasadyGry().setPlansza(plansza);
        pokoj.getZasadyGry().setStanGry(pokoj.kontroler_stanu_gry.getStan());
        pokoj.getZasadyGry().setGracz(gracz);
        pokoj.getZasadyGry().setRuchy(ruchy);

        if(pokoj.getZasadyGry().sprawdz()) {
            for(int i = 0; i + 3 < ruchy.size(); i += 2) {
                plansza[ruchy.get(i + 2)][ruchy.get(i + 3)] = plansza[ruchy.get(i)][ruchy.get(i + 1)];
                plansza[ruchy.get(i)][ruchy.get(i + 1)] = 0;

                for(int j = 1; j < Math.abs(ruchy.get(i + 2) - ruchy.get(i)) - 1; j += 1) {
                    if(plansza[ruchy.get(i) + ((ruchy.get(i+2) - ruchy.get(i))/Math.abs(ruchy.get(i+2) - ruchy.get(i)) * j)][ruchy.get(i+1) + ((ruchy.get(i+3) - ruchy.get(i+1))/Math.abs(ruchy.get(i+3) - ruchy.get(i+1)) * j)] != 0) {
                        plansza[ruchy.get(i) + ((ruchy.get(i+2) - ruchy.get(i))/Math.abs(ruchy.get(i+2) - ruchy.get(i)) * j)][ruchy.get(i+1) + ((ruchy.get(i+3) - ruchy.get(i+1))/Math.abs(ruchy.get(i+3) - ruchy.get(i+1)) * j)] = 0;
                    }
                }
            }
            pokoj.setPlansza(plansza);
            return "true";
        }
        return "false";
    }
}