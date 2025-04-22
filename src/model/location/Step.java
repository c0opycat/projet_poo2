package model.location;

import model.item.Item;

public class Step {
    public Item item;
    public String terrain;
    private final String DEFAULT_TERRAIN = "flat";

    public Step() {
        this.item = null;
        this.terrain = DEFAULT_TERRAIN;
    }
    public Step(Item item, String terrain) {
        this.item = item;
        this.terrain = terrain;
    }

    public void setItem(Item item){
        this.item = null;
    }
    public void setTerrain(String terrain){
        this.terrain = terrain;
    }
}
