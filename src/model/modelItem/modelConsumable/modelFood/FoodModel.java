package model.modelItem.modelConsumable.modelFood;

import model.modelItem.modelConsumable.ConsumableModel;

/**
 * Abstract class representing food items that can be consumed to restore health.
 * <p>Food items are a type of {@link ConsumableModel} and define a healing value and a weight.</p>
 * <p>This class is intended to be extended by specific food types (such as Cake, Apple, etc.).</p>
 */
public abstract class FoodModel extends ConsumableModel {

    /**
     * Constructs a new FoodModel with the specified healing value and inventory weight.
     * @param hp     the amount of health restored when the food is consumed
     * @param weight the weight of the food item in the inventory
     */
    public FoodModel(int hp, int weight) {
        super(hp, weight);
    }
}
