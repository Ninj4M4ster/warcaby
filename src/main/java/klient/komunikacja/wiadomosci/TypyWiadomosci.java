package klient.komunikacja.wiadomosci;

/**
 * Enumerator reprezentujacy typy mozliwych do wyslania wiadomosci.
 */
public enum TypyWiadomosci {
  /** Typ wiadomosci wyslanej przy wprowadzeniu przez uzytkownika nazwy */
  IMIE {
    /**
     * Metoda zwraca napis reprezentujacy polecenie rozumiane przez serwer.
     *
     * @return Polecenie rozumiane przez serwer.
     */
    @Override
    public String toString() {
      return "Imie";
    }
  },
  /** Typ wiadomosci wyslanej przy zaproszeniu innego gracza do pokoju */
  ZAPROSZENIE {
    /**
     * Metoda zwraca napis reprezentujacy polecenie rozumiane przez serwer.
     *
     * @return Polecenie rozumiane przez serwer.
     */
    @Override
    public String toString() {
      return "Zapros";
    }
  },
  /** Typ wiadomosci wyslanej po rozpoczeciu rozgrywki z wybranymi zasadami */
  ROZPOCZECIE_GRY {
    /**
     * Metoda zwraca napis reprezentujacy polecenie rozumiane przez serwer.
     *
     * @return Polecenie rozumiane przez serwer.
     */
    @Override
    public String toString() {
      return "WTG";
    }
  },
  /** Typ wiadomosci wyslanej po ruszeniu sie pionkiem */
  RUCH_PIONKA {
    /**
     * Metoda zwraca napis reprezentujacy polecenie rozumiane przez serwer.
     *
     * @return Polecenie rozumiane przez serwer.
     */
    @Override
    public String toString() {
      return "RP";
    }
  }
}
