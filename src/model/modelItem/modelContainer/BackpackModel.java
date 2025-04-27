package model.modelItem.modelContainer;


import model.modelItem.ItemModel;

public class BackpackModel extends ContainerModel {
    
    public final static int DEFAULT_MAX_CAPACITY = 30;
    public final static int DEFAULT_MAX_NB_ITEMS = 6;
    public final static int DEFAULT_WEIGHT = 5;
    
    //Creates a backpack with random items
    //Parameters : the max capacity of the backpack and the number of items to put in it
    public BackpackModel(int max_cap, int fill){
        super(DEFAULT_MAX_NB_ITEMS, max_cap, DEFAULT_WEIGHT);
        fillContainer(fill);
    }

    @Override
    public boolean addItem(ItemModel item) {
        return super.addItem(item);
    }

    //Default constructor
    public BackpackModel(){
        super(DEFAULT_MAX_NB_ITEMS, DEFAULT_MAX_CAPACITY,DEFAULT_WEIGHT);
    }
}
