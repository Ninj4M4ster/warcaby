package klient.widoki;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import klient.kontroler.KontrolerOgladaniaGry;
import klient.kontroler.KontrolerWidoku;
import klient.model.Model;
import klient.model.ModelOgladaniaGry;
import klient.widoki.widgety.NieaktywnyPionek;
import klient.widoki.widgety.Pionek;
import klient.widoki.widgety.PolePlanszy;

/**
 * Klasa reprezentujaca widok ogladania rozgrywki zapisanej w bazie danych.
 */
public class WidokOgladaniaGry extends Widok {
  private BorderPane okno_;
  /** Kontroler widoku */
  private KontrolerOgladaniaGry kontroler_;
  /** Model widoku */
  private ModelOgladaniaGry model_;

  private GridPane planszaGry_;

  /** Zmienna okreslajaca procentowa szerokosc lub/i
   * wysokosc pol przechowujacych indeksy pol planszy */
  private static final double SZEROKOSC_INDEKSOW = 4;

  /**
   * Metoda odpowiedzialna za utworzenie widoku.
   *
   * @param kontroler Kontroler widoku.
   * @param model Model widoku.
   * @return Kontener wszystkich elementow widoku.
   */
  @Override
  public Parent utworzWidok(KontrolerWidoku kontroler, Model model) {
    this.model_ = (ModelOgladaniaGry) model;
    this.kontroler_ = (KontrolerOgladaniaGry) kontroler;

    this.okno_ = new BorderPane();
    this.okno_.setBackground(Background.fill(Color.valueOf("#242424")));

    this.okno_.setBottom(this.utworzPasekStatusu(model, kontroler));
    this.utworzPlanszeGry();

    return this.okno_;
  }

  /**
   * Metoda odpowiedzialna za utworzenie planszy gry razem ze wszystkimi polami.
   */
  public void utworzPlanszeGry() {
    BorderPane kontenerPlanszyGry = new BorderPane();

    // plansza gry
    planszaGry_ = this.model_.planszaGry();
    planszaGry_.setStyle("-fx-background-color: #222222;");
    planszaGry_.maxHeightProperty().bind(this.okno_.heightProperty().multiply(0.9));
    planszaGry_.maxWidthProperty().bind(planszaGry_.maxHeightProperty());
    planszaGry_.setAlignment(Pos.CENTER);

    kontenerPlanszyGry.setCenter(planszaGry_);

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
    this.wyswietlPola(listaPol);

    this.ustawNazwyGraczy(kontenerPlanszyGry);
    this.utworzPrzyciskiDoPrzewijaniaRuchow(kontenerPlanszyGry);

    this.okno_.setCenter(kontenerPlanszyGry);
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
    // i - rzedy, j - kolumny
    for(int i=0; i < iloscPol; i+=2) {
      for(int j=0; j < iloscPol; j += 2) {
        listaPol[i][j] = this.utworzPolePlanszy(Color.valueOf("#212121"), i, j);
        listaPol[i][j+1] = this.utworzPolePlanszy(Color.BEIGE, i, j + 1);
      }
      for(int j=0; j < iloscPol; j += 2) {
        listaPol[i+1][j] = this.utworzPolePlanszy(Color.BEIGE, i + 1, j);
        listaPol[i+1][j+1] = this.utworzPolePlanszy(Color.valueOf("#212121"), i + 1, j + 1);
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
  private PolePlanszy utworzPolePlanszy(Color kolor, int rzad, int kolumna) {
    PolePlanszy pole = new PolePlanszy(rzad, kolumna);
    pole.setBackground(Background.fill(kolor));
    pole.setAlignment(Pos.CENTER);
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
        if(rzad%2 == 0 && kolumna%2 == 0 || rzad%2 == 1 && kolumna%2 == 1) {
          this.dodajPionekNaPlansze(kolumna,
              rzad,
              listaPol,
              Color.valueOf("#dbdbdb"),
              Color.valueOf("#a3a3a3"),
              "bialy");
        }
      }
    }
    for(int rzad=iloscPol - 1; rzad > iloscPol/2; rzad--) {
      for(int kolumna=0; kolumna < iloscPol; kolumna++) {
        if(rzad%2 == 0 && kolumna%2 == 0 || rzad%2 == 1 && kolumna%2 == 1) {
          this.dodajPionekNaPlansze(kolumna,
              rzad,
              listaPol,
              Color.valueOf("#363636"),
              Color.valueOf("#424242"),
              "czarny");
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
    StackPane pole = (StackPane) listaPol[rzad][kolumna];
    NieaktywnyPionek pionek =
        new Pionek(kolor, kolorObramowki, pole.widthProperty(), this.kontroler_, kolorPionka);

    pole.getChildren().add(pionek);
  }

  /**
   * Metoda ta wyswietla podane pola z perspektywy uzytkownika grajacego bialymi pionkami.
   *
   * @param listaPol Lista wszystkich pol na planszy.
   */
  private void wyswietlPola(Parent[][] listaPol) {
    int iloscPol = this.model_.iloscPol();
    for(int i=0; i < iloscPol; i++) {
      for(int j=0; j < iloscPol; j++) {
        this.planszaGry_.add(listaPol[iloscPol - i - 1][j], j + 1, i + 1);
      }
    }
  }

  /**
   * Metoda sluzaca do utworzenia opisow z nazwami graczy po stronie,
   * po ktorej grali w oryginalnej grze.
   *
   * @param kontenerPlanszy Kontener zawierajacy plansze w centrum.
   */
  private void ustawNazwyGraczy(BorderPane kontenerPlanszy) {
    HBox kontenerNazwyCzarnych = new HBox();
    kontenerNazwyCzarnych.setAlignment(Pos.CENTER);
    kontenerNazwyCzarnych.setPadding(new Insets(5, 0, 5, 0));

    Label nazwaGraczaGrajacegoCzarnymi = new Label();
    nazwaGraczaGrajacegoCzarnymi.setTextFill(Color.WHITE);
    nazwaGraczaGrajacegoCzarnymi.setBackground(Background.fill(Color.GRAY));
    nazwaGraczaGrajacegoCzarnymi.setPadding(new Insets(2, 5, 2, 5));
    nazwaGraczaGrajacegoCzarnymi.setFont(new Font("Book Antiqua", 16));
    nazwaGraczaGrajacegoCzarnymi.setBorder(Border.stroke(Color.BLACK));
    kontenerNazwyCzarnych.getChildren().add(nazwaGraczaGrajacegoCzarnymi);

    kontenerPlanszy.setTop(kontenerNazwyCzarnych);

    HBox kontenerNazwyBialych = new HBox();
    kontenerNazwyBialych.setAlignment(Pos.CENTER);
    kontenerNazwyBialych.setPadding(new Insets(5, 0, 5, 0));

    Label nazwaGraczaGrajacegoBialymi = new Label();
    nazwaGraczaGrajacegoBialymi.setTextFill(Color.WHITE);
    nazwaGraczaGrajacegoBialymi.setBackground(Background.fill(Color.GRAY));
    nazwaGraczaGrajacegoBialymi.setPadding(new Insets(2, 5, 2, 5));
    nazwaGraczaGrajacegoBialymi.setFont(new Font("Book Antiqua", 16));
    nazwaGraczaGrajacegoBialymi.setBorder(Border.stroke(Color.BLACK));
    kontenerNazwyBialych.getChildren().add(nazwaGraczaGrajacegoBialymi);

    kontenerPlanszy.setBottom(kontenerNazwyBialych);
    if(this.model_.kolorGracza1() == 1) {
      nazwaGraczaGrajacegoBialymi.setText(this.model_.nazwaGracza1());
      nazwaGraczaGrajacegoCzarnymi.setText(this.model_.nazwaGracza2());
    } else {
      nazwaGraczaGrajacegoBialymi.setText(this.model_.nazwaGracza2());
      nazwaGraczaGrajacegoCzarnymi.setText(this.model_.nazwaGracza1());
    }
  }

  /**
   * Metoda odpowiedzialna za utworzenie przyciskow sluzacych do przewijania
   * kolejnych ruchow ogladanej gry.
   *
   * @param kontenerPlanszy kontener z plansza w centrum.
   */
  private void utworzPrzyciskiDoPrzewijaniaRuchow(BorderPane kontenerPlanszy) {
    // kontener na lewy przycisk
    VBox kontenerPrzyciskLewy = new VBox();
    kontenerPrzyciskLewy.setAlignment(Pos.CENTER);
    kontenerPrzyciskLewy.setPadding(new Insets(20, 20, 20, 20));

    // lewy przycisk
    Button przyciskLewy = new Button("<");
    przyciskLewy.setPrefSize(50, 50);
    przyciskLewy.setBackground(Background.fill(Color.WHITE));
    przyciskLewy.setBorder(Border.stroke(Color.GRAY));
    przyciskLewy.disableProperty().bind(this.model_.numerRuchuRownyZero());
    przyciskLewy.setOnMouseClicked((mouseEvent -> this.kontroler_.poprzedniRuch()));

    kontenerPrzyciskLewy.getChildren().add(przyciskLewy);

    // kontener na prawy przycisk
    VBox kontenerPrzyciskPrawy = new VBox();
    kontenerPrzyciskPrawy.setAlignment(Pos.CENTER);
    kontenerPrzyciskPrawy.setPadding(new Insets(20, 20, 20, 20));

    // prawy przycisk
    Button przyciskPrawy = new Button(">");
    przyciskPrawy.setPrefSize(50, 50);
    przyciskPrawy.setBackground(Background.fill(Color.WHITE));
    przyciskPrawy.setBorder(Border.stroke(Color.GRAY));
    przyciskPrawy.disableProperty().bind(this.model_.numerRuchuMaksymalny());
    przyciskPrawy.setOnMouseClicked((mouseEvent -> this.kontroler_.nastepnyRuch()));

    kontenerPrzyciskPrawy.getChildren().add(przyciskPrawy);

    kontenerPlanszy.setLeft(kontenerPrzyciskLewy);
    kontenerPlanszy.setRight(kontenerPrzyciskPrawy);
  }
}
