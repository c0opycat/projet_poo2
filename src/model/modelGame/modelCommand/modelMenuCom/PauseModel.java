package model.modelGame.modelCommand.modelMenuCom;

import model.modelGame.GameModel;
import model.modelGame.modelCommand.CommandModel;

public class PauseModel extends CommandModel {
    long startTime;
    long stopTime;

    public PauseModel(String[] commands, GameModel game) {
        this.gameM = game;
        this.commands = commands;
    }
    private void startPause() {
        gameM.isRunning = false;
        this.startTime = System.currentTimeMillis();
    }

    private void stopPause() {
        this.stopTime = System.currentTimeMillis();
        int pause = (int) (this.stopTime - this.startTime);
        gameM.timePaused = gameM.timePaused + pause;
        gameM.isRunning = true;
    }

    @Override
    public boolean execute() {
        if (gameM.isEnd()){
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