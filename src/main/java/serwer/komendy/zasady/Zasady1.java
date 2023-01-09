package serwer.komendy.zasady;

import serwer.dane.KontrolerStanuGry;

public class Zasady1 extends ZasadyGry {
    private boolean mozliweBicie() {
        int max_bicie;

        for(int x = 0; x < plansza.length; x += 1) {
            for(int y = 0; y < plansza.length; y += 1) {
                if(pionek == 1 || pionek == 2) {
                    max_bicie = biciePion(x, y, plansza);
                    if(max_bicie == 0) {
                        return true;
                    }
                    else if(((ruchy.size() / 2) - 1) == max_bicie) {
                        if(bij()) {
                            return true;
                        }
                    }
                }
                else if(pionek == 3 || pionek == 4) {
                    max_bicie = bicieKrol(x, y, plansza);
                    if(max_bicie == 0) {
                        return true;
                    }
                    else if(((ruchy.size() / 2) - 1) == max_bicie) {
                        if(bij()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private int biciePion(int x, int y, int[][] plansza) {
        int[][] plansza_temp = plansza;
        int pionek = plansza_temp[x][y];
        int max = 0;
        int temp;

        if(czyWPlanszy(x+1, y+1) && plansza_temp[x+1][y+1] != 0 && plansza_temp[x+1][y+1] != pionek && plansza_temp[x+1][y+1] != pionek+2) {
            if(czyWPlanszy(x+2, y+2) && plansza_temp[x+2][y+2] == 0) {
                plansza_temp[x+1][y+1] = 0;
                plansza_temp[x][y] = 0;
                plansza_temp[x+2][y+2] = pionek;
                max = ((temp = biciePion(x+2, y+2, plansza_temp)) > max ? temp : max);
            }
        }

        plansza_temp = plansza;
        if(czyWPlanszy(x-1, y+1) && plansza_temp[x-1][y+1] != 0 && plansza_temp[x-1][y+1] != pionek && plansza_temp[x-1][y+1] != pionek+2) {
            if(czyWPlanszy(x-2, y+2) && plansza_temp[x-2][y+2] == 0) {
                plansza_temp[x-1][y+1] = 0;
                plansza_temp[x][y] = 0;
                plansza_temp[x-2][y+2] = pionek;
                max = ((temp = biciePion(x-2, y+2, plansza_temp)) > max ? temp : max);
            }
        }

        plansza_temp = plansza;
        if(czyWPlanszy(x+1, y-1) && plansza_temp[x+1][y-1] != 0 && plansza_temp[x+1][y-1] != pionek && plansza_temp[x+1][y-1] != pionek+2) {
            if(czyWPlanszy(x+2, y-2) && plansza_temp[x+2][y-2] == 0) {
                plansza_temp[x+1][y-1] = 0;
                plansza_temp[x][y] = 0;
                plansza_temp[x+2][y-2] = pionek;
                max = ((temp = biciePion(x+2, y-2, plansza_temp)) > max ? temp : max);
            }
        }

        plansza_temp = plansza;
        if(czyWPlanszy(x-1, y-1) && plansza_temp[x-1][y-1] != 0 && plansza_temp[x-1][y-1] != pionek && plansza_temp[x-1][y-1] != pionek+2) {
            if(czyWPlanszy(x-2, y-2) && plansza_temp[x-2][y-2] == 0) {
                plansza_temp[x-1][y-1] = 0;
                plansza_temp[x][y] = 0;
                plansza_temp[x-2][y-2] = pionek;
                max = ((temp = biciePion(x-2, y-2, plansza_temp)) > max ? temp : max);
            }
        }

        return max;
    }

    private int bicieKrol(int x, int y, int[][] plansza) {
        int[][] plansza_temp = plansza;
        int pionek = plansza_temp[x][y];
        int max = 0;
        int temp;

        if(czyWPlanszy(x+1, y+1) && plansza_temp[x+1][y+1] != 0 && plansza_temp[x+1][y+1] != pionek && plansza_temp[x+1][y+1] != pionek+2) {
            if(czyWPlanszy(x+2, y+2) && plansza_temp[x+2][y+2] == 0) {
                plansza_temp[x+1][y+1] = 0;
                plansza_temp[x][y] = 0;
                plansza_temp[x+2][y+2] = pionek;
                max = ((temp = biciePion(x+2, y+2, plansza_temp)) > max ? temp : max);
            }
        }

        plansza_temp = plansza;
        if(czyWPlanszy(x-1, y+1) && plansza_temp[x-1][y+1] != 0 && plansza_temp[x-1][y+1] != pionek && plansza_temp[x-1][y+1] != pionek+2) {
            if(czyWPlanszy(x-2, y+2) && plansza_temp[x-2][y+2] == 0) {
                plansza_temp[x-1][y+1] = 0;
                plansza_temp[x][y] = 0;
                plansza_temp[x-2][y+2] = pionek;
                max = ((temp = biciePion(x-2, y+2, plansza_temp)) > max ? temp : max);
            }
        }

        plansza_temp = plansza;
        if(czyWPlanszy(x+1, y-1) && plansza_temp[x+1][y-1] != 0 && plansza_temp[x+1][y-1] != pionek && plansza_temp[x+1][y-1] != pionek+2) {
            if(czyWPlanszy(x+2, y-2) && plansza_temp[x+2][y-2] == 0) {
                plansza_temp[x+1][y-1] = 0;
                plansza_temp[x][y] = 0;
                plansza_temp[x+2][y-2] = pionek;
                max = ((temp = biciePion(x+2, y-2, plansza_temp)) > max ? temp : max);
            }
        }

        plansza_temp = plansza;
        if(czyWPlanszy(x-1, y-1) && plansza_temp[x-1][y-1] != 0 && plansza_temp[x-1][y-1] != pionek && plansza_temp[x-1][y-1] != pionek+2) {
            if(czyWPlanszy(x-2, y-2) && plansza_temp[x-2][y-2] == 0) {
                plansza_temp[x-1][y-1] = 0;
                plansza_temp[x][y] = 0;
                plansza_temp[x-2][y-2] = pionek;
                max = ((temp = biciePion(x-2, y-2, plansza_temp)) > max ? temp : max);
            }
        }

        return max;
    }

    private boolean bij() {
        int[][] plansza_temp = plansza;

        for(int i = 0; i + 3 < ruchy.size(); i += 2) {
            if(pionek < 3 && dlugoscBicia(ruchy.get(i), ruchy.get(i + 2), ruchy.get(i + 2), ruchy.get(i + 3))
                    && plansza_temp[(ruchy.get(i) + ruchy.get(i + 2)) / 2][(ruchy.get(i + 1) + ruchy.get(i + 3)) / 2] != pionek
                    && plansza_temp[(ruchy.get(i) + ruchy.get(i + 2)) / 2][(ruchy.get(i + 1) + ruchy.get(i + 3)) / 2] != pionek - 2
                    && plansza_temp[ruchy.get(i + 2)][ruchy.get(i + 3)] == 0) {

                plansza_temp[(ruchy.get(i) + ruchy.get(i + 2)) / 2][(ruchy.get(i + 1) + ruchy.get(i + 3)) / 2] = 0;
                plansza_temp[ruchy.get(i)][ruchy.get(i + 1)] = 0;
                plansza_temp[ruchy.get(i + 2)][ruchy.get(i + 3)] = pionek;
            }
            if(pionek > 2 && dlugoscBicia(ruchy.get(i), ruchy.get(i + 2), ruchy.get(i + 2), ruchy.get(i + 3))) {
                int x_dozbicia = -1, y_dozbicia = -1;
                for(int j = 1; j < Math.abs((ruchy.get(j + 2) - ruchy.get(j))); j += 1) {
                    int pionek_temp = plansza_temp[ruchy.get(i) + (ruchy.get(i+2)/Math.abs(ruchy.get(i+2)) * j)][ruchy.get(i + 1) + (ruchy.get(i+3)/Math.abs(ruchy.get(i+3)) * j)];

                    if(pionek_temp == pionek || pionek_temp == pionek + 2) {
                        return false;
                    }
                    if(pionek_temp != 0) {
                        if(x_dozbicia != -1 || y_dozbicia != -1) {
                            return false;
                        }

                        x_dozbicia = ruchy.get(i) + (ruchy.get(i+2)/Math.abs(ruchy.get(i+2)) * j);
                        y_dozbicia = ruchy.get(i + 1) + (ruchy.get(i+3) / Math.abs(ruchy.get(i+3)) * j);
                    }
                }

                plansza_temp[(ruchy.get(i) + ruchy.get(i + 2)) / 2][(ruchy.get(i + 1) + ruchy.get(i + 3)) / 2] = 0;
                plansza_temp[ruchy.get(i)][ruchy.get(i + 1)] = 0;
                plansza_temp[ruchy.get(i + 2)][ruchy.get(i + 3)] = pionek;
            }
            else {
                return false;
            }
        }
        return true;
    }
}