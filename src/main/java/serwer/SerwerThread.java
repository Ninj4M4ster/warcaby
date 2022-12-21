package serwer;

import serwer.dane.Gracz;
import serwer.dane.Pokoj;
import serwer.komendy.Komenda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SerwerThread extends Thread {
    private Socket socket;
    private Pokoj pokoj = null;
    private Gracz gracz;
    BufferedReader in = null;
    PrintWriter out = null;


    public SerwerThread(Socket socket, int gracze_id) {
        this.socket = socket;
        gracz = new Gracz(gracze_id, this);
        Serwer.addGracz(gracz);
    }

    public void Wyslij(String wiadomosc) {
        out.println(wiadomosc);
    }

    @Override
    public void run() {
        PrzetwarzaczWiadomosci pw = new PrzetwarzaczWiadomosci();

        String wiadomosc;

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
        } catch(IOException ioe) {
            System.out.println("Problem IO z wątkiem serwera");
        }

        while (true) {
            try {
                wiadomosc = in.readLine();
                pw.setWiadomosc(wiadomosc);
                Komenda kom = pw.getKomenda(gracz);
                String zwrotne = kom.Wykonaj(pw.getReszta(), pokoj);
                out.println(zwrotne);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
