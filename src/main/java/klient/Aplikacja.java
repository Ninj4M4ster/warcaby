package klient;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import klient.kontroller.KontrolerAplikacji;
public class Aplikacja extends javafx.application.Application {
  KontrolerAplikacji kontroler_;
  static Scene scena_;

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

  public static void main(String[] args) {
    launch();
  }

  public static void ustawNowyKorzen(Parent nowyKorzen) {
    scena_.setRoot(nowyKorzen);
  }
}
