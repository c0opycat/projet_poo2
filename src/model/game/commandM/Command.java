package model.game.commandM;

import java.util.Scanner;

import model.game.*;
import model.game.commandM.interractM.*;
import model.game.commandM.itemComM.Equip;
import model.game.commandM.itemComM.Use;
import model.game.commandM.menuCommandM.Help;
import model.game.commandM.menuCommandM.Quit;

public class Command {
    protected String[] commands;
    protected GameM gameM;
    protected Scanner scan;

    public Command() {}

    public Command(String command, GameM gameM, Scanner scan) {
        this.commands = (command.toLowerCase()).split(" ");
        this.gameM = gameM;
        this.scan = scan;
    }

    public String[] getCommands()
    {
        return this.commands;
    }

    public static boolean confirmation(Scanner scan){
        String letter;
        
        letter = scan.next().toLowerCase().trim();

        return letter.equals("y");
    }

    private static boolean isInteger(String s)
    {
        try
        {
            Integer.parseInt(s);
        }
        catch(NumberFormatException e)
        {
            return false;
        }

        return true;
    }

    public boolean execute(){
        if (commands.length == 0) {
            return false;
        }
        switch(commands[0]){
            case "go":
                if (commands.length == 1) {
                    new Go(commands, gameM).help();
                    return false;
                } else if ((commands.length != 2) || (!isInteger(commands[1]))) {
                    System.out.println(MessageM.invalidCommand());
                    return false;
                }
                return new Go(commands, gameM).execute();
            case "help":
                if (commands.length != 1) {
                    System.out.println(MessageM.invalidCommand());
                    return false;
                }
                return new Help(gameM).execute();
            case "look":
                if ((commands.length > 2) || ((commands.length == 2) && (!isInteger(commands[1])))) {
                    System.out.println(MessageM.invalidCommand());
                    return false;
                }
                return new Look(commands, gameM).execute(false);
            case "attack":
                if (commands.length != 1) {
                    System.out.println(MessageM.invalidCommand());
                    return false;
                }
                return new Attack(commands, gameM).execute();
            case "take":
                if ((commands.length < 2) || (commands.length > 3) || (!isInteger(commands[1])) || ((commands.length == 3) && (!isInteger(commands[2])))) {
                    System.out.println(MessageM.invalidCommand());
                    return false;
                }
                return new Take(commands, gameM).execute();
            case "use":
                if ((commands.length < 2) || (commands.length > 3) || (!isInteger(commands[1])) || ((commands.length == 3) && (!isInteger(commands[2])))) {
                    System.out.println(MessageM.invalidCommand());
                    return false;
                }
                return new Use(commands, gameM).execute();
            case "quit":
                if (commands.length != 1) {
                    System.out.println(MessageM.invalidCommand());
                    return false;
                }
                return new Quit(commands, gameM).execute();
            case "drop":
                if ((commands.length != 2) || (!isInteger(commands[1]))) {
                    System.out.println(MessageM.invalidCommand());
                    return false;
                }
                return new Drop(commands, gameM).execute();
            case "equip":
                if((commands.length != 3) || (!isInteger(commands[1])) || (!isInteger(commands[2])))
                {
                    System.out.println(MessageM.invalidCommand());
                    return false;
                }
                return new Equip(commands, gameM).execute();
            default:
                System.out.println(MessageM.invalidCommand());
                return false;
        }
    }
}
