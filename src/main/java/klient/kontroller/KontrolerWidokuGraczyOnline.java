package klient.kontroller;

import java.util.List;
import javafx.collections.ListChangeListener.Change;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
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
   * Metoda odpowiedzialna za zapisane wprowadzonej nazwy uzytkownika i wyslanie jej do serwera.
   *
   * @param nazwa Wprowadzona nazwa uzytkownika.
   * @param oknoGlowne Widget, z ktorym gracz wchodzi w interakcje.
   */
  public void zapiszNazweGracza(String nazwa, BorderPane oknoGlowne) {
    if(nazwa.isBlank())
      return;
    // TODO(Jakub Drzewiecki): dodać animację przejścia do widoku listy graczy online
    oknoGlowne.setPrefHeight(600);
    this.model_.ustawNazweGracza(nazwa);
    this.kontrolerGlowny_.przekazNazweDoSerwera(nazwa);
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
