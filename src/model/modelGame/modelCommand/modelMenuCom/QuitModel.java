package model.modelGame.modelCommand.modelMenuCom;

import model.modelGame.GameModel;
import model.modelGame.modelCommand.CommandModel;

/**
 * The QuitModel class represents the quit command in the game.
 * <p>
 * When executed, this command forces the player to forfeit the game,
 * marking it as ended.
 */
public class QuitModel extends CommandModel {

    /**
     * Constructs a new QuitModel command.
     * @param cmd the command arguments (not used, but required by the command system)
     * @param gameM the game model associated with this command
     */
    public QuitModel(String[] cmd, GameModel gameM) {
        this.gameM = gameM;
        this.commands = cmd;
    }

    /**
     * Executes the quit command.
     * <p>
     * This forces the player to forfeit and marks the game as ended.
     * @return true to indicate the command was executed successfully
     */
    public boolean execute() {
        gameM.forfeit(true);
        return true;
    }
}
