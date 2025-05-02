package model.modelLocation;

import model.modelItem.ItemModel;

/**
 * Represents a step on the map grid.
 * Each step can contain either an item or an exit, and has a terrain type.
 */
public class StepModel {

  /** The item placed on this step (if any). */
  private ItemModel item;

  /** The exit placed on this step (if any). */
  private ExitModel exit;

  /** The type of terrain for this step. */
  public String terrain;

  /** The default terrain type when none is specified. */
  private final String DEFAULT_TERRAIN = "flat";

  /**
   * Creates an empty step with the default terrain type.
   */
  public StepModel() {
    this.exit = null;
    this.item = null;
    this.terrain = DEFAULT_TERRAIN;
  }

  /**
   * Creates a step with an item and a custom terrain type
   * @param item the item to be placed on the step
   * @param terrain the terrain type
   */
  public StepModel(ItemModel item, String terrain) {
    this.terrain = terrain;
    setItem(item);
  }

  /**
   * Creates a step with an item and the default terrain type.
   * @param item the item to be placed on the step
   */
  public StepModel(ItemModel item) {
    this.terrain = DEFAULT_TERRAIN;
    setItem(item);
  }

  /**
   * Creates a step with an exit and a custom terrain type.
   * @param exit the exit to be placed on the step
   * @param terrain the terrain type
   */
  public StepModel(ExitModel exit, String terrain) {
    this.terrain = terrain;
    setExit(exit);
  }

  /**
   * Creates a step with an exit and the default terrain type.
   * @param exit the exit to be placed on the step
   */
  public StepModel(ExitModel exit) {
    this.terrain = DEFAULT_TERRAIN;
    setExit(exit);
  }

  /**
   * Places an item on this step.
   * Replaces any existing exit.
   * @param item the item to set
   */
  public void setItem(ItemModel item) {
    this.item = item;
    this.exit = null;
  }

  /**
   * Places an exit on this step.
   * Replaces any existing item.
   * @param exit the exit to set
   */
  public void setExit(ExitModel exit) {
    this.exit = exit;
    this.item = null;
  }

  /**
   * Removes the item from this step.
   * Does nothing if no item is present.
   */
  public void removeItem() {
    this.item = null;
  }

  /**
   * Gets the item on this step.
   * @return the item placed on this step, or null if none
   */
  public ItemModel getItem() {
    return this.item;
  }

  /**
   * Gets the exit on this step.
   * @return the exit placed on this step, or null if none
   */
  public ExitModel getExit() {
    return this.exit;
  }

  /**
   * Sets the terrain type for this step.
   * @param terrain the terrain type to set
   */
  public void setTerrain(String terrain) {
    this.terrain = terrain;
  }
}
