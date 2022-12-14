package klient.model;

public class GlownyModel {
  private ModelGraczyOnline modelGraczyOnline_;
  public GlownyModel() {
    this.modelGraczyOnline_ = new ModelGraczyOnline();
  }

  public ModelGraczyOnline dajModelGraczyOnline() {
    return this.modelGraczyOnline_;
  }
}
