package klient.kontroller;

import klient.model.Model;
import klient.model.ModelGraczyOnline;

public class KontrolerWidokuGraczyOnline implements KontrolerWidoku {
  private ModelGraczyOnline model_;
  private KontrolerAplikacji kontrolerGlowny_;

  @Override
  public void przekazModel(Model model) {
    this.model_ = (ModelGraczyOnline) model;
  }

  @Override
  public void przekazGlownyKontroler(KontrolerAplikacji kontrolerGlowny) {
    this.kontrolerGlowny_ = kontrolerGlowny;
  }

  public void zapiszNazweGracza(String nazwa) {
    if(nazwa.isBlank())
      return;
    // TODO(Jakub Drzewiecki): dodać animację przejścia do widoku listy graczy online
    this.model_.ustawNazweGracza(nazwa);
    this.kontrolerGlowny_.przekazNazweDoSerwera(nazwa);
    this.model_.ustawGoreMenu(this.model_.kontenerOpisuListyGraczy());
    this.model_.ustawCentrumMenu(this.model_.kontenerListyGraczy());
  }
}
