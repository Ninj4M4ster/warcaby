package klient.kontroller;

import java.io.IOException;
import javafx.scene.Scene;
import klient.Klient;
import klient.model.PlanszaGry;
import klient.widoki.TworcaWidoku;
import klient.widoki.TypyWidokow;
import klient.widoki.Widok;

public class KontrolerAplikacji {
  private Klient klient_;
  private boolean czyPolaczono_;
  private PlanszaGry aktualnaPlansza_;
  public KontrolerAplikacji() {
    try {
      klient_ = new Klient();
      czyPolaczono_ = true;
    } catch (IOException e) {
      czyPolaczono_ = false;
    }
  }

  public Scene utworzPodstawowaScene() {
    Widok widok = TworcaWidoku.wybierzWidok(TypyWidokow.WIDOK_GRACZY_ONLINE);
    assert widok != null;
    return widok.utworzWidok(czyPolaczono_);
  }
}
