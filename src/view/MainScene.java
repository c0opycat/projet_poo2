package view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import view.viewMenu.MenuView;

/**
 * The main scene of the application.
 * Extends the JavaFX Scene class and provides functionality to set the root content.
 * @author L. Cooper
 * @author A. Bertrand-Bernard
 */
public class MainScene extends Scene {

  /**
   * Constructs a new MainScene instance with default dimensions and a Menu as the root.
   * Adds event handlers to the Menu.
   */
  public MainScene() {
    // Taille d'origine 1024 * 768
    super(new MenuView(), 1331, 998);
    ((MenuView) this.getRoot()).addHandlers();
  }

  /**
   * Sets the root of the scene with an instance of a class extending BorderWithButtons.
   *
   * @param root the new root node for the scene.
   */
  public void setContent(Parent root) {
    this.setRoot(root);
  }
}
