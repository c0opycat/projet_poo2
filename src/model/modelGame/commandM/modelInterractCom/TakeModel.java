package model.modelGame.commandM.modelInterractCom;

import model.modelCharacter.modelHeros.HeroModel;
import model.modelGame.GameModel;
import model.modelGame.MessageEnModel;
import model.modelGame.commandM.CommandModel;
import model.modelItem.ItemModel;
import model.modelItem.ProtectionModel;
import model.modelItem.modelContainer.ContainerModel;
import model.modelItem.modelContainer.CrateModel;

public class TakeModel extends CommandModel {
    private int nb_arg; 

    public TakeModel(String[] cmd, GameModel gameM) {
        this.gameM = gameM;
        this.commands = cmd;
        this.nb_arg = this.commands.length;
    }

    public boolean execute(){
        boolean res = false;

        ItemModel toTake;
        int ind = Integer.parseInt(commands[1]);
        int locNbItems = this.gameM.getCurLocation().itemList.size();

        if(nb_arg == 2)
        {
            if(ind < 0 || ind >= locNbItems)
            {
                System.out.println(MessageEnModel.InvalidNumber(ind));
            }
            else
            {
                toTake = this.gameM.getCurLocation().itemList.get(ind);

                if((toTake instanceof ContainerModel) || (toTake instanceof ProtectionModel))
                {
                    System.out.println(MessageEnModel.wrongItem("take"));
                }
                else
                {
                    boolean taken = HeroModel.gBackpack().addItem(toTake);
                    
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
                System.out.println(MessageEnModel.InvalidNumber(indCont));
            }
            else
            {
                ItemModel toTakeFrom = this.gameM.getCurLocation().itemList.get(indCont);

                if(!(toTakeFrom instanceof ContainerModel))
                {
                    System.out.println(MessageEnModel.wrongItem("take something from"));
                }
                else
                {
                    if(toTakeFrom instanceof CrateModel)
                    {
                        CrateModel crate = (CrateModel)toTakeFrom;
                        if(!crate.open)
                        {
                            System.out.println(MessageEnModel.toolRequired());
                            return res;
                        }
                    }

                    ContainerModel container = (ContainerModel)toTakeFrom;
                    int contNbItems = container.getNbItems();
                    
                    if(ind < 0 || ind >= contNbItems)
                    {
                        System.out.println(MessageEnModel.InvalidNumber(ind));
                    }
                    else
                    {
                        toTake = container.getNthItem(ind);

                        boolean taken = HeroModel.gBackpack().addItem(toTake);
                    
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
