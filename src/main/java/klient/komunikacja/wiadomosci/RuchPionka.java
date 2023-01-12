package klient.komunikacja.wiadomosci;

import java.util.ArrayList;

/**
 * Klasa sluzaca do przechowywania informacji o wspolrzednych, po ktorych porusza sie pionek.
 * Umozliwia pozniej proste wyslanie tych informacji.
 */
public class RuchPionka {
  /** Lista z wprowadzonymi kolejnymi wspolrzednymi kolejnych ruchow */
  private final ArrayList<Integer> listaRuchow_;

  /**
   * Konstruktor.
   */
  public RuchPionka() {
    listaRuchow_ = new ArrayList<>();
  }

  /**
   * Metoda odpowiedzialna za dodanie wspolrzednych do listy.
   *
   * @param kolumna Kolumna planszy.
   * @param rzad Rzad planszy.
   */
  public void dodajRuch(int kolumna, int rzad) {
    listaRuchow_.add(kolumna);
    listaRuchow_.add(rzad);
  }

  /**
   * Metoda sygnalizujaca, czy rozpoczeto wprowadzac informacje o ruchu.
   *
   * @return Czy rozpoczeto wprowadzac informacje o ruchu?
   */
  public boolean ruchZaczety() {
    return !(listaRuchow_.size() == 0);
  }

  /**
   * Metoda tworzaca napis ze wszystkimi wspolrzednymi planszy w celu wyslania do serwera.
   * Napis ten ma rozdzielone spacjami wspolrzedne, przy czym pierwsza to kolumna, a druga to rzad.
   *
   * @return Napis ze wspolrzednymi po ktorych poruszal sie pionek.
   */
  public String napisListaRuchow() {
    StringBuilder napis = new StringBuilder();
    for(int i=0; i < listaRuchow_.size(); i += 2) {
      napis.append(listaRuchow_.get(i + 1));
      napis.append(" ");
      napis.append(listaRuchow_.get(i));
      if(i != listaRuchow_.size() - 1)
        napis.append(" ");
    }
    return napis.toString();
  }

  /**
   * Metoda resetujaca liste wprowadzonych ruchow.
   */
  public void usunRuchy() {
    listaRuchow_.clear();
  }
}
