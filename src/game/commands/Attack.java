package game.commands;

import game.Game;
import game.Message;

public class Attack extends Command {
    public Attack(String[] cmd, Game game) {
        this.game = game;
        this.commands = cmd;
    }

    @Override
    public String toString() {
        return "attack";
    }

    public boolean execute() {
        if(game.getCurLocation().getMonster() != null)
        {
            if(game.getHero().attack(game.getCurLocation().getMonster()))
            {
                System.out.println(Message.monsterHP(game.getCurLocation().getMonster()));
                return true;
            }
            else
            {
                System.out.println(Message.NoWeapon());
                return false;
            }
        }
        else System.out.println(Message.cantDo(this));
        return false;

    }
}
