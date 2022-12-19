package klient.kontroller;

import javafx.scene.control.Label;
import klient.model.Model;
import klient.model.ModelPokoju;

public class KontrolerPokoju implements KontrolerWidoku{
  private ModelPokoju model_;
  private KontrolerAplikacji kontrolerGlowny_;

  @Override
  public void przekazModel(Model model) {
    if(this.model_ != null)
      throw new IllegalStateException("Nie mozna dwa razy przekazac modelu do kontrolera widoku.");
    this.model_ = (ModelPokoju) model;
  }

  @Override
  public void przekazGlownyKontroler(KontrolerAplikacji kontrolerGlowny) {
    if(this.kontrolerGlowny_ != null)
      throw new IllegalStateException("Nie mozna dwa razy przekaza kontrolera aplikacji "
          + "do kontrolera widoku.");
    this.kontrolerGlowny_ = kontrolerGlowny;
  }

  public void wyslijWiadomosc() {
    String wiadomosc = this.model_.tekstWiadomosci().get();
    // TODO(Jakub Drzewiecki): Utworzyć klasę reprezentującą chmurki wiadomosci, ktore beda dodawane do historii chatu
    if(!wiadomosc.isBlank()) {
      Label nodeWiadomosc = new Label(wiadomosc);
      model_.dodajWiadomoscDoHistorii(nodeWiadomosc);
      this.model_.ustawTekstWiadomosci("");
    }
  }

  public void rozpocznijGre(String tryb) {
    this.kontrolerGlowny_.rozpocznijGre(tryb);
  }
}
