package model.modelItem.modelConsumable.modelFood;

/**
 * Represents a Fruit item that can be consumed to restore health and reduce hunger.
 * <p>This class extends {@link FoodModel} and defines specific values for healing and inventory weight.</p>
 * <p>When consumed, a Fruit restores 15 health points and occupies 1 unit of weight in the inventory.</p>
 */
public class FruitModel extends FoodModel {
    /**
     * Constructs a new FruitModel with predefined healing and weight values.
     * <p>Specifically:
     * <ul>
     *   <li>Heals the player for 15 health points.</li>
     *   <li>Weighs 1 unit in the inventory.</li>
     * </ul>
     * </p>
     */
    public FruitModel() {
        super(15, 1);
    }
}
