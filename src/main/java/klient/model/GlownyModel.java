package klient.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GlownyModel {
  // TODO(Jakub Drzewiecki): Trzeba zmienić ta zmienna na BooleanProperty i bindować ją w widoku do odpowiedniego widgetu
  private boolean czyPolaczono_;
  private final StringProperty nazwaGracza_ = new SimpleStringProperty();
  private final ModelGraczyOnline modelGraczyOnline_;
  private final ModelPokoju modelPokoju_;
  public GlownyModel(boolean czyPolaczono) {
    this.czyPolaczono_ = czyPolaczono;
    this.modelGraczyOnline_ = new ModelGraczyOnline(nazwaGracza_);
    this.modelPokoju_ = new ModelPokoju();
  }

  public ModelGraczyOnline dajModelGraczyOnline() {
    return this.modelGraczyOnline_;
  }
  public ModelPokoju dajModelPokoju() {
    return this.modelPokoju_;
  }
  public boolean czyPolaczono() {
    return this.czyPolaczono_;
  }
  public void ustawCzyPolaczono(boolean czyPolaczono) {
    this.czyPolaczono_ = czyPolaczono;
  }
}
