package model.game.commandM.interractM;

import model.game.GameM;
import model.game.MessageM;
import model.game.commandM.Command;

/**
 * Represents the "attack" command in the game.
 * <p>
 * When executed, this command attempts to make the Hero attack a monster
 * in the current location. If a monster is present and the Hero is equipped
 * with a weapon, the monster takes damage.
 */

public class Attack extends Command {
    /**
     * Constructs an Attack command with the given command parameters and game instance.
     *
     * @param cmd   the command arguments (typically user input split into words)
     * @param gameM the game instance to operate on
     */
    public Attack(String[] cmd, GameM gameM) {
        this.gameM = gameM;
        this.commands = cmd;
    }

    /**
     * Returns the string identifier for this command.
     *
     * @return the string "attack"
     */
    @Override
    public String toString() {
        return "attack";
    }

    /**
     * Executes the attack command.
     * <p>
     * If there is a monster in the current location:
     * - The Hero attempts to attack it.
     * - If successful, the monster takes damage and its HP is printed.
     * - If the Hero has no weapon, a message is displayed.
     * If no monster is present, a message is displayed indicating the action is not possible.
     *
     * @return true if the attack was successfully executed, false otherwise
     */
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
