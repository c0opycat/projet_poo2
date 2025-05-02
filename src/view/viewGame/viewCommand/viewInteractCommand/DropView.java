package view.viewGame.viewCommand.viewInteractCommand;

import controller.controllerGame.controllerCommand.controllerInterractCommand.DropController;
import javafx.scene.control.Button;
import view.Lang;
import view.viewGame.GameView;

/**
 * The DropView class represents a button that allows the player to drop items from their inventory.
 * This class extends Button to provide item-dropping functionality in the game interface.
 * When pressed, it triggers the drop action for a specific inventory item through its controller.
 * @author L. Cooper
 */
public class DropView extends Button {

  /** Language handler for internationalization support. */
  private final Lang lang = new Lang();

  /** Controller that manages the business logic for dropping items. */
  private final DropController dropController;

  /** Reference to the main game view for accessing game state and UI elements. */
  private final GameView gameView;

  /**
   * Constructs a new DropView with the specified game view and inventory index.
   * <p>
   * Initializes the drop button with appropriate text based on the current language,
   * and sets up an action handler that executes the drop operation when clicked.
   * The button visually updates both the location view and container view after a successful drop.
   * </p>
   *
   * @param gameView the game view to associate with this drop button
   * @param ind the index of the item in the inventory to be dropped
   */
  public DropView(GameView gameView, int ind) {
    super();
    this.dropController = new DropController(gameView, this, ind);
    this.gameView = gameView;

    this.getLang().setButtonLang(this, "Lâcher", "Drop");
    this.getStyleClass().add("button-Commande");

    this.setOnAction(e -> {
        if (this.getDropController().execute()) {
          this.getGameView().getCurrentLocationView().updateItems();
          this.getGameView().getContainerView().updateContainerView(true);
        }
      });
  }

  /**
   * Gets the drop controller associated with this view.
   *
   * @return the DropController for this button
   */
  public DropController getDropController() {
    return this.dropController;
  }

  /**
   * Gets the game view associated with this drop button.
   *
   * @return the GameView instance
   */
  public GameView getGameView() {
    return this.gameView;
  }

  /**
   * Gets the language handler for this view.
   *
   * @return the Lang object used for text internationalization
   */
  public Lang getLang() {
    return this.lang;
  }
}
