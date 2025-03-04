package model.game.commands;

import model.game.Game;
import model.location.Exit;

import java.util.ArrayList;
import java.util.Scanner;

import model.character.monster.Colossus;

public class Go extends Command {
    private ArrayList<Exit> exits;
    private int nbExits;
    private int arg;

    public Go(String[] cmd, Game game, Scanner scan) {
        this.commands = cmd;
        this.game = game;

        if (cmd.length > 1){
            this.arg = Integer.parseInt(cmd[1]);
        }
        
        this.exits = game.getCurLocation().getExits();
        this.nbExits = exits.size();

        this.scan = scan;
    }

    public boolean execute() {
        if (arg < 0 || arg >= nbExits){
            return false;
        }

        ArrayList<Exit> nextExits = this.exits.get(arg).destination.getExits();

        if (game.getCurLocation().getMonster() == null){

            boolean isOneWay = true;
            for(Exit e : nextExits)
            {
                if(e.destination == game.getCurLocation())
                {
                    
                    isOneWay = false;
                    break;
                }
            }
            
            if (isOneWay){
                System.out.println("This exit is one way only\n Are you sure you want to take this exit ? (y/n)\n");
                boolean yn = Command.confirmation(scan);
                if (yn){
                    this.game.setCurLocation(exits.get(arg).destination);
                    this.game.getCurLocation().displayLocation();
                    return new Look(null, game).execute(true);
                }
                else
                {
                    return false;
                }
            } 
            else{
                this.game.setCurLocation(exits.get(arg).destination);
                this.game.getCurLocation().displayLocation();
                return new Look(null, game).execute(true);
            }
        }
        else if(this.game.getCurLocation().getMonster().getClass() == Colossus.class)
        {
            System.out.println("There is a colossus !\nDo you want to flee ? (y/n)\n");
                if(!Command.confirmation(scan))
                {
                    return false;
                }
                else
                {
                    this.game.setCurLocation(exits.get(arg).destination);
                    this.game.getCurLocation().displayLocation();
                    return new Look(null, game).execute(true);
                }
        }
        else 
        {
            System.out.println("You have to kill all the monsters in the room before you can go to another room\n");
            return false;
        }
    }

    public void help(){
        System.out.println("You can go to :");
        for (int i = 0 ; i < nbExits ; i++) {
            System.out.println("    " + i + ": " + exits.get(i).destination);
        }
    }

}
