package klient.kontroller;

import javafx.scene.control.Label;
import klient.model.Model;
import klient.model.ModelPokoju;

/**
 * Klasa kontrolera widoku pokoju.
 */
public class KontrolerPokoju implements KontrolerWidoku {
  /** Model widoku pokoju */
  private ModelPokoju model_;

  /** Glowny kontroler aplikacji */
  private KontrolerAplikacji kontrolerGlowny_;

  /**
   * Metoda odpowiedzialna za przechowanie modelu.
   *
   * @param model Model widoku pokoju.
   */
  @Override
  public void przekazModel(Model model) {
    if(this.model_ != null)
      throw new IllegalStateException("Nie mozna dwa razy przekazac modelu do kontrolera widoku.");
    this.model_ = (ModelPokoju) model;
  }

  /**
   * Metoda odpowiedzialna za przechowanie glownego kontrolera aplikacji.
   *
   * @param kontrolerGlowny Glowny kontroler aplikacji.
   */
  @Override
  public void przekazGlownyKontroler(KontrolerAplikacji kontrolerGlowny) {
    if(this.kontrolerGlowny_ != null)
      throw new IllegalStateException("Nie mozna dwa razy przekaza kontrolera aplikacji "
          + "do kontrolera widoku.");
    this.kontrolerGlowny_ = kontrolerGlowny;
  }

  /**
   * Metoda odpowiedzialna za wyslanie wprowadzonej wiadomosci
   * oraz usuniecie jej z pola tekstowego.
   */
  public void wyslijWiadomosc() {
    String wiadomosc = this.model_.tekstWiadomosci().get();
    // TODO(Jakub Drzewiecki): Utworzyć klasę reprezentującą chmurki wiadomosci, ktore beda dodawane do historii chatu
    if(!wiadomosc.isBlank()) {
      Label nodeWiadomosc = new Label(wiadomosc);
      model_.dodajWiadomoscDoHistorii(nodeWiadomosc);
      this.model_.ustawTekstWiadomosci("");
    }
  }

  /**
   * Metoda odpowiedzialna za rozpoczecie gry z wybranym trybem.
   *
   * @param tryb Wybrane zasady gry.
   */
  public void rozpocznijGre(String tryb) {
    this.kontrolerGlowny_.rozpocznijGre(tryb);
  }
}
