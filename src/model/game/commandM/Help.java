package model.game.commandM;

import model.game.GameM;

public class Help extends Command {
    public Help(GameM gameM) {
        this.gameM = gameM;
    }

    public boolean execute() {
        System.out.println("Help :");
        System.out.println("    ATTACK                 (to attack the monster)");
        System.out.println("    LOOK                   (to see what is in the place)");
        System.out.println("    LOOK nbChest/nbInv     (to see what is in the chest/your inventory)");
        System.out.println("    DROP nbItem            (to drop an item from your inventory)");
        System.out.println("    GO nbExit              (to go to an other place, you can use it to run away from big and slow dangers)");
        System.out.println("    USE nbItem             (to use an item)");
        System.out.println("    USE nbItem1 nbItem2    (to use an item on an another item)");
        System.out.println("    TAKE nbItem            (to take an item from the location)");
        System.out.println("    TAKE nbItem1 nbItem2   (to take an item from an another item)");
        System.out.println("    EQUIP nbItem nbSource  (to equip an item from the source identified by nbSource (1 for your backpack, 2 for the location, 3 for a chest or a crate))");
        System.out.println("    QUIT                   (to quit the game)");
        return false;
    }
}
