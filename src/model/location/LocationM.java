package model.location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import model.character.monster.Monster;
import model.item.*;
import model.game.GameMapM;
import model.game.MessageM;

public class LocationM {
    public int width;
    public int height;
    public String name;
    public Step[][] locMap;
    public Map<Integer, Exit> exits;
    public ArrayList<Item> itemList;
    public String description;
    public Monster monster;

    
    public LocationM(String name){
        this.exits = new HashMap<>();
        this.name = name;
        //description will not be displayed but will keep being there as help for audio-description for
        //people with sight disabilities
        //this.description = MessageM.locationDescription(this);
        this.itemList = new ArrayList<>();
        //this.structure  = new matrix (height, width)
        boolean hasContainer = false;
        
        Item item;

        for (int i = 0; i < 3; i++)
        {
            if(hasContainer)
            {
                item = Item.NonContainerRI();
            }
            else
            {
                item = Item.randomItem();
                if(Item.isContainer(item))
                {
                    hasContainer = true;
                }
            }

            this.addItem(item);
        }

        Random r = new Random();

        int hasMonster = r.nextInt(2);

        if(hasMonster == 0)
        {
            this.monster = null;
        }
        else
        {
            this.monster = Monster.randMonster();
        }
    }

    //Adds the exit associated with key to the location
    public void addExit(Exit e, Integer key)
    {
        //Adds the exit only if it isn't already there and if the key isn't already used
        if(e.start == this && !this.exits.containsKey(key))
        {
            this.exits.put(key, e);
        }
    }

    //Set all the exits of a location
    public void setExits(Exit[] exits)
    {
        for(int i = 0; i < exits.length; i++)
        {
            this.addExit(exits[i], i);
        }
    }

    //Returns a list of the exits of the location
    public ArrayList<Exit> getExits()
    {
        ArrayList<Exit> res = new ArrayList<>();

        for(int i = 0; i < this.exits.size(); i++)
        {
            res.add(this.exits.get(i));
        }

        return res;
    }
    
    //Returns the monster from the location
    public Monster getMonster(){
        return this.monster;
    }

    public void removeMonsterIfKO()
    {
        if(this.monster != null)
        {
            if(this.monster.isKO())
            {
                this.monster = null;
            }
        } 
    }

    public void setMonster(Monster m)
    {
        this.monster = m;
    }

    //Removes the item from the location
    public void removeItem(Item item)
    {
        this.itemList.remove(item);
    }

    //Adds the item from the location
    public void addItem(Item item)
    {
        this.itemList.add(item);
    }

    //Displays the description of the location
    public void displayLocation()
    {
        System.out.println(MessageM.locationDescription(this));
    }

    //Getter for Location Name
    public LocationName getName(){
        return this.name;
    }

    public String toString(){
        return switch (name) {
            case "BEAULIEU" -> "Beaulieu ";
            case "MILETRIE" -> "Milétrie";
            case "CITY_CENTER" -> "the city center ";
            case "COURONNERIES" -> "the Couronneries ";
            case "GIBAUDERIE" -> "the Gibauderie ";
            case "WEST_POITIERS" -> "west Poitiers ";
            case "SOUTH_POITIERS" -> "south Poitiers ";
            case "PONT_NEUF" -> "the Pont neuf ";
            case "SAINT_ELOI" -> "Saint Éloi ";
            case "TROIS_CITES" -> "the trois cités ";
            case "NORTH_POITIERS" -> "north Poitiers ";
            case "MONTBERNAGE" -> "Montbernage ";
            case "FINAL_EXIT" -> "the limits of the city ";
        };
    }

    public static void northPoitiers(LocationM location)
    {
        Exit e1 = new Exit(location, GameMapM.locations.get(5));
        Exit[] exits = {e1};
        location.setExits(exits);
    }

    public static void beaulieu(LocationM location)
    {
        Exit e1 = new Exit(location, GameMapM.locations.get(7));
        Exit e2 = new Exit(location, GameMapM.locations.get(8));
        Exit e3 = new Exit(location, GameMapM.locations.get(10));
        Exit e4 = new Exit(location, GameMapM.locations.get(11));
        Exit[] exits = {e1, e2, e3, e4};
        location.setExits(exits);
    }

    public static void cityCenter(LocationM location)
    {
        Exit e1 = new Exit(location, GameMapM.locations.get(5));
        Exit e2 = new Exit(location, GameMapM.locations.get(6));
        Exit e3 = new Exit(location, GameMapM.locations.get(7));
        Exit[] exits = {e1, e2, e3};
        location.setExits(exits);
    }

    public static void couronneries(LocationM location)
    {
        Exit e1 = new Exit(location, GameMapM.locations.get(10));
        Exit e2 = new Exit(location, GameMapM.locations.get(5));
        Exit e3 = new Exit(location, GameMapM.locations.get(8));
        Exit[] exits = {e1, e2, e3};
        location.setExits(exits);
    }

    public static void gibauderie(LocationM location)
    {
        Exit e1 = new Exit(location, GameMapM.locations.get(11));
        Exit e2 = new Exit(location, GameMapM.locations.get(1));
        Exit e3 = new Exit(location, GameMapM.locations.get(7));
        Exit[] exits = {e1, e2, e3};
        location.setExits(exits);
    }

    public static void westPoitiers(LocationM location)
    {
        Exit e1 = new Exit(location, GameMapM.locations.get(0));
        Exit e2 = new Exit(location, GameMapM.locations.get(2));
        Exit e3 = new Exit(location, GameMapM.locations.get(6));
        Exit[] exits = {e1, e2, e3};
        location.setExits(exits);
    }

    public static void southPoitiers(LocationM location)
    {
        Exit e1 = new Exit(location, GameMapM.locations.get(5));
        Exit e2 = new Exit(location, GameMapM.locations.get(9));
        Exit[] exits = {e1, e2};
        location.setExits(exits);
    }

    public static void pontNeuf(LocationM location)
    {
        Exit e1 = new Exit(location, GameMapM.locations.get(9));
        Exit e2 = new Exit(location, GameMapM.locations.get(4));
        Exit e3 = new Exit(location, GameMapM.locations.get(10));
        Exit[] exits = {e1, e2, e3};
        location.setExits(exits);
    }

    public static void saintEloi(LocationM location)
    {
        Exit e1 = new Exit(location, GameMapM.locations.get(3));
        Exit e2 = new Exit(location, GameMapM.locations.get(10));
        Exit e3 = new Exit(location, GameMapM.locations.get(1));
        Exit[] exits = {e1, e2, e3};
        location.setExits(exits);
    }

    public static void troisCites(LocationM location)
    {
        Exit e1 = new Exit(location, GameMapM.locations.get(2));
        Exit e2 = new Exit(location, GameMapM.locations.get(4));
        Exit[] exits = {e1, e2};
        location.setExits(exits);
    }

    public static void montbernage(LocationM location)
    {
        Exit e1 = new Exit(location, GameMapM.locations.get(2));
        Exit e2 = new Exit(location, GameMapM.locations.get(5));
        Exit e3 = new Exit(location, GameMapM.locations.get(7));
        Exit e4 = new Exit(location, GameMapM.locations.get(8));
        Exit[] exits = {e1, e2, e3, e4};
        location.setExits(exits);
    }

    public static void miletrie(LocationM location)
    {
        Exit e1 = new Exit(location, GameMapM.locations.get(4));
        Exit e2 = new Exit(location, GameMapM.locations.get(1));
        Exit e3 = new Exit(location, GameMapM.locations.get(12));
        Exit[] exits = {e1, e2, e3};
        location.setExits(exits);
    }

    public static void finalExit(LocationM location)
    {
        Exit e1 = new Exit(location, GameMapM.locations.get(11));
        Exit[] exits = {e1};
        location.setExits(exits);
    }
}

