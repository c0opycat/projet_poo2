package controller.controllerGame.controllerCommand.controllerMenuCommand;

import model.modelGame.modelCommand.modelMenuCom.PauseModel;
import view.viewGame.GameView;
import view.viewGame.viewCommand.viewMenuCommand.PauseView;

/**
 * Controller class for managing the game's pause functionality.
 * This class acts as an intermediary between the PauseModel and PauseView.
 * It handles the execution of pause commands initiated by the player,
 * allowing the game to be temporarily suspended and resumed.
 * @author L. Cooper
 */
public class PauseController {

  /** The view that displays the pause button in the game interface. */
  private final PauseView pauseView;

  /** The model containing the pause action's logic and execution code. */
  private final PauseModel pauseModel;

  /**
   * Constructs a new PauseController with the specified components.
   * Initializes the controller with references to the game view and pause view,
   * and creates a new PauseModel with the appropriate command parameters and
   * game model reference.
   *
   * @param gameView the game view to associate with this pause controller
   * @param pauseView the pause view that this controller will manage
   */
  public PauseController(GameView gameView, PauseView pauseView) {
    this.pauseView = pauseView;
    this.pauseModel = new PauseModel(
      new String[] { "PAUSE" },
      gameView.getGameController().getGameModel()
    );
  }

  /**
   * Gets the pause view associated with this controller.
   *
   * @return the PauseView instance
   */
  public PauseView getPauseView() {
    return this.pauseView;
  }

  /**
   * Gets the pause model associated with this controller.
   *
   * @return the PauseModel instance
   */
  public PauseModel getPauseModel() {
    return this.pauseModel;
  }

  /**
   * Executes a pause action, toggling the game's paused state.
   * Delegates to the pause model to perform the actual pause/unpause logic.
   *
   * @return true if the operation was successful, false otherwise
   */
  public boolean execute() {
    return this.getPauseModel().execute();
  }
}
