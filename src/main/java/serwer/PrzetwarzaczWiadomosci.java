package serwer;

public class PrzetwarzaczWiadomosci {
    String wiadomosc;
    String[] slowa;
    String komenda;
    void getWiadomosc(String wiadomosc) {
        this.wiadomosc = wiadomosc;
        slowa = wiadomosc.split(" ");

        Dekoder();
    }

    void Dekoder() {
        komenda = slowa[0];



    }
}