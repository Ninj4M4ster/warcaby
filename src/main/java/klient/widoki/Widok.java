package klient.widoki;

import javafx.scene.Scene;
import klient.kontroller.KontrolerWidoku;
import klient.model.Model;

public interface Widok {
  Scene utworzWidok(KontrolerWidoku kontroler,
      Model model,
      boolean statusPolaczenia);
}
