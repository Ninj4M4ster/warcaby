package serwer;

import serwer.komendy.Komenda;
import serwer.komendy.TworcaKomendy;

public class PrzetwarzaczWiadomosci {
    private String wiadomosc;
    private String[] slowa;
    private String komenda;
    private String reszta;
    protected Komenda getWiadomosc(String wiadomosc) {
        this.wiadomosc = wiadomosc;
        slowa = wiadomosc.split(" ", 2);

        Dekoder();
        TworcaKomendy tk = new TworcaKomendy();
        Komenda objekt = tk.tworzKomende(komenda, reszta);
        return objekt;
    }

    private void Dekoder() {
        komenda = slowa[0];
        reszta = slowa[1];
    }
}