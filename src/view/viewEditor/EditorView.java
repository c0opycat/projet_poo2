package view.viewEditor;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import view.BorderWithButtons;
import view.ButtonMenu;
import view.ButtonQuit;

/**
 * EditorView is the main container for the level editor screen.
 * It extends BorderWithButtons and organizes:
 * - A title
 * - A scrollable editor panel (EditorPane)
 * - Background image
 * - Action buttons (Menu, Quit)
 *
 * @author C. Besançon
 */
public class EditorView extends BorderWithButtons {

  /**
   * Constructor: initializes the editor view and adds components.
   */
  public EditorView() {
    super();
    this.addComp();
  }

  /**
   * Adds the core components to the editor view:
   * - A title
   * - A scrollable EditorPane
   * - A background image
   */
  private void addComp() {
    this.addTitle("Editeur", false);

    EditorPane ep = new EditorPane();
    ep.addHandlers();

    // Ajouter EditorPane dans un ScrollPane
    ScrollPane scrollPane = new ScrollPane(ep);
    scrollPane.setFitToWidth(true); // Ajuste la largeur du contenu au ScrollPane
    scrollPane.setFitToHeight(true); // Permet le défilement vertical uniquement
    scrollPane.setPrefSize(500, 500);
    scrollPane.setStyle(
      "-fx-background: transparent; -fx-background-color: transparent;"
    );

    this.setContent(scrollPane);
    this.addBackground("backgroundEditor.png");
  }

  /**
   * Sets up the bottom action buttons (Menu and Quit).
   * These buttons are added to the layout's bottom area.
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
