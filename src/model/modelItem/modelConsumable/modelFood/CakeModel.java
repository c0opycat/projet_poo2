package model.modelItem.modelConsumable.modelFood;

/**
 * Represents a Cake item that can be consumed to restore health and reduce hunger.
 * <p>This class extends {@link FoodModel} and defines specific values for healing and hunger restoration.</p>
 * <p>When consumed, a Cake restores 10 health points and
 * when stored it takes 5 units in the inventory.</p>
 */
public class CakeModel extends FoodModel {

    /**
     * Constructs a new CakeModel with predefined healing and hunger values.
     * <p>Specifically:
     * <ul>
     *   <li>Heals the player for 10 health points.</li>
     *   <li>Weights 5 units in the inventory.</li>
     * </ul>
     * </p>
     */
    public CakeModel() {
        super(10, 5);
    }
}
