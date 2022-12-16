package klient.kontroller;

import klient.model.Model;
import klient.model.ModelPokoju;

public class KontrolerPokoju implements KontrolerWidoku{
  private ModelPokoju model_;
  private KontrolerAplikacji kontrolerGlowny_;

  @Override
  public void przekazModel(Model model) {
    this.model_ = (ModelPokoju) model;
  }

  @Override
  public void przekazGlownyKontroler(KontrolerAplikacji kontrolerGlowny) {
    this.kontrolerGlowny_ = kontrolerGlowny;
  }
}
