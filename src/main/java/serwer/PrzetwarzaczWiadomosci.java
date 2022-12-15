package serwer;

import serwer.komendy.Komenda;
import serwer.komendy.TworcaKomendy;

public class PrzetwarzaczWiadomosci {
    String wiadomosc;
    String[] slowa;
    String komenda;
    String reszta;
    Komenda getWiadomosc(String wiadomosc) {
        this.wiadomosc = wiadomosc;
        slowa = wiadomosc.split(" ", 2);

        Dekoder();
        TworcaKomendy tk = new TworcaKomendy();
        Komenda komenda_objekt = tk.tworzKomende(komenda, reszta);
        return komenda_objekt;
    }

    void Dekoder() {
        komenda = slowa[0];
        reszta = slowa[1];
    }
}