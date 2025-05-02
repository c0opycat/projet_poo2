package model.modelGame.modelCommand.modelInterractCom;

import java.awt.Point;
import model.modelCharacter.modelHeros.HeroModel;
import model.modelGame.GameModel;
import model.modelGame.MessageModel;
import model.modelGame.modelCommand.CommandModel;
import model.modelItem.ItemModel;
import model.modelItem.ProtectionModel;
import model.modelItem.modelContainer.ContainerModel;
import model.modelItem.modelContainer.CrateModel;
import model.modelLocation.StepModel;

/**
 * Represents a "Take" command allowing the player to take items from the ground or from containers.
 * <p>
 * If no argument is provided, attempts to take the item from the ground.
 * If an argument is provided, attempts to take the item at the given index from a container.
 */
public class TakeModel extends CommandModel {

  /**
   * The number of arguments in the command.
   */
  private int nb_arg;

  /**
   * Constructs a TakeModel command.
   * @param cmd   the command arguments as a String array
   * @param gameM the current game model
   */
  public TakeModel(String[] cmd, GameModel gameM) {
    this.gameM = gameM;
    this.commands = cmd;
    this.nb_arg = this.commands.length;
  }

  /**
   * Executes the take command at the given position.
   * <p>
   * The method handles different cases:
   * <ul>
   *     <li>If the item is a container and the player wants to take an item from inside, it checks the index.</li>
   *     <li>If the item is a crate and not open, the action is rejected.</li>
   *     <li>If the item is valid, adds it to the hero's backpack and removes it from the location/container.</li>
   * </ul>
   *
   * @param p the position of the item in the current location
   * @return true if the item was successfully taken, false otherwise
   */
  public boolean execute(Point p) {
    boolean res = false;

    ItemModel toTake;

    int ind = 0;
    try {
      ind = Integer.parseInt(commands[1]);
    } catch (Exception e) {}

    StepModel step = this.gameM.getCurLocation().getLocMap().get(p);
    if (step == null) {
      System.out.println(MessageModel.InvalidItem());
      return res;
    }

    ItemModel item = step.getItem();

    if (item == null) {
      System.out.println(MessageModel.InvalidItem());
    }

    if (nb_arg == 1) {
      if (
        (item instanceof ContainerModel) || (item instanceof ProtectionModel)
      ) {
        System.out.println(MessageModel.wrongItem("take"));
      } else {
        boolean taken = HeroModel.gBackpack().addItem(item);

        if (taken) {
          this.gameM.getCurLocation().removeItem(p);
          res = true;
        }
      }
    } else {
      if (!(item instanceof ContainerModel)) {
        System.out.println(MessageModel.wrongItem("take something from"));
      } else {
        if (item instanceof CrateModel) {
          CrateModel crate = (CrateModel) item;
          if (!crate.open) {
            System.out.println(MessageModel.toolRequired());
            return res;
          }
        }

        ContainerModel container = (ContainerModel) item;
        int contNbItems = container.getNbItems();

        if (ind < 0 || ind >= contNbItems) {
          System.out.println(MessageModel.InvalidNumber(ind));
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
