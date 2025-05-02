package model.modelItem.modelContainer;

import model.modelItem.ItemModel;

/**
 * Represents a backpack container which can hold multiple items.
 * <p>
 * A {@code BackpackModel} extends {@link ContainerModel} and defines default values for:
 * <ul>
 *     <li>Maximum capacity (total weight it can carry)</li>
 *     <li>Maximum number of items</li>
 *     <li>Its own weight</li>
 * </ul>
 * </p>
 * <p>
 * Backpacks can be created empty or initialized with random items.
 * </p>
 */
public class BackpackModel extends ContainerModel {

    /** Default maximum weight capacity of the backpack. */
    public static final int DEFAULT_MAX_CAPACITY = 30;

    /** Default maximum number of items that can be stored in the backpack. */
    public static final int DEFAULT_MAX_NB_ITEMS = 6;

    /** Default weight of the backpack itself when empty. */
    public static final int DEFAULT_WEIGHT = 5;

    /**
     * Constructs a {@code BackpackModel} with a given capacity and automatically fills it with random items.
     * @param max_cap the maximum weight capacity of the backpack
     * @param fill the number of random items to fill in the backpack upon creation
     */
    public BackpackModel(int max_cap, int fill) {
        super(DEFAULT_MAX_NB_ITEMS, max_cap, DEFAULT_WEIGHT);
        fillContainer(fill);
    }

    /**
     * Default constructor.
     * <p>
     * Creates an empty backpack with default capacity, item limit, and weight.
     * </p>
     */
    public BackpackModel() {
        super(DEFAULT_MAX_NB_ITEMS, DEFAULT_MAX_CAPACITY, DEFAULT_WEIGHT);
    }

    /**
     * Adds an item to the backpack.
     * @param item the item to add
     * @return {@code true} if the item was added successfully, {@code false} otherwise
     */
    @Override
    public boolean addItem(ItemModel item) {
        return super.addItem(item);
    }
}
