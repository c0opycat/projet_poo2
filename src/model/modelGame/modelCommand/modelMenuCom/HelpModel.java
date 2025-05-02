package model.modelGame.modelCommand.modelMenuCom;

import model.modelGame.GameModel;
import model.modelGame.MessageEnModel;
import model.modelGame.modelCommand.CommandModel;

/**
 * The HelpModel class represents the "help" command.
 * <p>
 * When executed, this command displays a list of all available commands
 * to assist the player in understanding how to play the game.
 */
public class HelpModel extends CommandModel {

  /**
   * Constructs a new HelpModel command.
   * @param gameM the game model associated with this command
   */
  public HelpModel(GameModel gameM) {
    this.gameM = gameM;
  }

  /**
   * Executes the help command.
   * <p>
   * Prints the list of available commands to the console.
   * @return false as this command does not modify the game state
   */
  public boolean execute() {
    System.out.println(MessageEnModel.helpCommands());
    return false;
  }
}
