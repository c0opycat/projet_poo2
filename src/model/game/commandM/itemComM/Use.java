package model.game.commandM.itemComM;

import model.game.GameM;
import model.game.MessageM;
import model.game.commandM.Command;
import model.item.Crowbar;
import model.item.Item;
import model.item.Protection;
import model.item.consumable.Consumable;
import model.item.weapon.Weapon;
import model.item.container.Crate;
import model.character.heros.HeroM;

public class Use extends Command {
    private int nb_arg;

    public Use(String[] cmd, GameM gameM) {
        this.gameM = gameM;
        this.commands = cmd;
        this.nb_arg = this.commands.length;

    }


    public boolean execute(){
        boolean res = false;
        int bpSize = HeroM.gBackpack().getNbItems();
        int ind = Integer.parseInt(commands[1]);

        if(ind < 0 || ind >= bpSize)
        {
            System.out.println(MessageM.InvalidNumber(ind));
        }
        else
        {
            Item toUse = HeroM.gBackpack().getNthItem(ind);
            
            if(toUse instanceof Weapon || toUse instanceof Protection)
            {
                System.out.println(MessageM.cantUseItem(toUse));
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
                        Item arg2 = this.gameM.getCurLocation().itemList.get(ind2);
                        if(!(arg2 instanceof Crate))
                        {
                            System.out.println(MessageM.cantUseItem(toUse));
                        }
                        else
                        {
                            Crate crate = (Crate)arg2;
                            Crowbar c = (Crowbar)toUse;

                            if(crate.open(c))
                            {
                                res = true;
                                System.out.println(MessageM.commandOnItem("used ",toUse));
                            }      
                        }
                    }
                }
                else
                {
                    Consumable cs = (Consumable)toUse;

                    cs.consume(this.gameM.getHero());

                    res = true;
                }
            }
        }
        
        return res;
    }
}
