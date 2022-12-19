package serwer.dane;

import java.util.EnumMap;

public class KontrolerStanuGry {
    StanGry stan;

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
}
