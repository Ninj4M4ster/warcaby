package serwer.komendy;

import serwer.dane.Gracz;
import serwer.dane.KontrolerStanuGry;
import serwer.dane.Pokoj;

public class RuchPionka implements Komenda{
    int x, y, przesuniecie_x, przesuniecie_y;
    int[][] plansza;
    Gracz gracz;

    public RuchPionka(Gracz gracz) {
        this.gracz = gracz;
    }

    @Override
    public String Wykonaj(String reszta, Pokoj pokoj) {
        plansza = pokoj.getPlansza();

        try {
            x = Integer.parseInt(reszta.split(" ")[0]);
            y = Integer.parseInt(reszta.split(" ")[1]);
            przesuniecie_x = Integer.parseInt(reszta.split(" ")[2]);
            przesuniecie_y = Integer.parseInt(reszta.split(" ")[3]);
        } catch(NumberFormatException nfe) {
            return "false";
        }

        pokoj.getZasadyGry().setPlansza(plansza);
        pokoj.getZasadyGry().setStanGry(pokoj.kontroler_stanu_gry.getStan());
        pokoj.getZasadyGry().setGracz(gracz);

        if(pokoj.getZasadyGry().ruchPionem(x, y, przesuniecie_x, przesuniecie_y) || pokoj.getZasadyGry().ruchKrolowa(x, y, przesuniecie_x, przesuniecie_y)) {      // || zasady_gry.bicie()
            int pionek = plansza[x][y];
            plansza[x][y] = 0;
            plansza[x + przesuniecie_x][y + przesuniecie_y] = pionek;
            pokoj.setPlansza(plansza);

            if(pokoj.kontroler_stanu_gry.getStan() == KontrolerStanuGry.StanGry.RUCH_BIALYCH) {
                if(pokoj.getMistrz().getKolor() == 1) {
                    pokoj.getGosc().getSt().Wyslij("plansza " + pokoj.planszaToString());
                }
                else if(pokoj.getMistrz().getKolor() == 2) {
                    pokoj.getMistrz().getSt().Wyslij("plansza " + pokoj.planszaToString());
                }
            }
            else if(pokoj.kontroler_stanu_gry.getStan() == KontrolerStanuGry.StanGry.RUCH_CZARNYCH) {
                if(pokoj.getMistrz().getKolor() == 2) {
                    pokoj.getGosc().getSt().Wyslij("plansza " + pokoj.planszaToString());
                }
                else if(pokoj.getMistrz().getKolor() == 1) {
                    pokoj.getMistrz().getSt().Wyslij("plansza " + pokoj.planszaToString());
                }
            }
            pokoj.kontroler_stanu_gry.RUCH();
            return "true " + pokoj.planszaToString();
        }
        return "false";
    }
}
