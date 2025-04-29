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

public class EquipModel extends CommandModel {

  private int fstArg;
  private int scdArg;

  public EquipModel(String[] cmd, GameModel gameM) {
    this.gameM = gameM;
    this.commands = cmd;
    this.fstArg = Integer.parseInt(commands[1]);
    this.scdArg = Integer.parseInt(commands[2]);
  }

  @Override
  public String toString() {
    return "equip anything";
  }

  public boolean execute(Point p) {
    boolean res = false;

    if (scdArg < 1 || scdArg > 4) {
      System.out.println("The second number : " + scdArg + "is invalid.");
    } else {
      LocationModel loc = this.gameM.getCurLocation();
      int nbItems = 0;
      ItemModel toEquip;

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
              boolean switched = bp.addItem(herosWeapon);
              if (!switched) {
                Point pt = loc.getRandomFreeStepCoord();
                if (pt != null) {
                  loc.addItem(herosWeapon, pt);
                }
              }
            }

            bp.removeItem(w);
            this.gameM.getHero().setWeapon(w);

            res = true;
          }
        }
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
                  boolean switched = cont.addItem(herosWeapon);
                  if (!switched) {
                    Point pt = loc.getRandomFreeStepCoord();
                    if (pt != null) {
                      loc.addItem(herosWeapon, pt);
                    }
                  }
                }

                cont.removeItem(w);
                this.gameM.getHero().setWeapon(w);
              } else {
                ProtectionModel pt = (ProtectionModel) toEquip;
                ProtectionModel herosProtection =
                  this.gameM.getHero().getShield();

                if (herosProtection != null) {
                  boolean switched = cont.addItem(herosProtection);
                  if (!switched) {
                    Point point = loc.getRandomFreeStepCoord();
                    if (point != null) {
                      loc.addItem(herosProtection, point);
                    }
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
