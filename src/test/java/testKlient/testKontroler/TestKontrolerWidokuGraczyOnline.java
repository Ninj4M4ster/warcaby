package testKlient.testKontroler;

import java.util.List;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import klient.kontroler.KontrolerAplikacji;
import klient.kontroler.KontrolerWidokuGraczyOnline;
import klient.model.ModelGraczyOnline;
import klient.widoki.widgety.KafelekGraczaOnline;
import org.junit.Test;

public class TestKontrolerWidokuGraczyOnline {
  @Test
  public void testPrzekazModel() {
    KontrolerWidokuGraczyOnline kontroler = new KontrolerWidokuGraczyOnline();
    ModelGraczyOnline model = new ModelGraczyOnline(new SimpleStringProperty());
    kontroler.przekazModel(model);
  }

  @Test
  public void testPrzekazGlownyKontroler() {
    KontrolerWidokuGraczyOnline kontroler = new KontrolerWidokuGraczyOnline();
    KontrolerAplikacji kontrolerAplikacji = new KontrolerAplikacji();
    kontroler.przekazGlownyKontroler(kontrolerAplikacji);
  }

  @Test
  public void testZapiszNazweGracza() {
    KontrolerWidokuGraczyOnline kontroler = this.utworzGotowyKontroler();

    kontroler.zapiszNazweGracza("");
    kontroler.zapiszNazweGracza("abc");
  }

  @Test
  public void testUruchomWydarzenieNaKazdymDziecku() {
    //TODO(Jakub Drzewiecki): trzeba utworzyc bardziej rozbudowany event w celu testu
    Event event = new Event(MouseEvent.MOUSE_CLICKED);
    KontrolerWidokuGraczyOnline kontroler = new KontrolerWidokuGraczyOnline();
    kontroler.uruchomWydarzenieNaKazdymDziecku(event);
  }

  @Test
  public void testPrzypiszFunkcjeKafelkowi() {
    KontrolerWidokuGraczyOnline kontroler = this.utworzGotowyKontroler();

    ObservableList<KafelekGraczaOnline> lista = new SimpleListProperty<>();
    ListChangeListener.Change<? extends Node> change =
        new ListChangeListener.Change<>(lista) {
          @Override
          public boolean next() {
            return false;
          }

          @Override
          public void reset() {

          }

          @Override
          public int getFrom() {
            return 0;
          }

          @Override
          public int getTo() {
            return 0;
          }

          @Override
          public List<KafelekGraczaOnline> getRemoved() {
            return null;
          }

          @Override
          public boolean wasAdded() {
            return true;
          }

          @Override
          public boolean wasRemoved() {
            return false;
          }

          @Override
          protected int[] getPermutation() {
            return new int[0];
          }
        };
    kontroler.przypiszFunkcjeKafelkowi(change);
  }

  private KontrolerWidokuGraczyOnline utworzGotowyKontroler() {
    KontrolerWidokuGraczyOnline kontroler = new KontrolerWidokuGraczyOnline();
    KontrolerAplikacji kontrolerAplikacji = new KontrolerAplikacji();
    ModelGraczyOnline model = new ModelGraczyOnline(new SimpleStringProperty());

    kontroler.przekazModel(model);
    kontroler.przekazGlownyKontroler(kontrolerAplikacji);
    return kontroler;
  }
}
