package model.modelItem.modelContainer;

import java.util.ArrayList;
import model.modelCharacter.modelHeros.HeroModel;
import model.modelGame.*;
import model.modelItem.CrowbarModel;
import model.modelItem.ItemModel;

/**
 * Represents a crate container that can store multiple {@link ItemModel} objects but requires a {@link CrowbarModel} to be opened.
 * <p>
 * Until the crate is opened, its contents are hidden and cannot be accessed, modified, or removed.
 * Once unlocked using a crowbar, the contents become accessible.
 * </p>
 */
public class CrateModel extends ContainerModel {

  /** Default maximum capacity (weight limit) of the crate. */
  public static final int DEFAULT_MAX_CAPACITY = 45;

  /** Default maximum number of items allowed in the crate. */
  public static final int DEFAULT_MAX_NB_ITEMS = 5;

  /** Default weight of the crate itself. */
  public static final int DEFAULT_WEIGHT = 10;

  /** Indicates whether the crate is currently open or closed. */
  public boolean open;

  /**
   * Constructs a new closed {@code CrateModel} with default capacity and item limit.
   */
  public CrateModel() {
    super(DEFAULT_MAX_NB_ITEMS, DEFAULT_MAX_CAPACITY, DEFAULT_WEIGHT);
    this.open = false;
  }

  /**
   * Constructs a new closed {@code CrateModel} with a given number of random items and capacity.
   * @param nb_items the number of random items to generate
   * @param cap the capacity (weight limit) of the crate
   */
  public CrateModel(int nb_items, int cap) {
    super(DEFAULT_MAX_NB_ITEMS, cap, DEFAULT_WEIGHT);
    fillContainer(nb_items);
    this.open = false;
  }

  /**
   * Checks whether the crate is currently open.
   * @return {@code true} if the crate is open, {@code false} otherwise
   */
  public boolean getOpen() {
    return this.open;
  }

  /**
   * Attempts to open the crate using a {@link CrowbarModel}.
   * <p>If successful, the crate becomes accessible and usable.</p>
   * @param c the crowbar item used to open the crate
   * @return {@code true} if the crate was successfully opened, {@code false} otherwise
   */
  public boolean open(CrowbarModel c) {
    boolean res = false;

    if (c != null) {
      BackpackModel bp = HeroModel.gBackpack();
      boolean use = c.use(bp);

      if (use) {
        this.open = true;
        System.out.println(MessageModel.itemUsed(c));
        res = true;
      } else {
        System.out.println(MessageModel.failOpening());
      }
    }

    return res;
  }

  /**
   * Returns the list of items in the crate only if the crate is open.
   * @return the list of items if open, or {@code null} otherwise
   */
  @Override
  public ArrayList<ItemModel> getItemList() {
    if (this.getOpen()) {
      return this.itemList;
    }
    return null;
  }

  /**
   * Adds an item to the crate regardless of its state (open or closed).
   * <p>This overrides the parent method to preserve future extensibility, but behaves the same.</p>
   * @param item the item to add
   * @return {@code true} if the item was successfully added, {@code false} otherwise
   */
  @Override
  public boolean addItem(ItemModel item) {
    boolean is_added = false;

    is_added = super.addItem(item);

    return is_added;
  }

  /**
   * Removes an item from the crate only if the crate is open.
   * @param item the item to remove
   */
  @Override
  public void removeItem(ItemModel item) {
    if (this.getOpen()) {
      super.removeItem(item);
    }
  }

  /**
   * Displays the contents of the crate only if it is open.
   */
  @Override
  public void displayContent() {
    if (this.getOpen()) {
      displayContent();
    }
  }

  /**
   * Retrieves the item at the specified index in the crate only if the crate is open.
   * @param i the index of the item
   * @return the item at the specified index, or {@code null} if the crate is closed
   */
  @Override
  public ItemModel getNthItem(int i) {
    if (this.getOpen()) {
      return super.getNthItem(i);
    } else return null;
  }
}
