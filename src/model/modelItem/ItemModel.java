package model.modelItem;

import java.util.Random;
import model.modelItem.modelConsumable.*;
import model.modelItem.modelConsumable.modelFood.*;
import model.modelItem.modelContainer.*;
import model.modelItem.modelWeapon.*;

/**
 * Represents an abstract base class for all items in the game.
 * <p>
 * This class defines the core attributes and utility methods shared by all items.
 * Every item has a weight, and this class also provides static methods to generate random items.
 * </p>
 */
public abstract class ItemModel {

  /**
   * The weight of the item (in kg), used for inventory management.
   */
  public final int WEIGHT;

  /**
   * Constructs an ItemModel with a specified weight.
   * @param weight the weight of the item
   */
  public ItemModel(int weight) {
    this.WEIGHT = weight;
  }

  /**
   * Generates a random {@link ItemModel}, which can be any item including containers.
   * @return a randomly generated ItemModel
   */
  public static ItemModel randomItem() {
    Random rand = new Random();
    int i = rand.nextInt(12);
    ItemModel res;

    int fill = rand.nextInt(3) + 2; // Number of items to fill if container
    int max_cap = rand.nextInt(21) + 20; // Capacity for container items (20-40kg)

    switch (i) {
      case 0:
        if (max_cap >= 30) {
          max_cap -= 10;
        }
        res = new BackpackModel(max_cap, fill);
        break;
      case 1:
        res = new BaseballBatModel();
        break;
      case 2:
        res = new CakeModel();
        break;
      case 3:
        res = new ChestModel(fill, max_cap);
        break;
      case 4:
        res = new CrateModel(fill, max_cap);
        break;
      case 5:
        res = new CrowbarModel();
        break;
      case 6:
        res = new FruitModel();
        break;
      case 7:
        res = new GunModel();
        break;
      case 8:
        res = new MeatModel();
        break;
      case 9:
        res = new MedicineModel();
        break;
      case 10:
        res = new ProtectionModel();
        break;
      default:
        res = new SwordModel();
        break;
    }

    return res;
  }

  /**
   * Generates a random {@link ItemModel} which is guaranteed NOT to be a container.
   * @return a random non-container ItemModel
   */
  public static ItemModel NonContainerRI() {
    Random rand = new Random();
    int i = rand.nextInt(9);
    ItemModel res;

    switch (i) {
      case 0:
        res = new BaseballBatModel();
        break;
      case 1:
        res = new CakeModel();
        break;
      case 2:
        res = new CrowbarModel();
        break;
      case 3:
        res = new FruitModel();
        break;
      case 4:
        res = new GunModel();
        break;
      case 5:
        res = new MeatModel();
        break;
      case 6:
        res = new MedicineModel();
        break;
      case 7:
        res = new ProtectionModel();
        break;
      default:
        res = new SwordModel();
        break;
    }
    return res;
  }

  /**
   * Returns a string representation of the item, showing its class name (without "Model" suffix) and its weight.
   * @return a formatted string describing the item
   */
  @Override
  public String toString() {
    String className = this.getClass().getSimpleName();
    return (className.substring(0, className.length() - 5) + " (" + this.WEIGHT + " kg)");
  }

  /**
   * Checks whether the given item is a container.
   * @param it the item to check
   * @return {@code true} if the item is a container, {@code false} otherwise
   */
  public static boolean isContainer(ItemModel it) {
    return it instanceof ContainerModel;
  }
}
