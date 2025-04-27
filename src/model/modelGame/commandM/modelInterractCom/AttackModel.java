package model.modelGame.commandM.modelInterractCom;

import model.modelGame.GameModel;
import model.modelGame.MessageEnModel;
import model.modelGame.commandM.CommandModel;

/**
 * Represents the "attack" command in the modelGame.
 * <p>
 * When executed, this command attempts to make the Hero attack a monster
 * in the current modelLocation. If a monster is present and the Hero is equipped
 * with a modelWeapon, the monster takes damage.
 */

public class AttackModel extends CommandModel {
    /**
     * Constructs an Attack command with the given command parameters and modelGame instance.
     * @param cmd   the command arguments (typically user input split into words)
     * @param gameM the modelGame instance to operate on
     */
    public AttackModel(String[] cmd, GameModel gameM) {
        this.gameM = gameM;
        this.commands = cmd;
    }

    /**
     * Returns the string identifier for this command.
     * @return the string "attack"
     */
    @Override
    public String toString() {
        return "attack";
    }

    /**
     * Executes the attack command.
     * <p>
     * If there is a monster in the current modelLocation, the Hero attempts to attack it.
     * <ul>
     *     <li>If successful -> the monster takes damage and its HP is printed.</li>
     *     <li>If the Hero has no modelWeapon -> a message is displayed.</li>
     *     <li>If no monster is present -> a message is displayed indicating the action is not possible.</li>
     * </ul>
     * @return true if the attack was successfully executed, false otherwise
     */
    public boolean execute() {
        if(gameM.getCurLocation().getMonster() != null)
        {
            if(gameM.getHero().attack(gameM.getCurLocation().getMonster()))
            {
                System.out.println(MessageEnModel.monsterHP(gameM.getCurLocation().getMonster()));
                return true;
            }
            else
            {
                System.out.println(MessageEnModel.NoWeapon());
                return false;
            }
        }
        else System.out.println(MessageEnModel.cantDo(this));
        return false;

    }
}
