package klient.widoki;

import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import klient.kontroller.KontrolerWidoku;
import klient.model.Model;

public class WidokPokoju implements Widok {
  @Override
  public Parent utworzWidok(KontrolerWidoku kontroler, Model model) {
    return new StackPane();
  }
}
