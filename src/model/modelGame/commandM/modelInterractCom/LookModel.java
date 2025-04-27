package model.modelGame.commandM.modelInterractCom;

import model.modelGame.GameModel;
import model.modelGame.MessageEnModel;
import model.modelGame.commandM.CommandModel;
import model.modelItem.ItemModel;
import model.modelItem.modelContainer.ContainerModel;
import model.modelItem.modelContainer.CrateModel;
import model.modelLocation.ExitModel;
import model.modelCharacter.modelHeros.HeroModel;

import java.util.ArrayList;

/**
 * Represents the "look" command in the modelGame.
 * <p>
 * This command allows the player to observe the current modelLocation,
 * including available exits, visible items, any monsters present,
 * and information about the Hero. It also supports inspecting specific
 * items or containers such as crates and backpacks.
 */
public class LookModel extends CommandModel {

    /**
     * Constructs a Look command with a command string and modelGame instance.
     * @param cmd   the parsed player input (ex {"look", "2"})
     * @param gameM the current modelGame instance
     */
    public LookModel(String[] cmd, GameModel gameM) {
        this.gameM = gameM;
        this.commands = cmd;
    }

    /**
     * Constructs a Look command without input arguments (used when entering a new modelLocation).
     * @param gameM the current modelGame instance
     */
    public LookModel(GameModel gameM) {
        this.gameM = gameM;
        this.commands = null;
    }

    /**
     * Executes the look command.
     * <p>
     * If {@code enter} is true, it displays any monster in the newly entered modelLocation.
     * Otherwise, it displays exits, items, and the Hero’s status.
     * If a second argument is provided, it is treated as an index
     * for inspecting a specific modelItem or the backpack.
     * @param enter whether the player is entering a new modelLocation (true), or just observing (false)
     * @return true if something was inspected or displayed, false if the command failed or had no effect
     */
    public boolean execute(boolean enter) {
        if (!enter){
            ArrayList<ExitModel> exits = gameM.getCurLocation().getExits();
            int nbExits = exits.size();
            ArrayList<ItemModel> items = gameM.getCurLocation().itemList;
            int nbItems = items.size();

            if (commands == null || commands.length == 1){
                // Locations exits
                System.out.println(MessageEnModel.displayExitsInLoc());
                for (int i = 0 ; i < nbExits ; i++) {
                    System.out.println("    " + i + " to go to " + exits.get(i).destination);
                }

                // Items
                System.out.println(MessageEnModel.displayItemsInLoc());
                for (int i = 0 ; i < nbItems ; i++) {
                    System.out.println("    " + i + " to select " + items.get(i));
                }
                System.out.println("    " + nbItems + " to look in your backpack");

                //Monster if present
                if(gameM.getCurLocation().getMonster() != null)
                {
                    System.out.println("Monster : ");
                    System.out.println(gameM.getCurLocation().getMonster());
                }

                //Hero
                System.out.println(MessageEnModel.heroDescription(gameM.getHero()));
                
                return false;
            } else {
                // Looking at a specific modelItem by index
                int arg = Integer.parseInt(commands[1]);
                if (arg >= 0 && arg < nbItems){
                    ItemModel i = items.get(arg);
                    // If it's a CrateModel, check if it can be opened
                    if(ItemModel.isContainer(i))
                    {
                        if(i instanceof CrateModel)
                        {   
                            CrateModel c = (CrateModel) i;
                            if(!c.getOpen())
                            {
                                System.out.println(MessageEnModel.toolRequired());
                                return false;
                            }
                        }
                        ContainerModel c = (ContainerModel)i;
                        c.displayContent();
                    }
                    else
                    {
                        System.out.println(items.get(arg));
                    }
                } else if (arg == nbItems){
                    // Look into the backpack
                    HeroModel.gBackpack().displayContent();
                }
                else
                {
                    System.out.println(MessageEnModel.InvalidNumber(arg));
                    return false;
                }
            }
        } else {
            //Monsters when entering in a new modelLocation
            if(gameM.getCurLocation().getMonster() != null)
            {
                System.out.println(MessageEnModel.monsterApparition(gameM.getCurLocation().getMonster()));
            }
        }
        return true;
    }
}
