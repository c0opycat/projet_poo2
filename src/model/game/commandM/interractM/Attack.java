package model.game.commandM.interractM;

import model.game.GameM;
import model.game.MessageM;
import model.game.commandM.Command;

public class Attack extends Command {
    public Attack(String[] cmd, GameM gameM) {
        this.gameM = gameM;
        this.commands = cmd;
    }

    @Override
    public String toString() {
        return "attack";
    }

    public boolean execute() {
        if(gameM.getCurLocation().getMonster() != null)
        {
            if(gameM.getHero().attack(gameM.getCurLocation().getMonster()))
            {
                System.out.println(MessageM.monsterHP(gameM.getCurLocation().getMonster()));
                return true;
            }
            else
            {
                System.out.println(MessageM.NoWeapon());
                return false;
            }
        }
        else System.out.println(MessageM.cantDo(this));
        return false;

    }
}
