package bot;

import serwer.SerwerThread;
import serwer.dane.Gracz;
import serwer.komendy.PodajDostepneRuchy;
import serwer.komendy.RuchPionka;

import java.net.Socket;
import java.util.List;

import static java.lang.Math.sqrt;

public class Bot extends SerwerThread {
    int poziom;
    int[][] plansza, plansza_stara;
    boolean flaga = true;
    Gracz gracz;
    PodajDostepneRuchy pdr;
    String best_plansza;

    public Bot(String reszta) {
        setPoziom(reszta);
    }

    private void setPoziom(String poziom) {
        switch (poziom) {
            case "latwy":
                this.poziom = 0;
                break;
            case "sredni":
                this.poziom = 2;
                break;
            case "trudny":
                this.poziom = 4;
                break;
            default:
                this.poziom = 0;
        }
    }

    private int[][] stringToPlansza(String plansza) {
        int dlugosc = (int) sqrt(plansza.length());
        int[][] plansza2 = new int[dlugosc][dlugosc];
        for(int y = 0; y < dlugosc; y += 1) {
            for(int x = 0; x < dlugosc; x += 1) {
                plansza2[x][y] = plansza.charAt(y * dlugosc + x);
            }
        }
        return plansza2;
    }

    @Override
    public void wyslij(String wiadomosc) {
        String komenda = wiadomosc.split(" ", 2)[0];
        String reszta = wiadomosc.split(" ", 2)[1];

        switch(komenda) {
            case "plansza":
                this.gracz.setKolor(reszta.charAt(0));
                reszta = reszta.substring(1);
                this.plansza = stringToPlansza(reszta);
                break;
            case "Ruch":
                this.plansza_stara = plansza;
                this.plansza = stringToPlansza(reszta);
                break;
            default:
                this.flaga = false;
        }
    }

    @Override
    public void run() {
        pdr = new PodajDostepneRuchy(this.gracz);
        while(flaga) {
            if(plansza_stara != plansza) {
                bestMove(planszaToString(plansza), 0);

                String ruch = pdr.getRuch(best_plansza);
                new RuchPionka(this.gracz).Wykonaj(ruch, gracz.getPokoj());

                plansza_stara = plansza;
            }
        }
    }

    int bestMove(String plansza, int licznik) {
        if(licznik == poziom) {
            int max = -100;
            String[] dozwolone_ruchy = pdr.planszePoRuchach(null, stringToPlansza(plansza)).split(" ");
            for(String plansza2 : dozwolone_ruchy) {
                int wynik = 0;
                for(int i = 0; i < plansza2.length(); i += 1) {
                    if(plansza2.charAt(i) != 0) {
                        wynik += plansza2.charAt(i) % 2 == gracz.getKolor() ? 1 : -1;
                    }
                }
                if(wynik > max) {
                    best_plansza = plansza2;
                    max = wynik;
                }
            }
            return max;
        }
        else if(licznik % 2 == 0) {
            int max = -100;
            String[] dozwolone_ruchy = pdr.planszePoRuchach(null, stringToPlansza(plansza)).split(" ");
            for(String plansza2 : dozwolone_ruchy) {
                int wynik = bestMove(plansza2, licznik+1);
                if(wynik > max) {
                    best_plansza = plansza2;
                    max = wynik;
                }
            }
            return max;
        }
        else {
            int min = 100;
            String[] dozwolone_ruchy = pdr.planszePoRuchach(null, stringToPlansza(plansza)).split(" ");
            for(String plansza2 : dozwolone_ruchy) {
                int wynik = bestMove(plansza2, licznik+1);
                if(wynik < min) {
                    best_plansza = plansza2;
                    min = wynik;
                }
            }
            return min;
        }
    }

    public Gracz createGracz() {
        this.gracz = new Gracz(this);
        return this.gracz;
    }

    public String planszaToString(int[][] plansza) {
        StringBuilder plansza_temp = new StringBuilder();

        for(int y = 0; y < plansza.length; y += 1) {
            for(int x = 0; x < plansza.length; x += 1) {
                plansza_temp.append(plansza[x][y]);
            }
        }
        return plansza_temp.toString();
    }
}
