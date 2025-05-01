package model.modelGame.modelCommand.modelInterractCom;

import java.awt.Point;
import java.util.ArrayList;
import model.modelGame.GameModel;
import model.modelGame.MessageEnModel;
import model.modelGame.modelCommand.CommandModel;
import model.modelLocation.ExitModel;

/**
 * Represents the "go" command in the modelGame.
 * <p>
 * Allows the Hero to move from one modelLocation to another via an exit.
 * The command uses an argument to determine which exit to take.
 * Movement is blocked if a monster is still alive in the current modelLocation.
 */
public class GoModel extends CommandModel {

  private ArrayList<ExitModel> exits;
  private int nbExits;

  /**
   * Constructs a Go command with arguments and the current modelGame state.
   * @param cmd   the command array (ex:{"go", "1"})
   * @param gameM the current modelGame instance
   */
  public GoModel(String[] cmd, GameModel gameM) {
    this.commands = cmd;
    this.gameM = gameM;
  }

  /**
   * Executes the "go" command.
   * <p>
   * Moves the Hero to another modelLocation based on the selected exit.
   * The command checks if the chosen index is within bounds,
   * and whether a monster is still alive in the current modelLocation.
   * It also checks whether the chosen exit is a one-way path
   * and simulates confirmation logic (currently always true)
   * @return true if the Hero successfully moves to another modelLocation, false otherwise
   */
  public boolean execute(Point p) {
    //check for monsters others than a Colossus before allowing exit
    if (
      gameM.getCurLocation().getMonster() == null ||
      gameM
        .getCurLocation()
        .getMonster()
        .getClass()
        .getSimpleName()
        .equals("ColossusModel")
    ) {
      ExitModel exit = this.gameM.getCurLocation().getLocMap().get(p).getExit();

      if (exit != null) {
        this.gameM.setCurLocation(exit.destination);
        this.gameM.getCurLocation().displayLocation();
        return true;
      } else {
        System.out.println(MessageEnModel.notAnExit());
        return false;
      }
      // }
    } else {
      System.out.println(MessageEnModel.cantExit());
      return false;
    }
  }

  /**
   * Displays help text showing available exits and their corresponding indices.
   */
  public void help() {
    this.exits = gameM.getCurLocation().getExits();
    this.nbExits = exits.size();
    System.out.println("You can go to :");
    for (int i = 0; i < nbExits; i++) {
      System.out.println("    " + i + ": " + exits.get(i).destination);
    }
  }
}
