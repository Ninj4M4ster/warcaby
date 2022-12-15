package klient.widoki.eventy;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.Node;

public class OknoKlikniete extends Event {
  public static final EventType<OknoKlikniete> OKNO_KLIKNIETE =
      new EventType<>(Event.ANY, "OKNO_KLIKNIETE");

  private final Node zrodlo;

  public OknoKlikniete(Node source, EventTarget eventTarget) {
    super(source, eventTarget, OKNO_KLIKNIETE);
    zrodlo = source;
  }

  public Node zrodlo() {
    return zrodlo;
  }
}
