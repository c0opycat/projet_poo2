package model.item.container;

import model.game.*;
import model.item.Crowbar;
import model.item.Item;
import model.character.heros.HeroM;

public class Crate extends Container {
    public final static int DEFAULT_MAX_CAPACITY = 45;
    public final static int DEFAULT_MAX_NB_ITEMS = 5;
    public final static int DEFAULT_WEIGHT = 10;

    public boolean open;

    public Crate(){
        super(DEFAULT_MAX_NB_ITEMS, DEFAULT_MAX_CAPACITY, DEFAULT_WEIGHT);
        this.open = false;
    }

    public Crate(int nb_items, int cap){
        super(DEFAULT_MAX_NB_ITEMS, cap, DEFAULT_WEIGHT);
        fillContainer(nb_items);
        this.open = false;
    }

    public boolean getOpen()
    {
        return this.open;
    }
    
    public boolean open(Crowbar c){
        boolean res = false;

        if(c != null)
        {
            Backpack bp = HeroM.gBackpack();
            boolean use = c.use(bp);

            if (use)
            {
                this.open = true;
                System.out.println(MessageM.itemUsed(this));
                res = true;
            }
            else
            {
                System.out.println(MessageM.failOpening());
            }
        }

        return res;
    }

    //Tries to add the item only if the crate is open
    @Override
    public boolean addItem(Item item)
    {
        boolean is_added = false;

        if(this.getOpen())
        {
            is_added = super.addItem(item);
        }

        return is_added;
    }

    //Removes the item only if the crate is open
    @Override
    public void removeItem(Item item)
    {
        if(this.getOpen())
        {
            super.removeItem(item);
        }
    }

    //Displays the crate's content only if it is open
    @Override
    public void displayContent(){
        if(this.getOpen())
        {
            displayContent();
        }
    }

    @Override
    public Item getNthItem(int i){
        if(this.getOpen())
        {
            return super.getNthItem(i);
        }
        else return null;
    }
}
