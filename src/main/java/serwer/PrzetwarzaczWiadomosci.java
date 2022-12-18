package serwer;

import serwer.dane.Gracz;
import serwer.komendy.Komenda;
import serwer.komendy.TworcaKomendy;

public class PrzetwarzaczWiadomosci {
    private String wiadomosc;
    private String[] slowa;
    private String komenda;
    private String reszta;
    protected void setWiadomosc(String wiadomosc) {
        this.wiadomosc = wiadomosc;
        slowa = wiadomosc.split(" ", 2);

        Dekoder();
    }

    public void Dekoder() {
        komenda = slowa[0];
        reszta = slowa[1];
    }

    public Komenda getKomenda(Gracz gracz) {
        TworcaKomendy tk = new TworcaKomendy();
        Komenda kom = tk.tworzKomende(komenda, gracz);
        return kom;
    }

    public String getReszta() {
        return reszta;
    }
}