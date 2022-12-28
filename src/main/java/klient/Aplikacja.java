package klient;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import klient.kontroler.KontrolerAplikacji;

/**
 * Glowna klasa, odpowiedzialna za utworzenie GUI oraz zmiane widoku.
 */
public class Aplikacja extends javafx.application.Application {
  /** Glowny kontroler aplikacji */
  KontrolerAplikacji kontroler_;
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
    kontroler_ = new KontrolerAplikacji();
    scena_ = new Scene(kontroler_.utworzPodstawowaScene());
    // dummy gracze, po skonczeniu GUI trzeba ich usunac
    kontroler_.zaktualizujListeGraczy("Gracz1", true);
    kontroler_.zaktualizujListeGraczy("Gracz2", true);
    kontroler_.zaktualizujListeGraczy("Gracz3", true);
    kontroler_.zaktualizujListeGraczy("Gracz4", true);
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
    scena_.setRoot(nowyKorzen);
  }
}
