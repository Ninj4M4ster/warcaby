package klient.widoki;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import klient.kontroller.KontrolerGry;
import klient.kontroller.KontrolerWidoku;
import klient.model.Model;
import klient.model.ModelGry;

public class WidokGry implements Widok {
  private ModelGry model_;
  private KontrolerGry kontroler_;
  private StackPane okno_;
  private GridPane planszaGry_;

  @Override
  public Parent utworzWidok(KontrolerWidoku kontroler, Model model) {
    if(this.model_ != null || this.kontroler_ != null)
      throw new IllegalStateException("Nie mozna utworzyc juz utworzonego widoku");

    this.model_ = (ModelGry) model;
    this.kontroler_ = (KontrolerGry) kontroler;

    this.okno_ = new StackPane();
    this.okno_.setAlignment(Pos.CENTER);

    this.utworzPlanszeGry();

    return this.okno_;
  }

  public void utworzPlanszeGry() {
    // plansza gry
    planszaGry_ = new GridPane();
    planszaGry_.setStyle("-fx-background-color: #222222;");
    planszaGry_.maxHeightProperty().bind(this.okno_.heightProperty().multiply(0.98));
    planszaGry_.maxWidthProperty().bind(planszaGry_.maxHeightProperty());
    planszaGry_.setAlignment(Pos.CENTER);

    // ustawienie ograniczen na kolumny
    utworzDodajOgraniczenieKolumny(5);
    utworzDodajOgraniczenieRzedu(5);

    for(int i=0; i < 8; i++) {
      utworzDodajOgraniczenieKolumny(11.25);
      utworzDodajOgraniczenieRzedu(11.25);
    }

    utworzDodajOgraniczenieKolumny(5);
    utworzDodajOgraniczenieRzedu(5);

    // indeksy pol
    this.utworzDodajIndeksyPlanszy();

    // dodanie do planszy gry kafelkow w odpowiednich kolorach
    // TODO(Jakub Drzewiecki): Zmienić na dodawanie rzędów do listy i potem na podstawie parametru
    //  wyznaczajacego kolor pionkow wyświetlać rzędy z listy od początku lub od końca
    for(int i=1; i <= 8; i+=2) {
      for(int j=1; j <= 8; j += 2) {
        StackPane kafelek = new StackPane();
        kafelek.setBackground(Background.fill(Color.BLACK));

        Label opis = new Label(i + " - " + j);
        kafelek.getChildren().add(opis);

        planszaGry_.add(kafelek, i, j);

        kafelek = new StackPane();
        kafelek.setBackground(Background.fill(Color.BEIGE));

        opis = new Label(i + " - " + (j + 1));
        kafelek.getChildren().add(opis);

        planszaGry_.add(kafelek, i, j + 1);
      }
      for(int j=1; j <= 8; j += 2) {
        StackPane kafelek = new StackPane();
        kafelek.setBackground(Background.fill(Color.BEIGE));

        Label opis = new Label((i + 1) + " - " + j);
        kafelek.getChildren().add(opis);

        planszaGry_.add(kafelek, i + 1, j);

        kafelek = new StackPane();
        kafelek.setBackground(Background.fill(Color.BLACK));

        opis = new Label((i + 1) + " - " + (j + 1));
        kafelek.getChildren().add(opis);

        planszaGry_.add(kafelek, i + 1, j + 1);
      }
    }


    this.okno_.getChildren().add(planszaGry_);
  }

  private void utworzDodajOgraniczenieKolumny(double procentSzerokosci) {
    ColumnConstraints kolumna = new ColumnConstraints();
    kolumna.setPercentWidth(procentSzerokosci);

    planszaGry_.getColumnConstraints().add(kolumna);
  }

  private void utworzDodajOgraniczenieRzedu(double procentWysokosci) {
    RowConstraints rzad = new RowConstraints();
    rzad.setPercentHeight(procentWysokosci);

    planszaGry_.getRowConstraints().add(rzad);
  }

  private void utworzDodajIndeksyPlanszy() {
    // utworzenie naroznikow
    planszaGry_.add(this.utworzNaroznikPlanszy(), 0, 0);
    planszaGry_.add(this.utworzNaroznikPlanszy(), 9, 9);
    planszaGry_.add(this.utworzNaroznikPlanszy(), 0, 9);
    planszaGry_.add(this.utworzNaroznikPlanszy(), 9, 0);

    // utworzenie indeksow z wymaganymi opisami
    for(int i=1; i < 9; i++) {
      planszaGry_.add(this.utworzIndeksPlanszy(0 + " - " + i), 0, i);
      planszaGry_.add(this.utworzIndeksPlanszy(9 + " - " + i), 9, i);
      planszaGry_.add(this.utworzIndeksPlanszy(i + " - " + 0), i, 0);
      planszaGry_.add(this.utworzIndeksPlanszy(i +  " - " + 9), i, 9);
    }
  }

  private StackPane utworzNaroznikPlanszy() {
    StackPane rog = new StackPane();
    rog.setBackground(Background.fill(Color.BROWN));
    return rog;
  }

  private StackPane utworzIndeksPlanszy(String opis) {
    StackPane indeks = new StackPane();
    Label opisIndeksu = new Label(opis);
    indeks.getChildren().add(opisIndeksu);
    indeks.setBackground(Background.fill(Color.BROWN));
    return indeks;
  }
}
