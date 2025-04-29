package model;
import java.util.Objects;
import java.util.Scanner;

import model.modelGame.*;
import model.modelGame.modelCommand.*;
import model.modelCharacter.modelMonster.*;


//A changer en tant que partie
public class MainModel {
    public static void main(String[] args) {

        GameModel gameM = new GameModel();
        boolean theend = false;
        
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\n");
        CommandModel cmd;
        
        String nameCurLoc = gameM.getCurLocation().getName();
        MonsterModel curMonster;
        gameM.start();

        while(!theend)
        {
            if(nameCurLoc != gameM.getCurLocation().getName())
            {
                curMonster = gameM.getCurLocation().getMonster();
                
                if(curMonster instanceof DriedModel)
                {
                    curMonster.attack(gameM.getHero());
                    System.out.println(MessageEnModel.herosHP(gameM.getHero()));
                }
                
                nameCurLoc = gameM.getCurLocation().getName();
            }
            else
            {
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
                while (cmd == null || !cmd.execute() || Objects.equals(cmd.getCommands()[0], "help") || Objects.equals(cmd.getCommands()[0], "look")) {
                    System.out.print("> ");

                    String s = scan.next().trim();

                    cmd = new CommandModel(s, gameM, scan);
                }
            }
        }
        
        scan.close();

        gameM.displayEnd();
    }
}
