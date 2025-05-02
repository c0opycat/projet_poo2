package model.modelGame.modelCommand.modelItemCom;

import java.awt.Point;
import model.modelCharacter.modelHeros.HeroModel;
import model.modelGame.GameModel;
import model.modelGame.MessageModel;
import model.modelGame.modelCommand.CommandModel;
import model.modelItem.CrowbarModel;
import model.modelItem.ItemModel;
import model.modelItem.ProtectionModel;
import model.modelItem.modelConsumable.ConsumableModel;
import model.modelItem.modelContainer.CrateModel;
import model.modelItem.modelWeapon.WeaponModel;
import model.modelLocation.StepModel;

/**
 * The UseModel class represents the "use" command allowing the hero to use consumable items or tools like a crowbar.
 * <p>
 * The following actions can be performed using this command:
 * <ul>
 *     <li>Use a {@link ConsumableModel} to heal the hero.</li>
 *     <li>Use a {@link CrowbarModel} to open crates at a specified location.</li>
 * </ul>
 * Weapon and Protection items cannot be used with this command.
 */
public class UseModel extends CommandModel {

  /**
   * Constructs a UseModel command.
   * @param cmd   the command arguments
   * @param gameM the game model
   */
  public UseModel(String[] cmd, GameModel gameM) {
    this.gameM = gameM;
    this.commands = cmd;
  }

  /**
   * Executes the use command at the specified point.
   * <p>
   * The command uses an item from the hero's backpack. The item can:
   * <ul>
   *     <li>Heal the hero if it's a consumable.</li>
   *     <li>Open a crate if it's a crowbar and there is a crate at the given point.</li>
   * </ul>
   * Weapon and Protection items are not usable through this command.
   * @param p the point in the location where the item is to be used
   * @return true if the item was used successfully, false otherwise
   */
  public boolean execute(Point p) {
    boolean res = false;
    int bpSize = HeroModel.gBackpack().getNbItems();
    int ind = Integer.parseInt(commands[1]);

    if (ind < 0 || ind >= bpSize) {
      System.out.println(MessageModel.InvalidNumber(ind));
    } else {
      ItemModel toUse = HeroModel.gBackpack().getNthItem(ind);

      if (toUse instanceof WeaponModel || toUse instanceof ProtectionModel) {
        System.out.println(MessageModel.cantUseItem(toUse));
      } else {
        if (toUse instanceof CrowbarModel) {
          StepModel step = this.gameM.getCurLocation().getLocMap().get(p);
          if (p == null) {
            System.out.println(MessageModel.cantUseItem(toUse));
            return res;
          }

          ItemModel arg2 = step.getItem();
          if (arg2 == null) {
            System.out.println(MessageModel.InvalidItem());
            return res;
          }

          if (!(arg2 instanceof CrateModel)) {
            System.out.println(MessageModel.cantUseItem(toUse));
          } else {
            CrateModel crate = (CrateModel) arg2;
            CrowbarModel c = (CrowbarModel) toUse;

            if (crate.open(c)) {
              res = true;
            }
          }
        } else {
          ConsumableModel cs = (ConsumableModel) toUse;

          cs.consume(this.gameM.getHero());

          res = true;
        }
      }
    }

    return res;
  }
}
