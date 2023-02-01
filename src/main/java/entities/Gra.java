package entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Klasa reprezentujaca rekord z tabeli gry.
 */
@Entity
@Table(name = "gry", uniqueConstraints = {
    @UniqueConstraint(columnNames = "id")
})
public class Gra implements Serializable {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id_;

  @Column(name = "gracz1")
  private String gracz1_;

  @Column(name = "gracz2")
  private String gracz2_;

  @Column(name = "kolor_gracz1")
  private Integer kolor_gracz1_;

  @Column(name = "kolor_gracz2")
  private Integer kolor_gracz2_;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "gra_", orphanRemoval = true)
  private Set<StanPlanszy> stanyPlanszy_;

  public Gra(){}

  public Gra(String gracz1, String gracz2, int kolorGracz1, int kolorGracz2) {
    this.gracz1_ = gracz1;
    this.gracz2_ = gracz2;
    this.kolor_gracz1_ = kolorGracz1;
    this.kolor_gracz2_ = kolorGracz2;
  }

  public int getId() {
    return this.id_;
  }

  public String getGracz1() {
    return this.gracz1_;
  }

  public String getGracz2() {
    return this.gracz2_;
  }

  public Integer getKolorGracz1() {
    return this.kolor_gracz1_;
  }

  public Integer getKolorGracz2() {
    return this.kolor_gracz2_;
  }

  public Set<StanPlanszy> getStanyPlanszy() {
    return this.stanyPlanszy_;
  }

  public void setId(int id) {
    this.id_ = id;
  }

  public void setGracz1(String gracz) {
    this.gracz1_ = gracz;
  }

  public void setGracz2(String gracz) {
    this.gracz2_ = gracz1_;
  }

  public void setKolorGracz1(int kolor) {
    this.kolor_gracz1_ = kolor;
  }

  public void setKolorGracz2(int kolor) {
    this.kolor_gracz2_ = kolor;
  }

  public void setStanyPlanszy(Set<StanPlanszy> stanyPlanszy) {
    this.stanyPlanszy_ = stanyPlanszy;
  }

  public void dodajRuch(StanPlanszy stanPlanszy) {
    if(stanyPlanszy_ == null)
      stanyPlanszy_ = new HashSet<>();
    stanyPlanszy_.add(stanPlanszy);
    stanPlanszy.setGra(this);
  }
}
