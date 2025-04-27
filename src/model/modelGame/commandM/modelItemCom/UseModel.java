package model.modelGame.commandM.modelItemCom;

import java.awt.Point;
import model.modelCharacter.modelHeros.HeroModel;
import model.modelGame.GameModel;
import model.modelGame.MessageEnModel;
import model.modelGame.commandM.CommandModel;
import model.modelItem.CrowbarModel;
import model.modelItem.ItemModel;
import model.modelItem.ProtectionModel;
import model.modelItem.modelConsumable.ConsumableModel;
import model.modelItem.modelContainer.CrateModel;
import model.modelItem.modelWeapon.WeaponModel;
import model.modelLocation.StepModel;

public class UseModel extends CommandModel {

  private int nb_arg;

  public UseModel(String[] cmd, GameModel gameM) {
    this.gameM = gameM;
    this.commands = cmd;
    this.nb_arg = this.commands.length;
  }

  public boolean execute(Point p) {
    boolean res = false;
    int bpSize = HeroModel.gBackpack().getNbItems();
    int ind = Integer.parseInt(commands[1]);

    if (ind < 0 || ind >= bpSize) {
      System.out.println(MessageEnModel.InvalidNumber(ind));
    } else {
      ItemModel toUse = HeroModel.gBackpack().getNthItem(ind);

      if (toUse instanceof WeaponModel || toUse instanceof ProtectionModel) {
        System.out.println(MessageEnModel.cantUseItem(toUse));
      } else {
        if (toUse instanceof CrowbarModel) {
          if (nb_arg == 2) {
            System.out.println();
          } else {
            StepModel step = this.gameM.getCurLocation().getLocMap().get(p);
            if (p == null) {
              System.out.println(MessageEnModel.cantUseItem(toUse));
              return res;
            }

            ItemModel arg2 = step.getItem();
            if (arg2 == null) {
              System.out.println(MessageEnModel.InvalidItem());
              return res;
            }

            if (!(arg2 instanceof CrateModel)) {
              System.out.println(MessageEnModel.cantUseItem(toUse));
            } else {
              CrateModel crate = (CrateModel) arg2;
              CrowbarModel c = (CrowbarModel) toUse;

              if (crate.open(c)) {
                res = true;
                System.out.println(
                  MessageEnModel.commandOnItem("used ", toUse)
                );
              }
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
