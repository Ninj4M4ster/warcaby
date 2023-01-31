package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "stan_planszy")
public class StanPlanszy {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id_;

  @ManyToOne(fetch = FetchType.LAZY)
  @Column(name = "id_gry")
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
