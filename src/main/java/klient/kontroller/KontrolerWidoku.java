package klient.kontroller;

import klient.model.Model;

/**
 * Interfejs kontrolera widoku.
 * Kazdy kontroler widoku musi go implementowac.
 */
public interface KontrolerWidoku {

  /**
   * Metoda odpowiedzialna za przechowanie modelu widoku.
   *
   * @param model Model widoku.
   */
  void przekazModel(Model model);

  /**
   * Metoda odpowiedzialna za przechowanie glownego kontrolera aplikacji.
   * @param kontrolerGlowny Glowny kontroler aplikacji.
   * TODO(Jakub Drzewiecki): Zmienic interfejs na klase abstrakcyjna i zaimplementowac ta metode.
   */
  void przekazGlownyKontroler(KontrolerAplikacji kontrolerGlowny);
}
