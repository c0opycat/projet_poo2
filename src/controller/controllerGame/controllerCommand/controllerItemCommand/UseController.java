package controller.controllerGame.controllerCommand.controllerItemCommand;

import java.awt.Point;
import model.modelGame.modelCommand.modelItemCom.UseModel;
import view.viewGame.GameView;
import view.viewGame.viewCommand.viewItemCommand.UseView;

/**
 * Controller class for managing the "Use" command.
 * It acts as an intermediary between the UseModel, UseView, and GameView.
 * @author L. Cooper
 * @author A. Bertrand-Bernard
 */
public class UseController {

  /** The model containing the use action's logic and execution code. */
  private UseModel useModel;

  /** The view that displays the use button in the game interface. */
  private final UseView useView;

  /** Reference to the main game view for accessing game state and UI elements. */
  private final GameView gameView;

  /**
   * Constructs a UseController with the specified GameView and UseView.
   *
   * @param gameView the GameView instance associated with this controller
   * @param useView the UseView instance associated with this controller
   */
  public UseController(GameView gameView, UseView useView) {
    this.useView = useView;
    this.gameView = gameView;
  }

  /**
   * Gets the UseModel instance managed by this controller.
   *
   * @return the UseModel instance
   */
  public UseModel getUseModel() {
    return this.useModel;
  }

  /**
   * Gets the UseView instance associated with this controller.
   *
   * @return the UseView instance
   */
  public UseView getUseView() {
    return this.useView;
  }

  /**
   * Gets the GameView instance associated with this controller.
   *
   * @return the GameView instance
   */
  public GameView getGameView() {
    return this.gameView;
  }

  /**
   * Sets the UseModel instance with the specified item index.
   *
   * @param ind the index of the item to be used on
   */
  public void setUseModel(String ind) {
    this.useModel = new UseModel(
      new String[] { "USE", ind },
      this.getGameView().getGameController().getGameModel()
    );
  }

  /**
   * Executes the "Use" command at the specified point.
   *
   * @param p the point where the command is executed
   * @return true if the command is successfully executed, false otherwise
   */
  public boolean execute(Point p) {
    return this.getUseModel() == null ? false : this.getUseModel().execute(p);
  }
}
