package model.game.commandM.interractM;

import model.game.GameM;
import model.game.MessageM;
import model.game.commandM.Command;
import model.item.container.Backpack;
import model.character.heros.HeroM;
import model.item.Item;

/**
 * Represents the "drop" command in the game.
 * <p>
 * When executed, this command allows the player to drop an item from their backpack,
 * their protection (shield), or their weapon at the current location.
 */
public class Drop extends Command {
    /**
     * Constructs a Drop command with the provided input and game state.
     * @param cmd   the full command input from the player (tokenized)
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
     * If no arguments are provided, the backpack contents are printed.
     * If an index is provided:
     * <ul>
     *     <li>If the index is valid for the backpack, drops the selected item.</li>
     *     <li>If the index is equal to the backpack size, drops the Hero's protection.</li>
     *     <li>If the index is one above the backpack size, drops the Hero's weapon.</li>
     * </ul>
     * @return true if the item/protection/weapon was successfully dropped, false otherwise
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
