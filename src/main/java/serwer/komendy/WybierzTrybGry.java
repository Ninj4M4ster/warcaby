package serwer.komendy;

import entities.Gra;
import serwer.baza_danych.MenadzerBazyDanych;
import serwer.dane.Gracz;
import serwer.dane.Pokoj;
import serwer.komendy.zasady.Zasady1;
import serwer.komendy.zasady.ZasadyGry;

public class WybierzTrybGry implements Komenda{
    private int plansza_rozmiar;
    private Gracz gracz;

    public WybierzTrybGry(Gracz gracz) {
        this.gracz = gracz;
    }

    /**
     * Wybiera rodzaj gry
     * @param rodzaj - wariant gry
     * @return zasady przypisane do rodzaju gry
     */
    public ZasadyGry wybierz(String rodzaj) {
        switch (rodzaj) {
            case "0":
                plansza_rozmiar = 8;
                return new Zasady1();
            case "1":
                plansza_rozmiar = 10;
                return new Zasady1();
            case "2":
                plansza_rozmiar = 12;
                return new Zasady1();
            default:
                return null;
        }
    }

    /**
     * Wykonuje komende, tworzy plansze, losuje kolory graczy iw ysyla obie informacje onu graczom
     * @param reszta - wariant gry
     * @param pokoj - pokoj w ktorym jest gracz wysylajacy komende
     * @return czy poprawnie wykonano komende + kolor i plansze jesli poprawnie
     */
    @Override
    public String Wykonaj(String reszta, Pokoj pokoj) {
        if(pokoj != null) {
            if(gracz == pokoj.getGosc()) {
                return "false";
            }

            pokoj.setZasadyGry(wybierz(reszta));

            int[][] plansza = tworzPlansze();

            pokoj.setPlansza(plansza);

            pokoj.getMistrz().setKolor((int) (Math.random() * (2 - 1) + 1));
            pokoj.getGosc().setKolor(3 - pokoj.getMistrz().getKolor());

            pokoj.getGosc().getSt().wyslij("plansza " + pokoj.getGosc().getKolor() + pokoj.planszaToString());
            pokoj.kontroler_stanu_gry.START();

            // wprowadz nowa gre i poczatkowy stan planszy do bazy danych
            Gra gra = MenadzerBazyDanych.instancja().wprowadzNowaGre(
                    pokoj.getMistrz().getNick(),
                pokoj.getGosc().getNick(),
                pokoj.getMistrz().getKolor(),
                pokoj.getGosc().getKolor());

            MenadzerBazyDanych.instancja().wprowadzNowyRuch(gra, pokoj.getLicznik(),
                pokoj.planszaToString());
            pokoj.incrementLicznik();
            pokoj.setGra(gra);

            return "true " + pokoj.getMistrz().getKolor() + pokoj.planszaToString();
        }
        return "false";
    }

    /**
     * Tworzy plansze
     * @return plansza
     */
    public int[][] tworzPlansze() {
        int[][] plansza_temp = new int[plansza_rozmiar][plansza_rozmiar];

        for (int x = 0; x < plansza_rozmiar; x += 1) {
            for (int y = 0; y < plansza_rozmiar; y += 1) {
                if (y != ((plansza_rozmiar / 2) - 1) && y != (plansza_rozmiar / 2) && (x + y) % 2 == 0) {
                    if (y < (plansza_rozmiar / 2)) {
                        plansza_temp[x][y] = 1;
                    } else {
                        plansza_temp[x][y] = 2;
                    }
                } else {
                    plansza_temp[x][y] = 0;
                }
            }
        }
        return plansza_temp;
    }
}