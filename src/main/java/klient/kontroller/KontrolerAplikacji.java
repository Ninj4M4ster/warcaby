package klient.kontroller;

import java.io.IOException;
import javafx.scene.Parent;
import klient.Aplikacja;
import klient.Klient;
import klient.model.GlownyModel;
import klient.widoki.TworcaWidoku;
import klient.widoki.TypyWidokow;
import klient.widoki.Widok;

// TODO(Jakub Drzewiecki): Ta klasa mogłaby implementować wzorzec singleton.
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
    model_ = new GlownyModel(czyPolaczono_);
  }

  public Parent utworzPodstawowaScene() {
    KontrolerWidoku kontroler =
        TworcaKontrolera.wybierzKontroler(TypyKontrolerow.KONTROLER_GRACZY_ONLINE);
    assert kontroler != null;
    kontroler.przekazModel(model_.dajModelGraczyOnline());
    kontroler.przekazGlownyKontroler(this);

    Widok widok = TworcaWidoku.wybierzWidok(TypyWidokow.WIDOK_GRACZY_ONLINE);
    assert widok != null;
    // TODO(Jakub Drzewiecki): zmienna czyPolaczono musi byc elementem modelu i
    //  miec swoj wlasny widok dostepny z poziomu wszystkich widokow
    return widok.utworzWidok(kontroler, model_.dajModelGraczyOnline());
  }

  public void utworzPokoj(String zaproszonyGracz) {
    // TODO(Jakub Drzewiecki): wysłać do serwera nazwe zaproszonego gracza
    KontrolerWidoku kontroler =
        TworcaKontrolera.wybierzKontroler(TypyKontrolerow.KONTROLER_POKOJU);
    assert kontroler != null;
    kontroler.przekazModel(model_.dajModelPokoju());
    kontroler.przekazGlownyKontroler(this);

    Widok widok = TworcaWidoku.wybierzWidok(TypyWidokow.WIDOK_POKOJU);
    assert widok != null;
    Aplikacja.ustawNowyKorzen(widok.utworzWidok(kontroler, model_.dajModelPokoju()));
  }

  public void przekazNazweDoSerwera(String nazwaGracza) {
    System.out.println(nazwaGracza);
    // TODO(Jakub Drzewiecki): należy utworzyć klasę służącą do przekazywania danych do klienta
    //  i wysłać jej nazwę
  }

  public void zaktualizujListeGraczy(String nazwaGracza, boolean czyDolaczyl) {
    if(czyDolaczyl)
      this.model_.dajModelGraczyOnline().dodajGraczaDoListy(nazwaGracza);
    else
      this.model_.dajModelGraczyOnline().usunGraczaLista(nazwaGracza);
  }

  public void rozpocznijGre(String tryb) {
    // TODO(Jakub Drzewiecki): Należy wysłać wybrany tryb gry do serwera i
    //  na podstawie odpowiedzi najpierw ustawić rozmiar planszy w modelu a nastepnie utworzyć widok.
    KontrolerWidoku kontroler = TworcaKontrolera.wybierzKontroler(TypyKontrolerow.KONTROLER_GRY);
    assert kontroler != null;
    kontroler.przekazModel(model_.dajModelGry());
    kontroler.przekazGlownyKontroler(this);

    Widok widok = TworcaWidoku.wybierzWidok(TypyWidokow.WIDOK_GRY);
    assert widok != null;
    Aplikacja.ustawNowyKorzen(widok.utworzWidok(kontroler, model_.dajModelGry()));
  }
}
