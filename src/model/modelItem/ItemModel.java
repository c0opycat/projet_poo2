package model.modelItem;

import java.util.Random;

import model.modelItem.modelContainer.*;
import model.modelItem.modelWeapon.*;
import model.modelItem.modelConsumable.*;
import model.modelItem.modelConsumable.modelFood.*;

public abstract class ItemModel
{
    public final int WEIGHT;
    

    public ItemModel(int weight)
    {
        this.WEIGHT = weight;
    }
    
    //Returns a random modelItem
    public static ItemModel randomItem(){
        Random rand = new Random();

        int i = rand.nextInt(12);

        ItemModel res;

        int fill = rand.nextInt(3) + 2; //To fill a modelContainer with 2 to 4 items
        int max_cap = rand.nextInt(21) + 20; //Capacity max of the modelContainer between 20 and 40 kg

        switch(i)
        {
            case 0:
            {
                if(max_cap >= 30)
                {
                    max_cap -= 10;
                }
                res = new BackpackModel(max_cap, fill);
                break;
            }
            case 1:
            {
                res = new BaseballBatModel();
                break;
            }
            case 2:
            {
                res = new CakeModel();
                break;
            }
            case 3:
            {
                res = new ChestModel(fill, max_cap);
                break;
            }
            case 4:
            {
                res = new CrateModel(fill, max_cap);
                break;
            }
            case 5:
            {
                res = new CrowbarModel();
                break;
            }
            case 6:
            {
                res = new FruitModel();
                break;
            }
            case 7:
            {
                res = new GunModel();
                break;
            }
            case 8:
            {
                res = new MeatModel();
                break;
            }
            case 9:
            {
                res = new MedicineModel();
                break;
            }
            case 10:
            {
                res = new ProtectionModel();
                break;
            }
            default:
            {
                res = new SwordModel();
                break;
            }
        }

        return res;
    }

    //returns a random ItemModel that is not a modelContainer
    public static ItemModel NonContainerRI(){
        Random rand = new Random();

        int i = rand.nextInt(9);

        ItemModel res;

        switch(i)
        {
            case 0:
            {
                res = new BaseballBatModel();
                break;
            }
            case 1:
            {
                res = new CakeModel();
                break;
            }
            case 2:
            {
                res = new CrowbarModel();
                break;
            }
            case 3:
            {
                res = new FruitModel();
                break;
            }
            case 4:
            {
                res = new GunModel();
                break;
            }
            case 5:
            {
                res = new MeatModel();
                break;
            }
            case 6:
            {
                res = new MedicineModel();
                break;
            }
            case 7:
            {
                res = new ProtectionModel();
                break;
            }
            default:
            {
                res = new SwordModel();
                break;
            }
        }
            return res;
    }
    
    //Returns the name of the modelItem's class
    @Override
    public String toString()
    {
        return this.getClass().getSimpleName() + " (" + this.WEIGHT + " kg)";
    }

    //Returns true if the modelItem is a modelContainer
    public static boolean isContainer(ItemModel it){
        return it instanceof ContainerModel;
    }
}
