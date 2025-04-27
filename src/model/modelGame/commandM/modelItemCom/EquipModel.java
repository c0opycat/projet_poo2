package model.modelGame.commandM.modelItemCom;

import model.modelGame.GameModel;
import model.modelGame.MessageEnModel;
import model.modelGame.commandM.CommandModel;
import model.modelItem.ItemModel;
import model.modelItem.ProtectionModel;
import model.modelItem.modelContainer.*;
import model.modelItem.modelWeapon.WeaponModel;
import model.modelLocation.LocationModel;
import model.modelCharacter.modelHeros.HeroModel;

public class EquipModel extends CommandModel {
    private int fstArg;
    private int scdArg;

    public EquipModel(String[] cmd, GameModel gameM){
        this.gameM = gameM;
        this.commands = cmd;
        this.fstArg = Integer.parseInt(commands[1]);
        this.scdArg = Integer.parseInt(commands[2]);
    }

    @Override
    public String toString() {
        return "equip anything";
    }
    public boolean execute()
    {
        boolean res = false;

        if(scdArg < 1 || scdArg > 4)
        {
            System.out.println("The second number : " + scdArg + "is invalid.");
        }
        else
        {
            LocationModel loc = this.gameM.getCurLocation();
            int nbItems = 0;
            ItemModel toEquip;

            if(scdArg == 1)
            {
                BackpackModel bp = HeroModel.gBackpack();
                nbItems = bp.getNbItems();

                if(fstArg < 0 || fstArg >= nbItems)
                {
                    System.out.println(MessageEnModel.InvalidNumber(fstArg));
                }
                else
                {
                    toEquip = bp.getNthItem(fstArg);

                    if(!(toEquip instanceof WeaponModel))
                    {
                        System.out.println(MessageEnModel.wrongItem("equip"));
                    }
                    else
                    {
                        WeaponModel w = (WeaponModel)toEquip;
                        
                        WeaponModel herosWeapon = this.gameM.getHero().getWeapon();

                        if(herosWeapon != null)
                        {
                            boolean switched = bp.addItem(herosWeapon);
                            if(!switched)
                            {
                                loc.addItem(herosWeapon,loc.getRandomFreeStepCoord());
                            }
                        }
                        
                        bp.removeItem(w);
                        this.gameM.getHero().setWeapon(w);
                        
                        res = true;

                    }
                }

            }
            else if(scdArg == 2)
            {
                nbItems = loc.itemList.size();

                if(fstArg < 0 || fstArg >= nbItems)
                {
                    System.out.println(MessageEnModel.InvalidNumber(fstArg));
                }
                else
                {
                    toEquip = loc.itemList.get(fstArg);
                    
                    if((toEquip instanceof WeaponModel) ||(toEquip instanceof ProtectionModel) || (toEquip instanceof BackpackModel))
                    {
                        if(toEquip instanceof WeaponModel)
                        {
                            WeaponModel w = (WeaponModel)toEquip;
                            WeaponModel herosWeapon = this.gameM.getHero().getWeapon();
                            
                            if(herosWeapon != null)
                            {
                                loc.addItem(herosWeapon, loc.getRandomFreeStepCoord());
                            }
                            
                            this.gameM.getHero().setWeapon(w);
                            loc.removeItem(w);

                            res = true;
                        }
                        else if(toEquip instanceof ProtectionModel)
                        {
                            ProtectionModel p = (ProtectionModel)toEquip;
                            ProtectionModel herosProtection = this.gameM.getHero().getShield();

                            if(herosProtection != null)
                            {
                                loc.addItem(herosProtection, loc.getRandomFreeStepCoord());
                            }

                            this.gameM.getHero().setShield(p);
                            loc.removeItem(p);
                            
                            res = true;
                        }
                        else
                        {
                            BackpackModel bp = (BackpackModel)toEquip;

                            this.gameM.getHero().switchBackpack(bp, loc);
                            
                            res = true;
                        }
                    }
                    else
                    {
                        System.out.println(MessageEnModel.wrongItem("equip"));
                    }
                }
            }
            else
            {
                ItemModel tmp = null;
                for(ItemModel i : loc.itemList)
                {
                    if((i instanceof ChestModel) || (i instanceof CrateModel))
                    {
                        tmp = i;
                        break;
                    }
                }

                if(tmp == null)
                {
                    System.out.println("There is no chest or crate in this modelLocation");
                }
                else
                {
                    ContainerModel cont = (ContainerModel) tmp;

                    if(cont instanceof CrateModel)
                    {
                        CrateModel c = (CrateModel)cont;
                        if(!c.open)
                        {
                            System.out.println(MessageEnModel.toolRequired());
                            return res;
                        }
                    }
                    else
                    {
                        nbItems = cont.getNbItems();

                        if(fstArg < 0 || fstArg >= nbItems)
                        {
                            System.out.println(MessageEnModel.InvalidNumber(fstArg));
                        }
                        else
                        {
                            toEquip = cont.getNthItem(fstArg);

                            if((toEquip instanceof WeaponModel) || (toEquip instanceof ProtectionModel))
                            {
                                if(toEquip instanceof WeaponModel)
                                {
                                    WeaponModel w = (WeaponModel)toEquip;
                                    WeaponModel herosWeapon = this.gameM.getHero().getWeapon();

                                    if(herosWeapon != null)
                                    {
                                        boolean switched = cont.addItem(herosWeapon);
                                        if(!switched)
                                        {
                                            loc.addItem(herosWeapon, loc.getRandomFreeStepCoord());
                                        }
                                    }

                                    cont.removeItem(w);
                                    this.gameM.getHero().setWeapon(w);

                                }
                                else
                                {
                                    ProtectionModel p = (ProtectionModel)toEquip;
                                    ProtectionModel herosProtection = this.gameM.getHero().getShield();

                                    if(herosProtection != null)
                                    {
                                        boolean switched = cont.addItem(herosProtection);
                                        if(!switched)
                                        {
                                            loc.addItem(herosProtection, loc.getRandomFreeStepCoord());
                                        }
                                    }
                                    
                                    cont.removeItem(p);
                                    this.gameM.getHero().setShield(p);

                                }
                                res = true;
                            }
                            else
                            {
                                System.out.println(MessageEnModel.wrongItem("equip"));
                            }
                        }
                    }
                }
            }
        }

        return res;
    }
}
