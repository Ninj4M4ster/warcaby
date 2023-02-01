package klient;

import baza_danych.MenadzerBazyDanych;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import klient.kontroler.GlownyKontroler;

/**
 * Glowna klasa, odpowiedzialna za utworzenie GUI oraz zmiane widoku.
 */
public class Aplikacja extends javafx.application.Application {
  /** Glowny kontroler aplikacji */
  GlownyKontroler kontroler_;
  /** Scena widoczna aktualnie w GUI */
  static Scene scena_ = new Scene(new StackPane());

  /**
   * Metoda uruchamiajaca GUI.
   *
   * @param stage Glowny kontener GUI.
   * @throws Exception Blad w czasie dzialania aplikacji.
   */
  @Override
  public void start(Stage stage) throws Exception {
    MenadzerBazyDanych menadzerBazyDanych = MenadzerBazyDanych.instancja();
    menadzerBazyDanych.konfiguruj(new String[]{"klient.warcaby"});

    kontroler_ = GlownyKontroler.instancja();
    scena_ = new Scene(kontroler_.utworzPodstawowaScene());
    stage.setTitle("Warcaby");
    stage.setScene(scena_);
    stage.setWidth(1000);
    stage.setHeight(800);
    stage.show();
  }

  /**
   * Nadpisana metoda uruchamiana przy zamykaniu aplikacji.
   * Jej celem jest przerwanie dzialania watku polaczenia z serwerem.
   */
  @Override
  public void stop() {
    this.kontroler_.zakonczPolaczenie();
  }

  /**
   * Metoda uruchamiana bezpośrednio po uruchomieniu aplikacji.
   *
   * @param args Argumenty z linii komend.
   */
  public static void main(String[] args) {
    launch();
  }

  /**
   * Metoda mająca na celu zmiane widoku przez zmianę korzenia widoku.
   *
   * @param nowyKorzen Nowy korzeń widoku.
   */
  public static void ustawNowyKorzen(Parent nowyKorzen) {
    Platform.runLater(() -> scena_.setRoot(nowyKorzen));
  }
}
