package klient.widoki;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import klient.kontroler.KontrolerOgladaniaGry;
import klient.kontroler.KontrolerWidoku;
import klient.model.Model;
import klient.model.ModelOgladaniaGry;

/**
 * Klasa reprezentujaca widok ogladania rozgrywki zapisanej w bazie danych.
 */
public class WidokOgladaniaGry extends Widok {
  private BorderPane okno_;
  /** Kontroler widoku */
  private KontrolerOgladaniaGry kontroler_;
  /** Model widoku */
  private ModelOgladaniaGry model_;

  /**
   * Metoda odpowiedzialna za utworzenie widoku.
   *
   * @param kontroler Kontroler widoku.
   * @param model Model widoku.
   * @return Kontener wszystkich elementow widoku.
   */
  @Override
  public Parent utworzWidok(KontrolerWidoku kontroler, Model model) {
    this.model_ = (ModelOgladaniaGry) model;
    this.kontroler_ = (KontrolerOgladaniaGry) kontroler;

    this.okno_ = new BorderPane();

    this.okno_.setBottom(this.utworzPasekStatusu(model, kontroler));

    return this.okno_;
  }
}
