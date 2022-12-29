package klient.kontroler;

import java.util.List;
import javafx.collections.ListChangeListener.Change;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import klient.komunikacja.Mediator;
import klient.komunikacja.wiadomosci.TypyWiadomosci;
import klient.komunikacja.wiadomosci.Wiadomosc;
import klient.model.Model;
import klient.model.ModelGraczyOnline;
import klient.widoki.eventy.OknoKlikniete;
import klient.widoki.widgety.KafelekGraczaOnline;

/**
 * Klasa kontrolera widoku menu glownego (graczy online).
 */
public class KontrolerWidokuGraczyOnline implements KontrolerWidoku {
  /** Model widoku graczy online */
  private ModelGraczyOnline model_;

  /** Glowny kontroler aplikacji */
  private KontrolerAplikacji kontrolerGlowny_;
  /** Mediator pomiedzy aplikacja oraz polaczeniem z serwerem */
  private Mediator mediator_;

  /**
   * Metoda odpowiedzialna za przechowanie modelu widoku.
   *
   * @param model Model widoku graczy online.
   */
  @Override
  public void przekazModel(Model model) {
    this.model_ = (ModelGraczyOnline) model;
  }

  /**
   * Metoda odpowiedzialna za przechowanie glownego kontrolera aplikacji.
   *
   * @param kontrolerGlowny Glowny kontroler aplikacji.
   */
  @Override
  public void przekazGlownyKontroler(KontrolerAplikacji kontrolerGlowny) {
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
   * Metoda odpowiedzialna za zapisane wprowadzonej nazwy uzytkownika i wyslanie jej do serwera.
   *
   * @param nazwa Wprowadzona nazwa uzytkownika.
   */
  public void zapiszNazweGracza(String nazwa) {
    if(nazwa.isBlank())
      return;
    Wiadomosc wiadomosc = new Wiadomosc(nazwa, TypyWiadomosci.IMIE);
    mediator_.wyslijWiadomoscDoSerwera(wiadomosc);

    this.model_.ustawNazweGracza(nazwa);
  }

  /**
   * Metoda odpowiedzialna za ukazanie listy graczy online po otrzymaniu odpowiedzi od serwera,
   * ze nazwa zostala przyjeta.
   * TODO(Jakub Drzewiecki): Potrzebna jest metoda wywolywana gdy nazwa zostanie odrzucona, w ktorej wyswietlane bedzie odpowiednie powiadomienie.
   */
  public void przejdzDoListyGraczy() {
    // TODO(Jakub Drzewiecki): dodać animację przejścia do widoku listy graczy online
    this.model_.ustawGoreMenu(this.model_.kontenerOpisuListyGraczy());
    this.model_.ustawCentrumMenu(this.model_.kontenerListyGraczy());
  }

  /**
   * Metoda odpowiedzialna za uruchomienie podanego wydarzenia na kazdym elemencie widgetu,
   * ktory byl celem tego wydarzenia.
   *
   * @param event Wydarzenie wywolane kliknieciem jakiegos elementu widoku.
   */
  public void uruchomWydarzenieNaKazdymDziecku(Event event) {
    if(event.getTarget() instanceof Parent && event.getTarget() == event.getSource()) {
      for (Node node :
          ((Parent) event.getTarget()).getChildrenUnmodifiable()) {
        node.fireEvent(new OknoKlikniete(((OknoKlikniete)event).zrodlo(), node));
      }
    }
  }

  /**
   * Metoda odpowiedzialna za przypisanie funkcji przyciskowi zaproszenia gracza do gry,
   * gdy do serwera dolaczy nowy gracz i powstanie nowy kafelek reprezentujacy go.
   *
   * @param change Nowy kafelek reprezentujacy dostepnego gracza.
   */
  public void przypiszFunkcjeKafelkowi(Change<? extends Node> change) {
    while(change.next()) {
      if(change.wasAdded()) {
        List<? extends Node> lista = change.getAddedSubList();
        for(Node node: lista) {
          KafelekGraczaOnline kafelek = (KafelekGraczaOnline) node;
          kafelek.przyciskZapros().setOnMouseClicked(
              (mouseEvent) -> this.kontrolerGlowny_.utworzPokoj(kafelek.nazwaGracza()));
        }
      }
    }
  }
}
