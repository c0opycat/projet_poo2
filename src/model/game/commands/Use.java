package model.game.commands;

import java.util.Scanner;
import model.game.Game;
import model.game.Message;
import model.item.Crowbar;
import model.item.Item;
import model.item.Protection;
import model.item.consumable.Consumable;
import model.item.weapon.Weapon;
import model.item.container.Crate;
import model.character.Hero;

public class Use extends Command {
    private int nb_arg;

    public Use(String[] cmd, Game game, Scanner scan) {
        this.game = game;
        this.commands = cmd;
        this.nb_arg = this.commands.length;

        this.scan = scan;
    }


    public boolean execute(){
        boolean res = false;
        int bpSize = Hero.gBackpack().getNbItems();
        int ind = Integer.parseInt(commands[1]);

        if(ind < 0 || ind >= bpSize)
        {
            System.out.println(Message.InvalidNumber(ind));
        }
        else
        {
            Item toUse = Hero.gBackpack().getNthItem(ind);
            
            if(toUse instanceof Weapon || toUse instanceof Protection)
            {
                System.out.println(Message.cantUseItem(toUse));
            }
            else
            {
                if(toUse instanceof Crowbar)
                {
                    if(nb_arg == 2)
                    {
                        System.out.println();
                    }
                    else
                    {
                        int ind2 = Integer.parseInt(commands[2]);
                        Item arg2 = this.game.getCurLocation().itemList.get(ind2);
                        if(!(arg2 instanceof Crate))
                        {
                            System.out.println(Message.cantUseItem(toUse));
                        }
                        else
                        {
                            Crate crate = (Crate)arg2;
                            Crowbar c = (Crowbar)toUse;

                            if(crate.open(c, scan))
                            {
                                res = true;
                                System.out.println(Message.commandOnItem("used ",toUse));
                            }      
                        }
                    }
                }
                else
                {
                    Consumable cs = (Consumable)toUse;

                    cs.consume(this.game.getHero());

                    res = true;
                }
            }
        }
        
        return res;
    }
}
