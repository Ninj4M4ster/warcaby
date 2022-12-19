package klient.kontroller;

import klient.model.Model;
import klient.model.ModelGry;

public class KontrolerGry implements KontrolerWidoku {

  private ModelGry model_;
  private KontrolerAplikacji glownyKontroler_;
  @Override
  public void przekazModel(Model model) {
    if(this.model_ != null)
      throw new IllegalStateException("Nie mozna dwa razy przekazac modelu do kontrolera widoku");
    this.model_ = (ModelGry) model;
  }

  @Override
  public void przekazGlownyKontroler(KontrolerAplikacji kontrolerGlowny) {
    if(this.glownyKontroler_ != null)
      throw new IllegalStateException("Nie mozna dwa razy przekazac kontrolera "
          + "aplikacji do kontrolera widoku");
    this.glownyKontroler_ = kontrolerGlowny;
  }
}
