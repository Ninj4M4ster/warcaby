package serwer.komendy;

import serwer.dane.Gracz;
import serwer.dane.KontrolerStanuGry;
import serwer.dane.Pokoj;
import serwer.komendy.zasady.ZasadyGry;

import java.util.ArrayList;
import java.util.List;

public class PodajDostepneRuchy extends ZasadyGry implements Komenda {
    Gracz gracz;

    List<Ruch> bez_bicia = new ArrayList<Ruch>();
    List<Ruch> bicia = new ArrayList<Ruch>();

    public PodajDostepneRuchy(Gracz gracz) {
        this.gracz = gracz;
    }

    class Ruch implements Comparable<Ruch> {
        private int ilosc_bic = 0;

        @Override
        public int compareTo(Ruch o) {
            return this.ilosc_bic - o.ilosc_bic;
        }
    }
    @Override
    public String Wykonaj(String reszta, Pokoj pokoj) {
        for(int x = 0; x < pokoj.getPlansza().length; x += 1) {
            for(int y = 0; y < pokoj.getPlansza().length; y += 1) {
                if(pokoj.kontroler_stanu_gry.getStan() == KontrolerStanuGry.StanGry.RUCH_BIALYCH && pokoj.getPlansza()[x][y] % 2 == 1) {

                }
                else if(pokoj.kontroler_stanu_gry.getStan() == KontrolerStanuGry.StanGry.RUCH_CZARNYCH && pokoj.getPlansza()[x][y] % 2 == 0 && pokoj.getPlansza()[x][y] != 0) {

                }
            }
        }
        return "true";
    }

    private int mozliweBiciePion(int x, int y, int[][] plansza) {
        int[][] plansza_temp = copyPlansza(plansza);
        int pionek = plansza_temp[x][y];
        int max = 0;
        int temp;

        if(czyWPlanszy(x+1, y+1) && plansza_temp[x+1][y+1] != 0 && plansza_temp[x+1][y+1] % 2 != pionek % 2) {
            if(czyWPlanszy(x+2, y+2) && plansza_temp[x+2][y+2] == 0) {
                plansza_temp[x+1][y+1] = 0;
                plansza_temp[x][y] = 0;
                plansza_temp[x+2][y+2] = pionek;
                max = ((temp = mozliweBiciePion(x+2, y+2, plansza_temp) + 1) > max ? temp : max);
            }
        }

        plansza_temp =copyPlansza(plansza);
        if(czyWPlanszy(x-1, y+1) && plansza_temp[x-1][y+1] != 0 && plansza_temp[x-1][y+1] % 2 != pionek % 2) {
            if(czyWPlanszy(x-2, y+2) && plansza_temp[x-2][y+2] == 0) {
                plansza_temp[x-1][y+1] = 0;
                plansza_temp[x][y] = 0;
                plansza_temp[x-2][y+2] = pionek;
                max = ((temp = mozliweBiciePion(x-2, y+2, plansza_temp) + 1) > max ? temp : max);
            }
        }

        plansza_temp = copyPlansza(plansza);
        if(czyWPlanszy(x+1, y-1) && plansza_temp[x+1][y-1] != 0 && plansza_temp[x+1][y-1] % 2 != pionek % 2) {
            if(czyWPlanszy(x+2, y-2) && plansza_temp[x+2][y-2] == 0) {
                plansza_temp[x+1][y-1] = 0;
                plansza_temp[x][y] = 0;
                plansza_temp[x+2][y-2] = pionek;
                max = ((temp = mozliweBiciePion(x+2, y-2, plansza_temp) + 1) > max ? temp : max);
            }
        }

        plansza_temp = copyPlansza(plansza);
        if(czyWPlanszy(x-1, y-1) && plansza_temp[x-1][y-1] != 0 && plansza_temp[x-1][y-1] % 2 != pionek % 2) {
            if(czyWPlanszy(x-2, y-2) && plansza_temp[x-2][y-2] == 0) {
                plansza_temp[x-1][y-1] = 0;
                plansza_temp[x][y] = 0;
                plansza_temp[x-2][y-2] = pionek;
                max = ((temp = mozliweBiciePion(x-2, y-2, plansza_temp) + 1) > max ? temp : max);
            }
        }

        return max;
    }

    private int mozliweBicieKrol(int x, int y, int[][] plansza) {
        int[][] plansza_temp = copyPlansza(plansza);
        int pionek = plansza_temp[x][y];
        int max = 0;
        int temp;

        boolean flaga = true;
        plansza_temp = plansza;
        int x_dozbicia = -1, y_dozbicia = -1, i = 1;
        while(flaga) {
            if(!czyWPlanszy(x+i, y+i)) {
                flaga = false;
            }
            else if(plansza_temp[x+i][y+i] != 0 && plansza_temp[x+i][y+i] % 2 != pionek % 2) {
                flaga = false;
            }
            else if(plansza_temp[x+i][y+i] == 0) {
                if(x_dozbicia != -1) {
                    plansza_temp[x][y] = 0;
                    plansza_temp[x_dozbicia][y_dozbicia] = 0;
                    plansza_temp[x+i][y+i] = pionek;

                    max = ((temp = mozliweBicieKrol(x+i, y+i, plansza_temp) + 1) > max ? temp : max);
                }
            }
            else {
                if(x_dozbicia == -1) {
                    x_dozbicia = x + i;
                    y_dozbicia = y + i;
                }
                else {
                    flaga = false;
                }
            }

            i += 1;
        }

        flaga = true;
        plansza_temp = copyPlansza(plansza);
        x_dozbicia = -1; y_dozbicia = -1; i = 1;
        while(flaga) {
            if(!czyWPlanszy(x-i, y+i)) {
                flaga = false;
            }
            else if(plansza_temp[x-i][y+i] != 0 && plansza_temp[x-i][y+i] % 2 != pionek % 2) {
                flaga = false;
            }
            else if(plansza_temp[x-i][y+i] == 0) {
                if(x_dozbicia != -1) {
                    plansza_temp[x][y] = 0;
                    plansza_temp[x_dozbicia][y_dozbicia] = 0;
                    plansza_temp[x-i][y+i] = pionek;

                    max = ((temp = mozliweBicieKrol(x-i, y+i, plansza_temp) + 1) > max ? temp : max);
                }
            }
            else {
                if(x_dozbicia == -1) {
                    x_dozbicia = x - i;
                    y_dozbicia = y + i;
                }
                else {
                    flaga = false;
                }
            }

            i += 1;
        }

        flaga = true;
        plansza_temp = copyPlansza(plansza);
        x_dozbicia = -1; y_dozbicia = -1; i = 1;
        while(flaga) {
            if(!czyWPlanszy(x-i, y-i)) {
                flaga = false;
            }
            else if(plansza_temp[x-i][y-i] != 0 && plansza_temp[x-i][y-i] % 2 != pionek % 2) {
                flaga = false;
            }
            else if(plansza_temp[x-i][y-i] == 0) {
                if(x_dozbicia != -1) {
                    plansza_temp[x][y] = 0;
                    plansza_temp[x_dozbicia][y_dozbicia] = 0;
                    plansza_temp[x-i][y-i] = pionek;

                    max = ((temp = mozliweBicieKrol(x-i, y-i, plansza_temp) + 1) > max ? temp : max);
                }
            }
            else {
                if(x_dozbicia == -1) {
                    x_dozbicia = x - i;
                    y_dozbicia = y - i;
                }
                else {
                    flaga = false;
                }
            }

            i += 1;
        }

        flaga = true;
        plansza_temp = copyPlansza(plansza);
        x_dozbicia = -1; y_dozbicia = -1; i = 1;
        while(flaga) {
            if(!czyWPlanszy(x+i, y-i)) {
                flaga = false;
            }
            else if(plansza_temp[x+i][y-i] != 0 && plansza_temp[x+i][y-i] % 2 != pionek % 2) {
                flaga = false;
            }
            else if(plansza_temp[x+i][y-i] == 0) {
                if(x_dozbicia != -1) {
                    plansza_temp[x][y] = 0;
                    plansza_temp[x_dozbicia][y_dozbicia] = 0;
                    plansza_temp[x+i][y-i] = pionek;

                    max = ((temp = mozliweBicieKrol(x+i, y-i, plansza_temp) + 1) > max ? temp : max);
                }
            }
            else {
                if(x_dozbicia == -1) {
                    x_dozbicia = x + i;
                    y_dozbicia = y - i;
                }
                else {
                    flaga = false;
                }
            }

            i += 1;
        }

        return max;
    }
}