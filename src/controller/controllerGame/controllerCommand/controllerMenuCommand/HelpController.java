package controller.controllerGame.controllerCommand.controllerMenuCommand;

import model.modelGame.modelCommand.modelMenuCom.HelpModel;
import view.viewGame.GameView;
import view.viewGame.viewCommand.viewMenuCommand.HelpView;

/**
 * The controller class for handling the help command in the game menu.
 * Manages interactions between the HelpView and HelpModel.
 * @author L. Cooper
 */
public class HelpController {

  /**
   * The view class for the help button in the game menu.
   * Provides functionality to display help information and manage interactions.
   */
  private final HelpView helpView;
  /**
   * The model class for the help command.
   * Handles the logic and interactions related to displaying help information.
   */
  private final HelpModel helpModel;

  /**
   * Constructs a new HelpController instance.
   *
   * @param helpView the HelpView instance associated with this controller.
   * @param gameView the GameView instance associated with this controller.
   */
  public HelpController(HelpView helpView, GameView gameView) {
    this.helpView = helpView;
    this.helpModel = new HelpModel(gameView.getGameController().getGameModel());
  }

  /**
   * Retrieves the HelpView associated with this controller.
   *
   * @return the HelpView instance.
   */
  public HelpView getHelpView() {
    return this.helpView;
  }

  /**
   * Retrieves the HelpModel associated with this controller.
   *
   * @return the HelpModel instance.
   */
  public HelpModel getHelpModel() {
    return this.helpModel;
  }

  /**
   * Executes the help command to display help information.
   */
  public void help() {
    this.getHelpModel().execute();
  }
}
