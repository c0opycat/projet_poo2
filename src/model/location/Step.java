package model.location;

import model.item.Item;

public class Step {
    private Item item;
    private Exit exit;
    private boolean free;
    public String terrain;
    private final String DEFAULT_TERRAIN = "flat";

    public Step() {
        this.exit = null;
        this.item = null;
        this.terrain = DEFAULT_TERRAIN;
        this.free = true;
    }
    public Step(Object object, String terrain) {
        this.terrain = terrain;
        setObject(object);
    }

    public Step(Object object) {
        this.terrain = DEFAULT_TERRAIN;
        setObject(object);
    }

    /**
     * Sets an object on the step if it's currently free.
     * Accepts either an Exit or an Item. Otherwise, throws an exception.
     * @param object the object to place on this step (Exit or Item)
     * @throws IllegalStateException    if the step is already used
     * @throws IllegalArgumentException if the object is neither an Exit nor an Item
     */
    public void setObject(Object object) {
        if (!free) {
            throw new IllegalStateException("Step is already used");
        }
        if (object instanceof Exit exit) {
            this.exit = exit;
            this.free = false;
        } else if (object instanceof Item item) {
            this.item = item;
            this.free = false;
        } else {
            throw new IllegalArgumentException("Invalid object type for step");
        }
    }

    public void removeItem() {
        this.item = null;
        this.free = true;
    }

    public Item getItem(){
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
