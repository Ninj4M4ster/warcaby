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
import klient.kontroler.KontrolerWidoku;
import klient.kontroler.TypyKontrolerow;
import klient.model.Model;
import klient.widoki.widgety.Pionek;
import klient.widoki.widgety.PolePlanszy;
import org.junit.Test;

public class TestKontrolerGry extends TestKontroler {
  @Test
  public void testPrzekazModel() {
    KontrolerGry kontroler = new KontrolerGry();
    Model model = this.utworzModel(TypyKontrolerow.KONTROLER_GRY, false);
    kontroler.przekazModel(model);
  }

  @Test(expected = IllegalStateException.class)
  public void testPrzekazModelError() {
    KontrolerWidoku kontroler =
        this.utworzGotowyKontroler(TypyKontrolerow.KONTROLER_GRY, false);
    Model model = this.utworzModel(TypyKontrolerow.KONTROLER_GRY, false);
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
    KontrolerWidoku kontroler =
        utworzGotowyKontroler(TypyKontrolerow.KONTROLER_GRY, false);
    KontrolerAplikacji kontrolerAplikacji = new KontrolerAplikacji();
    kontroler.przekazGlownyKontroler(kontrolerAplikacji);
  }

  @Test
  public void testZacznijPrzesuwacPionek() {
    KontrolerGry kontroler =
        (KontrolerGry)this.utworzGotowyKontroler(TypyKontrolerow.KONTROLER_GRY, false);

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
    KontrolerGry kontroler =
        (KontrolerGry)this.utworzGotowyKontroler(TypyKontrolerow.KONTROLER_GRY, false);

    PolePlanszy pole = new PolePlanszy(0, 0);
    Pionek pionek = this.utworzSztucznyBialyPionek();
    pole.getChildren().add(pionek);

    kontroler.przesunPionek(this.utworzSztucznyMouseEvent(), pionek);

    kontroler.zacznijPrzesuwacPionek(this.utworzSztucznyMouseEvent(), pionek);

    kontroler.przesunPionek(this.utworzSztucznyMouseEvent(), pionek);
  }

  @Test
  public void testSkonczPrzesuwacPionek() {
    KontrolerGry kontroler =
        (KontrolerGry)this.utworzGotowyKontroler(TypyKontrolerow.KONTROLER_GRY, false);

    PolePlanszy pole = new PolePlanszy(0, 0);
    Pionek pionek = this.utworzSztucznyBialyPionek();
    pole.getChildren().add(pionek);

    kontroler.skonczPrzesuwacPionek();

    kontroler.zacznijPrzesuwacPionek(this.utworzSztucznyMouseEvent(), pionek);

    kontroler.skonczPrzesuwacPionek();
  }

  @Test
  public void testPuszczonoMyszkeNadPolem() {
    KontrolerGry kontroler =
        (KontrolerGry)this.utworzGotowyKontroler(TypyKontrolerow.KONTROLER_GRY, false);

    PolePlanszy pole = new PolePlanszy(0, 0);
    Pionek pionek = this.utworzSztucznyBialyPionek();
    pole.getChildren().add(pionek);

    PickResult wynik = new PickResult(pionek, new Point3D(0, 0, 0), 5.0);
    kontroler.puszczonoMyszkeNadPolem(pole, wynik);

    wynik = new PickResult(new Circle(), new Point3D(0, 0, 0), 5.0);
    kontroler.zacznijPrzesuwacPionek(this.utworzSztucznyMouseEvent(), pionek);

    kontroler.puszczonoMyszkeNadPolem(pole, wynik);
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
