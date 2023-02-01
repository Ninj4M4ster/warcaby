package serwer.komendy;

import serwer.baza_danych.MenadzerBazyDanych;
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

    /**
     * zmienia ruchy z stringów na inty, przesyla potrzebne inforamcje zasadom zeby mogly sprawdzic ruch
     * wywołuje zasady, wykonuje ruch, ewentulanie promuje pionka na krola, powiadamia graczy o ewentualnym zwyciezcy
     * @param reszta - ruchy pionka/krola
     * @param pokoj - pokoj w ktorym jest gracz wysylajacy komende
     * @return czy pomyslnie wykonano komende
     */
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

                for(int j = 1; j < Math.abs(ruchy.get(i + 2) - ruchy.get(i)); j += 1) {
                    plansza[ruchy.get(i) + ((ruchy.get(i+2) - ruchy.get(i))/Math.abs(ruchy.get(i+2) - ruchy.get(i)) * j)][ruchy.get(i+1) + ((ruchy.get(i+3) - ruchy.get(i+1))/Math.abs(ruchy.get(i+3) - ruchy.get(i+1)) * j)] = 0;
                }
            }

            if(pokoj.getZasadyGry().promocja(ruchy.get(ruchy.size() - 2), ruchy.get(ruchy.size() - 1))) {
                plansza[ruchy.get(ruchy.size() - 2)][ruchy.get(ruchy.size() - 1)] += 2;
            }

            System.out.println("po ruchu");
            pokoj.wypiszPlansze(pokoj.getPlansza());

            Gracz gracz_temp = (gracz.equals(pokoj.getMistrz()) ? pokoj.getGosc() : pokoj.getMistrz());
            gracz_temp.getSt().wyslij("Ruch " + pokoj.planszaToString());

            // wprowadz ruch do bazy danych
            MenadzerBazyDanych.instancja().wprowadzNowyRuch(pokoj.getGra(), pokoj.getLicznik(), pokoj.planszaToString());
            pokoj.incrementLicznik();

            if(pokoj.getZasadyGry().czyWygrana() == 0) {
                pokoj.kontroler_stanu_gry.RUCH();
            }
            else if(pokoj.getZasadyGry().czyWygrana() == 1) {
                pokoj.getGosc().getSt().wyslij("Wygrana biale");
                pokoj.getMistrz().getSt().wyslij("Wygrana biale");
                pokoj.kontroler_stanu_gry.ZAKONCZ();
            }
            else if(pokoj.getZasadyGry().czyWygrana() == 2) {
                pokoj.getGosc().getSt().wyslij("Wygrana czrane");
                pokoj.getMistrz().getSt().wyslij("Wygrana czarne");
                pokoj.kontroler_stanu_gry.ZAKONCZ();
            }
            return "true";
        }
        return "false";
    }
}