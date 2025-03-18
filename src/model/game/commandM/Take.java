package model.game.commandM;

import model.character.heros.HeroM;
import model.game.GameM;
import model.game.MessageM;
import model.item.Item;
import model.item.container.Container;
import model.item.Protection;
import model.item.container.Crate;

public class Take extends Command {
    private int nb_arg; 

    public Take(String[] cmd, GameM gameM) {
        this.gameM = gameM;
        this.commands = cmd;
        this.nb_arg = this.commands.length;
    }

    public boolean execute(){
        boolean res = false;

        Item toTake;
        int ind = Integer.parseInt(commands[1]);
        int locNbItems = this.gameM.getCurLocation().itemList.size();

        if(nb_arg == 2)
        {
            if(ind < 0 || ind >= locNbItems)
            {
                System.out.println(MessageM.InvalidNumber(ind));
            }
            else
            {
                toTake = this.gameM.getCurLocation().itemList.get(ind);

                if((toTake instanceof Container) || (toTake instanceof Protection))
                {
                    System.out.println(MessageM.wrongItem("take"));
                }
                else
                {
                    boolean taken = HeroM.gBackpack().addItem(toTake);
                    
                    if(taken)
                    {
                        this.gameM.getCurLocation().removeItem(toTake);
                        res = true;
                    }
                }
            }
        }
        else{
            int indCont = Integer.parseInt(commands[2]);

            if(indCont < 0 || indCont >= locNbItems)
            {
                System.out.println(MessageM.InvalidNumber(indCont));
            }
            else
            {
                Item toTakeFrom = this.gameM.getCurLocation().itemList.get(indCont);

                if(!(toTakeFrom instanceof Container))
                {
                    System.out.println(MessageM.wrongItem("take something from"));
                }
                else
                {
                    if(toTakeFrom instanceof Crate)
                    {
                        Crate crate = (Crate)toTakeFrom;
                        if(!crate.open)
                        {
                            System.out.println(MessageM.toolRequired());
                            return res;
                        }
                    }

                    Container container = (Container)toTakeFrom;
                    int contNbItems = container.getNbItems();
                    
                    if(ind < 0 || ind >= contNbItems)
                    {
                        System.out.println(MessageM.InvalidNumber(ind));
                    }
                    else
                    {
                        toTake = container.getNthItem(ind);

                        boolean taken = HeroM.gBackpack().addItem(toTake);
                    
                        if(taken)
                        {
                            container.removeItem(toTake);
                            res = true;
                        }
                    }
                }
            }
        }

        return res;
    }
}
