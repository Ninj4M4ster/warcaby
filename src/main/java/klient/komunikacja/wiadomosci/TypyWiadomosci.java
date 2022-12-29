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
}
