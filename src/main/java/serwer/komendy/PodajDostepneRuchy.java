package serwer.komendy;

import serwer.dane.Gracz;
import serwer.dane.KontrolerStanuGry;
import serwer.dane.Pokoj;
import serwer.komendy.zasady.ZasadyGry;

import java.util.ArrayList;
import java.util.List;

public class PodajDostepneRuchy extends ZasadyGry implements Komenda {
    Gracz gracz;
    Pokoj pokoj;
    boolean flaga_bicia;
    List<Ruch> wynik;


    public PodajDostepneRuchy(Gracz gracz) {
        this.gracz = gracz;
    }

    class Ruch implements Comparable<Ruch> {
        private int ilosc_bic;
        private String ruch;
        String plansza;

        public Ruch() {
            this.ilosc_bic = 0;
            this.ruch = "";
        }

        public Ruch(int x, int y) {
            this.ilosc_bic = 0;
            this.ruch = "";
            this.addRuch(x, y, null);
        }

        public Ruch(int x, int y, int x1, int y1, String plansza) {
            this.ilosc_bic = 0;
            this.ruch = "";
            this.addRuch(x, y, plansza);
            this.addRuch(x1, y1, plansza);
        }

        void addRuch(int x, int y, String plansza) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.ruch);
            if(ruch.length() == 0) {
                stringBuilder.append(x + " " + y);
            }
            else {
                stringBuilder.append(" " + x + " " + y);
            }
            this.ruch = stringBuilder.toString();
            this.setPlanszaPoRuchu(plansza);
        }

        public Ruch kopiujRuch() {
            Ruch ruch_temp = new Ruch();
            ruch_temp.ruch = this.ruch;
            ruch_temp.ilosc_bic = this.ilosc_bic;
            ruch_temp.setPlanszaPoRuchu(plansza);
            return ruch_temp;
        }

        public Ruch kopiujRuch(int x, int y, String plansza) {
            Ruch ruch_temp = this.kopiujRuch();
            ruch_temp.addRuch(x, y, plansza);
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

        public String getPlanszaPoRuchu() {
            return plansza;
        }

        private void setPlanszaPoRuchu(String plansza) {
            this.plansza = plansza;
        }
    }
    @Override
    public String Wykonaj(String reszta, Pokoj pokoj) {
        this.pokoj = pokoj;
        flaga_bicia = false;

        List<Ruch> ruchy = wypiszRuchy(pokoj.getPlansza());
        wynik = new ArrayList<Ruch>();
        ruchy.sort(null);

        int max = 0;
        for(Ruch ruch : ruchy) {
            if(ruch.ilosc_bic > max) {
                max = ruch.ilosc_bic;
                wynik.clear();
                wynik.add(ruch);
            }
            else if(ruch.ilosc_bic == max) {
                wynik.add(ruch);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("true");
        for(Ruch ruch : wynik) {
            sb.append(" ");
            for(String s : ruch.getRuch().split(" ")) {
                if(s != ruch.getRuch().split(" ")[0]) {
                    sb.append("-");
                }
                sb.append(s);
            }
        }
        return sb.toString();
    }

    public String planszePoRuchach(int[][] plansza) {
        flaga_bicia = false;

        List<Ruch> ruchy = wypiszRuchy(kopiuj(plansza));
        wynik = new ArrayList<Ruch>();
        ruchy.sort(null);

        int max = -100;
        System.out.println(ruchy.isEmpty());
        for(Ruch ruch : ruchy) {
            if(ruch.ilosc_bic > max) {
                max = ruch.ilosc_bic;
                wynik.clear();
                wynik.add(ruch);
            }
            else if(ruch.ilosc_bic == max) {
                wynik.add(ruch);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(Ruch ruch : wynik) {
            if(sb.toString().length() != 0) {
                sb.append(" ");
            }
            System.out.println(ruch.getPlanszaPoRuchu());
            sb.append(ruch.getPlanszaPoRuchu());
        }
        System.out.println(sb.toString());
        return sb.toString();
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
        System.out.println(pionek);
        if(pionek < 3) {
            int kierunek = pionek == 1 ? 1 : -1;

            for(int i = 1; i > -2; i -= 2) {
                if(czyDozwolonyRuch(plansza, x + i, y + kierunek) && !flaga_bicia) {
                    int[][] plansza_temp = kopiuj(plansza);
                    plansza_temp[x + i][y + kierunek] = plansza_temp[x][y];
                    plansza_temp[x][y] = 0;
                    ruchy.add(new Ruch(x, y, x + i, y + kierunek, planszaToString(plansza_temp)));
                }
            }

            List<Ruch> bicia = sprawdzBiciaPion(plansza, x, y, null);
            if(!bicia.isEmpty()) {
                flaga_bicia = true;
                ruchy = bicia;
            }
        }
        else if(pionek == 3 || pionek == 4) {
            for(int i = 1; i > -2; i -= 2) {
                for(int j = 1; j > -2; j -= 2) {
                    int nowy_x = x + i;
                    int nowy_y = y + j;
                    while(czyDozwolonyRuch(plansza, nowy_x, nowy_y) && !flaga_bicia) {
                        int[][] plansza_temp = kopiuj(plansza);
                        plansza_temp[nowy_x][nowy_y] = plansza_temp[x][y];
                        plansza_temp[x][y] = 0;
                        ruchy.add(new Ruch(x, y, nowy_x, nowy_y, planszaToString(plansza_temp)));
                        nowy_x += i;
                        nowy_y += j;
                    }
                }
            }

            List<Ruch> bicia = sprawdzBiciaKrol(plansza, x, y, null);
            if(flaga_bicia) {
                ruchy.addAll(bicia);
            }
            else if(!bicia.isEmpty()) {
                flaga_bicia = true;
                ruchy = bicia;
            }
        }

        return ruchy;
    }

    private List<Ruch> sprawdzBiciaPion(int[][] plansza, int x, int y, Ruch ruch) {
        plansza = kopiuj(plansza);
        List<Ruch> bicia = new ArrayList<Ruch>();
        List<Ruch> bicia_temp = new ArrayList<Ruch>();

        for(int i = 1; i > -2; i -= 2) {
            for(int j = 1; j > -2; j -= 2) {
                if(czyDozwoloneBiciePion(plansza, x, y, i, j, plansza[x][y])) {
                    int[][] plansza_temp = kopiuj(plansza);
                    plansza_temp[x + 2*i][y + 2*j] = plansza_temp[x][y];
                    plansza_temp[x][y] = 0;
                    plansza_temp[x + i][y + j] = 0;
                    if(ruch == null) {
                        ruch = new Ruch(x, y, x + 2*i, y + 2*j, planszaToString(plansza_temp));
                        ruch.ilosc_bic += 1;
                    }
                    else {
                        ruch.addRuch(x + 2*i, y + 2*j, planszaToString(plansza_temp));
                        ruch.ilosc_bic += 1;
                    }

                    bicia_temp.addAll(sprawdzBiciaPion(plansza_temp, x+2*i, y+2*j, ruch.kopiujRuch()));
                }
            }
        }

        if(bicia_temp.isEmpty()) {
            if(ruch != null) {
                bicia.add(ruch);
            }
        }

        return bicia;
    }

    private List<Ruch> sprawdzBiciaKrol(int[][] plansza, int x, int y, Ruch ruch) {
        plansza = kopiuj(plansza);

        List<Ruch> bicia = new ArrayList<Ruch>();
        List<Ruch> bicia_temp = new ArrayList<Ruch>();

        for(int i = 1; i > -2; i -= 2) {
            for(int j = 1; j > -2; j -= 2) {
                int nowy_x = x + i;
                int nowy_y = y + j;
                while(czyDozwolonyRuch(plansza, nowy_x, nowy_y)) {
                    nowy_x += i;
                    nowy_y += j;
                }
                if(czyWPlanszy(nowy_x, nowy_y) && plansza[nowy_x][nowy_y] % 2 != plansza[x][y] && plansza[nowy_x][nowy_y] != 0) {
                    int[][] plansza_temp = kopiuj(plansza);
                    plansza_temp[nowy_x][nowy_y] = 0;

                    nowy_x += i;
                    nowy_y += j;
                    while(czyDozwolonyRuch(plansza, nowy_x, nowy_y)) {
                        int[][] plansza_temp_temp = kopiuj(plansza_temp);
                        if(ruch == null) {
                            ruch = new Ruch(x, y);
                            ruch.ilosc_bic += 1;
                        }

                        plansza_temp_temp[nowy_x][nowy_y] = plansza_temp[x][y];
                        plansza_temp_temp[x][y] = 0;
                        bicia_temp.addAll(sprawdzBiciaPion(plansza_temp_temp, nowy_x, nowy_y, ruch.kopiujRuch(nowy_x, nowy_y, planszaToString(plansza_temp_temp))));

                        nowy_x += i;
                        nowy_y += j;
                    }
                }
            }
        }
        if(bicia_temp.isEmpty()) {
            if(ruch != null) {
                bicia.add(ruch);
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

    private boolean czyDozwoloneBiciePion(int[][] plansza, int x, int y, int i, int j, int pionek) {
        return y + 2*j >= 0 && y + 2*j < plansza.length && x + 2*i >= 0 && x + 2*i < plansza.length && plansza[x+i][y+j] % 2 != pionek && plansza[x+i][y+j] != 0 && plansza[x + 2*i][y + 2*j] == 0;
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

    public String getRuch(String plansza) {
        for(Ruch ruch : wynik) {
            if(plansza.compareTo(ruch.getPlanszaPoRuchu()) == 0) {
                return ruch.getRuch();
            }
        }
        return "false";
    }

    public void setPokoj(Pokoj pokoj) {
        this.pokoj = pokoj;
    }
}