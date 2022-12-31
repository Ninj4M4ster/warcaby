package klient.kontroler;

import static klient.ZasadyGry.KANADYJSKIE;
import static klient.ZasadyGry.KLASYCZNE;
import static klient.ZasadyGry.POLSKIE;

import javafx.scene.control.Label;
import klient.komunikacja.Mediator;
import klient.komunikacja.wiadomosci.TypyWiadomosci;
import klient.komunikacja.wiadomosci.Wiadomosc;
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
  /** Mediator pomiedzy aplikacja oraz polaczeniem z serwerem */
  private Mediator mediator_;

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
   * Metoda odpowiedzialna za przekazanie instancji mediatora do aplikacji.
   *
   * @param mediator Mediator miedzy aplikacja oraz polaczeniem z serwerem.
   */
  @Override
  public void przekazMediator(Mediator mediator) {
    this.mediator_ = mediator;
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
   * Wysyla ona wybrany tryb gry do serwera.
   *
   * @param tryb Wybrane zasady gry.
   */
  public void wyslijRozpocznijGre(String tryb) {
    String numer_trybu = this.wybierzNumerTrybu(tryb);
    Wiadomosc wiadomosc = new Wiadomosc(numer_trybu, TypyWiadomosci.ROZPOCZECIE_GRY);
    this.mediator_.wyslijWiadomoscDoSerwera(wiadomosc);
  }

  /**
   * Metoda dobierajaca numer zasad na podstawie nazwy wybranej w aplikacji nazwy.
   *
   * @param tryb Wybrana w aplikacji nazwa trybu.
   * @return Numer trybu.
   */
  private String wybierzNumerTrybu(String tryb) {
    if(tryb.compareTo(KLASYCZNE.toString()) == 0)
      return KLASYCZNE.numer();
    else if(tryb.compareTo(POLSKIE.toString()) == 0)
      return POLSKIE.numer();
    else
      return KANADYJSKIE.numer();
  }
}
