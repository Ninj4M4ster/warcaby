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
    scena_ = kontroler_.utworzPodstawowaScene();
    stage.setTitle("Warcaby");
    stage.setScene(scena_);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }

  public static void ustawNowaScene(Parent nowaScena) {
    scena_.setRoot(nowaScena);
  }
}
