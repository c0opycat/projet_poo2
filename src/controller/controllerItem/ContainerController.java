package controller.controllerItem;

import java.awt.Point;
import java.util.ArrayList;
import model.modelItem.ItemModel;
import model.modelItem.modelContainer.ContainerModel;
import view.viewContainer.ContainerView;
import view.viewGame.GameView;

/**
 * Controller class for managing containers in the game.
 * It acts as an intermediary between the ContainerModel, ContainerView, and GameView.
 */
public class ContainerController {

  private final GameView gameView;
  private final ContainerView containerView;
  private ContainerModel containerModel;

  /**
   * Constructs a ContainerController with the specified ContainerView and GameView.
   *
   * @param containerView the ContainerView instance associated with this controller
   * @param gameView the GameView instance associated with this controller
   */
  public ContainerController(ContainerView containerView, GameView gameView) {
    this.containerView = containerView;
    this.gameView = gameView;
    this.containerModel = null;
  }

  /**
   * Gets the ContainerView instance associated with this controller.
   *
   * @return the ContainerView instance
   */
  public ContainerView getContainerView() {
    return this.containerView;
  }

  /**
   * Gets the ContainerModel instance managed by this controller.
   *
   * @return the ContainerModel instance
   */
  public ContainerModel getContainerModel() {
    return this.containerModel;
  }

  /**
   * Gets the GameView instance associated with this controller.
   *
   * @return the GameView instance
   */
  public GameView getGameView() {
    return this.gameView;
  }

  /**
   * Sets the ContainerModel instance for this controller.
   *
   * @param container the ContainerModel instance to be managed by this controller
   */
  public void setContainerModel(ContainerModel container) {
    this.containerModel = container;
  }

  /**
   * Gets the content of the hero's backpack as a list of strings.
   *
   * @return a list of strings representing the items in the hero's backpack
   */
  public ArrayList<String> getBackPackContent() {
    ArrayList<ItemModel> itemList =
      this.getGameView().getHeroView().getHeroController().getBackpackContent();

    return this.getItemStringList(itemList);
  }

  /**
   * Converts a list of ItemModel objects to a list of their string representations.
   *
   * @param itemList the list of ItemModel objects
   * @return a list of strings representing the items
   */
  public ArrayList<String> getItemStringList(ArrayList<ItemModel> itemList) {
    ArrayList<String> itemStringList = new ArrayList<>();

    for (ItemModel item : itemList) {
      itemStringList.add(item.toString());
    }

    return itemStringList;
  }

  /**
   * Gets the items of a container at the specified point in the current location.
   *
   * @param point the point in the current location
   * @return a list of strings representing the items
   */
  public ArrayList<String> getItems(Point point) {
    this.setContainerModel(
        (ContainerModel) this.getGameView()
          .getGameController()
          .getGameModel()
          .getCurLocation()
          .getLocMap()
          .get(point)
          .getItem()
      );
    return this.getItemStringList(this.getContainerModel().getItemList());
  }

  /**
   * Removes an item from the container view based on the specified index.
   *
   * @param ind the index of the item to be removed
   */
  public void removeItemContainerView(int ind) {
    this.getContainerView().getChildren().remove(ind);
  }
}
