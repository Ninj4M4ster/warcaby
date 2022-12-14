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

/**
 * Glowny kontroler, odpowiedzialny za utworzenie polaczenia z serwerem
 * oraz tworzenie nowych widokow.
 */
public class KontrolerAplikacji {
  /** Zmienna przechowujaca obiekt odpowiedzialny za polaczenie z serwerem */
  private Klient klient_;

  /** Glowny model aplikacji */
  private final GlownyModel model_;

  /**
   * Konstruktor, tworzy polaczenie i glowny model.
   */
  public KontrolerAplikacji() {
    boolean czyPolaczono;
    try {
      klient_ = new Klient();
      czyPolaczono = true;
    } catch (IOException e) {
      czyPolaczono = false;
    }
    model_ = new GlownyModel(czyPolaczono);
  }

  /**
   * Metoda ta tworzy widok glownego menu (graczy online)
   * oraz zwraca kontener przechowujacy wszystkie jego elementy.
   *
   * @return Kontener przechowujacy wszystkie elementy widoku graczy online.
   */
  public Parent utworzPodstawowaScene() {
    KontrolerWidoku kontroler =
        TworcaKontrolera.wybierzKontroler(TypyKontrolerow.KONTROLER_GRACZY_ONLINE);
    assert kontroler != null;
    kontroler.przekazModel(model_.modelGraczyOnline());
    kontroler.przekazGlownyKontroler(this);

    Widok widok = TworcaWidoku.wybierzWidok(TypyWidokow.WIDOK_GRACZY_ONLINE);
    assert widok != null;
    // TODO(Jakub Drzewiecki): zmienna czyPolaczono musi byc elementem modelu i
    //  miec swoj wlasny widok dostepny z poziomu wszystkich widokow
    return widok.utworzWidok(kontroler, model_.modelGraczyOnline());
  }

  /**
   * Metoda odpowiedzialna za utworzenie widoku pokoju
   * oraz wyslanie do serwera nazwy zaproszonego gracza.
   *
   * @param zaproszonyGracz Nazwa zaproszonego gracza.
   */
  public void utworzPokoj(String zaproszonyGracz) {
    // TODO(Jakub Drzewiecki): wysłać do serwera nazwe zaproszonego gracza
    KontrolerWidoku kontroler =
        TworcaKontrolera.wybierzKontroler(TypyKontrolerow.KONTROLER_POKOJU);
    assert kontroler != null;
    kontroler.przekazModel(model_.modelPokoju());
    kontroler.przekazGlownyKontroler(this);

    Widok widok = TworcaWidoku.wybierzWidok(TypyWidokow.WIDOK_POKOJU);
    assert widok != null;
    Aplikacja.ustawNowyKorzen(widok.utworzWidok(kontroler, model_.modelPokoju()));
  }

  /**
   * Metoda odpowiedzialna za przekazanie wprowadzonej nazwy uzytkownika do serwera.
   *
   * @param nazwaGracza Wprowadzona nazwa uzytkownika.
   */
  public void przekazNazweDoSerwera(String nazwaGracza) {
    System.out.println(nazwaGracza);
    // TODO(Jakub Drzewiecki): należy utworzyć klasę służącą do przekazywania danych do klienta
    //  i wysłać jej nazwę
  }

  /**
   * Metoda odpowiedzialna za zaktualizowanie listy dostepnych graczy.
   *
   * @param nazwaGracza Nazwa gracza.
   * @param czyDolaczyl Czy podany gracz dolaczyl do serwera?
   */
  public void zaktualizujListeGraczy(String nazwaGracza, boolean czyDolaczyl) {
    if(czyDolaczyl)
      this.model_.modelGraczyOnline().dodajGraczaDoListy(nazwaGracza);
    else
      this.model_.modelGraczyOnline().usunGraczaLista(nazwaGracza);
  }

  /**
   * Metoda odpowiedzialna za utworzenie widoku gry oraz wyslanie do serwera wybranego trybu gry.
   *
   * @param tryb Wybrane zasady gry.
   */
  public void rozpocznijGre(String tryb) {
    // TODO(Jakub Drzewiecki): Należy wysłać wybrany tryb gry do serwera i
    //  na podstawie odpowiedzi najpierw ustawić rozmiar planszy w modelu a nastepnie utworzyć widok.
    KontrolerWidoku kontroler = TworcaKontrolera.wybierzKontroler(TypyKontrolerow.KONTROLER_GRY);
    assert kontroler != null;
    kontroler.przekazModel(model_.modelGry());
    kontroler.przekazGlownyKontroler(this);

    Widok widok = TworcaWidoku.wybierzWidok(TypyWidokow.WIDOK_GRY);
    assert widok != null;
    Aplikacja.ustawNowyKorzen(widok.utworzWidok(kontroler, model_.modelGry()));
  }
}
