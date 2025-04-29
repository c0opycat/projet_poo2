package model.modelGame.modelCommand.modelInterractCom;

import java.awt.Point;
import model.modelCharacter.modelHeros.HeroModel;
import model.modelGame.GameModel;
import model.modelGame.MessageEnModel;
import model.modelGame.modelCommand.CommandModel;
import model.modelItem.ItemModel;
import model.modelItem.ProtectionModel;
import model.modelItem.modelContainer.ContainerModel;
import model.modelItem.modelContainer.CrateModel;
import model.modelLocation.StepModel;

public class TakeModel extends CommandModel {

  private int nb_arg;

  public TakeModel(String[] cmd, GameModel gameM) {
    this.gameM = gameM;
    this.commands = cmd;
    this.nb_arg = this.commands.length;
  }

  public boolean execute(Point p) {
    boolean res = false;

    ItemModel toTake;
    int ind = Integer.parseInt(commands[1]);
    int locNbItems = this.gameM.getCurLocation().itemList.size();

    StepModel step = this.gameM.getCurLocation().getLocMap().get(p);
    if (step == null) {
      System.out.println(MessageEnModel.InvalidItem());
      return res;
    }

    ItemModel item = step.getItem();

    if (item == null) {
      System.out.println(MessageEnModel.InvalidItem());
    }

    if (nb_arg == 2) {
      if (ind < 0 || ind >= locNbItems) {
        System.out.println(MessageEnModel.InvalidNumber(ind));
      } else {
        if (
          (item instanceof ContainerModel) || (item instanceof ProtectionModel)
        ) {
          System.out.println(MessageEnModel.wrongItem("take"));
        } else {
          boolean taken = HeroModel.gBackpack().addItem(item);

          if (taken) {
            this.gameM.getCurLocation().removeItem(p);
            res = true;
          }
        }
      }
    } else {
      if (!(item instanceof ContainerModel)) {
        System.out.println(MessageEnModel.wrongItem("take something from"));
      } else {
        if (item instanceof CrateModel) {
          CrateModel crate = (CrateModel) item;
          if (!crate.open) {
            System.out.println(MessageEnModel.toolRequired());
            return res;
          }
        }

        ContainerModel container = (ContainerModel) item;
        int contNbItems = container.getNbItems();

        if (ind < 0 || ind >= contNbItems) {
          System.out.println(MessageEnModel.InvalidNumber(ind));
        } else {
          toTake = container.getNthItem(ind);

          boolean taken = HeroModel.gBackpack().addItem(toTake);

          if (taken) {
            container.removeItem(toTake);
            res = true;
          }
        }
      }
    }
    return res;
  }
}
