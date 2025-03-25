package model.game.commandM.itemComM;

import model.game.GameM;
import model.game.MessageM;
import model.game.commandM.Command;
import model.item.Item;
import model.item.container.*;
import model.location.LocationM;
import model.item.weapon.Weapon;
import model.item.Protection;
import model.character.heros.HeroM;

public class Equip extends Command {
    private int fstArg;
    private int scdArg;

    public Equip(String[] cmd, GameM gameM){
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
            LocationM loc = this.gameM.getCurLocation();
            int nbItems = 0;
            Item toEquip;

            if(scdArg == 1)
            {
                Backpack bp = HeroM.gBackpack();
                nbItems = bp.getNbItems();

                if(fstArg < 0 || fstArg >= nbItems)
                {
                    System.out.println(MessageM.InvalidNumber(fstArg));
                }
                else
                {
                    toEquip = bp.getNthItem(fstArg);

                    if(!(toEquip instanceof Weapon))
                    {
                        System.out.println(MessageM.wrongItem("equip"));
                    }
                    else
                    {
                        Weapon w = (Weapon)toEquip;
                        
                        Weapon herosWeapon = this.gameM.getHero().getWeapon();

                        if(herosWeapon != null)
                        {
                            boolean switched = bp.addItem(herosWeapon);
                            if(!switched)
                            {
                                loc.addItem(herosWeapon);
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
                    System.out.println(MessageM.InvalidNumber(fstArg));
                }
                else
                {
                    toEquip = loc.itemList.get(fstArg);
                    
                    if((toEquip instanceof Weapon) ||(toEquip instanceof Protection) || (toEquip instanceof Backpack))
                    {
                        if(toEquip instanceof Weapon)
                        {
                            Weapon w = (Weapon)toEquip;
                            Weapon herosWeapon = this.gameM.getHero().getWeapon();
                            
                            if(herosWeapon != null)
                            {
                                loc.addItem(herosWeapon);
                            }
                            
                            this.gameM.getHero().setWeapon(w);
                            loc.removeItem(w);

                            res = true;
                        }
                        else if(toEquip instanceof Protection)
                        {
                            Protection p = (Protection)toEquip;
                            Protection herosProtection = this.gameM.getHero().getShield();

                            if(herosProtection != null)
                            {
                                loc.addItem(herosProtection);
                            }

                            this.gameM.getHero().setShield(p);
                            loc.removeItem(p);
                            
                            res = true;
                        }
                        else
                        {
                            Backpack bp = (Backpack)toEquip;

                            this.gameM.getHero().switchBackpack(bp, loc);
                            
                            res = true;
                        }
                    }
                    else
                    {
                        System.out.println(MessageM.wrongItem("equip"));
                    }
                }
            }
            else
            {
                Item tmp = null;
                for(Item i : loc.itemList)
                {
                    if((i instanceof Chest) || (i instanceof Crate))
                    {
                        tmp = i;
                        break;
                    }
                }

                if(tmp == null)
                {
                    System.out.println("There is no chest or crate in this location");
                }
                else
                {
                    Container cont = (Container) tmp;

                    if(cont instanceof Crate)
                    {
                        Crate c = (Crate)cont;
                        if(!c.open)
                        {
                            System.out.println(MessageM.toolRequired());
                            return res;
                        }
                    }
                    else
                    {
                        nbItems = cont.getNbItems();

                        if(fstArg < 0 || fstArg >= nbItems)
                        {
                            System.out.println(MessageM.InvalidNumber(fstArg));
                        }
                        else
                        {
                            toEquip = cont.getNthItem(fstArg);

                            if((toEquip instanceof Weapon) || (toEquip instanceof Protection))
                            {
                                if(toEquip instanceof Weapon)
                                {
                                    Weapon w = (Weapon)toEquip;
                                    Weapon herosWeapon = this.gameM.getHero().getWeapon();

                                    if(herosWeapon != null)
                                    {
                                        boolean switched = cont.addItem(herosWeapon);
                                        if(!switched)
                                        {
                                            loc.addItem(herosWeapon);
                                        }
                                    }

                                    cont.removeItem(w);
                                    this.gameM.getHero().setWeapon(w);

                                }
                                else
                                {
                                    Protection p = (Protection)toEquip;
                                    Protection herosProtection = this.gameM.getHero().getShield();

                                    if(herosProtection != null)
                                    {
                                        boolean switched = cont.addItem(herosProtection);
                                        if(!switched)
                                        {
                                            loc.addItem(herosProtection);
                                        }
                                    }
                                    
                                    cont.removeItem(p);
                                    this.gameM.getHero().setShield(p);

                                }
                                res = true;
                            }
                            else
                            {
                                System.out.println(MessageM.wrongItem("equip"));
                            }
                        }
                    }
                }
            }
        }

        return res;
    }
}
