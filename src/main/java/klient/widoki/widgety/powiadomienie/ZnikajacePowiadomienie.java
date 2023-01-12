package klient.widoki.widgety.powiadomienie;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.layout.VBox;

/**
 * Konkretna klasa powiadomienia, jest to
 * powiadomienie w widoku graczy online znikajace po 5 sekundach.
 */
public class ZnikajacePowiadomienie extends Powiadomienie {
  /** Instancja tej klasy */
  private final Powiadomienie instancja_ = this;

  /**
   * Konstruktor, tworzy opis powiadomienia oraz zaczyna odliczanie do usuniecia.
   *
   * @param powiadomienie Wiadomosc do wyswietlenia.
   */
  public ZnikajacePowiadomienie(String powiadomienie) {
    super();
    this.stworzOpisPowiadomienia(powiadomienie);
    this.usunPowiadomienie();
  }

  /**
   * Metoda usuwajaca powiadomienie po podanym w sekundach czasie.
   */
  private void usunPowiadomienie() {
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        Platform.runLater(() -> ((VBox)getParent()).getChildren().remove(instancja_));
      }
    }, 5 * 1000L);
  }
}
