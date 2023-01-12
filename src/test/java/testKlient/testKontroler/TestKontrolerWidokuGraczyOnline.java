package testKlient.testKontroler;

import java.util.List;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import klient.kontroler.GlownyKontroler;
import klient.kontroler.KontrolerWidokuGraczyOnline;
import klient.kontroler.TypyKontrolerow;
import klient.model.Model;
import klient.widoki.widgety.KafelekGraczaOnline;
import org.junit.Test;

public class TestKontrolerWidokuGraczyOnline extends TestKontroler {
  @Test
  public void testPrzekazModel() {
    KontrolerWidokuGraczyOnline kontroler = new KontrolerWidokuGraczyOnline();
    Model model = this.utworzModel(TypyKontrolerow.KONTROLER_GRACZY_ONLINE, false);
    kontroler.przekazModel(model);
  }

  @Test
  public void testOdnowPolaczenie() {
    KontrolerWidokuGraczyOnline kontroler =
        (KontrolerWidokuGraczyOnline)this.utworzGotowyKontroler(TypyKontrolerow.KONTROLER_GRACZY_ONLINE, false);
    kontroler.odnowPolaczenie();
  }



  @Test
  public void testZapiszNazweGracza() {
    KontrolerWidokuGraczyOnline kontroler =
        (KontrolerWidokuGraczyOnline)this.utworzGotowyKontroler(
            TypyKontrolerow.KONTROLER_GRACZY_ONLINE, false);

    kontroler.zapiszNazweGracza("");
    kontroler.zapiszNazweGracza("abc");
  }

  @Test
  public void testUruchomWydarzenieNaKazdymDziecku() {
    Event event = new Event(MouseEvent.MOUSE_CLICKED);
    KontrolerWidokuGraczyOnline kontroler = new KontrolerWidokuGraczyOnline();
    kontroler.uruchomWydarzenieNaKazdymDziecku(event);
  }

  @Test
  public void testPrzypiszFunkcjeKafelkowi() {
    KontrolerWidokuGraczyOnline kontroler =
        (KontrolerWidokuGraczyOnline)this.utworzGotowyKontroler(
            TypyKontrolerow.KONTROLER_GRACZY_ONLINE, false);

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

  @Test
  public void testWyswietlZaproszeneOdGracza() {
    KontrolerWidokuGraczyOnline kontroler =
        (KontrolerWidokuGraczyOnline)this.utworzGotowyKontroler(TypyKontrolerow.KONTROLER_GRACZY_ONLINE, false);
    kontroler.wyswietlZaproszenieOdGracza("");
  }

  @Test
  public void testDolaczDoPokoju() {
    KontrolerWidokuGraczyOnline kontroler =
        (KontrolerWidokuGraczyOnline)this.utworzGotowyKontroler(TypyKontrolerow.KONTROLER_GRACZY_ONLINE, false);
    kontroler.dolaczDoPokoju("");
  }

  @Test
  public void testOdrzucZaproszenie() {
    KontrolerWidokuGraczyOnline kontroler =
        (KontrolerWidokuGraczyOnline)this.utworzGotowyKontroler(TypyKontrolerow.KONTROLER_GRACZY_ONLINE, false);
    kontroler.odrzucZaproszenie("");
  }

  @Test
  public void testWyswietlPowiadomienie() {
    KontrolerWidokuGraczyOnline kontroler =
        (KontrolerWidokuGraczyOnline)this.utworzGotowyKontroler(TypyKontrolerow.KONTROLER_GRACZY_ONLINE, false);
    kontroler.wyswietlPowiadomienie("");
  }
}
