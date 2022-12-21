package serwer.komendy;

import serwer.dane.Gracz;
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
        pokoj.getZasady_gry().setPlansza(plansza);
        pokoj.getZasady_gry().setStan_gry(pokoj.kontroler_stanu_gry.getStan());
        if(pokoj.getZasady_gry().ruchPionem(x, y, przesuniecie_x, przesuniecie_y) || pokoj.getZasady_gry().ruchKrolowa(x, y, przesuniecie_x, przesuniecie_y)) {      // || zasady_gry.bicie()
            int pionek = plansza[x][y];
            plansza[x][y] = 0;
            plansza[x + przesuniecie_x][y + przesuniecie_y] = pionek;
            pokoj.setPlansza(plansza);

            return "true " + pokoj.planszaToString();
        }
        return "false";
    }
}
