package serwer.komendy.zasady;

public class Zasady1 extends ZasadyGry {

    int max;

    /**
     * Sprawdza wszytskie zasady warcab dla podanego ruchu (poza tym czy gra skonczona i czy pionka trzeba promowac)
     * @return czy ruch byl poprawny
     */
    public boolean sprawdz() {
        if(!czyWToku() || !czyKolor()) {
            return false;
        }
        if(sprawdzBicie()) {
            return true;
        }
        if(max != 0) {
            return false;
        }
        return (pionek < 3 && ruchPionem()) || (pionek > 2 && ruchKrol());
    }

    /**
     * Sprawdza czy bicie powinno wystapic, jak dlugie czy wystapilo odpowiednio dlugie oraz czy bylo poprawne
     * @return czy potrzebnie długie bicie wytapilo
     */
    boolean sprawdzBicie() {
        int max_bicie = 0, max_temp;

        for(int x = 0; x < plansza.length; x += 1) {
            for(int y = 0; y < plansza.length; y += 1) {
                if(pionek % 2 == plansza[x][y] % 2 && plansza[x][y] != 0) {
                    if(plansza[x][y] < 3) {
                        max_bicie = ((max_temp = mozliweBiciePion(x, y, plansza)) > max_bicie ? max_temp : max_bicie);
                    }
                    else {
                        max_bicie = ((max_temp = mozliweBicieKrol(x, y, plansza)) > max_bicie ? max_temp : max_bicie);
                    }
                }
            }
        }
        this.max = max_bicie;
        if(max_bicie + 1 == ruchy.size() / 2) {
            return bij();
        }
        return false;
    }

    /**
     * Sprawdza czy w obecnej planszy jest mozliwe bicie pionkiem
     * @param x - kolumna sprawdzanego pionka
     * @param y - rzad sprawdzanego pionka
     * @param plansza - obecna plansza
     * @return int liczba bic w jednym ruchu
     */
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

    /**
     * Sprawdza czy w obecnej planszy jest mozliwe bicie krolem
     * @param x - kolumna sprawdzanego krola
     * @param y - rzad sprawdzanego krola
     * @param plansza - obecna plansza
     * @return int liczba bic w jednym ruchu
     */
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

    /**
     * Sprawdza czy wykonane bicie jest poprawne
     * @return true jesli bicie bylo poprawne
     */
    private boolean bij() {
        int[][] plansza_temp = copyPlansza(plansza);

        for(int i = 0; i + 3 < ruchy.size(); i += 2) {
            if(pionek < 3 && dlugoscBicia(ruchy.get(i), ruchy.get(i + 1), ruchy.get(i + 2), ruchy.get(i + 3))) {
                int pionek_temp = plansza_temp[(ruchy.get(i) + ruchy.get(i + 2)) / 2][(ruchy.get(i + 1) + ruchy.get(i + 3)) / 2];

                if(pionek_temp != 0 && pionek_temp % 2 != pionek % 2 && plansza_temp[ruchy.get(i + 2)][ruchy.get(i + 3)] == 0) {
                    plansza_temp[(ruchy.get(i) + ruchy.get(i + 2)) / 2][(ruchy.get(i + 1) + ruchy.get(i + 3)) / 2] = 0;
                    plansza_temp[ruchy.get(i)][ruchy.get(i + 1)] = 0;
                    plansza_temp[ruchy.get(i + 2)][ruchy.get(i + 3)] = pionek;
                }
                else {
                    return false;
                }
            }
            else if(pionek > 2 && dlugoscBicia(ruchy.get(i), ruchy.get(i + 2), ruchy.get(i + 2), ruchy.get(i + 3))) {
                int x_dozbicia = -1, y_dozbicia = -1;

                for(int j = 1; j < Math.abs((ruchy.get(j + 2) - ruchy.get(j))); j += 1) {
                    int pionek_temp = plansza_temp[ruchy.get(i) + ((ruchy.get(i+2) - ruchy.get(i))/Math.abs(ruchy.get(i+2) - ruchy.get(i)) * j)][ruchy.get(i+1) + ((ruchy.get(i+3) - ruchy.get(i+1))/Math.abs(ruchy.get(i+3) - ruchy.get(i+1)) * j)];


                    if(pionek_temp != 0) {
                        if(x_dozbicia != -1 || y_dozbicia != -1 || pionek_temp % 2 == pionek % 2) {
                            return false;
                        }

                        x_dozbicia = ruchy.get(i) + (ruchy.get(i+2) / Math.abs(ruchy.get(i+2)) * j);
                        y_dozbicia = ruchy.get(i + 1) + (ruchy.get(i+3) / Math.abs(ruchy.get(i+3)) * j);
                    }
                }

                plansza_temp[ruchy.get(i)][ruchy.get(i + 1)] = 0;
                plansza_temp[x_dozbicia][y_dozbicia] = 0;
                plansza_temp[ruchy.get(i + 2)][ruchy.get(i + 3)] = pionek;
            }
            else {
                return false;
            }
        }
        return true;
    }
}