package klient.kontroller;

import java.io.IOException;
import javafx.scene.Scene;
import klient.Klient;
import klient.model.GlownyModel;
import klient.widoki.TworcaWidoku;
import klient.widoki.TypyWidokow;
import klient.widoki.Widok;

public class KontrolerAplikacji {
  private Klient klient_;
  private boolean czyPolaczono_;
  private final GlownyModel model_;
  public KontrolerAplikacji() {
    try {
      klient_ = new Klient();
      czyPolaczono_ = true;
    } catch (IOException e) {
      czyPolaczono_ = false;
    }
    model_ = new GlownyModel();
  }

  public Scene utworzPodstawowaScene() {
    KontrolerWidoku kontroler = TworcaKontrolera.wybierzKontroler(TypyKontrolerow.KONTROLER_GRACZY_ONLINE);
    assert kontroler != null;
    kontroler.przekazModel(model_.dajModelGraczyOnline());
    kontroler.przekazGlownyKontroler(this);

    Widok widok = TworcaWidoku.wybierzWidok(TypyWidokow.WIDOK_GRACZY_ONLINE);
    assert widok != null;
    // TODO(Jakub Drzewiecki): zmienna czyPolaczono musi byc elementem modelu i
    //  miec swoj wlasny widok dostepny z poziomu wszystkich widokow
    return widok.utworzWidok(kontroler, model_.dajModelGraczyOnline(), czyPolaczono_);
  }

  public void przekazNazweDoSerwera(String nazwaGracza) {
    System.out.println(nazwaGracza);
    // TODO(Jakub Drzewiecki): należy utworzyć klasę służącą do przekazywania danych do klienta
    //  i wysłać jej nazwę
  }
}
