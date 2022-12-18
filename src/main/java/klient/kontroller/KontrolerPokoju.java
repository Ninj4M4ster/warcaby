package klient.kontroller;

import javafx.scene.control.Label;
import klient.model.Model;
import klient.model.ModelPokoju;

public class KontrolerPokoju implements KontrolerWidoku{
  private ModelPokoju model_;
  private KontrolerAplikacji kontrolerGlowny_;

  @Override
  public void przekazModel(Model model) {
    this.model_ = (ModelPokoju) model;
  }

  @Override
  public void przekazGlownyKontroler(KontrolerAplikacji kontrolerGlowny) {
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
}
