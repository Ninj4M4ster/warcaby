package serwer.dane;

import java.util.EnumMap;

public class KontrolerStanuGry {
    private StanGry stan;

    public enum StanGry{
        RUCH_BIALYCH,
        RUCH_CZARNYCH,
        SKONCZONA,
        NIEROZPOCZETA,
        PRZERWANA
    }

    public KontrolerStanuGry() {
        stan = StanGry.NIEROZPOCZETA;
    }

    public StanGry getStan() {
        return stan;
    }

    public void START() {
        if(stan == StanGry.NIEROZPOCZETA) {
            stan = StanGry.RUCH_BIALYCH;
        }
    }

    public void RUCH() {
        if(stan == StanGry.RUCH_BIALYCH) {
            stan = StanGry.RUCH_CZARNYCH;
        }
        else if(stan == StanGry.RUCH_CZARNYCH) {
            stan = StanGry.RUCH_BIALYCH;
        }
    }

    public void ZAKONCZ() {
        stan = StanGry.SKONCZONA;
    }

    public void PRZERWIJ() {
        stan = StanGry.PRZERWANA;
    }
}
