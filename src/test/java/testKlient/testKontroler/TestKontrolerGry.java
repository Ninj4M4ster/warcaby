package testKlient.testKontroler;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point3D;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import klient.kontroler.KontrolerAplikacji;
import klient.kontroler.KontrolerGry;
import klient.model.ModelGry;
import klient.widoki.widgety.Pionek;
import klient.widoki.widgety.PolePlanszy;
import org.junit.Test;

public class TestKontrolerGry {
  @Test
  public void testPrzekazModel() {
    KontrolerGry kontroler = new KontrolerGry();
    ModelGry model = new ModelGry();
    kontroler.przekazModel(model);
  }

  @Test(expected = IllegalStateException.class)
  public void testPrzekazModelError() {
    KontrolerGry kontroler = this.utworzPelnyKontroler();
    ModelGry model = new ModelGry();
    kontroler.przekazModel(model);
  }

  @Test
  public void testPrzekazGlownyKontroler() {
    KontrolerGry kontroler = new KontrolerGry();
    KontrolerAplikacji kontrolerAplikacji = new KontrolerAplikacji();
    kontroler.przekazGlownyKontroler(kontrolerAplikacji);
  }

  @Test(expected = IllegalStateException.class)
  public void testPrzekazGlownyKontrolerError() {
    KontrolerGry kontroler = utworzPelnyKontroler();
    KontrolerAplikacji kontrolerAplikacji = new KontrolerAplikacji();
    kontroler.przekazGlownyKontroler(kontrolerAplikacji);
  }

  @Test
  public void testZacznijPrzesuwacPionek() {
    KontrolerGry kontroler = this.utworzPelnyKontroler();

    PolePlanszy pole = new PolePlanszy(0, 0);
    Pionek pionek = this.utworzSztucznyBialyPionek();
    pole.getChildren().add(pionek);

    kontroler.zacznijPrzesuwacPionek(this.utworzSztucznyMouseEvent(), pionek);

    pole = new PolePlanszy(0, 0);
    pionek = this.utworzSztucznyCzarnyPionek();
    pole.getChildren().add(pionek);

    kontroler.zacznijPrzesuwacPionek(this.utworzSztucznyMouseEvent(), pionek);
  }

  @Test
  public void testPrzesunPionek() {
    KontrolerGry kontroler = this.utworzPelnyKontroler();

    PolePlanszy pole = new PolePlanszy(0, 0);
    Pionek pionek = this.utworzSztucznyBialyPionek();
    pole.getChildren().add(pionek);

    kontroler.przesunPionek(this.utworzSztucznyMouseEvent(), pionek);

    kontroler.zacznijPrzesuwacPionek(this.utworzSztucznyMouseEvent(), pionek);

    kontroler.przesunPionek(this.utworzSztucznyMouseEvent(), pionek);
  }

  @Test
  public void testSkonczPrzesuwacPionek() {
    KontrolerGry kontroler = this.utworzPelnyKontroler();

    PolePlanszy pole = new PolePlanszy(0, 0);
    Pionek pionek = this.utworzSztucznyBialyPionek();
    pole.getChildren().add(pionek);

    kontroler.skonczPrzesuwacPionek();

    kontroler.zacznijPrzesuwacPionek(this.utworzSztucznyMouseEvent(), pionek);

    kontroler.skonczPrzesuwacPionek();
  }

  @Test
  public void testPuszczonoMyszkeNadPolem() {
    KontrolerGry kontroler = this.utworzPelnyKontroler();

    PolePlanszy pole = new PolePlanszy(0, 0);
    Pionek pionek = this.utworzSztucznyBialyPionek();
    pole.getChildren().add(pionek);

    PickResult wynik = new PickResult(pionek, new Point3D(0, 0, 0), 5.0);
    kontroler.puszczonoMyszkeNadPolem(pole, wynik);

    wynik = new PickResult(new Circle(), new Point3D(0, 0, 0), 5.0);
    kontroler.zacznijPrzesuwacPionek(this.utworzSztucznyMouseEvent(), pionek);

    kontroler.puszczonoMyszkeNadPolem(pole, wynik);
  }

  private KontrolerGry utworzPelnyKontroler() {
    KontrolerGry kontroler = new KontrolerGry();
    ModelGry model = new ModelGry();
    KontrolerAplikacji kontrolerAplikacji = new KontrolerAplikacji();

    kontroler.przekazModel(model);
    kontroler.przekazGlownyKontroler(kontrolerAplikacji);
    return kontroler;
  }

  private MouseEvent utworzSztucznyMouseEvent() {
    return new MouseEvent(MouseEvent.MOUSE_CLICKED,
        0, 0, 1, 1, MouseButton.PRIMARY,
        1,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        new PickResult(
            new Pionek(
                Color.BLACK,
                Color.BLACK,
                new SimpleDoubleProperty(),
                new KontrolerGry(),
                "bialy"),
            new Point3D(0, 0, 0),
            5.0));
  }

  private Pionek utworzSztucznyBialyPionek() {
    return new Pionek(
        Color.BLACK,
        Color.BLACK,
        new SimpleDoubleProperty(),
        new KontrolerGry(),
        "bialy");
  }

  private Pionek utworzSztucznyCzarnyPionek() {
    return new Pionek(
        Color.BLACK,
        Color.BLACK,
        new SimpleDoubleProperty(),
        new KontrolerGry(),
        "czarny");
  }
}
