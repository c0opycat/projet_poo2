package model.game.commands;

import model.character.heros.Hero;
import model.game.Game;
import model.game.Message;
import model.item.Item;
import model.item.container.Container;
import model.item.Protection;
import model.item.container.Crate;

public class Take extends Command {
    private int nb_arg; 

    public Take(String[] cmd, Game game) {
        this.game = game;
        this.commands = cmd;
        this.nb_arg = this.commands.length;
    }

    public boolean execute(){
        boolean res = false;

        Item toTake;
        int ind = Integer.parseInt(commands[1]);
        int locNbItems = this.game.getCurLocation().itemList.size();

        if(nb_arg == 2)
        {
            if(ind < 0 || ind >= locNbItems)
            {
                System.out.println(Message.InvalidNumber(ind));
            }
            else
            {
                toTake = this.game.getCurLocation().itemList.get(ind);

                if((toTake instanceof Container) || (toTake instanceof Protection))
                {
                    System.out.println(Message.wrongItem("take"));
                }
                else
                {
                    boolean taken = Hero.gBackpack().addItem(toTake);
                    
                    if(taken)
                    {
                        this.game.getCurLocation().removeItem(toTake);
                        res = true;
                    }
                }
            }
        }
        else{
            int indCont = Integer.parseInt(commands[2]);

            if(indCont < 0 || indCont >= locNbItems)
            {
                System.out.println(Message.InvalidNumber(indCont));
            }
            else
            {
                Item toTakeFrom = this.game.getCurLocation().itemList.get(indCont);

                if(!(toTakeFrom instanceof Container))
                {
                    System.out.println(Message.wrongItem("take something from"));
                }
                else
                {
                    if(toTakeFrom instanceof Crate)
                    {
                        Crate crate = (Crate)toTakeFrom;
                        if(!crate.open)
                        {
                            System.out.println(Message.toolRequired());
                            return res;
                        }
                    }

                    Container container = (Container)toTakeFrom;
                    int contNbItems = container.getNbItems();
                    
                    if(ind < 0 || ind >= contNbItems)
                    {
                        System.out.println(Message.InvalidNumber(ind));
                    }
                    else
                    {
                        toTake = container.getNthItem(ind);

                        boolean taken = Hero.gBackpack().addItem(toTake);
                    
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
