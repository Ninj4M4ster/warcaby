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
                this.plansza_stara = kopiuj(plansza);
                plansza_stara[0][0] = 0;
                this.start();
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
        pdr.setPokoj(this.gracz.getPokoj());
        while(flaga) {

            if(planszaToString(plansza_stara).compareTo(planszaToString(plansza)) == 0) {
                bestMove(planszaToString(plansza), 0);

                String ruch = pdr.getRuch(best_plansza);
                new RuchPionka(this.gracz).Wykonaj(ruch, gracz.getPokoj());

                plansza_stara = plansza;
            }
        }
    }

    int bestMove(String plansza, int licznik) {
        if(licznik == poziom) {
            int max = poziom % 2 == 0 ? 100 : -100;
            String[] dozwolone_ruchy = pdr.planszePoRuchach(null, stringToPlansza(plansza)).split(" ");
            for(String plansza2 : dozwolone_ruchy) {
                int wynik = 0;
                for(int i = 0; i < plansza2.length(); i += 1) {
                    if(plansza2.charAt(i) != 0) {
                        wynik += plansza2.charAt(i) % 2 == gracz.getKolor() ? 1 : -1;
                    }
                }
                if(poziom % 2 == 0 && wynik > max) {
                    best_plansza = plansza2;
                    max = wynik;
                }
                else if(poziom % 2 == 1 && wynik < max) {
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

    private int[][] kopiuj(int[][] plansza_do_kopii) {
        int[][] plansza = new int[plansza_do_kopii.length][plansza_do_kopii.length];
        for (int x = 0; x < plansza_do_kopii.length; x += 1) {
            for (int y = 0; y < plansza_do_kopii.length; y += 1) {
                plansza[x][y] = plansza_do_kopii[x][y];
            }
        }
        return plansza;
    }
}
