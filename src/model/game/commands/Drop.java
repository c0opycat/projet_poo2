package model.game.commands;

import model.game.Game;
import model.game.Message;
import model.item.container.Backpack;
import model.character.heros.Hero;
import model.item.Item;

public class Drop extends Command {
    public Drop(String[] cmd, Game game) {
        this.game = game;
        this.commands = cmd;
    }

    @Override
    public String toString() {
        return "drop anything";
    }

    public boolean execute() {
        Backpack bp = Hero.gBackpack();
        if (commands.length == 1) {
            System.out.println(bp);
        } else{
            int arg = Integer.parseInt(commands[1]);
            if (arg >= 0 && arg < bp.getNbItems()){
                Item toDrop = bp.getNthItem(arg);
                this.game.getCurLocation().addItem(toDrop);
                bp.removeItem(toDrop);
                System.out.println(Message.commandOnItem("dropped ",toDrop));
                return true;
            } else if (arg == bp.getNbItems()){
                game.getHero().dropProtection(this.game.getCurLocation());
            } else if (arg == bp.getNbItems() + 1){
                game.getHero().dropWeapon(this.game.getCurLocation());
            }
        }
        return false;
    }
}
