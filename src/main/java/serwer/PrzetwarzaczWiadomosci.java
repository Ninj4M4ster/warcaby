package serwer;

import serwer.dane.Gracz;
import serwer.komendy.Komenda;
import serwer.komendy.TworcaKomendy;

public class PrzetwarzaczWiadomosci {
    private String[] slowa;
    private String komenda;
    private String reszta;
    protected void setWiadomosc(String wiadomosc) {
        slowa = wiadomosc.split(" ", 2);

        Dekoder();
    }

    private void Dekoder() {
        komenda = slowa[0];
        reszta = slowa[1];
    }

    protected Komenda getKomenda(Gracz gracz) {
        TworcaKomendy tk = new TworcaKomendy();
        return tk.tworzKomende(komenda, gracz);
    }

    protected String getReszta() {
        return reszta;
    }
}