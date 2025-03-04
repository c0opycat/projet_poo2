package model.item.container;

import java.util.ArrayList;
import model.item.Item;
import model.game.Message;

public abstract class Container extends Item{
    public final int MAX_NB_ITEMS;
    public final int MAX_CAPACITY;
    private int nb_items;
    private int capacity;
    public ArrayList<Item> itemList;

    protected Container(int nb_items, int cap, int weight)
    {
        super(weight);
        this.MAX_NB_ITEMS = nb_items;
        this.MAX_CAPACITY = cap;
        this.capacity = 0;
        this.nb_items = 0;
        this.itemList = new ArrayList<>(nb_items);
    }

    //Fills a container with a chosen max amount of items as the parameter
    public void fillContainer(int nb){
        if (nb + this.nb_items > this.MAX_NB_ITEMS)
        {System.out.println(Message.contFull(this));}
        Item item;
        for (int i = 0; i < nb ; i++)
        {
            item = Item.NonContainerRI();

             if(this.capacity + item.WEIGHT <= this.MAX_CAPACITY)
            {
                this.addItem(item);
            }
        }
    }

    //Returns the container's capacity
    public int getCapacity()
    {
        return this.capacity;
    }

    //Returns the container's number of items
    public int getNbItems()
    {
        return this.nb_items;
    }

    //Set the capacity
    public void setCapacity(int cap)
    {
        if(cap <= this.MAX_CAPACITY){
            this.capacity = cap;
        }
    }
    
    //Set the number of items
    public void setNbItems(int nb_items)
    {
        if(nb_items <= this.MAX_NB_ITEMS) {
            this.nb_items = nb_items;
        }
    }

    //Returns true if the number of items in the backpack is the max
    public boolean isFull()
    {
        return (this.getNbItems() == this.MAX_NB_ITEMS);
    }

    //Displays each item contained in the container
    public void displayContent(){
        System.out.print(this);
        System.out.println(" : ");

        int ind = 0;
        for (Item i : itemList)
        {
            System.out.println("    " + ind + ". " + i);
            ind++;
        }
    }

    //Returns the item in the i position of the list
    public Item getNthItem(int i){
        if (this.itemList.size() == 0)
        {return null;}
        else return this.itemList.get(i);
    }

    //Remove the item from the container
    public void removeItem(Item item)
    {
        if(this.itemList.contains(item)){
            this.itemList.remove(item);
            this.setCapacity(this.getCapacity() - item.WEIGHT);
            this.setNbItems(this.getNbItems() - 1);
        }
    }

    //Returns true if the item is too heavy to be put in the container
    public boolean tooHeavy(Item i){
        return ((this.getCapacity())+(i.WEIGHT) > this.MAX_CAPACITY);
    }

    //Add the item only if there is space for it
    //Returns true if the item has been added
    public boolean addItem(Item item)
    {
        boolean is_added = false;

        if(item != null)
        {
            if(this.tooHeavy(item))
            {
                System.out.println(Message.noSpace(this, item));
            }
            else if(this.isFull())
            {
                System.out.println(Message.contFull(this));
            }
            else{
                this.itemList.add(item);
                this.setCapacity(this.getCapacity() + item.WEIGHT);
                this.setNbItems(this.getNbItems() + 1);

                is_added = true;
            }
        }

        return is_added;
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName();
    }
}