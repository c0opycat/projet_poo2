package game.commands;

import java.util.Scanner;

import game.*;

public class Command {
    protected String[] commands;
    protected Game game;
    protected Scanner scan;

    public Command() {}

    public Command(String command, Game game, Scanner scan) {
        this.commands = (command.toLowerCase()).split(" ");
        this.game = game;
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

    public boolean execute(){
        if (commands.length == 0) {
            return false;
        }
        switch(commands[0]){
            case "go":
                if (commands.length == 1) {
                    new Go(commands, game, scan).help();
                    return false;
                } else if (commands.length != 2) {
                    System.out.println(Message.invalidCommand());
                    return false;
                }
                return new Go(commands, game, scan).execute();
            case "help":
                if (commands.length != 1) {
                    System.out.println(Message.invalidCommand());
                    return false;
                }
                return new Help(game).execute();
            case "look":
                if (commands.length > 2) {
                    System.out.println(Message.invalidCommand());
                    return false;
                }
                return new Look(commands, game).execute(false);
            case "attack":
                if (commands.length != 1) {
                    System.out.println(Message.invalidCommand());
                    return false;
                }
                return new Attack(commands, game).execute();
            case "take":
                if (commands.length < 2 || commands.length > 3) {
                    System.out.println(Message.invalidCommand());
                    return false;
                }
                return new Take(commands, game).execute();
            case "use":
                if (commands.length < 2 || commands.length > 3) {
                    System.out.println(Message.invalidCommand());
                    return false;
                }
                return new Use(commands, game, scan).execute();
            case "quit":
                if (commands.length != 1) {
                    System.out.println(Message.invalidCommand());
                    return false;
                }
                return new Quit(commands, game).execute();
            case "drop":
                if (commands.length > 2) {
                    System.out.println(Message.invalidCommand());
                    return false;
                }
                return new Drop(commands, game).execute();
            case "equip":
                if(commands.length != 3)
                {
                    System.out.println(Message.invalidCommand());
                    return false;
                }
                return new Equip(commands, game).execute();
            default:
                System.out.println(Message.invalidCommand());
                return false;
        }
    }
}
