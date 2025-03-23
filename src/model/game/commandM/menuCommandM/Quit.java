package model.game.commandM.menuCommandM;

import model.game.GameM;
import model.game.commandM.Command;

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
