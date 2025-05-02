package controller.controllerGame.controllerCommand.controllerInterractCommand;

import model.modelGame.modelCommand.modelInterractCom.DropModel;
import view.viewGame.GameView;
import view.viewGame.viewCommand.viewInteractCommand.DropView;

/**
 * Controller class for managing drop item actions in the game.
 * This class acts as an intermediary between the DropModel and DropView.
 * It handles the execution of drop commands initiated by the player to remove
 * items from their inventory and place them in the current location.
 * @author L. Cooper
 */
public class DropController {

  /** The view that displays the drop button in the game interface. */
  private final DropView dropView;

  /** The model containing the drop action's logic and execution code. */
  private final DropModel dropModel;

  /** Reference to the main game view for updating UI after dropping items. */
  private final GameView gameView;

  /**
   * Constructs a new DropController with the specified components.
   * Initializes the controller with references to the game view and drop view,
   * and creates a new DropModel with the appropriate command parameters and
   * game model reference. The index parameter identifies which inventory item
   * will be dropped when this controller's execute method is called.
   *
   * @param gameView the game view to associate with this drop controller
   * @param dropView the drop view that this controller will manage
   * @param ind the index of the inventory item to be dropped
   */
  public DropController(GameView gameView, DropView dropView, int ind) {
    this.dropView = dropView;
    this.gameView = gameView;
    this.dropModel = new DropModel(
      new String[] { "DROP", String.valueOf(ind) },
      this.getGameView().getGameController().getGameModel()
    );
  }

  /**
   * Gets the drop view associated with this controller.
   *
   * @return the DropView instance
   */
  public DropView getDropView() {
    return this.dropView;
  }

  /**
   * Gets the drop model associated with this controller.
   *
   * @return the DropModel instance
   */
  public DropModel getDropModel() {
    return this.dropModel;
  }

  /**
   * Gets the game view associated with this controller.
   *
   * @return the GameView instance
   */
  public GameView getGameView() {
    return this.gameView;
  }

  /**
   * Executes a drop item action.
   * Delegates to the drop model to perform the actual drop logic,
   * which typically includes removing the item from the hero's inventory
   * and adding it to the current location.
   *
   * @return true if the item was successfully dropped, false otherwise
   */
  public boolean execute() {
    return this.getDropModel() == null ? false : this.getDropModel().execute();
  }
}
