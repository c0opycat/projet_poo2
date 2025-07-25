package controller.controllerItem;

import java.awt.Point;
import java.util.ArrayList;
import model.modelItem.ItemModel;
import model.modelItem.modelContainer.ContainerModel;
import model.modelLocation.StepModel;
import view.viewContainer.ContainerView;
import view.viewGame.GameView;

/**
 * Controller class for managing containers in the game.
 * It acts as an intermediary between the ContainerModel, ContainerView, and GameView.
 * @author L. Cooper
 * @author A. Bertrand-Bernard
 */
public class ContainerController {

  /** Reference to the main game view for accessing game state and UI elements. */
  private final GameView gameView;

  /** The view that displays the container contents in the game interface. */
  private final ContainerView containerView;

  /** The model containing the container's data and content management logic. */
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
  public void setContainerModel(Point point) {
    if (point == null) {
      this.containerModel = null;
    } else {
      this.containerModel = (ContainerModel) ((StepModel) this.getGameView()
          .getGameController()
          .getGameModel()
          .getCurLocation()
          .getLocMap()
          .get(point)).getItem();
    }
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

    if (itemList != null && !itemList.isEmpty()) {
      for (ItemModel item : itemList) {
        itemStringList.add(item.toString());
      }
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
    this.setContainerModel(point);
    return this.getItemStringList(this.getContainerModel().getItemList());
  }

  /**
   * Removes an item from the container view based on the specified index.
   *
   * @param ind the index of the item to be removed
   */
  public void updateContainerView(boolean isBackpack) {
    this.getContainerView().getChildren().clear();

    if (isBackpack) {
      this.getContainerView().addItemList(true, this.getBackPackContent());
    } else {
      this.getContainerView()
        .addItemList(
          false,
          this.getItemStringList(this.getContainerModel().getItemList())
        );
    }
  }
}
