package model.modelItem.modelContainer;

/**
 * Represents a chest container that can hold multiple items.
 * <p>
 * A {@code ChestModel} extends {@link ContainerModel} and defines default values for:
 * <ul>
 *     <li>Maximum capacity (total weight it can carry)</li>
 *     <li>Maximum number of items</li>
 *     <li>Its own weight</li>
 * </ul>
 * </p>
 * <p>
 * Chests can be created empty or initialized with a specific number of random items.
 * </p>
 */
public class ChestModel extends ContainerModel {

    /** Default maximum weight capacity of the chest. */
    public static final int DEFAULT_MAX_CAPACITY = 40;

    /** Default maximum number of items that can be stored in the chest. */
    public static final int DEFAULT_MAX_NB_ITEMS = 4;

    /** Default weight of the chest itself when empty. */
    public static final int DEFAULT_WEIGHT = 10;

    /**
     * Default constructor.
     * <p>
     * Creates an empty chest with default capacity, item limit, and weight.
     * </p>
     */
    public ChestModel() {
        super(DEFAULT_MAX_NB_ITEMS, DEFAULT_MAX_CAPACITY, DEFAULT_WEIGHT);
    }

    /**
     * Constructs a {@code ChestModel} with a given number of random items and a specific weight capacity.
     * @param nb_items the number of random items to fill in the chest upon creation
     * @param cap the maximum weight capacity of the chest
     */
    public ChestModel(int nb_items, int cap) {
        super(DEFAULT_MAX_NB_ITEMS, cap, DEFAULT_WEIGHT);
        fillContainer(nb_items);
    }
}
