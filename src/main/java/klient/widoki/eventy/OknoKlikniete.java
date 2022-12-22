package klient.widoki.eventy;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.Node;

/**
 * Wydarzenie wywolywane gdy nacisnie sie na ktorykolwiek element w GUI.
 * Jego glownym celem jest chowanie opcji zaproszenia gracza do pokoju w widoku graczy online.
 */
public class OknoKlikniete extends Event {
  /** Typ wydarzenia */
  public static final EventType<OknoKlikniete> OKNO_KLIKNIETE =
      new EventType<>(Event.ANY, "OKNO_KLIKNIETE");

  /** Zrodlo wydarzenia, czyli nacisniety element widoku */
  private final Node zrodlo;

  /**
   * Konstruktor, tworzy wydarzenie uruchamiajac metode po ktorej dziedziczy ta klasa.
   *
   * @param source Zrodlo wydarzenia, czyli nacisniety element GUI.
   * @param eventTarget Cel wydarzenia, czyli element GUI do ktorego chcemy, aby wydarzenie dotarlo.
   */
  public OknoKlikniete(Node source, EventTarget eventTarget) {
    super(source, eventTarget, OKNO_KLIKNIETE);
    zrodlo = source;
  }

  /**
   * Metoda ta zwraca widget, ktory byl zrodlem wydarzenia.
   *
   * @return Nacisniety element GUI.
   */
  public Node zrodlo() {
    return zrodlo;
  }
}
