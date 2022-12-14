package klient.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GlownyModel {
  private final StringProperty nazwaGracza_ = new SimpleStringProperty();
  private final ModelGraczyOnline modelGraczyOnline_;
  public GlownyModel() {
    this.modelGraczyOnline_ = new ModelGraczyOnline(nazwaGracza_);
  }

  public ModelGraczyOnline dajModelGraczyOnline() {
    return this.modelGraczyOnline_;
  }
}
