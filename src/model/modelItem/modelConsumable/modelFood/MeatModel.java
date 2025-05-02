package model.modelItem.modelConsumable.modelFood;

/**
 * Represents a Meat item that can be consumed to restore health and reduce hunger.
 * <p>This class extends {@link FoodModel} and defines specific values for healing and inventory weight.</p>
 * <p>When consumed, Meat restores 25 health points and occupies 3 units of weight in the inventory.</p>
 */
public class MeatModel extends FoodModel {

    /**
     * Constructs a new MeatModel with predefined healing and weight values.
     * <p>Specifically:
     * <ul>
     *   <li>Heals the player for 25 health points.</li>
     *   <li>Weighs 3 units in the inventory.</li>
     * </ul>
     * </p>
     */
    public MeatModel() {
        super(25, 3);
    }
}
