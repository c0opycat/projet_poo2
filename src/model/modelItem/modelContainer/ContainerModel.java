package model.modelItem.modelContainer;

import java.util.ArrayList;
import model.modelGame.MessageEnModel;
import model.modelItem.ItemModel;

public abstract class ContainerModel extends ItemModel {

  public final int MAX_NB_ITEMS;
  public final int MAX_CAPACITY;
  private int nb_items;
  private int capacity;
  public ArrayList<ItemModel> itemList;

  protected ContainerModel(int nb_items, int cap, int weight) {
    super(weight);
    this.MAX_NB_ITEMS = nb_items;
    this.MAX_CAPACITY = cap;
    this.capacity = 0;
    this.nb_items = 0;
    this.itemList = new ArrayList<>(nb_items);
  }

  //Fills a modelContainer with a chosen max amount of items as the parameter
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

  //Returns the modelContainer's capacity
  public int getCapacity() {
    return this.capacity;
  }

  //Returns the modelContainer's number of items
  public int getNbItems() {
    return this.nb_items;
  }

  //Set the capacity
  public void setCapacity(int cap) {
    if (cap <= this.MAX_CAPACITY) {
      this.capacity = cap;
    }
  }

  //Set the number of items
  public void setNbItems(int nb_items) {
    if (nb_items <= this.MAX_NB_ITEMS) {
      this.nb_items = nb_items;
    }
  }

  //Returns true if the number of items in the backpack is the max
  public boolean isFull() {
    return (this.getNbItems() == this.MAX_NB_ITEMS);
  }

  //Displays each modelItem contained in the modelContainer
  public void displayContent() {
    System.out.print(this);
    System.out.println(" : ");

    int ind = 0;
    for (ItemModel i : itemList) {
      System.out.println("    " + ind + ". " + i);
      ind++;
    }
  }

  //Returns the modelItem in the i position of the list
  public ItemModel getNthItem(int i) {
    if (this.itemList.size() == 0) {
      return null;
    } else return this.itemList.get(i);
  }

  public ArrayList<ItemModel> getItemList() {
    return this.itemList;
  }

  //Remove the modelItem from the modelContainer
  public void removeItem(ItemModel item) {
    if (this.itemList.contains(item)) {
      this.itemList.remove(item);
      this.setCapacity(this.getCapacity() - item.WEIGHT);
      this.setNbItems(this.getNbItems() - 1);
    }
  }

  //Returns true if the modelItem is too heavy to be put in the modelContainer
  public boolean tooHeavy(ItemModel i) {
    return ((this.getCapacity()) + (i.WEIGHT) > this.MAX_CAPACITY);
  }

  //Add the modelItem only if there is space for it
  //Returns true if the modelItem has been added
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

  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }
}
