package model.modelLocation;

import model.modelItem.ItemModel;

public class StepModel {
    private ItemModel item;
    private ExitModel exit;
    private boolean free;
    public String terrain;
    private final String DEFAULT_TERRAIN = "flat";

    public StepModel() {
        this.exit = null;
        this.item = null;
        this.terrain = DEFAULT_TERRAIN;
        this.free = true;
    }
    public StepModel(Object object, String terrain) {
        this.terrain = terrain;
        setObject(object);
    }

    public StepModel(Object object) {
        this.terrain = DEFAULT_TERRAIN;
        setObject(object);
    }

    /**
     * Sets an object on the step if it's currently free.
     * Accepts either an ExitModel or an ItemModel. Otherwise, throws an exception.
     * @param object the object to place on this step (ExitModel or ItemModel)
     * @throws IllegalStateException    if the step is already used
     * @throws IllegalArgumentException if the object is neither an ExitModel nor an ItemModel
     */
    public void setObject(Object object) {
        if (!free) {
            System.out.println("StepModel is already used");
        }
        if (object instanceof ExitModel exit) {
            this.exit = exit;
            this.free = false;
        } else if (object instanceof ItemModel item) {
            this.item = item;
            this.free = false;
        } else {
            System.out.println("invalid object type");
        }
    }

    public void removeItem() {
        this.item = null;
        this.free = true;
    }

    public ItemModel getItem(){
        return this.item;
    }

    public Object getObject() {
        if (!free) {
            if (this.exit != null) {
                return this.exit;
            }
            else if (this.item != null) {
                return this.item;
            }
        }
        return null;
    }
    public void setTerrain(String terrain){
        this.terrain = terrain;
    }
}
