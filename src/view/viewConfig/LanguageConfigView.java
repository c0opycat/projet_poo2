package view.viewConfig;

import java.io.FileWriter;
import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.json.JSONObject;
import view.MyAlert;

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
    englishButton.setOnAction(e -> {
      saveLanguage("EN");
    });

    Button frenchButton = new Button("Français");
    frenchButton.setOnAction(e -> {
      saveLanguage("FR");
    });

    // Définir la taille des boutons
    englishButton.setPrefSize(buttonWidth, buttonHeight);
    frenchButton.setPrefSize(buttonWidth, buttonHeight);

    // Ajouter les boutons au conteneur principal
    this.getChildren().addAll(title, englishButton, frenchButton);
    this.setAlignment(Pos.TOP_CENTER);
  }

  public void saveLanguage(String language) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("language", language);
    try (FileWriter fileWriter = new FileWriter("./save/language.json")) {
      fileWriter.write(jsonObject.toString());
      MyAlert alert = new MyAlert(
        "Language",
        "Language changed to " + language,
        "The language has been changed to " + language
      );
      alert.showInformation();
    } catch (IOException e) {
      MyAlert alert = new MyAlert(
        "Language",
        "Error",
        "Error while saving language"
      );
      alert.showInformation();
      e.printStackTrace();
    }
  }
}
