package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.io.Serializable;

/**
 * Klasa reprezentujaca rekordy z tabeli stan_planszy.
 */
@Entity
@Table(name = "stan_planszy", uniqueConstraints = {
    @UniqueConstraint(columnNames = "id"),
    @UniqueConstraint(columnNames = "id_gry")
})
public class StanPlanszy implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id_;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_gry", nullable = false)
  private Gra gra_;
  @Column(name = "numer_ruchu")
  private int numerRuchu_;
  @Column(name = "plansza")
  private String plansza_;

  public StanPlanszy() {}

  public StanPlanszy(int numerRuchu, String plansza) {
    this.numerRuchu_ = numerRuchu;
    this.plansza_ = plansza;
  }

  public int getId() {
    return this.id_;
  }

  public int getNumerRuchu() {
    return this.numerRuchu_;
  }

  public String getPlansza() {
    return this.plansza_;
  }

  public void setGra(Gra gra) {
    this.gra_ = gra;
  }
}
