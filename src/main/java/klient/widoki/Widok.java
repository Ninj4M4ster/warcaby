package klient.widoki;

import javafx.scene.Parent;
import klient.kontroller.KontrolerWidoku;
import klient.model.Model;

public interface Widok {
  Parent utworzWidok(KontrolerWidoku kontroler,
      Model model);
}
