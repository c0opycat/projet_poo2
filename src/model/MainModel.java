package model;
import java.util.Objects;
import java.util.Scanner;

import model.modelGame.*;
import model.modelGame.modelCommand.*;
import model.modelCharacter.modelMonster.*;


/**
 * Entry point of the game.
 * <p>Handles the game loop, which manages player commands, monsters' actions, and game state transitions.</p>
 * <p>The game loop ends when the player dies or escapes successfully.</p>
 */
public class MainModel {

    /**
     * Main method that starts the game.
     * <p>Initializes the game, starts the main loop, and processes player commands.
     * Monsters will automatically attack the player when applicable, and the game
     * ends when the player reaches the final location or dies.</p>
     * @param args Command-line arguments (unused)
     */
    public static void main(String[] args) {

        GameModel gameM = new GameModel();
        boolean theend = false;
        
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\n");
        CommandModel cmd;
        
        String nameCurLoc = gameM.getCurLocation().getName();
        MonsterModel curMonster;
        gameM.start();

        // Main game loop
        while(!theend)
        {
            // If the player changes location
            if(nameCurLoc != gameM.getCurLocation().getName())
            {
                curMonster = gameM.getCurLocation().getMonster();

                // If the new location has a Dried monster, it attacks immediately
                if(curMonster instanceof DriedModel)
                {
                    curMonster.attack(gameM.getHero());
                    System.out.println(MessageEnModel.herosHP(gameM.getHero()));
                }
                
                nameCurLoc = gameM.getCurLocation().getName();
            }
            else
            {
                // Same location, check for existing monster and KO status
                gameM.getCurLocation().removeMonsterIfKO(gameM);
                curMonster = gameM.getCurLocation().getMonster();

                if(curMonster != null)
                {
                    curMonster.attack(gameM.getHero());
                    System.out.println(MessageEnModel.herosHP(gameM.getHero()));
                }
            }
            
            theend = gameM.isEnd();

            if(!theend){
                cmd = null;

                // Read player input until a valid command is executed (excluding help/look)
                while (cmd == null || !cmd.execute() || Objects.equals(cmd.getCommands()[0], "help") || Objects.equals(cmd.getCommands()[0], "look")) {
                    System.out.print("> ");

                    String s = scan.next().trim();

                    cmd = new CommandModel(s, gameM, scan);
                }
            }
        }

        // Cleanup and end of game
        scan.close();
        gameM.displayEnd();
    }
}
