package klient.kontroler;

import klient.komunikacja.Mediator;
import klient.model.Model;
import klient.model.ModelOgladaniaGry;

/**
 * Klasa kontrolera widoku ogladania gry.
 */
public class KontrolerOgladaniaGry implements KontrolerWidoku {
  /** Model widoku ogladania gry */
  private ModelOgladaniaGry model_;
  /** Mediator pomiedzy aplikacja oraz polaczeniem z serwerem */
  private Mediator mediator_;

  /**
   * Metoda odpowiedzialna za przechowanie modelu.
   *
   * @param model Model widoku pokoju.
   */
  @Override
  public void przekazModel(Model model) {
    this.model_ = (ModelOgladaniaGry) model;
  }

  /**
   * Metoda odpowiedzialna za przekazanie instancji mediatora do aplikacji.
   *
   * @param mediator Mediator miedzy aplikacja oraz polaczeniem z serwerem.
   */
  @Override
  public void przekazMediator(Mediator mediator) {
    this.mediator_ = mediator;
  }

  /**
   * Metoda odpowiedzialna za podjecie proby odnowienia polaczenia.
   */
  @Override
  public void odnowPolaczenie() {
    this.mediator_.odnowPolaczenie();
  }
}
