package model.modelGame.commandM.modelMenuCom;

import model.modelGame.GameModel;
import model.modelGame.commandM.CommandModel;

public class HelpModel extends CommandModel {
    public HelpModel(GameModel gameM) {
        this.gameM = gameM;
    }

    public boolean execute() {
        System.out.println("Help :");
        System.out.println("    ATTACK                 (to attack the monster)");
        System.out.println("    LOOK                   (to see what is in the place)");
        System.out.println("    LOOK nbChest/nbInv     (to see what is in the chest/your inventory)");
        System.out.println("    DROP nbItem            (to drop an modelItem from your inventory)");
        System.out.println("    GO nbExit              (to go to an other place, you can use it to run away from big and slow dangers)");
        System.out.println("    USE nbItem             (to use an modelItem)");
        System.out.println("    USE nbItem1 nbItem2    (to use an modelItem on an another modelItem)");
        System.out.println("    TAKE nbItem            (to take an modelItem from the modelLocation)");
        System.out.println("    TAKE nbItem1 nbItem2   (to take an modelItem from an another modelItem)");
        System.out.println("    EQUIP nbItem nbSource  (to equip an modelItem from the source identified by nbSource (1 for your backpack, 2 for the modelLocation, 3 for a chest or a crate))");
        System.out.println("    QUIT                   (to quit the modelGame)");
        return false;
    }
}
