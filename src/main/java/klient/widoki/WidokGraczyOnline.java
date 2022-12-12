package klient.widoki;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class WidokGraczyOnline implements Widok {

  @Override
  public Scene utworzWidok(boolean statusPolaczenia) {
    StackPane stackPane = new StackPane();
    return new Scene(stackPane);
  }
}
