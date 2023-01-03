package klient.widoki;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import klient.kontroler.KontrolerGry;
import klient.kontroler.KontrolerWidoku;
import klient.model.Model;
import klient.model.ModelGry;
import klient.widoki.widgety.Pionek;
import klient.widoki.widgety.PolePlanszy;

/**
 * Klasa reprezentujaca widok rozgrywki w warcaby.
 * TODO(Jakub Drzewiecki): Kolory poszczegolnych elementow widoku moglyby
 *  znajdywac sie w jakiejs globalnej konfiguracji badz modelu.
 */
public class WidokGry extends Widok {
  /** Model widoku */
  private ModelGry model_;

  /** Kontroler widoku. */
  private KontrolerGry kontroler_;

  /** Kontener zawierajacy wszystkie elementy widoku */
  private BorderPane okno_;

  /** Kontener przechowujacy plansze gry, czyli wszystkie jej pola */
  private GridPane planszaGry_;

  /** Zmienna okreslajaca procentowa szerokosc lub/i
   * wysokosc pol przechowujacych indeksy pol planszy */
  private static final double SZEROKOSC_INDEKSOW = 4;

  /**
   * Metoda tworzaca wszystkie elementy widoku oraz zwracajaca kontener przechowujacy je.
   *
   * @param kontroler Kontroler widoku.
   * @param model Model widoku.
   * @return Kontener wszystkich elementow widoku.
   */
  @Override
  public Parent utworzWidok(KontrolerWidoku kontroler, Model model) {
    if(this.model_ != null || this.kontroler_ != null)
      throw new IllegalStateException("Nie mozna utworzyc juz utworzonego widoku");

    this.model_ = (ModelGry) model;
    this.kontroler_ = (KontrolerGry) kontroler;

    this.okno_ = new BorderPane();
    this.okno_.setBackground(Background.fill(Color.valueOf("#242424")));

    this.okno_.setBottom(this.utworzPasekStatusu(this.model_));
    this.utworzPlanszeGry();

    return this.okno_;
  }

  /**
   * Metoda odpowiedzialna za utworzenie planszy gry razem ze wszystkimi polami.
   */
  public void utworzPlanszeGry() {
    // plansza gry
    planszaGry_ = this.model_.planszaGry();
    planszaGry_.setStyle("-fx-background-color: #222222;");
    planszaGry_.maxHeightProperty().bind(this.okno_.heightProperty().multiply(0.98));
    planszaGry_.maxWidthProperty().bind(planszaGry_.maxHeightProperty());
    planszaGry_.setAlignment(Pos.CENTER);

    int iloscPol = this.model_.iloscPol();

    // ustawienie ograniczen na kolumny
    utworzDodajOgraniczenieKolumny(SZEROKOSC_INDEKSOW);
    utworzDodajOgraniczenieRzedu(SZEROKOSC_INDEKSOW);

    for(int i=0; i < iloscPol; i++) {
      utworzDodajOgraniczenieKolumny((100.0 - SZEROKOSC_INDEKSOW) / iloscPol);
      utworzDodajOgraniczenieRzedu((100.0 - SZEROKOSC_INDEKSOW) / iloscPol);
    }

    utworzDodajOgraniczenieKolumny(SZEROKOSC_INDEKSOW);
    utworzDodajOgraniczenieRzedu(SZEROKOSC_INDEKSOW);

    // indeksy pol
    this.utworzDodajIndeksyPlanszy();

    // dodanie do planszy gry kafelkow w odpowiednich kolorach
    Parent[][] listaPol = this.stworzListePolPlanszy();
    this.model_.ustawPolaPlanszy(listaPol);
    this.utworzStartowePionki(listaPol);
    if(this.model_.kolorPionkow().equals("bialy"))
      this.wyswietlWidokBialy(listaPol);
    else
      this.wyswietlWidokCzarny(listaPol);

    this.okno_.setCenter(planszaGry_);
  }

  /**
   * Metoda tworzaca i dodajaca do planszy ograniczenie kolumny z podana procentowa szerokoscia.
   *
   * @param procentSzerokosci Procentowa szerokosc kolumny.
   */
  private void utworzDodajOgraniczenieKolumny(double procentSzerokosci) {
    ColumnConstraints kolumna = new ColumnConstraints();
    kolumna.setPercentWidth(procentSzerokosci);

    planszaGry_.getColumnConstraints().add(kolumna);
  }

  /**
   * Metoda tworzaca i dodajaca do planszy ograniczenie rzedu z podana procentowa wysokoscia.
   *
   * @param procentWysokosci Procentowa wysokosc rzedu.
   */
  private void utworzDodajOgraniczenieRzedu(double procentWysokosci) {
    RowConstraints rzad = new RowConstraints();
    rzad.setPercentHeight(procentWysokosci);

    planszaGry_.getRowConstraints().add(rzad);
  }

  // TODO(Jakub Drzewiecki): Dosyć długa ta funkcja,
  //  trzeba się zastanowić czy nie da się jej jakoś sensownie uprościć

  /**
   * Metoda odpowiedzialna za utworzenie i dodanie do planszy pol
   * reprezentujacych obramowanie planszy razem z indeksami pol.
   */
  private void utworzDodajIndeksyPlanszy() {
    int iloscPol = this.model_.iloscPol();
    // utworzenie naroznikow
    planszaGry_.add(this.utworzNaroznikPlanszy(Color.valueOf("#5c3600")), 0, 0);
    planszaGry_.add(this.utworzNaroznikPlanszy(Color.valueOf("#5c3600")), iloscPol + 1, iloscPol + 1);
    planszaGry_.add(this.utworzNaroznikPlanszy(Color.valueOf("#382100")), 0, iloscPol + 1);
    planszaGry_.add(this.utworzNaroznikPlanszy(Color.valueOf("#382100")), iloscPol + 1, 0);

    // utworzenie indeksow z wymaganymi opisami
    String[] znakiIndeksow = this.model_.znakiIndeksow();
    for(int i=1; i < iloscPol + 1; i+=2) {
      if(this.model_.kolorPionkow().equals("bialy")) {
        // normalna kolejnosc wyswietlania indeksow
        planszaGry_.add(
            this.utworzIndeksPlanszy(znakiIndeksow[iloscPol - i], Color.valueOf("#382100")),
            0,
            i);
        planszaGry_.add(
            this.utworzIndeksPlanszy(znakiIndeksow[iloscPol - i - 1], Color.valueOf("#5c3600")),
            0,
            i + 1);

        planszaGry_.add(
            this.utworzIndeksPlanszy(znakiIndeksow[iloscPol - i], Color.valueOf("#5c3600")),
            iloscPol + 1,
            i);
        planszaGry_.add(
            this.utworzIndeksPlanszy(znakiIndeksow[iloscPol - i - 1], Color.valueOf("#382100")),
            iloscPol + 1,
            i + 1);

        planszaGry_.add(
            this.utworzIndeksPlanszy(String.valueOf(i), Color.valueOf("#382100")),
            i,
            0);
        planszaGry_.add(
            this.utworzIndeksPlanszy(String.valueOf(i + 1), Color.valueOf("#5c3600")),
            i + 1,
            0);

        planszaGry_.add(
            this.utworzIndeksPlanszy(String.valueOf(i), Color.valueOf("#5c3600")),
            i,
            iloscPol + 1);
        planszaGry_.add(
            this.utworzIndeksPlanszy(String.valueOf(i+1), Color.valueOf("#382100")),
            i + 1, iloscPol + 1);
      } else {
        // odwrocona kolejnosc wyswietlania indeksow
        planszaGry_.add(
            this.utworzIndeksPlanszy(znakiIndeksow[i-1], Color.valueOf("#382100")),
            0,
            i);
        planszaGry_.add(
            this.utworzIndeksPlanszy(znakiIndeksow[i], Color.valueOf("#5c3600")),
            0,
            i + 1);

        planszaGry_.add(
            this.utworzIndeksPlanszy(znakiIndeksow[i-1], Color.valueOf("#5c3600")),
            iloscPol + 1,
            i);
        planszaGry_.add(
            this.utworzIndeksPlanszy(znakiIndeksow[i], Color.valueOf("#382100")),
            iloscPol + 1,
            i + 1);

        planszaGry_.add(
            this.utworzIndeksPlanszy(
                String.valueOf(iloscPol - i + 1), Color.valueOf("#382100")),
            i,
            0);
        planszaGry_.add(
            this.utworzIndeksPlanszy(
                String.valueOf(iloscPol - i), Color.valueOf("#5c3600")),
            i + 1,
            0);

        planszaGry_.add(
            this.utworzIndeksPlanszy(
                String.valueOf(iloscPol - i + 1), Color.valueOf("#5c3600")),
            i,
            iloscPol + 1);
        planszaGry_.add(
            this.utworzIndeksPlanszy(
                String.valueOf(iloscPol - i), Color.valueOf("#382100")),
            i + 1, iloscPol + 1);
      }
    }
  }

  /**
   * Metoda tworzaca i zwracajaca widget reprezentujacy naroznik obramowania planszy.
   *
   * @param kolor Kolor wypelnienia widgetu.
   * @return Widget reprezentujacy naroznik obramowania planszy.
   */
  private StackPane utworzNaroznikPlanszy(Color kolor) {
    StackPane rog = new StackPane();
    rog.setBorder(Border.stroke(Color.valueOf("#2e2e2e")));
    rog.setBackground(Background.fill(kolor));
    return rog;
  }

  /**
   * Metoda tworzaca i zwracajaca widget reprezentujacy obramowanie planszy
   * razem z wyswietlonym indeksem pol.
   *
   * @param opis Indeks pol przy ktorych znajduje sie widget.
   * @param kolor Kolor wypelnienia widgetu.
   * @return Widget reprezentujacy obramowanie planszy z wyswietlonym indeksem pol.
   */
  private StackPane utworzIndeksPlanszy(String opis, Color kolor) {
    StackPane indeks = new StackPane();
    indeks.setBorder(Border.stroke(Color.valueOf("#2e2e2e")));

    Label opisIndeksu = new Label(opis);
    opisIndeksu.setTextFill(Color.valueOf("#bababa"));

    indeks.getChildren().add(opisIndeksu);

    indeks.setBackground(Background.fill(kolor));
    return indeks;
  }

  /**
   * Metoda odpowiedzialna za utworzenie listy wszystkich pol planszy,
   * ktore potem trzeba wyswietlic.
   *
   * @return Lista wszystkich pol planszy.
   */
  private Parent[][] stworzListePolPlanszy() {
    int iloscPol = this.model_.iloscPol();
    Parent[][] listaPol = new Parent[iloscPol][iloscPol];
    // i - kolumny, j - rzedy
    for(int i=0; i < iloscPol; i+=2) {
      for(int j=0; j < iloscPol; j += 2) {
        listaPol[i][j] = this.utworzPolePlanszy(Color.BEIGE, i, j);
        listaPol[i][j+1] = this.utworzPolePlanszy(Color.valueOf("#212121"), i, j + 1);
      }
      for(int j=0; j < iloscPol; j += 2) {
        listaPol[i+1][j] = this.utworzPolePlanszy(Color.valueOf("#212121"), i + 1, j);
        listaPol[i+1][j+1] = this.utworzPolePlanszy(Color.BEIGE, i + 1, j + 1);
      }
    }
    return listaPol;
  }

  /**
   * Metoda tworzaca widget reprezentujacy pole planszy.
   *
   * @param kolor Kolor pola planszy.
   * @param kolumna Numer kolumny, w ktorej dodawany jest pionek.
   * @param rzad Numer rzedu, w ktorym dodawany jest pionek.
   * @return Widget reprezentujacy pole planszy.
   */
  private PolePlanszy utworzPolePlanszy(Color kolor, int kolumna, int rzad) {
    PolePlanszy pole = new PolePlanszy(kolumna, rzad);
    pole.setBackground(Background.fill(kolor));
    pole.setAlignment(Pos.CENTER);
    pole.setOnMouseEntered(
        mouseDragEvent -> kontroler_.puszczonoMyszkeNadPolem(pole, mouseDragEvent.getPickResult()));
    return pole;
  }

  /**
   * Metoda odpowiedzialna za utworzenie pionkow i ustawienie ich na odpowiednich polach planszy.
   *
   * @param listaPol Wszystkie pola na planszy.
   */
  private void utworzStartowePionki(Parent[][] listaPol) {
    int iloscPol = this.model_.iloscPol();
    for(int rzad=0; rzad < iloscPol/2 - 1; rzad++) {
      for(int kolumna=0; kolumna < iloscPol; kolumna++) {
        if(rzad%2 == 0 && kolumna%2 == 1 || rzad%2 == 1 && kolumna%2 == 0) {
          this.dodajPionekNaPlansze(kolumna,
              rzad,
              listaPol,
              Color.valueOf("#363636"),
              Color.valueOf("#424242"),
              "czarny");
        }
      }
    }
    for(int rzad=iloscPol - 1; rzad > iloscPol/2; rzad--) {
      for(int kolumna=0; kolumna < iloscPol; kolumna++) {
        if(rzad%2 == 0 && kolumna%2 == 1 || rzad%2 == 1 && kolumna%2 == 0) {
          this.dodajPionekNaPlansze(kolumna,
              rzad,
              listaPol,
              Color.valueOf("#dbdbdb"),
              Color.valueOf("#a3a3a3"),
              "bialy");
        }
      }
    }
  }

  /**
   * Metoda odpowiedzialna za utworzenie i dodanie pionka do planszy na podanych indeksach.
   *
   * @param kolumna Kolumna, w ktorej ma byc dodany pionek.
   * @param rzad Rzad, w ktorym ma byc dodany pionek.
   * @param listaPol Wszystkie pola planszy.
   * @param kolor Kolor, jaki ma miec pionek.
   * @param kolorObramowki Kolor obramowania pionka.
   * @param kolorPionka Napis reprezentujacy kolor pionka - 'bialy' lub 'czarny'.
   */
  private void dodajPionekNaPlansze(int kolumna,
      int rzad,
      Parent[][] listaPol,
      Color kolor,
      Color kolorObramowki,
      String kolorPionka) {
    StackPane pole = (StackPane) listaPol[kolumna][rzad];
    Pionek pionek =
        new Pionek(kolor, kolorObramowki, pole.widthProperty(), this.kontroler_, kolorPionka);

    pole.getChildren().add(pionek);
  }

  /**
   * Metoda ta wyswietla podane pola z perspektywy uzytkownika grajacego bialymi pionkami.
   *
   * @param listaPol Lista wszystkich pol na planszy.
   */
  private void wyswietlWidokBialy(Parent[][] listaPol) {
    int iloscPol = this.model_.iloscPol();
    for(int i=0; i < iloscPol; i++) {
      for(int j=0; j < iloscPol; j++) {
        this.planszaGry_.add(listaPol[i][j], i + 1, j + 1);
      }
    }
  }

  /**
   * Metoda ta wyswietla podane pola z perspektywy uzytkownika grajacego czarnymi pionkami.
   *
   * @param listaPol Lista wszystkich pol na planszy.
   */
  private void wyswietlWidokCzarny(Parent[][] listaPol) {
    int iloscPol = this.model_.iloscPol();
    for(int i=0; i < iloscPol; i++) {
      for(int j=0; j < iloscPol; j++) {
        this.planszaGry_.add(listaPol[iloscPol - i - 1][iloscPol - j - 1], i + 1, j + 1);
      }
    }
  }
}
