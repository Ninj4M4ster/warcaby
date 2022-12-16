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

  public void uruchomWydarzenieNaKazdymDziecku(Event event) {
    if(event.getTarget() instanceof Parent && event.getTarget() == event.getSource()) {
      for (Node node :
          ((Parent) event.getTarget()).getChildrenUnmodifiable()) {
        node.fireEvent(new OknoKlikniete(((OknoKlikniete)event).zrodlo(), node));
      }
    }
  }

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
