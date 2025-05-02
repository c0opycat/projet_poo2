package model.modelGame.modelCommand;

import java.util.Scanner;
import model.modelGame.*;
import model.modelGame.modelCommand.modelInterractCom.*;
import model.modelGame.modelCommand.modelItemCom.EquipModel;
import model.modelGame.modelCommand.modelItemCom.UseModel;
import model.modelGame.modelCommand.modelMenuCom.HelpModel;
import model.modelGame.modelCommand.modelMenuCom.PauseModel;
import model.modelGame.modelCommand.modelMenuCom.QuitModel;

/**
 * The CommandModel class handles parsing and execution of player commands in the game.
 * <p>
 * It processes textual input commands, validates them, and executes corresponding actions
 * by delegating to specific command classes such as {@link GoModel}, {@link AttackModel}, {@link TakeModel}, etc.
 */
public class CommandModel {

  /** The parsed command arguments. */
  protected String[] commands;

  /** The game model to interact with. */
  protected GameModel gameM;

  /** Scanner used for additional user input if necessary. */
  protected Scanner scan;

  /**
   * Default constructor.
   */
  public CommandModel() {}

  /**
   * Constructs a new CommandModel instance with a command string, game model, and scanner.
   * @param command the raw command string input by the player
   * @param gameM the current game model
   * @param scan the scanner for user input
   */
  public CommandModel(String command, GameModel gameM, Scanner scan) {
    this.commands = (command.toLowerCase()).split(" ");
    this.gameM = gameM;
    this.scan = scan;
  }

  /**
   * Sets the game model.
   * @param gameModel the game model to set
   */
  public void setGameM(GameModel gameModel) {
    this.gameM = gameModel;
  }

  /**
   * Returns the parsed command arguments.
   * @return the command arguments as a String array
   */
  public String[] getCommands() {
    return this.commands;
  }

  /**
   * Asks for user confirmation (expects "y" to confirm).
   * @param scan the scanner to read user input
   * @return true if the user confirmed with "y", false otherwise
   */
  public static boolean confirmation(Scanner scan) {
    String letter;

    letter = scan.next().toLowerCase().trim();

    return letter.equals("y");
  }

  /**
   * Checks whether a string is a valid integer.
   * @param s the string to check
   * @return true if the string can be parsed as an integer, false otherwise
   */
  private static boolean isInteger(String s) {
    try {
      Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return false;
    }

    return true;
  }

  /**
   * Executes the command based on the parsed command arguments.
   * <p>
   * This method validates the command format and delegates execution to the corresponding command class.
   * @return true if the command was executed successfully, false otherwise
   */
  public boolean execute() {
    if (commands.length == 0) {
      return false;
    }
    if (gameM.isRunning){
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
    } else if (commands[0].equals("pause")) {
      if (commands.length != 1) {
        System.out.println(MessageEnModel.invalidCommand());
        return false;
      }
      return new PauseModel(commands, gameM).execute();
    } else{
      System.out.println("PAUSE");
      return false;
    }
  }
}
