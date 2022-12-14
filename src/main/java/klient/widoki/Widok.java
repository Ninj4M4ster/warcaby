package klient.widoki;

import javafx.scene.Scene;
import klient.kontroller.KontrolerWidoku;
import klient.model.ModelWidoku;

public interface Widok {
  Scene utworzWidok(KontrolerWidoku kontroler,
      ModelWidoku model,
      boolean statusPolaczenia);
}
