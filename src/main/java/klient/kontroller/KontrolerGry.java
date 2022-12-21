package klient.kontroller;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.shape.Circle;
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
  private KontrolerAplikacji glownyKontroler_;

  /** Kontener aktualnie przesuwanego pionka */
  private Pionek kontenerAktualniePrzesuwanegoPionka_;

  /** Czy jakis pionek jest aktualnie przesuwany? */
  private boolean pionekPrzesuwany_;

  private boolean przyciskMyszkiTrzymany_;

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
    if(this.glownyKontroler_ != null)
      throw new IllegalStateException("Nie mozna dwa razy przekazac kontrolera "
          + "aplikacji do kontrolera widoku");
    this.glownyKontroler_ = kontrolerGlowny;
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
   *
   * @param pionek Przesuwany pionek.
   */
  public void skonczPrzesuwacPionek(Circle pionek) {
    if(this.pionekPrzesuwany_) {
      kontenerAktualniePrzesuwanegoPionka_.setTranslateX(0);
      kontenerAktualniePrzesuwanegoPionka_.setTranslateY(0);
    }
  }

  /**
   * Metoda odpowiedzialna za przesuniecie pionka po jego puszczeniu.
   *
   * @param pole Pole nad ktorym znajdowala sie myszka po puszczeniu pionka.
   */
  public void puszczonoMyszkeNadPolem(PolePlanszy pole, PickResult wynikWydarzenia) {
    if(this.pionekPrzesuwany_
        && wynikWydarzenia.getIntersectedNode() instanceof Circle) {
      PolePlanszy startowePole =
          (PolePlanszy) this.kontenerAktualniePrzesuwanegoPionka_.getParent();
      int kolumnaStartowa = startowePole.kolumna();
      int rzadStartowy = startowePole.rzad();

      int docelowaKolumna = pole.kolumna();
      int docelowyRzad = pole.rzad();

      startowePole.getChildren().remove(this.kontenerAktualniePrzesuwanegoPionka_);
      pole.getChildren().add(this.kontenerAktualniePrzesuwanegoPionka_);
    }
    this.pionekPrzesuwany_ = false;
  }
}
