package testKlient.testKontroler;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point3D;
import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import klient.komunikacja.Mediator;
import klient.kontroler.GlownyKontroler;
import klient.kontroler.KontrolerGry;
import klient.kontroler.KontrolerWidoku;
import klient.kontroler.TypyKontrolerow;
import klient.model.Model;
import klient.model.ModelGry;
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
  public void testOdnowPolaczenie() {
    KontrolerWidoku kontroler =
        utworzGotowyKontroler(TypyKontrolerow.KONTROLER_GRY, false);
    kontroler.odnowPolaczenie();
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
  public void testZaktualizujPlansze() {
    GlownyKontroler glownyKontroler = GlownyKontroler.instancja();
    KontrolerGry kontroler = new KontrolerGry();
    BooleanProperty czyPolaczono = new SimpleBooleanProperty();
    czyPolaczono.set(false);
    ModelGry model = new ModelGry(czyPolaczono);
    kontroler.przekazModel(model);
    Mediator mediator = new Mediator(GlownyKontroler.instancja());
    kontroler.przekazMediator(mediator);

    glownyKontroler.rozpocznijGre("110101010010101011010101000000000"
            + "00000000020202022020202002020202");
    Parent[][] polaPlanszy = new Parent[8][8];
    for(int i=0; i < 8; i++)
      for(int j=0; j < 8; j++) {
        StackPane pole = new StackPane();
        polaPlanszy[i][j] = pole;
      }

    model.ustawPolaPlanszy(polaPlanszy);

    String plansza = "01010101\n"
        + "10101010\n"
        + "01010101\n"
        + "00000000\n"
        + "00000000\n"
        + "20202020\n"
        + "02020202\n"
        + "20202020\n";
    kontroler.zaktualizujPlansze(plansza);
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

  @Test
  public void testProbujUsunPionek() {
    GlownyKontroler glownyKontroler = GlownyKontroler.instancja();
    KontrolerGry kontroler = new KontrolerGry();
    BooleanProperty czyPolaczono = new SimpleBooleanProperty();
    czyPolaczono.set(false);
    ModelGry model = new ModelGry(czyPolaczono);
    kontroler.przekazModel(model);
    Mediator mediator = new Mediator(GlownyKontroler.instancja());
    kontroler.przekazMediator(mediator);

    glownyKontroler.rozpocznijGre("110101010010101011010101000000000"
        + "00000000020202022020202002020202");
    Parent[][] polaPlanszy = new Parent[8][8];
    for(int i=0; i < 8; i++)
      for(int j=0; j < 8; j++) {
        StackPane pole = new StackPane();
        polaPlanszy[i][j] = pole;
      }
    ((StackPane)polaPlanszy[0][0]).getChildren().add(new StackPane());
    model.ustawPolaPlanszy(polaPlanszy);

    String plansza = "01010101\n"
        + "10101010\n"
        + "03030303\n"
        + "00000000\n"
        + "00000000\n"
        + "40404040\n"
        + "02020202\n"
        + "20202020\n";
    kontroler.zaktualizujPlansze(plansza);
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
