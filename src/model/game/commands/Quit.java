package model.game.commands;

import model.game.Game;

public class Quit extends Command {
    public Quit(String[] cmd, Game game) {
        this.game = game;
        this.commands = cmd;
    }

    public boolean execute() {
        game.forfeit(true);
        return true;
    }
}
