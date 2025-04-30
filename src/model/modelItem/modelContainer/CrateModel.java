package model.modelItem.modelContainer;

import java.util.ArrayList;
import model.modelCharacter.modelHeros.HeroModel;
import model.modelGame.*;
import model.modelItem.CrowbarModel;
import model.modelItem.ItemModel;

public class CrateModel extends ContainerModel {

  public static final int DEFAULT_MAX_CAPACITY = 45;
  public static final int DEFAULT_MAX_NB_ITEMS = 5;
  public static final int DEFAULT_WEIGHT = 10;

  public boolean open;

  public CrateModel() {
    super(DEFAULT_MAX_NB_ITEMS, DEFAULT_MAX_CAPACITY, DEFAULT_WEIGHT);
    this.open = false;
  }

  public CrateModel(int nb_items, int cap) {
    super(DEFAULT_MAX_NB_ITEMS, cap, DEFAULT_WEIGHT);
    fillContainer(nb_items);
    this.open = false;
  }

  public boolean getOpen() {
    return this.open;
  }

  public boolean open(CrowbarModel c) {
    boolean res = false;

    if (c != null) {
      BackpackModel bp = HeroModel.gBackpack();
      boolean use = c.use(bp);

      if (use) {
        this.open = true;
        System.out.println(MessageEnModel.itemUsed(c));
        res = true;
      } else {
        System.out.println(MessageEnModel.failOpening());
      }
    }

    return res;
  }

  @Override
  public ArrayList<ItemModel> getItemList() {
    if (this.getOpen()) {
      return this.itemList;
    }
    return null;
  }

  //Tries to add the modelItem only if the crate is open
  @Override
  public boolean addItem(ItemModel item) {
    boolean is_added = false;

    is_added = super.addItem(item);

    return is_added;
  }

  //Removes the modelItem only if the crate is open
  @Override
  public void removeItem(ItemModel item) {
    if (this.getOpen()) {
      super.removeItem(item);
    }
  }

  //Displays the crate's content only if it is open
  @Override
  public void displayContent() {
    if (this.getOpen()) {
      displayContent();
    }
  }

  @Override
  public ItemModel getNthItem(int i) {
    if (this.getOpen()) {
      return super.getNthItem(i);
    } else return null;
  }
}
