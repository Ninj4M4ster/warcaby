package klient.widoki;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import klient.kontroler.KontrolerWidoku;
import klient.model.Model;

/**
 * Interfejs przedstawiajacy widok aplikacji.
 * TODO(Jakub Drzewiecki): Zmienic na klase abstrakcyjna oraz dodac metode
 *  tworzaca pasek informujacy o aktualnym stanie polaczenia.
 */
public abstract class Widok {

  /**
   * Metoda sluzaca do utworzenia widoku i zwrocenia jego konteneru.
   *
   * @param kontroler Kontroler widoku.
   * @param model Model widoku.
   * @return Kontener wszystkich elementow widoku.
   */
  public abstract Parent utworzWidok(KontrolerWidoku kontroler,
      Model model);

  /**
   * Metoda tworzaca i zwracajaca pasek statusu polaczenia.
   * Pasek ten umozliwia podjecie proby ponownego polaczenia z serwerem.
   *
   * @return Pasek statusu aktualnego polaczenia.
   */
  protected HBox utworzPasekStatusu(Model model) {
    HBox pasek = new HBox();
    pasek.setBackground(Background.fill(Color.RED));
    pasek.setAlignment(Pos.CENTER);

    Label status = new Label("Brak polaczenia");
    pasek.getChildren().add(status);
    pasek.visibleProperty().bind(model.czyPolaczono().not());

    return pasek;
  }
}
