package klient.kontroller;

import klient.model.Model;
import klient.model.ModelGry;

/**
 * Klasa kontrolera widoku gry.
 */
public class KontrolerGry implements KontrolerWidoku {
  /** Model widoku */
  private ModelGry model_;

  /** Glowny kontroler aplikacji */
  private KontrolerAplikacji glownyKontroler_;

  /**
   * Metoda odpowiedzialna za przechowanie modelu widoku gry.
   * @param model Model widoku gry.
   */
  @Override
  public void przekazModel(Model model) {
    if(this.model_ != null)
      throw new IllegalStateException("Nie mozna dwa razy przekazac modelu do kontrolera widoku");
    this.model_ = (ModelGry) model;
  }

  /**
   * Metoda odpowiedzialna za przechowanie glownego kontrolera.
   *
   * @param kontrolerGlowny Glowny kontroler aplikacji.
   */
  @Override
  public void przekazGlownyKontroler(KontrolerAplikacji kontrolerGlowny) {
    if(this.glownyKontroler_ != null)
      throw new IllegalStateException("Nie mozna dwa razy przekazac kontrolera "
          + "aplikacji do kontrolera widoku");
    this.glownyKontroler_ = kontrolerGlowny;
  }
}
