package serwer;

import serwer.dane.Gracz;
import serwer.komendy.Komenda;
import serwer.komendy.TworcaKomendy;

public class PrzetwarzaczWiadomosci {
    private String[] slowa;
    private String komenda;
    private String reszta;

    /**
     * Metoda rozdzielajaca wiadomosc na komende i jej parametry (reszta)
     * @param wiadomosc - wiadomosc wyslana przez gracza
     */
    protected void setWiadomosc(String wiadomosc) {
        slowa = wiadomosc.split(" ", 2);

        komenda = slowa[0];
        reszta = slowa[1];
    }

    /**
     * Metoda zwracajaca stwrozona komende po zdekodowaniu wiadomosci
     * @param gracz - gracz wysylajacy wiadomosc
     * @return obiekt komendy wywolanej przez gracza
     */
    protected Komenda getKomenda(Gracz gracz) {
        TworcaKomendy tk = new TworcaKomendy();
        return tk.tworzKomende(komenda, gracz);
    }

    /**
     * Metoda zwracajaca reszte czyli parametry komendy
     * @return
     */
    protected String getReszta() {
        return reszta;
    }
}