package klient.kontroller;

import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import klient.Aplikacja;
import klient.Klient;
import klient.widoki.TworcaWidoku;
import klient.widoki.Widok;

public class KontrolerAplikacji {
  private Klient klient_;
  private boolean czyPolaczono_;
  public KontrolerAplikacji() {
    try {
      klient_ = new Klient();
      czyPolaczono_ = true;
    } catch (IOException e) {
      czyPolaczono_ = false;
    }
  }

  public Scene utworzPodstawowaScene() {
    // TODO(Jakub Drzewiecki): Tutaj potrzebny jest enumerator dla typów pokojów
    Widok widok = TworcaWidoku.wybierzWidok("gracze-online");
    return widok.utworzWidok(czyPolaczono_);
  }
}
