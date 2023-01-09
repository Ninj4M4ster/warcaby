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
                    && plansza_temp[(ruchy.get(i) + ruchy.get(i + 2)) / 2][(ruchy.get(i + 1) + ruchy.get(i + 3)) / 2] != pionek + 2
                    && plansza_temp[ruchy.get(i + 2)][ruchy.get(i + 3)] == 0) {

                plansza_temp[(ruchy.get(i) + ruchy.get(i + 2)) / 2][(ruchy.get(i + 1) + ruchy.get(i + 3)) / 2] = 0;
                plansza_temp[ruchy.get(i)][ruchy.get(i + 1)] = 0;
                plansza_temp[ruchy.get(i + 2)][ruchy.get(i + 3)] = pionek;
            }
            if(pionek > 2 && dlugoscBicia(ruchy.get(i), ruchy.get(i + 2), ruchy.get(i + 2), ruchy.get(i + 3))
                    && plansza_temp[(ruchy.get(i) + ruchy.get(i + 2)) / 2][(ruchy.get(i + 1) + ruchy.get(i + 3)) / 2] != pionek
                    && plansza_temp[(ruchy.get(i) + ruchy.get(i + 2)) / 2][(ruchy.get(i + 1) + ruchy.get(i + 3)) / 2] != pionek + 2
                    && plansza_temp[ruchy.get(i + 2)][ruchy.get(i + 3)] == 0) {

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

    private boolean dlugoscBicia(int x, int y, int nowy_x, int nowy_y) {
        if(pionek < 3 && Math.abs(nowy_x - x) == 2 && Math.abs(nowy_y - y) == 2) {
            return true;
        }
        if(pionek >= 3 && Math.abs(nowy_x - x) >= 2 && Math.abs(nowy_x - x) == Math.abs(nowy_y - y) ) {
            return true;
        }
        return false;
    }

    private boolean czyWPlanszy(int x, int y) {
        if(x < plansza.length && x >= 0 && y < plansza.length && y >= 0) {
            return true;
        }
        return false;
    }
}