package model.modelItem.modelConsumable;

/**
 * Represents a medicine consumable item.
 * <p>
 * Medicine is a consumable item that restores a large amount of health when used.
 * It does not take any space (0 weight) in the inventory.
 * </p>
 * <p>
 * When consumed, a {@code MedicineModel} restores 50 health points to the player.
 * </p>
 */
public class MedicineModel extends ConsumableModel {

    /**
     * Constructs a new {@code MedicineModel} with predefined healing and weight values.
     * <p>Specifically:
     * <ul>
     *   <li>Heals the player for 50 health points.</li>
     *   <li>Has a weight of 0 in the inventory (does not occupy space).</li>
     * </ul>
     * </p>
     */
    public MedicineModel() {
        super(50, 0);
    }
}
