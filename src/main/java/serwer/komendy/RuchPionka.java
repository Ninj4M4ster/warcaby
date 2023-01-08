package serwer.komendy;

import serwer.dane.Gracz;
import serwer.dane.KontrolerStanuGry;
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

        if(pokoj.getZasadyGry().sprawdz()) {      // || zasady_gry.bicie()
            int pionek = plansza[ruchy.get(0)][ruchy.get(1)];
            plansza[ruchy.get(0)][ruchy.get(1)] = 0;
            plansza[ruchy.get(1)][y + nowy_y] = pionek;
            pokoj.setPlansza(plansza);

            Gracz gracz2 = (gracz.getPokoj().getMistrz().equals(gracz) ? gracz.getPokoj().getGosc() : gracz.getPokoj().getMistrz());
            gracz2.getSt().Wyslij("Plansza " + pokoj.planszaToString());

            pokoj.kontroler_stanu_gry.RUCH();
            return "true " + pokoj.planszaToString();
        }
        return "false";
    }
}