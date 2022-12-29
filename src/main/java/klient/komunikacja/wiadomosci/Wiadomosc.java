package klient.komunikacja.wiadomosci;

/**
 * Klasa reprezentujaca wiadomosc wysylana do serwera przez aplikacje.
 */
public class Wiadomosc {
  /** Argumenty wiadomosci */
  private String[] argumenty_;
  /** Typ wiadomosci, zalezny od dzialania uzytkownik */
  private TypyWiadomosci typWiadomosci_;

  /**
   * Konstruktor.
   * @param argument Pojedynczy argument wiadomosci.
   * @param typ Typ wiadomosci.
   */
  public Wiadomosc(String argument, TypyWiadomosci typ) {
    this.argumenty_ = new String[]{argument};
    this.typWiadomosci_ = typ;
  }

  /**
   * Konstruktor.
   * @param argumenty Argumenty wiadomosci.
   * @param typ Typ wiadomosci.
   */
  public Wiadomosc(String[] argumenty, TypyWiadomosci typ) {
    this.argumenty_ = argumenty;
    this.typWiadomosci_ = typ;
  }

  /**
   * Metoda zwracajaca typ utworzonej wiadomosci.
   *
   * @return Typ wiadomosci.
   */
  public TypyWiadomosci typWiadomosci() {
    return this.typWiadomosci_;
  }

  /**
   * Nadpisana metoda toString.
   * Przy wywolaniu tworzy napis gotowy do wyslania do serwera.
   *
   * @return Sformatowany napis gotowy do wyslania do serwera.
   */
  @Override
  public String toString() {
    StringBuilder komenda = new StringBuilder(this.typWiadomosci_.toString());
    for(String argument: this.argumenty_) {
      komenda.append(" ").append(argument);
    }
    return komenda.toString();
  }
}
