package model.modelGame.commandM.modelItemCom;

import model.modelGame.GameModel;
import model.modelGame.MessageEnModel;
import model.modelGame.commandM.CommandModel;
import model.modelItem.CrowbarModel;
import model.modelItem.ItemModel;
import model.modelItem.ProtectionModel;
import model.modelItem.modelConsumable.ConsumableModel;
import model.modelItem.modelContainer.CrateModel;
import model.modelItem.modelWeapon.WeaponModel;
import model.modelCharacter.modelHeros.HeroModel;

public class UseModel extends CommandModel {
    private int nb_arg;

    public UseModel(String[] cmd, GameModel gameM) {
        this.gameM = gameM;
        this.commands = cmd;
        this.nb_arg = this.commands.length;

    }


    public boolean execute(){
        boolean res = false;
        int bpSize = HeroModel.gBackpack().getNbItems();
        int ind = Integer.parseInt(commands[1]);

        if(ind < 0 || ind >= bpSize)
        {
            System.out.println(MessageEnModel.InvalidNumber(ind));
        }
        else
        {
            ItemModel toUse = HeroModel.gBackpack().getNthItem(ind);
            
            if(toUse instanceof WeaponModel || toUse instanceof ProtectionModel)
            {
                System.out.println(MessageEnModel.cantUseItem(toUse));
            }
            else
            {
                if(toUse instanceof CrowbarModel)
                {
                    if(nb_arg == 2)
                    {
                        System.out.println();
                    }
                    else
                    {
                        int ind2 = Integer.parseInt(commands[2]);
                        ItemModel arg2 = this.gameM.getCurLocation().itemList.get(ind2);
                        if(!(arg2 instanceof CrateModel))
                        {
                            System.out.println(MessageEnModel.cantUseItem(toUse));
                        }
                        else
                        {
                            CrateModel crate = (CrateModel)arg2;
                            CrowbarModel c = (CrowbarModel)toUse;

                            if(crate.open(c))
                            {
                                res = true;
                                System.out.println(MessageEnModel.commandOnItem("used ",toUse));
                            }      
                        }
                    }
                }
                else
                {
                    ConsumableModel cs = (ConsumableModel)toUse;

                    cs.consume(this.gameM.getHero());

                    res = true;
                }
            }
        }
        
        return res;
    }
}
