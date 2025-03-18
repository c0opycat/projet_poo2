package model.game.commandM;

import model.game.GameM;

public class Quit extends Command {
    public Quit(String[] cmd, GameM gameM) {
        this.gameM = gameM;
        this.commands = cmd;
    }

    public boolean execute() {
        gameM.forfeit(true);
        return true;
    }
}
