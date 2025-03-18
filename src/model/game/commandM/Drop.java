package model.game.commandM;

import model.game.GameM;
import model.game.MessageM;
import model.item.container.Backpack;
import model.character.heros.HeroM;
import model.item.Item;

public class Drop extends Command {
    public Drop(String[] cmd, GameM gameM) {
        this.gameM = gameM;
        this.commands = cmd;
    }

    @Override
    public String toString() {
        return "drop anything";
    }

    public boolean execute() {
        Backpack bp = HeroM.gBackpack();
        if (commands.length == 1) {
            System.out.println(bp);
        } else{
            int arg = Integer.parseInt(commands[1]);
            if (arg >= 0 && arg < bp.getNbItems()){
                Item toDrop = bp.getNthItem(arg);
                this.gameM.getCurLocation().addItem(toDrop);
                bp.removeItem(toDrop);
                System.out.println(MessageM.commandOnItem("dropped ",toDrop));
                return true;
            } else if (arg == bp.getNbItems()){
                gameM.getHero().dropProtection(this.gameM.getCurLocation());
            } else if (arg == bp.getNbItems() + 1){
                gameM.getHero().dropWeapon(this.gameM.getCurLocation());
            }
        }
        return false;
    }
}
