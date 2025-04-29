package model.modelGame.modelCommand.modelMenuCom;

import model.modelGame.GameModel;
import model.modelGame.modelCommand.CommandModel;

public class QuitModel extends CommandModel {
    public QuitModel(String[] cmd, GameModel gameM) {
        this.gameM = gameM;
        this.commands = cmd;
    }

    public boolean execute() {
        gameM.forfeit(true);
        return true;
    }
}
