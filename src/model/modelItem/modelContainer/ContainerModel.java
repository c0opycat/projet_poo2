package model.modelItem.modelContainer;

import java.util.ArrayList;
import model.modelGame.MessageEnModel;
import model.modelItem.ItemModel;

/**
 * Represents a generic container capable of storing multiple {@link ItemModel} objects.
 * <p>
 * A {@code ContainerModel} defines limits for:
 * <ul>
 *     <li>The number of items it can hold.</li>
 *     <li>The total capacity (i.e., weight) it can support.</li>
 * </ul>
 * This abstract class is intended to be extended by specific container types like backpacks and chests.
 * </p>
 */
public abstract class ContainerModel extends ItemModel {

  /** Maximum number of items allowed in the container. */
  public final int MAX_NB_ITEMS;

  /** Maximum capacity (total weight limit) of the container. */
  public final int MAX_CAPACITY;

  /** Current number of items in the container. */
  private int nb_items;

  /** Current total weight of the items in the container. */
  private int capacity;

  /** List of items stored inside the container. */
  public ArrayList<ItemModel> itemList;

  /**
   * Constructs a new {@code ContainerModel} with the specified limits and weight.
   * @param nb_items maximum number of items the container can hold
   * @param cap maximum weight capacity of the container
   * @param weight weight of the container itself
   */
  protected ContainerModel(int nb_items, int cap, int weight) {
    super(weight);
    this.MAX_NB_ITEMS = nb_items;
    this.MAX_CAPACITY = cap;
    this.capacity = 0;
    this.nb_items = 0;
    this.itemList = new ArrayList<>(nb_items);
  }

  /**
   * Fills the container with a specified number of random items.
   * @param nb number of random items to add
   */
  public void fillContainer(int nb) {
    if (nb + this.nb_items > this.MAX_NB_ITEMS) {
      System.out.println(MessageEnModel.contFull(this));
    }
    ItemModel item;
    for (int i = 0; i < nb; i++) {
      item = ItemModel.NonContainerRI();

      if (this.capacity + item.WEIGHT <= this.MAX_CAPACITY) {
        this.addItem(item);
      }
    }
  }

  /**
   * Gets the current total weight the container can contain.
   * @return the current capacity (weight)
   */
  public int getCapacity() {
    return this.capacity;
  }

  /**
   * Gets the current number of items in the container.
   * @return the number of items
   */
  public int getNbItems() {
    return this.nb_items;
  }

  /**
   * Sets the weight capacity of the container.
   * @param cap the new capacity value
   */
  public void setCapacity(int cap) {
    if (cap <= this.MAX_CAPACITY) {
      this.capacity = cap;
    }
  }

  /**
   * Sets the current number of items.
   * @param nb_items the new number of items
   */
  public void setNbItems(int nb_items) {
    if (nb_items <= this.MAX_NB_ITEMS) {
      this.nb_items = nb_items;
    }
  }

  /**
   * Checks if the container is full.
   * @return {@code true} if full, otherwise {@code false}
   */
  public boolean isFull() {
    return (this.getNbItems() == this.MAX_NB_ITEMS);
  }

  /**
   * Displays the contents of the container in a formatted manner.
   */
  public void displayContent() {
    System.out.print(this);
    System.out.println(" : ");

    int ind = 0;
    for (ItemModel i : itemList) {
      System.out.println("    " + ind + ". " + i);
      ind++;
    }
  }

  /**
   * Gets the item at the specified index.
   * @param i the index
   * @return the item at the specified index or {@code null} if empty
   */
  public ItemModel getNthItem(int i) {
    if (this.itemList.size() == 0) {
      return null;
    } else return this.itemList.get(i);
  }

  /**
   * Gets the list of items in the container.
   * @return the list of items
   */
  public ArrayList<ItemModel> getItemList() {
    return this.itemList;
  }

  /**
   * Removes a specific item from the container.
   * @param item the item to remove
   */
  public void removeItem(ItemModel item) {
    if (this.itemList.contains(item)) {
      this.itemList.remove(item);
      this.setCapacity(this.getCapacity() - item.WEIGHT);
      this.setNbItems(this.getNbItems() - 1);
    }
  }

  /**
   * Checks if an item is too heavy to be added to the container.
   * @param i the item to check
   * @return {@code true} if the item is too heavy, otherwise {@code false}
   */
  public boolean tooHeavy(ItemModel i) {
    return ((this.getCapacity()) + (i.WEIGHT) > this.MAX_CAPACITY);
  }

  /**
   * Adds an item to the container if there is enough space.
   * @param item the item to add
   * @return {@code true} if the item was added, {@code false} otherwise
   */
  public boolean addItem(ItemModel item) {
    boolean is_added = false;

    if (item != null) {
      if (this.tooHeavy(item)) {
        System.out.println(MessageEnModel.noSpace(this, item));
      } else if (this.isFull()) {
        System.out.println(MessageEnModel.contFull(this));
      } else {
        this.itemList.add(item);
        this.setCapacity(this.getCapacity() + item.WEIGHT);
        this.setNbItems(this.getNbItems() + 1);

        is_added = true;
      }
    }

    return is_added;
  }

  /**
   * Returns the name of the container type as a string.
   * @return the class name
   */
  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }
}
