package controller.controllerGame.controllerCommand.controllerMenuCommand;

import model.modelGame.modelCommand.modelMenuCom.QuitModel;
import view.viewGame.GameView;
import view.viewGame.viewCommand.viewMenuCommand.QuitView;

/**
 * The controller class for handling the quit command in the game menu.
 * Manages interactions between the QuitView and QuitModel.
 * @author L. Cooper
 */
public class QuitController {

  /**
   * The view class for the quit button in the game menu.
   * Provides functionality to display quit options and manage interactions.
   */
  private final QuitView quitView;
  /**
   * The model class for the quit command.
   * Handles the logic and interactions related to quitting the game.
   */
  private final QuitModel quitModel;

  /**
   * Constructs a new QuitController instance.
   *
   * @param quitView the QuitView instance associated with this controller.
   * @param gameView the GameView instance associated with this controller.
   */
  public QuitController(QuitView quitView, GameView gameView) {
    this.quitView = quitView;
    this.quitModel = new QuitModel(
      new String[] { "QUIT" },
      gameView.getGameController().getGameModel()
    );
  }

  /**
   * Retrieves the QuitModel associated with this controller.
   *
   * @return the QuitModel instance.
   */
  public QuitModel getQuitModel() {
    return this.quitModel;
  }

  /**
   * Retrieves the QuitView associated with this controller.
   *
   * @return the QuitView instance.
   */
  public QuitView getQuitView() {
    return this.quitView;
  }

  /**
   * Executes the quit command to forfeit the game.
   */
  public void forfeit() {
    this.quitModel.execute();
  }
}
