package klient;

/**
 * Enumerator przedstawiajacy wszystkie dostepne warianty zasad rozgrywki.
 */
public enum ZasadyGry {
  /** Klasyczna rozgrywka na planszy 8x8 */
  KLASYCZNE {
    /**
     * Metoda ta zwraca napis wyswietlany w aplikacji uzytkownikowi.
     *
     * @return Napis wyswietlany uzytkownikowi.
     */
    @Override
    public String toString() {
      return "Warcaby klasyczne (brazylijskie)";
    }

    /**
     * Metoda zwracajaca numer zasad. Numer ten jest wysylany do serwera.
     *
     * @return Numer zasad wysylany do serwera.
     */
    @Override
    public String numer() {
      return "0";
    }
  },

  /** Rozgrywka z klasycznymi zasadami na planszy 10x10 */
  POLSKIE {
    /**
     * Metoda ta zwraca napis wyswietlany w aplikacji uzytkownikowi.
     *
     * @return Napis wyswietlany uzytkownikowi.
     */
    @Override
    public String toString() {
      return "Warcaby polskie";
    }

    /**
     * Metoda zwracajaca numer zasad. Numer ten jest wysylany do serwera.
     *
     * @return Numer zasad wysylany do serwera.
     */
    @Override
    public String numer() {
      return "1";
    }
  },

  /** Rozgrywka z klasycznymi zasadami na planszy 12x12 */
  KANADYJSKIE {
    /**
     * Metoda ta zwraca napis wyswietlany w aplikacji uzytkownikowi.
     *
     * @return Napis wyswietlany uzytkownikowi.
     */
    @Override
    public String toString() {
      return "Warcaby kanadyjskie";
    }

    /**
     * Metoda zwracajaca numer zasad. Numer ten jest wysylany do serwera.
     *
     * @return Numer zasad wysylany do serwera.
     */
    @Override
    public String numer() {
      return "2";
    }
  };

  /**
   * Metoda zwracajaca numer zasad. Uruchamiajac ja na klasie,
   * a nie jednej z wartosci, uzyskamy blad.
   *
   * @return Numer zasad wysylany do serwera.
   */
  public String numer() {
    throw new IllegalAccessError("Nie mozna uzyskac numeru dla niewybranych zasad.");
  }
}
