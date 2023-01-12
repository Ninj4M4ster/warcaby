package serwer;

import kontrolerDanych.KontrolerDanych;
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
    private Gracz gracz;
    BufferedReader in = null;
    PrintWriter out = null;


    public SerwerThread(Socket socket) {
        this.socket = socket;
        gracz = new Gracz(this);
    }


    /**
     * Metoda wysylajaca wiadomosc do gracza
     * @param wiadomosc
     */
    public void wyslij(String wiadomosc) {
        out.println(wiadomosc);
        out.flush();
    }

    /**
     * Główna metoda wątku serwera<br>
     * Obsługuje wysyła i odbiera wiadomości<br>
     * W razie potrzeby powiadamia innych uzytkowników o rozłączeniu się gracza
     */
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

        while(true) {
            try {
                wiadomosc = in.readLine();
                pw.setWiadomosc(wiadomosc);
                Komenda kom = pw.getKomenda(gracz);
                String zwrotne = kom.Wykonaj(pw.getReszta(), gracz.getPokoj());
                out.println(zwrotne);
                out.flush();
            } catch (IOException e) {
                for(Gracz gracz : KontrolerDanych.getInstance().getGracze()) {
                    if(!gracz.equals(this.gracz)) {
                        gracz.getSt().wyslij("Rozlaczono " + this.gracz.getNick());
                    }
                }

                KontrolerDanych.getInstance().removeGracz(this.gracz);

                if(this.gracz.getPokoj() != null) {
                    Pokoj pokoj1 = this.gracz.getPokoj();
                    pokoj1.kontroler_stanu_gry.PRZERWIJ();
                    pokoj1.setGosc(null);
                    pokoj1.setMistrz(null);
                    KontrolerDanych.getInstance().removePokoj(pokoj1);
                }

                System.out.println("Rozłaczono " + this.gracz.getNick());
                return;
            }
        }
    }
}
