package model.item.container;


public class Backpack extends Container{
    
    public final static int DEFAULT_MAX_CAPACITY = 30;
    public final static int DEFAULT_MAX_NB_ITEMS = 6;
    public final static int DEFAULT_WEIGHT = 5;
    
    //Creates a backpack with random items
    //Parameters : the max capacity of the backpack and the number of items to put in it
    public Backpack (int max_cap, int fill){
        super(DEFAULT_MAX_NB_ITEMS, max_cap, DEFAULT_WEIGHT);
        fillContainer(fill);
    }

    //Default constructor
    public Backpack(){
        super(DEFAULT_MAX_NB_ITEMS, DEFAULT_MAX_CAPACITY,DEFAULT_WEIGHT);
    }
}
