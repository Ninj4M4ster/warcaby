package klient.kontroler;

import entities.Gra;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import klient.Aplikacja;
import klient.komunikacja.Mediator;
import klient.komunikacja.wiadomosci.TypyWiadomosci;
import klient.komunikacja.wiadomosci.Wiadomosc;
import klient.model.GlownyModel;
import klient.widoki.TworcaWidoku;
import klient.widoki.TypyWidokow;
import klient.widoki.Widok;
import klient.widoki.widgety.powiadomienie.ZnikajacePowiadomienie;

/**
 * Glowny kontroler, odpowiedzialny za utworzenie polaczenia z serwerem
 * oraz tworzenie nowych widokow.
 */
public class GlownyKontroler {
  /** Instancja glownego kontrolera. */
  private static GlownyKontroler instancja_;
  /** Zmienna przechowujaca mediator pomiedzy aplikacja oraz polaczeniem z serwerem. */
  private final Mediator mediator_;

  /** Glowny model aplikacji */
  private final GlownyModel model_;

  /**
   * Konstruktor, tworzy polaczenie i glowny model.
   */
  private GlownyKontroler() {
    mediator_ = new Mediator(this);
    model_ = new GlownyModel(mediator_.czyPolaczono());
  }

  /**
   * Metoda sluzaca do uzyskania dostepu do instancji tej klasy.
   *
   * @return Instancja glownego kontrolera.
   */
  public static GlownyKontroler instancja() {
    if(instancja_ != null) {
      return instancja_;
    }
    synchronized(GlownyKontroler.class) {
      if(instancja_ == null) {
        instancja_ = new GlownyKontroler();
      }
      return instancja_;
    }
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
        TworcaKontrolera.wybierzKontroler(TypyKontrolerow.KONTROLER_GRACZY_ONLINE,
            this.model_.modelGraczyOnline(),
            this.mediator_);

    Widok widok = TworcaWidoku.wybierzWidok(TypyWidokow.WIDOK_GRACZY_ONLINE);
    return widok.utworzWidok(kontroler, model_.modelGraczyOnline());
  }

  /**
   * Metoda odpowiedzialna za utworzenie widoku pokoju
   * oraz wyslanie do serwera nazwy zaproszonego gracza.
   *
   * @param zaproszonyGracz Nazwa zaproszonego gracza.
   * @param czyWlasciciel Czy klient jest wlascicielem pokoju?
   * @param wiadomosc Wiadomosc do wyslania do serwera.
   */
  public void utworzPokoj(String zaproszonyGracz, boolean czyWlasciciel, Wiadomosc wiadomosc) {
    this.mediator_.wyslijWiadomoscDoSerwera(wiadomosc);

    this.model_.modelPokoju().ustawCzyWlasciciel(czyWlasciciel);
    this.model_.modelPokoju().ustawNazweDrugiegoGracza(zaproszonyGracz);

    KontrolerWidoku kontroler =
        TworcaKontrolera.wybierzKontroler(TypyKontrolerow.KONTROLER_POKOJU,
            this.model_.modelPokoju(),
            this.mediator_);

    Widok widok = TworcaWidoku.wybierzWidok(TypyWidokow.WIDOK_POKOJU);
    Aplikacja.ustawNowyKorzen(widok.utworzWidok(kontroler, model_.modelPokoju()));
  }

  /**
   * Metoda wywolywana gdy drugi gracz dolaczy do pokoju.
   */
  public void powiadomDolaczyl() {
    this.model_.modelPokoju().dodajWiadomoscDoHistorii(
        new Label(
            this.model_.modelPokoju().nazwaDrugiegoGracza().get() + " dolaczyl do pokoju.")
    );
  }

  /**
   * Metoda wywolywana, gdy drugi gracz odrzucil zaproszenie.
   */
  public void powiadomOdrzucil() {
    Aplikacja.ustawNowyKorzen(this.utworzPodstawowaScene());
    Platform.runLater(() ->
        this.model_.modelGraczyOnline().kontenerPowiadomien().getChildren().add(
        new ZnikajacePowiadomienie("Zaproszenie do gry zostało odrzucone")
    ));
  }

  /**
   * Metoda uruchamiana gdy klient odrzuci zaproszenie innego gracza do pokoju.
   *
   * @param wlascicielPokoju Wlasciciel innego pokoju.
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
   * @param argumenty Argumenty - kolor pionkow oraz rozmiar planszy.
   */
  public void rozpocznijGre(String argumenty) {
    if(argumenty.charAt(0) == '1')
      this.model_.modelGry().ustawKolorPionkow("bialy");
    else
      this.model_.modelGry().ustawKolorPionkow("czarny");
    this.model_.modelGry().ustawIloscPol((int)(Math.sqrt(argumenty.split("").length - 1)));

    KontrolerWidoku kontroler =
        TworcaKontrolera.wybierzKontroler(TypyKontrolerow.KONTROLER_GRY,
            this.model_.modelGry(),
            this.mediator_);

    Widok widok = TworcaWidoku.wybierzWidok(TypyWidokow.WIDOK_GRY);
    Aplikacja.ustawNowyKorzen(widok.utworzWidok(kontroler, model_.modelGry()));
  }

  /**
   * Metoda odpowiedzialna za utworzenie widoku,
   * w ktorym mozna obejrzec przebieg zapisanej w bazie danych gry.
   *
   * @param gra Gra do obejrzenia.
   */
  public void obejrzyjGre(Gra gra) {

  }
}
