package klient.kontroler;

import klient.komunikacja.Mediator;
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
   * Metoda odpowiedzialna za przekazanie instancji mediatora do kontrolera.
   *
   * @param mediator Mediator miedzy aplikacja oraz polaczeniem z serwerem.
   */
  void przekazMediator(Mediator mediator);

  /**
   * Metoda odpowiedzialna za podjcie proby ponownego polaczenia z serwerem.
   */
  void odnowPolaczenie();
}
