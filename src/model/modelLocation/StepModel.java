package model.modelLocation;

import model.modelItem.ItemModel;

public class StepModel {

  private ItemModel item;
  private ExitModel exit;
  public String terrain;
  private final String DEFAULT_TERRAIN = "flat";

  public StepModel() {
    this.exit = null;
    this.item = null;
    this.terrain = DEFAULT_TERRAIN;
  }

  public StepModel(ItemModel item, String terrain) {
    this.terrain = terrain;
    setItem(item);
  }

  public StepModel(ItemModel item) {
    this.terrain = DEFAULT_TERRAIN;
    setItem(item);
  }

  public StepModel(ExitModel exit, String terrain) {
    this.terrain = terrain;
    setExit(exit);
  }

  public StepModel(ExitModel exit) {
    this.terrain = DEFAULT_TERRAIN;
    setExit(exit);
  }

  /**
   * Sets an object on the step if it's currently free.
   * Accepts either an Exit or an Item. Otherwise, throws an exception.
   * @param item the Item to place on this step
   * @throws IllegalStateException    if the step is already used
   * @throws IllegalArgumentException if the object is neither an Exit nor an Item
   */
  public void setItem(ItemModel item) {
    this.item = item;
    this.exit = null;
  }

  /**
   * Sets an object on the step if it's currently free.
   * Accepts either an Exit or an Item. Otherwise, throws an exception.
   * @param exit the Exit to place on this step
   * @throws IllegalStateException    if the step is already used
   * @throws IllegalArgumentException if the object is neither an Exit nor an Item
   */
  public void setExit(ExitModel exit) {
    this.exit = exit;
    this.item = null;
  }

  public void removeItem() {
    this.item = null;
  }

  public ItemModel getItem() {
    return this.item;
  }

  public ExitModel getExit() {
    return this.exit;
  }

  public void setTerrain(String terrain) {
    this.terrain = terrain;
  }
}
