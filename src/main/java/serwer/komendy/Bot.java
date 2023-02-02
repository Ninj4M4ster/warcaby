package serwer.komendy;

import serwer.dane.Gracz;
import serwer.dane.Pokoj;

public class Bot implements Komenda {

    Gracz gracz;
    public Bot(Gracz gracz) {
        this.gracz = gracz;
    }
    @Override
    public String Wykonaj(String reszta, Pokoj pokoj) {
        Pokoj pokoj2 = new Pokoj(gracz);

        bot.Bot bot1 = new bot.Bot(reszta);

        pokoj2.setGosc(bot1.createGracz());
        return "true";
    }
}
