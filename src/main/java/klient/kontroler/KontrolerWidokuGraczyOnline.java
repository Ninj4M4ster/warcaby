package klient.kontroler;

import baza_danych.MenadzerBazyDanych;
import entities.Gra;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.collections.ListChangeListener.Change;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import klient.komunikacja.Mediator;
import klient.komunikacja.wiadomosci.TypyWiadomosci;
import klient.komunikacja.wiadomosci.Wiadomosc;
import klient.model.Model;
import klient.model.ModelGraczyOnline;
import klient.widoki.eventy.OknoKlikniete;
import klient.widoki.widgety.KafelekGraczaOnline;
import klient.widoki.widgety.KafelekGry;
import klient.widoki.widgety.powiadomienie.Powiadomienie;
import klient.widoki.widgety.powiadomienie.Zaproszenie;
import klient.widoki.widgety.powiadomienie.ZnikajacePowiadomienie;

/**
 * Klasa kontrolera widoku menu glownego (graczy online).
 */
public class KontrolerWidokuGraczyOnline implements KontrolerWidoku {
  /** Model widoku graczy online */
  private ModelGraczyOnline model_;

  /** Glowny kontroler aplikacji */
  private final GlownyKontroler kontrolerGlowny_ = GlownyKontroler.instancja();
  /** Mediator pomiedzy aplikacja oraz polaczeniem z serwerem */
  private Mediator mediator_;

  /**
   * Metoda odpowiedzialna za przechowanie modelu widoku.
   *
   * @param model Model widoku graczy online.
   */
  @Override
  public void przekazModel(Model model) {
    this.model_ = (ModelGraczyOnline) model;
  }

  /**
   * Metoda odpowiedzialna za przekazanie instancji mediatora do aplikacji.
   *
   * @param mediator Mediator miedzy aplikacja oraz polaczeniem z serwerem.
   */
  @Override
  public void przekazMediator(Mediator mediator) {
    this.mediator_ = mediator;
  }

  /**
   * Metoda odpowiedzialna za podjecie proby odnowienia polaczenia.
   */
  @Override
  public void odnowPolaczenie() {
    this.mediator_.odnowPolaczenie();
  }

  /**
   * Metoda odpowiedzialna za zapisane wprowadzonej nazwy uzytkownika i wyslanie jej do serwera.
   *
   * @param nazwa Wprowadzona nazwa uzytkownika.
   */
  public void zapiszNazweGracza(String nazwa) {
    if(nazwa.isBlank())
      return;
    Wiadomosc wiadomosc = new Wiadomosc(nazwa, TypyWiadomosci.IMIE);
    mediator_.wyslijWiadomoscDoSerwera(wiadomosc);

    this.model_.ustawNazweGracza(nazwa);
  }

  /**
   * Metoda odpowiedzialna za ukazanie listy graczy online po otrzymaniu odpowiedzi od serwera,
   * ze nazwa zostala przyjeta.
   */
  public void przejdzDoListyGraczy() {
    Platform.runLater(() -> {
      this.model_.ustawGoreMenu(this.model_.kontenerOpisuListyGraczy());
      this.model_.ustawCentrumMenu(this.model_.kontenerListyGraczy());
      this.model_.ustawDolMenu(this.model_.kontenerPrzyciskuPrzejsciaDoRozegranychGier());
    });
  }

  /**
   * Metoda odpowiedzialna za ukazanie listy rozegranych gier.
   * Pobiera ona wszystkie gry z serwera i tworzy dla kazdej widget na liscie.
   */
  public void przejdzDoListyRozegranychGier() {
    VBox listaGier = this.model_.listaGier();
    listaGier.getChildren().removeAll(listaGier.getChildren());  // usun wszystkie wprowadzone wczesniej do listy gry
    ArrayList<Gra> lista = MenadzerBazyDanych.instancja().pobierzGry();
    for(Gra gra: lista) {
      KafelekGry kafelek = new KafelekGry(gra);
      kafelek.przycisk().setOnMouseClicked((mouseEvent ->
          this.kontrolerGlowny_.obejrzyjGre(kafelek.gra())));
      listaGier.getChildren().add(kafelek);
    }
    Platform.runLater(() -> {
      this.model_.ustawGoreMenu(this.model_.kontenerOpisuListyRozegranychGier());
      this.model_.ustawCentrumMenu(this.model_.kontenerListyRozegranychGier());
      this.model_.ustawDolMenu(this.model_.kontenerPrzyciskuPrzejsciaDoListyGraczy());
    });
  }

  /**
   * Metoda odpowiedzialna za uruchomienie podanego wydarzenia na kazdym elemencie widgetu,
   * ktory byl celem tego wydarzenia.
   *
   * @param event Wydarzenie wywolane kliknieciem jakiegos elementu widoku.
   */
  public void uruchomWydarzenieNaKazdymDziecku(Event event) {
    if(event.getTarget() instanceof Parent && event.getTarget() == event.getSource()) {
      for (Node node :
          ((Parent) event.getTarget()).getChildrenUnmodifiable()) {
        node.fireEvent(new OknoKlikniete(((OknoKlikniete)event).zrodlo(), node));
      }
    }
  }

  /**
   * Metoda odpowiedzialna za przypisanie funkcji przyciskowi zaproszenia gracza do gry,
   * gdy do serwera dolaczy nowy gracz i powstanie nowy kafelek reprezentujacy go.
   *
   * @param change Nowy kafelek reprezentujacy dostepnego gracza.
   */
  public void przypiszFunkcjeKafelkowi(Change<? extends Node> change) {
    while(change.next()) {
      if(change.wasAdded()) {
        List<? extends Node> lista = change.getAddedSubList();
        for(Node node: lista) {
          KafelekGraczaOnline kafelek = (KafelekGraczaOnline) node;
          Wiadomosc wiadomosc = new Wiadomosc(kafelek.nazwaGracza(), TypyWiadomosci.ZAPROSZENIE);
          kafelek.przyciskZapros().setOnMouseClicked(
              (mouseEvent) ->
                  this.kontrolerGlowny_.utworzPokoj(
                      kafelek.nazwaGracza(),
                      true,
                      wiadomosc));
        }
      }
    }
  }

  /**
   * Metoda wyswietlajaca powiadomienie o zaproszeniu klienta do pokoju.
   *
   * @param gracz Gracz zapraszajacy klienta do pokoju.
   */
  public void wyswietlZaproszenieOdGracza(String gracz) {
    Platform.runLater(() -> this.model_.kontenerPowiadomien().getChildren().add(
        new Zaproszenie(gracz, this)));
  }

  /**
   * Metoda ta usuwa powiadomienie o zaproszeniu i przechodzi do widoku pokoju.
   *
   * @param wlascicielPokoju Wlasiciel pokoju, do ktorego dolacza klient.
   */
  public void dolaczDoPokoju(String wlascicielPokoju) {
    Wiadomosc wiadomosc =
        new Wiadomosc("Akceptuje " + wlascicielPokoju, TypyWiadomosci.ODPOWIEDZ);
    this.kontrolerGlowny_.utworzPokoj(wlascicielPokoju, false, wiadomosc);
    this.model_.kontenerPowiadomien().getChildren().removeIf(node -> {
      Powiadomienie powiadomienie = (Powiadomienie) node;
      return powiadomienie.gracz().compareTo(wlascicielPokoju) == 0;
    });
  }

  /**
   * Metoda ta usuwa powiadomienie o zaproszeniu i odrzuca zaproszenie od innego gracza.
   *
   * @param wlascicielPokoju Gracz, ktory zaprosil klienta do pokoju.
   */
  public void odrzucZaproszenie(String wlascicielPokoju) {
    this.kontrolerGlowny_.odrzucZaproszenie(wlascicielPokoju);
    VBox kontenerPowiadomien = this.model_.kontenerPowiadomien();
    kontenerPowiadomien.getChildren().removeIf(node -> node instanceof Powiadomienie
        && ((Powiadomienie) node).gracz().compareTo(wlascicielPokoju) == 0);
  }

  /**
   * Metoda wyswietlajaca powiadomienie z podana wiadomoscia.
   *
   * @param wiadomosc Powiadomienie do wyswietlenia.
   */
  public void wyswietlPowiadomienie(String wiadomosc) {
    Platform.runLater(() -> this.model_.kontenerPowiadomien().getChildren().add(
        new ZnikajacePowiadomienie(wiadomosc)));
  }
}
