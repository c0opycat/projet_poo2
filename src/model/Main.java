package model;
import java.util.Objects;
import java.util.Scanner;

import model.game.*;
import model.game.commandM.*;
import model.location.*;
import model.character.monster.*;


//A changer en tant que partie
public class Main {
    public static void main(String[] args) {

        GameM gameM = new GameM();
        boolean theend = false;
        
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\n");
        Command cmd;
        
        String nameCurLoc = gameM.getCurLocation().getName();
        Monster curMonster;
        gameM.start();

        while(!theend)
        {
            if(nameCurLoc != gameM.getCurLocation().getName())
            {
                curMonster = gameM.getCurLocation().getMonster();
                
                if(curMonster instanceof DriedM)
                {
                    curMonster.attack(gameM.getHero());
                    System.out.println(MessageM.herosHP(gameM.getHero()));
                }
                
                nameCurLoc = gameM.getCurLocation().getName();
            }
            else
            {
                gameM.getCurLocation().removeMonsterIfKO();
                curMonster = gameM.getCurLocation().getMonster();

                if(curMonster != null)
                {
                    curMonster.attack(gameM.getHero());
                    System.out.println(MessageM.herosHP(gameM.getHero()));
                }
            }
            
            theend = gameM.isEnd();

            if(!theend){
                cmd = null;
                while (cmd == null || !cmd.execute() || Objects.equals(cmd.getCommands()[0], "help") || Objects.equals(cmd.getCommands()[0], "look")) {
                    System.out.print("> ");

                    String s = scan.next().trim();

                    cmd = new Command(s, gameM, scan);
                }
            }
        }
        
        scan.close();

        gameM.displayEnd();
    }
}
