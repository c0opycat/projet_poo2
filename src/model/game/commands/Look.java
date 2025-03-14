package model.game.commands;

import model.game.Game;
import model.game.Message;
import model.item.Item;
import model.location.Exit;
import model.character.heros.Hero;
import model.item.container.Container;
import model.item.container.Crate;

import java.util.ArrayList;

public class Look extends Command {
    public Look(String[] cmd, Game game) {
        this.game = game;
        this.commands = cmd;
    }

    public Look(Game game) {
        this.game = game;
        this.commands = null;
    }

    public boolean execute(boolean enter) {
        if (!enter){
            ArrayList<Exit> exits = game.getCurLocation().getExits();
            int nbExits = exits.size();
            ArrayList<Item> items = game.getCurLocation().itemList;
            int nbItems = items.size();
            if (commands == null || commands.length == 1){
                // Locations
                System.out.println(Message.displayExitsInLoc());
                for (int i = 0 ; i < nbExits ; i++) {
                    System.out.println("    " + i + " to go to " + exits.get(i).destination);
                }

                // Items
                System.out.println(Message.displayItemsInLoc());
                for (int i = 0 ; i < nbItems ; i++) {
                    System.out.println("    " + i + " to select " + items.get(i));
                }
                System.out.println("    " + nbItems + " to look in your backpack");

                //Monsters
                if(game.getCurLocation().getMonster() != null)
                {
                    System.out.println("Monster : ");
                    System.out.println(game.getCurLocation().getMonster());
                }

                //Hero
                System.out.println(Message.heroDescription(game.getHero()));
                
                return false;
            } else {
                int arg = Integer.parseInt(commands[1]);
                if (arg >= 0 && arg < nbItems){
                    Item i = items.get(arg);
                    
                    if(Item.isContainer(i))
                    {

                        if(i instanceof Crate)
                        {   
                            Crate c = (Crate) i;
                            if(!c.getOpen())
                            {
                                System.out.println(Message.toolRequired());
                                return false;
                            }
                        }
                        
                        Container c = (Container)i;
                        c.displayContent();
                    }
                    else
                    {
                        System.out.println(items.get(arg));
                    }
                } else if (arg == nbItems){
                    Hero.gBackpack().displayContent();
                }
                else
                {
                    System.out.println(Message.InvalidNumber(arg));
                    return false;
                }
            }
        } else {
            //Monsters
            if(game.getCurLocation().getMonster() != null)
            {
                System.out.println(Message.monsterApparition(game.getCurLocation().getMonster()));
            }
        }
        return true;
    }
}
