package model.game.commandM.interractM;

import model.game.GameM;
import model.game.MessageM;
import model.game.commandM.Command;
import model.item.Item;
import model.location.Exit;
import model.character.heros.HeroM;
import model.item.container.Container;
import model.item.container.Crate;

import java.util.ArrayList;

/**
 * Represents the "look" command in the game.
 * <p>
 * This command allows the player to observe the current location,
 * including available exits, visible items, any monsters present,
 * and information about the Hero. It also supports inspecting specific
 * items or containers such as crates and backpacks.
 */
public class Look extends Command {

    /**
     * Constructs a Look command with a command string and game instance.
     * @param cmd   the parsed player input (ex {"look", "2"})
     * @param gameM the current game instance
     */
    public Look(String[] cmd, GameM gameM) {
        this.gameM = gameM;
        this.commands = cmd;
    }

    /**
     * Constructs a Look command without input arguments (used when entering a new location).
     * @param gameM the current game instance
     */
    public Look(GameM gameM) {
        this.gameM = gameM;
        this.commands = null;
    }

    /**
     * Executes the look command.
     * <p>
     * If {@code enter} is true, it displays any monster in the newly entered location.
     * Otherwise, it displays exits, items, and the Hero’s status.
     * If a second argument is provided, it is treated as an index
     * for inspecting a specific item or the backpack.
     * @param enter whether the player is entering a new location (true), or just observing (false)
     * @return true if something was inspected or displayed, false if the command failed or had no effect
     */
    public boolean execute(boolean enter) {
        if (!enter){
            ArrayList<Exit> exits = gameM.getCurLocation().getExits();
            int nbExits = exits.size();
            ArrayList<Item> items = gameM.getCurLocation().itemList;
            int nbItems = items.size();

            if (commands == null || commands.length == 1){
                // Locations exits
                System.out.println(MessageM.displayExitsInLoc());
                for (int i = 0 ; i < nbExits ; i++) {
                    System.out.println("    " + i + " to go to " + exits.get(i).destination);
                }

                // Items
                System.out.println(MessageM.displayItemsInLoc());
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
                System.out.println(MessageM.heroDescription(gameM.getHero()));
                
                return false;
            } else {
                // Looking at a specific item by index
                int arg = Integer.parseInt(commands[1]);
                if (arg >= 0 && arg < nbItems){
                    Item i = items.get(arg);
                    // If it's a Crate, check if it can be opened
                    if(Item.isContainer(i))
                    {
                        if(i instanceof Crate)
                        {   
                            Crate c = (Crate) i;
                            if(!c.getOpen())
                            {
                                System.out.println(MessageM.toolRequired());
                                return false;
                            }
                        }
                        Container c = (Container)i;
                        c.displayContent();
                    }
                    else
                    {
                        System.out.println(items.get(arg));
                    }
                } else if (arg == nbItems){
                    // Look into the backpack
                    HeroM.gBackpack().displayContent();
                }
                else
                {
                    System.out.println(MessageM.InvalidNumber(arg));
                    return false;
                }
            }
        } else {
            //Monsters when entering in a new location
            if(gameM.getCurLocation().getMonster() != null)
            {
                System.out.println(MessageM.monsterApparition(gameM.getCurLocation().getMonster()));
            }
        }
        return true;
    }
}
