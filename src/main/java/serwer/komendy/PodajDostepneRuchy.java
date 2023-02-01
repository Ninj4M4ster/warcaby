package serwer.komendy;

import serwer.dane.Gracz;
import serwer.dane.KontrolerStanuGry;
import serwer.dane.Pokoj;
import serwer.komendy.zasady.Zasady1;

import java.util.ArrayList;
import java.util.List;

public class PodajDostepneRuchy extends Zasady1 implements Komenda {
    Gracz gracz;
    Pokoj pokoj;
    boolean flaga_bicia;


    public PodajDostepneRuchy(Gracz gracz) {
        this.gracz = gracz;
    }

    class Ruch implements Comparable<Ruch> {
        private int ilosc_bic;
        private String ruch;

        public Ruch() {
            this.ilosc_bic = 0;
            this.ruch = "";
        }

        public Ruch(int x, int y, int x1, int y1) {
            this.ilosc_bic = 0;
            this.ruch = "";
            this.addRuch(x, y);
            this.addRuch(x1, y1);
        }

        void addRuch(int x, int y) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.ruch);
            if(ruch.length() == 0) {
                stringBuilder.append(x + " " + y);
            }
            else {
                stringBuilder.append(" " + x + " " + y);
            }
            this.ruch = stringBuilder.toString();
        }

        public Ruch kopiujRuch() {
            Ruch ruch_temp = new Ruch();
            ruch_temp.ruch = this.ruch;
            ruch_temp.ilosc_bic = this.ilosc_bic;
            return ruch_temp;
        }

        @Override
        public int compareTo(Ruch o) {
            return this.ilosc_bic - o.ilosc_bic;
        }

        public int getIlosc_bic() {
            return ilosc_bic;
        }

        public String getRuch() {
            return ruch;
        }
    }
    @Override
    public String Wykonaj(String reszta, Pokoj pokoj) {
        this.pokoj = pokoj;
        flaga_bicia = false;
        wypiszRuchy(pokoj.getPlansza());
        return "true";
    }

    public List<Ruch> wypiszRuchy(int[][] plansza) {
        plansza = kopiuj(plansza);
        List<Ruch> ruchy = new ArrayList<Ruch>();

        int kolor = pokoj.kontroler_stanu_gry.getStan() == KontrolerStanuGry.StanGry.RUCH_BIALYCH ? 1 : 0;

        for (int x = 0; x < plansza.length; x += 1) {
            for (int y = 0; y < plansza.length; y += 1) {
                if(plansza[x][y] != 0 && plansza[x][y] % 2 == kolor) {
                    ruchy.addAll(sprawdzRuchy(plansza, x, y));
                }
            }
        }
        return ruchy;
    }

    private List<Ruch> sprawdzRuchy(int[][] plansza, int x, int y) {
        int pionek = plansza[x][y];
        plansza = kopiuj(plansza);
        List<Ruch> ruchy = new ArrayList<Ruch>();

        if(pionek < 3) {
            int kierunek = pionek == 1 ? 1 : -1;

            for(int i = 1; i > -2; i -= 2) {
                if(czyDozwolonyRuch(plansza, x + i, y + kierunek) && !flaga_bicia) {
                    ruchy.add(new Ruch(x, y, x + i, y + kierunek));
                }
            }

            List<Ruch> bicia = sprawdzBiciaPion(plansza, x, y, null);
            if(!bicia.isEmpty()) {
                flaga_bicia = true;
                ruchy = bicia;
            }
        }
        else if(pionek == 3 || pionek == 4) {

        }

        return ruchy;
    }

    private List<Ruch> sprawdzBiciaPion(int[][] plansza, int x, int y, Ruch ruch) {
        plansza = kopiuj(plansza);
        List<Ruch> bicia = new ArrayList<Ruch>();

        for(int i = 1; i > -2; i -= 2) {
            for(int j = 1; j > -2; j -= 2) {
                if(czyDozwoloneBicie(plansza, x, y , i, j, plansza[x][y])) {
                    if(ruch == null) {
                        ruch = new Ruch(x, y, x + 2*i, y + 2*j);
                        ruch.ilosc_bic += 1;
                    }
                    else {
                        ruch.addRuch(x + 2*i, y + 2*j);
                        ruch.ilosc_bic += 1;
                    }

                    int[][] plansza_temp = kopiuj(plansza);
                    plansza_temp[x+2*i][y+2*j] = plansza_temp[x][y];
                    plansza_temp[x+i][y+j] = 0;
                    plansza_temp[x][y] = 0;

                    List<Ruch> bicia_temp = sprawdzBiciaPion(plansza_temp, x+2*i, y+2*j, ruch.kopiujRuch());
                    if(bicia_temp.isEmpty()) {
                        bicia.add(ruch);
                    }
                }
            }
        }

        return bicia;
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
    private boolean czyDozwolonyRuch(int[][] plansza, int x, int y) {
        return y >= 0 && y < plansza.length && x >= 0 && x < plansza.length && plansza[x][y] == 0;
    }

    private boolean czyDozwoloneBicie(int[][] plansza, int x, int y, int i, int j, int pionek) {
        return y + 2*j >= 0 && y + 2*j < plansza.length && x + 2*i >= 0 && x + 2*i < plansza.length && plansza[x+i][y+j] % 2 != pionek && plansza[x+i][y+j] != 0 && plansza[x + 2*i][y + 2*j] == 0;
    }
}