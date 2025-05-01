package model.modelGame.modelCommand;

import java.util.Scanner;
import model.modelGame.*;
import model.modelGame.modelCommand.modelInterractCom.*;
import model.modelGame.modelCommand.modelItemCom.EquipModel;
import model.modelGame.modelCommand.modelItemCom.UseModel;
import model.modelGame.modelCommand.modelMenuCom.HelpModel;
import model.modelGame.modelCommand.modelMenuCom.PauseModel;
import model.modelGame.modelCommand.modelMenuCom.QuitModel;

public class CommandModel {

  protected String[] commands;
  protected GameModel gameM;
  protected Scanner scan;

  public CommandModel() {}

  public CommandModel(String command, GameModel gameM, Scanner scan) {
    this.commands = (command.toLowerCase()).split(" ");
    this.gameM = gameM;
    this.scan = scan;
  }

  public void setGameM(GameModel gameModel) {
    this.gameM = gameModel;
  }

  public String[] getCommands() {
    return this.commands;
  }

  public static boolean confirmation(Scanner scan) {
    String letter;

    letter = scan.next().toLowerCase().trim();

    return letter.equals("y");
  }

  private static boolean isInteger(String s) {
    try {
      Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return false;
    }

    return true;
  }

  public boolean execute() {
    if (commands.length == 0) {
      return false;
    }
    switch (commands[0]) {
      case "go":
        if (commands.length == 1) {
          new GoModel(commands, gameM).help();
          return false;
        } else if ((commands.length != 2) || (!isInteger(commands[1]))) {
          System.out.println(MessageEnModel.invalidCommand());
          return false;
        }
        return new GoModel(commands, gameM).execute();
      case "help":
        if (commands.length != 1) {
          System.out.println(MessageEnModel.invalidCommand());
          return false;
        }
        return new HelpModel(gameM).execute();
      case "look":
        if (
          (commands.length > 2) ||
          ((commands.length == 2) && (!isInteger(commands[1])))
        ) {
          System.out.println(MessageEnModel.invalidCommand());
          return false;
        }
        return new LookModel(commands, gameM).execute(false);
      case "attack":
        if (commands.length != 1) {
          System.out.println(MessageEnModel.invalidCommand());
          return false;
        }
        return new AttackModel(commands, gameM).execute();
      case "take":
        if (
          (commands.length < 2) ||
          (commands.length > 3) ||
          (!isInteger(commands[1])) ||
          ((commands.length == 3) && (!isInteger(commands[2])))
        ) {
          System.out.println(MessageEnModel.invalidCommand());
          return false;
        }
        return new TakeModel(commands, gameM).execute();
      case "use":
        if (
          (commands.length < 2) ||
          (commands.length > 3) ||
          (!isInteger(commands[1])) ||
          ((commands.length == 3) && (!isInteger(commands[2])))
        ) {
          System.out.println(MessageEnModel.invalidCommand());
          return false;
        }
        return new UseModel(commands, gameM).execute();
      case "quit":
        if (commands.length != 1) {
          System.out.println(MessageEnModel.invalidCommand());
          return false;
        }
        return new QuitModel(commands, gameM).execute();
      case "drop":
        if ((commands.length != 2) || (!isInteger(commands[1]))) {
          System.out.println(MessageEnModel.invalidCommand());
          return false;
        }
        return new DropModel(commands, gameM).execute();
      case "equip":
        if (
          (commands.length != 3) ||
          (!isInteger(commands[1])) ||
          (!isInteger(commands[2]))
        ) {
          System.out.println(MessageEnModel.invalidCommand());
          return false;
        }
        return new EquipModel(commands, gameM).execute();
      case "pause":
        if (commands.length != 1) {
          System.out.println(MessageEnModel.invalidCommand());
          return false;
        }
        return new PauseModel(commands, gameM).execute();
      default:
        System.out.println(MessageEnModel.invalidCommand());
        return false;
    }
  }
}
