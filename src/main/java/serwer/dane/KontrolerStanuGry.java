package serwer.dane;


public class KontrolerStanuGry {
    private StanGry stan;

    public enum StanGry{
        RUCH_BIALYCH,
        RUCH_CZARNYCH,
        SKONCZONA,
        NIEROZPOCZETA,
        PRZERWANA
    }

    /**
     * Kontruktor rozpoczyna kontrolowanie stanu gry
     */
    public KontrolerStanuGry() {
        stan = StanGry.NIEROZPOCZETA;
    }

    public StanGry getStan() {
        return stan;
    }

    /**
     * Rozpoczyna gre od ruchy bialych
     */
    public void START() {
        if(stan == StanGry.NIEROZPOCZETA) {
            stan = StanGry.RUCH_BIALYCH;
        }
    }

    /**
     * Zmienia kolejke i osobe ktora ma sie ruszyc
     */
    public void RUCH() {
        if(stan == StanGry.RUCH_BIALYCH) {
            stan = StanGry.RUCH_CZARNYCH;
        }
        else if(stan == StanGry.RUCH_CZARNYCH) {
            stan = StanGry.RUCH_BIALYCH;
        }
    }

    /**
     * Konczy rozgrywke
     */
    public void ZAKONCZ() {
        stan = StanGry.SKONCZONA;
    }

    /**
     * Przerywa rozgrywke w razie bledow wszelkiego rodzaju
     */
    public void PRZERWIJ() {
        stan = StanGry.PRZERWANA;
    }
}