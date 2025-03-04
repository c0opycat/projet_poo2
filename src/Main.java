import java.util.Objects;
import java.util.Scanner;

import model.game.*;
import model.game.commands.*;
import model.location.LocationName;
import model.character.monster.*;

public class Main {
    public static void main(String[] args) {

        Game game = new Game();
        boolean theend = false;
        
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\n");
        Command cmd;
        
        LocationName nameCurLoc = game.getCurLocation().getName();
        Monster curMonster;
        game.start();

        while(!theend)
        {
            if(nameCurLoc != game.getCurLocation().getName())
            {
                curMonster = game.getCurLocation().getMonster();
                
                if(curMonster instanceof Dried)
                {
                    curMonster.attack(game.getHero());
                    System.out.println(Message.herosHP(game.getHero()));
                }
                
                nameCurLoc = game.getCurLocation().getName();
            }
            else
            {
                game.getCurLocation().removeMonsterIfKO();
                curMonster = game.getCurLocation().getMonster();

                if(curMonster != null)
                {
                    curMonster.attack(game.getHero());
                    System.out.println(Message.herosHP(game.getHero()));
                }
            }
            
            theend = game.isEnd();

            if(!theend){
                cmd = null;
                while (cmd == null || !cmd.execute() || Objects.equals(cmd.getCommands()[0], "help") || Objects.equals(cmd.getCommands()[0], "look")) {
                    System.out.print("> ");

                    String s = scan.next().trim();

                    cmd = new Command(s, game, scan);
                }
            }
        }
        
        scan.close();

        game.displayEnd();
    }
}
