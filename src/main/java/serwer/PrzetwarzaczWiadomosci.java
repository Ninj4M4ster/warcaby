package serwer;

import serwer.dane.Gracz;
import serwer.komendy.Komenda;
import serwer.komendy.TworcaKomendy;

public class PrzetwarzaczWiadomosci {
    private String wiadomosc;
    private String[] slowa;
    private String komenda;
    private String reszta;
    protected Komenda getWiadomosc(String wiadomosc, Gracz gracz) {
        this.wiadomosc = wiadomosc;
        slowa = wiadomosc.split(" ", 2);

        Dekoder();
        TworcaKomendy tk = new TworcaKomendy();
        Komenda kom = tk.tworzKomende(komenda, reszta, gracz);
        return kom;
    }

    private void Dekoder() {
        komenda = slowa[0];
        reszta = slowa[1];
    }
}