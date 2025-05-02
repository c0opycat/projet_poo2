package model.modelGame.modelCommand.modelMenuCom;

import model.modelGame.GameModel;
import model.modelGame.modelCommand.CommandModel;

/**
 * The PauseModel class represents the pause command in the game.
 * <p>
 * This command toggles the game's running state between paused and running.
 * When the game is paused, the elapsed pause duration is tracked and added
 * to the game's total paused time when resumed.
 */
public class PauseModel extends CommandModel {

    /**
     * The time when the pause started, in milliseconds.
     */
    long startTime;

    /**
     * The time when the pause ended, in milliseconds.
     */
    long stopTime;

    /**
     * Constructs a new PauseModel command.
     * @param commands the command arguments (not used here, but required by the command system)
     * @param game the game model associated with this command
     */
    public PauseModel(String[] commands, GameModel game) {
        this.gameM = game;
        this.commands = commands;
    }

    /**
     * Starts the pause.
     * <p>
     * Marks the game as not running and records the start time.
     */
    private void startPause() {
        gameM.isRunning = false;
        this.startTime = System.currentTimeMillis();
    }

    /**
     * Stops the pause.
     * <p>
     * Calculates the pause duration and adds it to the total paused time.
     * Marks the game as running again.
     */
    private void stopPause() {
        this.stopTime = System.currentTimeMillis();
        int pause = (int) (this.stopTime - this.startTime);
        gameM.timePaused = gameM.timePaused + pause;
        gameM.isRunning = true;
    }

    /**
     * Executes the pause command.
     * <p>
     * If the game has ended, the command does nothing. Otherwise,
     * it toggles the game's running state between paused and running.
     * @return true if the game state was changed, false if the game has ended
     */
    @Override
    public boolean execute() {
        if (gameM.isEnd()) {
            return false;
        } else if (gameM.isRunning) {
            startPause();
            return true;
        } else {
            stopPause();
            return true;
        }
    }
}
