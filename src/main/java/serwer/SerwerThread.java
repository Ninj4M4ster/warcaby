package serwer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SerwerThread extends Thread {
    private Socket socket;

    public SerwerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        PrzetwarzaczWiadomosci pw = new PrzetwarzaczWiadomosci();

        boolean[][] plansza;
        String komenda;

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
        } catch(IOException ioe) {
            System.out.println("Problem IO z wątkiem serwera");
        }

        while (true) {
            try {
                komenda = in.readLine();
                pw.getWiadomosc(komenda);


            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
