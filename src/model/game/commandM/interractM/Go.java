package model.game.commandM.interractM;

import model.game.GameM;
import model.game.commandM.Command;
import model.location.Exit;

import java.util.ArrayList;

import model.character.monster.ColossusM;

/**
 * Represents the "go" command in the game.
 * <p>
 * Allows the Hero to move from one location to another via an exit.
 * The command uses an argument to determine which exit to take.
 * Movement is blocked if a monster is still alive in the current location.
 */
public class Go extends Command {
    private ArrayList<Exit> exits;
    private int nbExits;
    private int arg;

    /**
     * Constructs a Go command with arguments and the current game state.
     * @param cmd   the command array (ex:{"go", "1"})
     * @param gameM the current game instance
     */
    public Go(String[] cmd, GameM gameM) {
        this.commands = cmd;
        this.gameM = gameM;

        if (cmd.length > 1){
            this.arg = Integer.parseInt(cmd[1]);
        }
        
        this.exits = gameM.getCurLocation().getExits();
        this.nbExits = exits.size();
    }

    /**
     * Executes the "go" command.
     * <p>
     * Moves the Hero to another location based on the selected exit.
     * The command checks if the chosen index is within bounds,
     * and whether a monster is still alive in the current location.
     * It also checks whether the chosen exit is a one-way path
     * and simulates confirmation logic (currently always true)
     * @return true if the Hero successfully moves to another location, false otherwise
     * @throws RuntimeException if the argument index is out of bounds
     */
    public boolean execute() {
        if (arg < 0 || arg >= nbExits){
            throw new RuntimeException("argument out of range");
        }

        ArrayList<Exit> nextExits = this.exits.get(arg).destination.getExits();
        //check for monsters before allowing exit
        if (gameM.getCurLocation().getMonster() == null){
            //removed "use one-way exit" confirmation
            boolean isOneWay = true;
            for(Exit e : nextExits) {
                if(e.destination == gameM.getCurLocation()) {
                    isOneWay = false;
                    break;
                }
            }
            if (isOneWay){
                boolean yn = true;
                if (yn){
                    // Move to new location
                    this.gameM.setCurLocation(exits.get(arg).destination);
                    this.gameM.getCurLocation().displayLocation();
                    return new Look(null, gameM).execute(true);
                }
                else {
                    return false;
                }
            } 
            else{
                // Move to new location
                this.gameM.setCurLocation(exits.get(arg).destination);
                this.gameM.getCurLocation().displayLocation();
                return new Look(null, gameM).execute(true);
            }
        }
        else 
        {
            System.out.println("You have to kill all the monsters in the room before you can go to another room\n");
            return false;
        }
    }

    /**
     * Displays help text showing available exits and their corresponding indices.
     */
    public void help(){
        System.out.println("You can go to :");
        for (int i = 0 ; i < nbExits ; i++) {
            System.out.println("    " + i + ": " + exits.get(i).destination);
        }
    }
}
