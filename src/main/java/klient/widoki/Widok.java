package klient.widoki;

import javafx.scene.Parent;
import klient.kontroler.KontrolerWidoku;
import klient.model.Model;

/**
 * Interfejs przedstawiajacy widok aplikacji.
 * TODO(Jakub Drzewiecki): Zmienic na klase abstrakcyjna oraz dodac metode
 *  tworzaca pasek informujacy o aktualnym stanie polaczenia.
 */
public interface Widok {

  /**
   * Metoda sluzaca do utworzenia widoku i zwrocenia jego konteneru.
   *
   * @param kontroler Kontroler widoku.
   * @param model Model widoku.
   * @return Kontener wszystkich elementow widoku.
   */
  Parent utworzWidok(KontrolerWidoku kontroler,
      Model model);
}
