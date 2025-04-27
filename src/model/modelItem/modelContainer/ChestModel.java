package model.modelItem.modelContainer;

public class ChestModel extends ContainerModel {
    public final static int DEFAULT_MAX_CAPACITY = 40;
    public final static int DEFAULT_MAX_NB_ITEMS = 4;
    public final static int DEFAULT_WEIGHT = 10;
    
    public ChestModel(){
        super(DEFAULT_MAX_NB_ITEMS, DEFAULT_MAX_CAPACITY, DEFAULT_WEIGHT);
    }

    public ChestModel(int nb_items, int cap){
        super(DEFAULT_MAX_NB_ITEMS, cap, DEFAULT_WEIGHT);
        fillContainer(nb_items);
    }

}
