package model.game.commandM.interractM;

import model.game.GameM;
import model.game.MessageM;
import model.game.commandM.Command;
import model.item.container.Backpack;
import model.character.heros.HeroM;
import model.item.Item;
import model.location.LocationM;


/**
 * Represents the "drop" command in the game.
 * <p>
 * When executed, this command allows the player to drop an item from their backpack,
 * their protection (shield), or their weapon at the current location.
 */
public class Drop extends Command {
    /**
     * Constructs a Drop command with the provided input and game state.
     * @param cmd   the parsed user command (e.g. {"drop", "1"})
     * @param gameM the current game instance
     */
    public Drop(String[] cmd, GameM gameM) {
        this.gameM = gameM;
        this.commands = cmd;
    }

    /**
     * Returns a short description of the command.
     * @return the string "drop anything"
     */
    @Override
    public String toString() {
        return "drop anything";
    }

    /**
     * Executes the drop command.
     * <p>
     * Behavior depends on the number and value of command arguments:
     * <ul>
     *     <li>no index is provided -> the contents of the backpack are displayed.</li>
     *     <li>an index within backpack bounds is provided -> the corresponding item is dropped on the map.</li>
     *     <li>the index equals the number of items -> the Hero's protection is dropped.</li>
     *     <li>the index is one above that -> the Hero's weapon is dropped.</li>
     * </ul>
     * @return true if something was successfully dropped, false otherwise
     */
    public boolean execute() {
        Backpack bp = HeroM.gBackpack();
        //If no item index -> print the backpack contents
        if (commands.length == 1) {
            System.out.println(bp);
        } else{
            int arg = Integer.parseInt(commands[1]);
            if (arg >= 0 && arg < bp.getNbItems()){
                Item toDrop = bp.getNthItem(arg);
                LocationM l = this.gameM.getCurLocation();
                l.addItem(toDrop,l.getRandomFreeStepCoord());
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
