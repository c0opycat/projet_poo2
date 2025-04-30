package controller.controllerGame.controllerCommand.controllerInterractCommand;

import java.awt.Point;
import model.modelGame.modelCommand.modelInterractCom.TakeModel;
import view.viewGame.GameView;
import view.viewGame.viewCommand.viewInteractCommand.TakeView;

/**
 * The controller class for handling the take command in the game.
 * Manages interactions between the TakeView and TakeModel.
 * @author L. Cooper
 * @author A. Bertrand-Bernard
 */
public class TakeController {

  /**
   * The model class for the take command.
   * Handles the logic and interactions related to taking items.
   */
  private TakeModel takeModel;
  /**
   * The view class for the take button in the game.
   * Provides functionality to display take options and manage interactions.
   */
  private final TakeView takeView;
  /**
   * The game view associated with this TakeController.
   * Provides access to the main game interface and other views.
   */
  private final GameView gameView;

  /**
   * Constructs a new TakeController instance.
   *
   * @param gameView the GameView instance associated with this controller.
   * @param takeView the TakeView instance associated with this controller.
   */
  public TakeController(GameView gameView, TakeView takeView) {
    this.takeView = takeView;
    this.gameView = gameView;
  }

  /**
   * Retrieves the TakeModel associated with this controller.
   *
   * @return the TakeModel instance.
   */
  public TakeModel getTakeModel() {
    return this.takeModel;
  }

  /**
   * Retrieves the TakeView associated with this controller.
   *
   * @return the TakeView instance.
   */
  public TakeView getTakeView() {
    return this.takeView;
  }

  /**
   * Retrieves the GameView associated with this controller.
   *
   * @return the GameView instance.
   */
  public GameView getGameView() {
    return this.gameView;
  }

  /**
   * Sets the TakeModel with the specified item index.
   *
   * @param ind the index of the item to take.
   */
  public void setTakeModel(String ind) {
    this.takeModel = new TakeModel(
      new String[] { "TAKE", ind },
      this.getGameView().getGameController().getGameModel()
    );
  }

  /**
   * Sets the TakeModel without specifying an item index.
   */
  public void setTakeModel() {
    this.takeModel = new TakeModel(
      new String[] { "TAKE" },
      this.getGameView().getGameController().getGameModel()
    );
  }

  /**
   * Executes the take command at the specified point.
   *
   * @param p the point where the command is executed.
   * @return true if the command was successfully executed, false otherwise.
   */
  public boolean execute(Point p) {
    return this.getTakeModel() == null ? false : this.getTakeModel().execute(p);
  }
}
