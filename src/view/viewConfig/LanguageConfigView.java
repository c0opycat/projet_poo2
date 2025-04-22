package view.viewConfig;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Classe représentant la vue de configuration de la langue.
 * Elle permet à l'utilisateur de choisir la langue de l'interface.
 * @author A. Bertrand-Bernard
 */
public class LanguageConfigView extends VBox {

  /**
   * Constructeur de la classe LanguageConfigView.
   * Il initialise la vue avec les composants nécessaires pour configurer la langue.
   */
  public LanguageConfigView() {
    super(20);
    this.addComp();
  }

  /**
   * Méthode pour ajouter les composants de la vue.
   */
  private void addComp() {
    // Créer une instance de Label pour le titre
    Label title = new Label("Language :");
    title.getStyleClass().add("under-title"); // Ajouter une classe CSS

    double buttonWidth = 150;
    double buttonHeight = 35;

    // Créer des boutons pour chaque langue
    Button englishButton = new Button("English");
    Button frenchButton = new Button("Français");

    // Définir la taille des boutons
    englishButton.setPrefSize(buttonWidth, buttonHeight);
    frenchButton.setPrefSize(buttonWidth, buttonHeight);

    // Ajouter les boutons au conteneur principal
    this.getChildren().addAll(title, englishButton, frenchButton);
    this.setAlignment(Pos.TOP_CENTER);
  }
}
