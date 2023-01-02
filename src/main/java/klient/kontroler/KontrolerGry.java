package klient.kontroler;

import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.shape.Circle;
import klient.komunikacja.Mediator;
import klient.komunikacja.wiadomosci.TypyWiadomosci;
import klient.komunikacja.wiadomosci.Wiadomosc;
import klient.model.Model;
import klient.model.ModelGry;
import klient.widoki.widgety.Pionek;
import klient.widoki.widgety.PolePlanszy;

/**
 * Klasa kontrolera widoku gry.
 */
public class KontrolerGry implements KontrolerWidoku {
  /** Model widoku */
  private ModelGry model_;

  /** Glowny kontroler aplikacji */
  private KontrolerAplikacji kontrolerGlowny_;
  /** Mediator pomiedzy aplikacja oraz polaczeniem z serwerem */
  private Mediator mediator_;

  /** Kontener aktualnie przesuwanego pionka */
  private Pionek kontenerAktualniePrzesuwanegoPionka_;

  /** Czy jakis pionek jest aktualnie przesuwany? */
  private boolean pionekPrzesuwany_;

  /**
   * Metoda odpowiedzialna za przechowanie modelu widoku gry.
   * @param model Model widoku gry.
   */
  @Override
  public void przekazModel(Model model) {
    if(this.model_ != null)
      throw new IllegalStateException("Nie mozna dwa razy przekazac modelu do kontrolera widoku");
    this.model_ = (ModelGry) model;
  }

  /**
   * Metoda odpowiedzialna za przechowanie glownego kontrolera.
   *
   * @param kontrolerGlowny Glowny kontroler aplikacji.
   */
  @Override
  public void przekazGlownyKontroler(KontrolerAplikacji kontrolerGlowny) {
    if(this.kontrolerGlowny_ != null)
      throw new IllegalStateException("Nie mozna dwa razy przekazac kontrolera "
          + "aplikacji do kontrolera widoku");
    this.kontrolerGlowny_ = kontrolerGlowny;
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
   * Metoda odpowiedzialna za rozpoczecie przesuwania pionka po planszy.
   *
   * @param mouseEvent Wydarzenie nacisniecia na pionek.
   * @param kontenerPrzesuwanegoPionka Kontener przesuwanego pionka.
   */
  public void zacznijPrzesuwacPionek(MouseEvent mouseEvent, Pionek kontenerPrzesuwanegoPionka) {
    if(kontenerPrzesuwanegoPionka.kolorPionka().equals(this.model_.kolorPionkow())) {
      kontenerPrzesuwanegoPionka.ustawStartowaPozycjaX(mouseEvent.getSceneX());
      kontenerPrzesuwanegoPionka.ustawStartowaPozycjaY(mouseEvent.getSceneY());
      kontenerPrzesuwanegoPionka.getParent().toFront();

      this.kontenerAktualniePrzesuwanegoPionka_ = kontenerPrzesuwanegoPionka;
      this.pionekPrzesuwany_ = true;
    }
  }

  /**
   * Metoda odpowiedzialna za przesuwanie pionka za myszka.
   *
   * @param mouseEvent                 Wydarzenie przesuniecia myszka po ekranie.
   * @param kontenerPrzesuwanegoPionka Kontener przesuwanego pionka.
   */
  public void przesunPionek(MouseEvent mouseEvent, Pionek kontenerPrzesuwanegoPionka) {
    if(this.pionekPrzesuwany_) {
      kontenerPrzesuwanegoPionka.setTranslateX(
          mouseEvent.getSceneX() - kontenerPrzesuwanegoPionka.startowaPozycjaX());
      kontenerPrzesuwanegoPionka.setTranslateY(
          mouseEvent.getSceneY() - kontenerPrzesuwanegoPionka.startowaPozycjaY());
    }
  }

  /**
   * Metoda odpowiedzialna za zakonczenie przesuwania pionka po planszy.
   * Ustawia przesuniecie pionka wzgledem aktualnego pola na zero.
   */
  public void skonczPrzesuwacPionek() {
    if(this.pionekPrzesuwany_) {
      kontenerAktualniePrzesuwanegoPionka_.setTranslateX(0);
      kontenerAktualniePrzesuwanegoPionka_.setTranslateY(0);
    }
  }

  /**
   * Metoda odpowiedzialna za wyslanie wiadomosci o przesuniecie pionka po
   * puszczeniu go na wybrane pole.
   *
   * @param pole Pole nad ktorym znajdowala sie myszka po puszczeniu pionka.
   * @param wynikWydarzenia Wynik wydarzenia.
   */
  public void puszczonoMyszkeNadPolem(PolePlanszy pole, PickResult wynikWydarzenia) {
    // sprawdz czy pionek przesuwany jest na inne pole niz to na ktorym stoi
    if(this.pionekPrzesuwany_
        && wynikWydarzenia.getIntersectedNode() instanceof Circle) {
      PolePlanszy startowePole =
          (PolePlanszy) this.kontenerAktualniePrzesuwanegoPionka_.getParent();
      int kolumnaStartowa = startowePole.kolumna();
      int rzadStartowy = startowePole.rzad();

      int docelowaKolumna = pole.kolumna();
      int docelowyRzad = pole.rzad();

      Wiadomosc wiadomosc =
          new Wiadomosc(kolumnaStartowa,
              rzadStartowy,
              docelowaKolumna,
              docelowyRzad,
              TypyWiadomosci.RUCH_PIONKA);
      this.mediator_.wyslijWiadomoscDoSerwera(wiadomosc);

      startowePole.getChildren().remove(this.kontenerAktualniePrzesuwanegoPionka_);
      pole.getChildren().add(this.kontenerAktualniePrzesuwanegoPionka_);
    }
    this.pionekPrzesuwany_ = false;
  }

  /**
   * Metoda odpowiedzialna za przesuniecie pionka.
   *
   * @param kolumnaStartowa Startowa kolumna pionka.
   * @param rzadStartowy Startowy rzad pionka.
   * @param kolumnaDocelowa Docelowa kolumna pionka.
   * @param rzadDocelowy Docelowy rzad pionka.
   */
  public void przesunPionekNaPodanePole(
      int kolumnaStartowa, int rzadStartowy, int kolumnaDocelowa, int rzadDocelowy) {
    Parent[][] polaPlanszy = this.model_.polaPlanszy();
    Pionek pionekDoPrzesuniecia =
        (Pionek) polaPlanszy[kolumnaStartowa][rzadStartowy].getChildrenUnmodifiable().get(0);

    // przesuniecie pionka
    polaPlanszy[kolumnaStartowa][rzadStartowy]
        .getChildrenUnmodifiable().remove(pionekDoPrzesuniecia);
    polaPlanszy[kolumnaDocelowa][rzadDocelowy].getChildrenUnmodifiable().add(pionekDoPrzesuniecia);
  }

  /**
   * Metoda odpowiedzialna za usuniecie pionka z podanego pola.
   *
   * @param kolumna Kolumna pionka.
   * @param rzad Rzad pionka.
   */
  public void zbijPionek(int kolumna, int rzad) {
    Parent[][] polaPlanszy = this.model_.polaPlanszy();
    polaPlanszy[kolumna][rzad].getChildrenUnmodifiable().remove(0);
  }
}
