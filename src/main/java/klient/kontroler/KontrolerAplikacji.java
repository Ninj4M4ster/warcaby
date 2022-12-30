package klient.kontroler;

import javafx.scene.Parent;
import klient.Aplikacja;
import klient.komunikacja.Mediator;
import klient.komunikacja.wiadomosci.TypyWiadomosci;
import klient.komunikacja.wiadomosci.Wiadomosc;
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
  /** Zmienna przechowujaca mediator pomiedzy aplikacja oraz polaczeniem z serwerem. */
  private Mediator mediator_;

  /** Glowny model aplikacji */
  private final GlownyModel model_;

  /**
   * Konstruktor, tworzy polaczenie i glowny model.
   */
  public KontrolerAplikacji() {
    boolean czyPolaczono;
    mediator_ = new Mediator(this);
    czyPolaczono = mediator_.czyPolaczono();
    model_ = new GlownyModel(czyPolaczono);
  }

  /**
   * Metoda ta przesyla sygnal do mediatora w celu przerwania watku polaczenia z serwerem.
   */
  public void zakonczPolaczenie() {
    this.mediator_.zakonczPolaczenie();
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
    kontroler.przekazMediator(this.mediator_);
    this.mediator_.ustawAktualnyKontroler(kontroler);

    Widok widok = TworcaWidoku.wybierzWidok(TypyWidokow.WIDOK_GRACZY_ONLINE);
    assert widok != null;
    return widok.utworzWidok(kontroler, model_.modelGraczyOnline());
  }

  /**
   * Metoda odpowiedzialna za utworzenie widoku pokoju
   * oraz wyslanie do serwera nazwy zaproszonego gracza.
   *
   * @param zaproszonyGracz Nazwa zaproszonego gracza.
   */
  public void utworzPokoj(String zaproszonyGracz) {
    Wiadomosc wiadomosc = new Wiadomosc(zaproszonyGracz, TypyWiadomosci.ZAPROSZENIE);
    this.mediator_.wyslijWiadomoscDoSerwera(wiadomosc);

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
    // TODO(Jakub Drzewiecki): Dodać enumerator dla zasad gry, ktory bedzie mial
    //  metody dla nazwy do aplikacji oraz numeru do wyslania do serwera
    Wiadomosc wiadomosc = new Wiadomosc("0", TypyWiadomosci.ROZPOCZECIE_GRY);
    this.mediator_.wyslijWiadomoscDoSerwera(wiadomosc);

    KontrolerWidoku kontroler = TworcaKontrolera.wybierzKontroler(TypyKontrolerow.KONTROLER_GRY);
    assert kontroler != null;
    kontroler.przekazModel(model_.modelGry());
    kontroler.przekazGlownyKontroler(this);

    Widok widok = TworcaWidoku.wybierzWidok(TypyWidokow.WIDOK_GRY);
    assert widok != null;
    Aplikacja.ustawNowyKorzen(widok.utworzWidok(kontroler, model_.modelGry()));
  }
}
