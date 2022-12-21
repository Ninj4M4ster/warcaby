package klient.kontroller;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import klient.model.Model;
import klient.model.ModelGry;
import klient.widoki.widgety.Pionek;

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
    kontenerPrzesuwanegoPionka.ustawStartowaPozycjaX(mouseEvent.getSceneX());
    kontenerPrzesuwanegoPionka.ustawStartowaPozycjaY(mouseEvent.getSceneY());
    kontenerPrzesuwanegoPionka.getParent().toFront();

    kontenerAktualniePrzesuwanegoPionka_ = kontenerPrzesuwanegoPionka;
    pionekPrzesuwany_ = true;
  }

  /**
   * Metoda odpowiedzialna za przesuwanie pionka za myszka.
   *
   * @param mouseEvent Wydarzenie przesuniecia myszka po ekranie.
   * @param kontenerPrzesuwanegoPionka Kontener przesuwanego pionka.
   * @param pionek Trzymany pionek.
   */
  public void przesunPionek(MouseEvent mouseEvent, Pionek kontenerPrzesuwanegoPionka, Circle pionek) {
    pionek.setTranslateX(mouseEvent.getSceneX() - kontenerPrzesuwanegoPionka.startowaPozycjaX());
    pionek.setTranslateY(mouseEvent.getSceneY() - kontenerPrzesuwanegoPionka.startowaPozycjaY());
  }

  /**
   * Metoda odpowiedzialna za zakonczenie przesuwania pionka po planszy.
   *
   * @param pionek Przesuwany pionek.
   */
  public void skonczPrzesuwacPionek(Circle pionek) {
    pionek.setTranslateX(0);
    pionek.setTranslateY(0);
  }

  /**
   * Metoda odpowiedzialna za przesuniecie pionka po jego puszczeniu.
   *
   * @param pole Pole nad ktorym znajdowala sie myszka po puszczeniu pionka.
   */
  public void puszczonoMyszkeNadPolem(StackPane pole) {
    if(pionekPrzesuwany_) {
      StackPane startowePole = (StackPane) kontenerAktualniePrzesuwanegoPionka_.getParent();
      startowePole.getChildren().remove(kontenerAktualniePrzesuwanegoPionka_);
      pole.getChildren().add(kontenerAktualniePrzesuwanegoPionka_);
      System.out.println(pole);
      pionekPrzesuwany_ = false;
    }
  }
}
