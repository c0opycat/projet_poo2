package view.viewConfig;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import view.BorderWithButtons;
import view.ButtonMenu;
import view.ButtonQuit;

/**
 * Classe représentant la vue de configuration Générale.
 * Elle permet à l'utilisateur de configurer les paramètres du jeu.
 * @author A. Bertrand-Bernard
 */

public class ConfigView extends BorderWithButtons {

  public ConfigView() {
    super();
    this.addComp();
  }

  private void addComp() {
    this.addTitle("Configuration", false);

    // Créer un VBox pour contenir les composants
    HBox Hb = new HBox(60);
    Hb.setAlignment(Pos.CENTER);

    // Créer une instance de KeybindConfigView
    AllKeybindsConfigView kb = new AllKeybindsConfigView();
    ResolutionConfigView res = new ResolutionConfigView();
    LanguageConfigView lang = new LanguageConfigView();

    // Ajouter KeybindConfigView dans un ScrollPane
    ScrollPane scrollPane = new ScrollPane(kb);
    scrollPane.setFitToWidth(true); // Ajuste la largeur du contenu au ScrollPane
    scrollPane.setFitToHeight(false); // Permet le défilement vertical uniquement
    scrollPane.setPrefHeight(500); // Hauteur préférée du ScrollPane
    scrollPane.setPrefWidth(300);
    scrollPane.setStyle(
      "-fx-background: transparent; -fx-background-color: transparent;"
    );

    // Ajouter le ScrollPane au VBox
    Hb.getChildren().addAll(scrollPane, res, lang);

    // Ajouter le VBox au contenu principal
    this.setContent(Hb);
    this.addBackground("background.png");
  }

  /**
   * Méthode pour ajouter les différents boutons au conteneur principal.
   */
  public void setButtons() {
    ArrayList<Button> buttons = new ArrayList<>();

    ButtonMenu menu = new ButtonMenu(this.getMainScene());
    ButtonQuit quit = new ButtonQuit();

    buttons.add(menu);
    buttons.add(quit);

    this.addButtons(buttons);
  }
}
