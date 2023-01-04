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
    // TODO(Jakub Drzewiecki): Trzeba tylko wysylac zaproszenie do pokoju i dolaczyc gdy zostanie potwierdzone
    Wiadomosc wiadomosc = new Wiadomosc(zaproszonyGracz, TypyWiadomosci.ZAPROSZENIE);
    this.mediator_.wyslijWiadomoscDoSerwera(wiadomosc);

    this.model_.modelPokoju().ustawCzyWlasciciel(true);

    KontrolerWidoku kontroler =
        TworcaKontrolera.wybierzKontroler(TypyKontrolerow.KONTROLER_POKOJU);
    assert kontroler != null;
    kontroler.przekazModel(model_.modelPokoju());
    kontroler.przekazGlownyKontroler(this);
    kontroler.przekazMediator(this.mediator_);

    Widok widok = TworcaWidoku.wybierzWidok(TypyWidokow.WIDOK_POKOJU);
    assert widok != null;
    Aplikacja.ustawNowyKorzen(widok.utworzWidok(kontroler, model_.modelPokoju()));
  }

  /**
   * Metoda wywolywana gdy drugi gracz dolaczy do pokoju.
   *
   * @param nazwaGracza Nazwa drugiego gracza.
   */
  public void przylaczGraczaDoPokoju(String nazwaGracza) {
    this.model_.modelPokoju().ustawNazweDrugiegoGracza(nazwaGracza);
  }

  /**
   * Metoda uruchamiana gdy klient zaakceptuje zaproszenie drugiego gracza do pokoju.
   *
   * @param wlascicielPokoju Wlasciciel innego pokoju.
   */
  public void dolaczDoPokoju(String wlascicielPokoju) {
    Wiadomosc wiadomosc =
        new Wiadomosc("akceptuje " + wlascicielPokoju, TypyWiadomosci.ODPOWIEDZ);
    this.mediator_.wyslijWiadomoscDoSerwera(wiadomosc);

    this.model_.modelPokoju().ustawCzyWlasciciel(false);
    this.model_.modelPokoju().ustawNazweDrugiegoGracza(wlascicielPokoju);

    KontrolerWidoku kontroler =
        TworcaKontrolera.wybierzKontroler(TypyKontrolerow.KONTROLER_POKOJU);
    assert kontroler != null;
    kontroler.przekazModel(this.model_.modelPokoju());
    kontroler.przekazGlownyKontroler(this);
    kontroler.przekazMediator(this.mediator_);

    Widok widok = TworcaWidoku.wybierzWidok(TypyWidokow.WIDOK_POKOJU);
    assert widok != null;
    Aplikacja.ustawNowyKorzen(widok.utworzWidok(kontroler, model_.modelPokoju()));
  }

  /**
   * Metoda uruchamiana gdy klient odrzuci zaproszenie innego gracza do pokoju.
   *
   * @param wlascicielPokoju Nazwa zapraszajacego gracza.
   */
  public void odrzucZaproszenie(String wlascicielPokoju) {
    Wiadomosc wiadomosc =
        new Wiadomosc("odrzuca " + wlascicielPokoju, TypyWiadomosci.ODPOWIEDZ);
    this.mediator_.wyslijWiadomoscDoSerwera(wiadomosc);
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
   * Metoda odpowiedzialna za utworzenie widoku gry z podanym przez serwer rozmiarem planszy.
   *
   * @param rozmiarPlanszy Rozmiar planszy odpowiadajacy wybranym zasadom.
   */
  public void rozpocznijGre(String rozmiarPlanszy) {
    // TODO(Jakub Drzewiecki): Brakuje wyboru koloru pionkow przez serwer
    this.model_.modelGry().ustawIloscPol(Integer.parseInt(rozmiarPlanszy));

    KontrolerWidoku kontroler = TworcaKontrolera.wybierzKontroler(TypyKontrolerow.KONTROLER_GRY);
    assert kontroler != null;
    kontroler.przekazModel(model_.modelGry());
    kontroler.przekazGlownyKontroler(this);
    kontroler.przekazMediator(this.mediator_);

    Widok widok = TworcaWidoku.wybierzWidok(TypyWidokow.WIDOK_GRY);
    assert widok != null;
    Aplikacja.ustawNowyKorzen(widok.utworzWidok(kontroler, model_.modelGry()));
  }
}
