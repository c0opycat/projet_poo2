package view.viewConfig;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Classe représentant la vue de configuration de la résolution.
 * Elle permet à l'utilisateur de choisir la résolution de la fenêtre du jeu.
 * @author A. Bertrand-Bernard
 */
public class ResolutionConfigView extends VBox {

  /**
   * Constructs a new ResolutionConfigView.
   * Initializes the view with vertical spacing of 20 pixels between elements
   * and adds the resolution configuration components to the view.
   * These components include a title label and buttons for selecting
   * different screen resolutions (1024x768, 1280x720, 1920x1080).
   */
  public ResolutionConfigView() {
    super(20);
    this.addComp();
  }

  /**
   * Méthode pour ajouter les composants de la vue.
   */
  private void addComp() {
    Label title = new Label("Resolution :");
    title.getStyleClass().add("under-title");

    double buttonWidth = 150;
    double buttonHeight = 35;

    Button res1 = new Button("1024x768");
    res1.setOnAction(e -> {
      setRes(1024, 768);
    });

    Button res2 = new Button("1280x720");
    res2.setOnAction(e -> {
      setRes(1280, 720);
    });

    Button res3 = new Button("1920x1080");
    res3.setOnAction(e -> {
      setRes(1920, 1080);
    });

    // Définir la taille des boutons
    res1.setPrefSize(buttonWidth, buttonHeight);
    res2.setPrefSize(buttonWidth, buttonHeight);
    res3.setPrefSize(buttonWidth, buttonHeight);

    this.getChildren().addAll(title, res1, res2, res3);
    this.setAlignment(Pos.TOP_CENTER);
  }

  /**
   * Méthode pour définir la résolution de la fenêtre.
   * @param width La largeur de la fenêtre.
   * @param height La hauteur de la fenêtre.
   */
  private void setRes(int width, int height) {
    Stage stage = (Stage) this.getScene().getWindow();
    stage.setWidth(width);
    stage.setHeight(height);
  }
}
