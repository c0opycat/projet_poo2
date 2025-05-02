package model.modelGame.modelCommand.modelItemCom;

import java.awt.Point;
import model.modelCharacter.modelHeros.HeroModel;
import model.modelGame.GameModel;
import model.modelGame.MessageEnModel;
import model.modelGame.modelCommand.CommandModel;
import model.modelItem.ItemModel;
import model.modelItem.ProtectionModel;
import model.modelItem.modelContainer.*;
import model.modelItem.modelWeapon.WeaponModel;
import model.modelLocation.LocationModel;
import model.modelLocation.StepModel;

/**
 * The EquipModel class represents the "equip" command which allows a hero to equip weapons, protections, or switch backpacks.
 * <p>
 * The equip command can target items from:
 * <ul>
 *     <li>The hero's backpack (if the second argument is 1)</li>
 *     <li>The ground/location (if the second argument is 2)</li>
 *     <li>Containers (if the second argument is 3 or more)</li>
 * </ul>
 * Depending on the item type and the source, the hero may equip it directly, swap with the current equipped item,
 * or switch backpacks.
 */
public class EquipModel extends CommandModel {

  /**
   * The index of the item to equip.
   */
  private int fstArg;

  /**
   * The source location of the item (1 = backpack, 2 = ground, 3+ = containers).
   */
  private int scdArg;

  /**
   * Creates a new EquipModel command.
   * @param cmd   the command arguments as a String array
   * @param gameM the game model
   */
  public EquipModel(String[] cmd, GameModel gameM) {
    this.gameM = gameM;
    this.commands = cmd;
    this.fstArg = Integer.parseInt(commands[1]);
    this.scdArg = Integer.parseInt(commands[2]);
  }

  /**
   * Returns a string representing this command.
   * @return "equip anything"
   */
  @Override
  public String toString() {
    return "equip anything";
  }

  /**
   * Executes the equip command on a specified point.
   * <p>
   * Equips an item from various sources depending on the arguments:
   * <ul>
   *     <li>Backpack: equipping weapons.</li>
   *     <li>Ground: equipping weapons, protections, or switching backpacks.</li>
   *     <li>Container (like crates): equipping weapons or protections if accessible.</li>
   * </ul>
   * @param p the point (location) where the equip action takes place
   * @return true if the item was successfully equipped or switched, false otherwise
   */
  public boolean execute(Point p) {
    boolean res = false;

    // Validate scdArg
    if (scdArg < 1 || scdArg > 4) {
      System.out.println("The second number : " + scdArg + "is invalid.");
    } else {
      LocationModel loc = this.gameM.getCurLocation();
      int nbItems = 0;
      ItemModel toEquip;

      // Equip from Backpack
      if (scdArg == 1) {
        BackpackModel bp = HeroModel.gBackpack();
        nbItems = bp.getNbItems();

        if (fstArg < 0 || fstArg >= nbItems) {
          System.out.println(MessageEnModel.InvalidNumber(fstArg));
        } else {
          toEquip = bp.getNthItem(fstArg);

          if (!(toEquip instanceof WeaponModel)) {
            System.out.println(MessageEnModel.wrongItem("equip"));
          } else {
            WeaponModel w = (WeaponModel) toEquip;

            WeaponModel herosWeapon = this.gameM.getHero().getWeapon();

            if (herosWeapon != null) {
              Point pt = loc.getRandomFreeStepCoord();
              if (pt != null) {
                loc.addItem(herosWeapon, pt);
              }
            }

            bp.removeItem(w);
            this.gameM.getHero().setWeapon(w);

            res = true;
          }
        }

        // Equip from ground
      } else if (scdArg == 2) {
        nbItems = loc.itemList.size();
        StepModel step = loc.getLocMap().get(p);

        if (step == null) {
          System.out.println(MessageEnModel.InvalidItem());
        }

        toEquip = step.getItem();

        if (toEquip == null) {
          System.out.println(MessageEnModel.InvalidItem());
        } else {
          if (
            (toEquip instanceof WeaponModel) ||
            (toEquip instanceof ProtectionModel) ||
            (toEquip instanceof BackpackModel)
          ) {
            if (toEquip instanceof WeaponModel) {
              WeaponModel w = (WeaponModel) toEquip;
              WeaponModel herosWeapon = this.gameM.getHero().getWeapon();

              if (herosWeapon != null) {
                Point pt = loc.getRandomFreeStepCoord();
                if (pt != null) {
                  loc.addItem(herosWeapon, pt);
                }
              }

              this.gameM.getHero().setWeapon(w);
              loc.removeItem(p);

              res = true;
            } else if (toEquip instanceof ProtectionModel) {
              ProtectionModel pt = (ProtectionModel) toEquip;
              ProtectionModel herosProtection =
                this.gameM.getHero().getShield();

              if (herosProtection != null) {
                Point point = loc.getRandomFreeStepCoord();
                if (point != null) {
                  loc.addItem(herosProtection, point);
                }
              }

              this.gameM.getHero().setShield(pt);
              loc.removeItem(p);

              res = true;
            } else {
              this.gameM.getHero().switchBackpack(p, loc);

              res = true;
            }
          } else {
            System.out.println(MessageEnModel.wrongItem("equip"));
          }
        }

        // Equip from Container
      } else {
        StepModel step = this.gameM.getCurLocation().getLocMap().get(p);
        if (step == null) {
          System.out.println(MessageEnModel.InvalidItem());
          return res;
        }

        ItemModel tmp = step.getItem();
        if (tmp == null) {
          System.out.println(MessageEnModel.InvalidItem());
          return res;
        }

        if (!(tmp instanceof ContainerModel)) {
          System.out.println(MessageEnModel.commandOnItem("equip from", tmp));
          return res;
        }

        ContainerModel cont = (ContainerModel) tmp;

        if (cont instanceof CrateModel) {
          CrateModel c = (CrateModel) cont;
          if (!c.open) {
            System.out.println(MessageEnModel.toolRequired());
            return res;
          }
        } else {
          nbItems = cont.getNbItems();

          if (fstArg < 0 || fstArg >= nbItems) {
            System.out.println(MessageEnModel.InvalidNumber(fstArg));
          } else {
            toEquip = cont.getNthItem(fstArg);

            if (
              (toEquip instanceof WeaponModel) ||
              (toEquip instanceof ProtectionModel)
            ) {
              if (toEquip instanceof WeaponModel) {
                WeaponModel w = (WeaponModel) toEquip;
                WeaponModel herosWeapon = this.gameM.getHero().getWeapon();

                if (herosWeapon != null) {
                  Point pt = loc.getRandomFreeStepCoord();
                  if (pt != null) {
                    loc.addItem(herosWeapon, pt);
                  }
                }

                cont.removeItem(w);
                this.gameM.getHero().setWeapon(w);
              } else {
                ProtectionModel pt = (ProtectionModel) toEquip;
                ProtectionModel herosProtection =
                  this.gameM.getHero().getShield();

                if (herosProtection != null) {
                  Point point = loc.getRandomFreeStepCoord();
                  if (point != null) {
                    loc.addItem(herosProtection, point);
                  }
                }

                cont.removeItem(pt);
                this.gameM.getHero().setShield(pt);
              }
              res = true;
            } else {
              System.out.println(MessageEnModel.wrongItem("equip"));
            }
          }
        }
      }
    }
    return res;
  }
}
