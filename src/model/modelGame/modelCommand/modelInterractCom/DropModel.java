package model.modelGame.modelCommand.modelInterractCom;

import java.awt.Point;
import model.modelCharacter.modelHeros.HeroModel;
import model.modelGame.GameModel;
import model.modelGame.MessageModel;
import model.modelGame.modelCommand.CommandModel;
import model.modelItem.ItemModel;
import model.modelItem.modelContainer.BackpackModel;
import model.modelLocation.LocationModel;

/**
 * Represents the "drop" command in the modelGame.
 * <p>
 * When executed, this command allows the player to drop an modelItem from their backpack,
 * their protection (shield), or their modelWeapon at the current modelLocation.
 */
public class DropModel extends CommandModel {

  /**
   * Constructs a Drop command with the provided input and modelGame state.
   * @param cmd   the parsed user command (e.g. {"drop", "1"})
   * @param gameM the current modelGame instance
   */
  public DropModel(String[] cmd, GameModel gameM) {
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
   *     <li>an index within backpack bounds is provided -> the corresponding modelItem is dropped on the map.</li>
   *     <li>the index equals the number of items -> the Hero's protection is dropped.</li>
   *     <li>the index is one above that -> the Hero's modelWeapon is dropped.</li>
   * </ul>
   * @return true if something was successfully dropped, false otherwise
   */
  public boolean execute() {
    BackpackModel bp = HeroModel.gBackpack();
    if (commands.length == 1) {
      System.out.println(bp);
    } else {
      int arg = Integer.parseInt(commands[1]);
      if (arg >= 0 && arg < bp.getNbItems()) {
        ItemModel toDrop = bp.getNthItem(arg);
        LocationModel l = this.gameM.getCurLocation();
        Point point = l.getRandomFreeStepCoord();
        if (point != null) {
          l.addItem(toDrop, point);
        }
        bp.removeItem(toDrop);
        System.out.println(MessageModel.commandOnItem("dropped ", toDrop));
        return true;
      } else if (arg == bp.getNbItems()) {
        gameM.getHero().dropProtection(this.gameM.getCurLocation());
      } else if (arg == bp.getNbItems() + 1) {
        gameM.getHero().dropWeapon(this.gameM.getCurLocation());
      }
    }
    return false;
  }
}
