package model.item;

import java.util.Random;

import model.item.container.*;
import model.item.weapon.*;
import model.item.consumable.*;
import model.item.consumable.food.*;

public abstract class Item
{
    public final int WEIGHT;
    

    public Item(int weight)
    {
        this.WEIGHT = weight;
    }
    
    //Returns a random item
    public static Item randomItem(){
        Random rand = new Random();

        int i = rand.nextInt(12);

        Item res;

        int fill = rand.nextInt(3) + 2; //To fill a container with 2 to 4 items
        int max_cap = rand.nextInt(21) + 20; //Capacity max of the container between 20 and 40 kg

        switch(i)
        {
            case 0:
            {
                if(max_cap >= 30)
                {
                    max_cap -= 10;
                }
                res = new Backpack(max_cap, fill);
                break;
            }
            case 1:
            {
                res = new BaseballBat();
                break;
            }
            case 2:
            {
                res = new Cake();
                break;
            }
            case 3:
            {
                res = new Chest(fill, max_cap);
                break;
            }
            case 4:
            {
                res = new Crate(fill, max_cap);
                break;
            }
            case 5:
            {
                res = new Crowbar();
                break;
            }
            case 6:
            {
                res = new Fruit();
                break;
            }
            case 7:
            {
                res = new Gun();
                break;
            }
            case 8:
            {
                res = new Meat();
                break;
            }
            case 9:
            {
                res = new Medicine();
                break;
            }
            case 10:
            {
                res = new Protection();
                break;
            }
            default:
            {
                res = new Sword();
                break;
            }
        }

        return res;
    }

    //returns a random Item that is not a container
    public static Item NonContainerRI(){
        Random rand = new Random();

        int i = rand.nextInt(9);

        Item res;

        switch(i)
        {
            case 0:
            {
                res = new BaseballBat();
                break;
            }
            case 1:
            {
                res = new Cake();
                break;
            }
            case 2:
            {
                res = new Crowbar();
                break;
            }
            case 3:
            {
                res = new Fruit();
                break;
            }
            case 4:
            {
                res = new Gun();
                break;
            }
            case 5:
            {
                res = new Meat();
                break;
            }
            case 6:
            {
                res = new Medicine();
                break;
            }
            case 7:
            {
                res = new Protection();
                break;
            }
            default:
            {
                res = new Sword();
                break;
            }
        }
            return res;
    }
    
    //Returns the name of the item's class
    @Override
    public String toString()
    {
        return this.getClass().getSimpleName() + " (" + this.WEIGHT + " kg)";
    }

    //Returns true if the item is a container
    public static boolean isContainer(Item it){
        return it instanceof Container;
    }
}
