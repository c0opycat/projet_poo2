package view.viewContainer;

import controller.controllerItem.ContainerController;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.viewGame.GameView;
import view.viewGame.viewCommand.viewInteractCommand.DropView;
import view.viewGame.viewCommand.viewInteractCommand.TakeView;
import view.viewGame.viewCommand.viewItemCommand.EquipView;
import view.viewGame.viewCommand.viewItemCommand.UseView;

/**
 * View class for displaying and managing containers in the game.
 * It provides a graphical interface for interacting with items in containers or the hero's backpack.
 * @author L. Cooper
 * @author A. Bertrand-Bernard
 */
public class ContainerView extends VBox {

  /** Controller that manages the container model and operations on container contents. */
  private final ContainerController containerController;

  /** X-coordinate of the container in the game grid. */
  private int x;

  /** Y-coordinate of the container in the game grid. */
  private int y;

  /** Reference to the main game view for accessing game state and UI elements. */
  private final GameView gameView;

  /**
   * Constructs a ContainerView with the specified GameView.
   *
   * @param gameView the GameView instance associated with this container view
   */
  public ContainerView(GameView gameView) {
    super(5);
    this.containerController = new ContainerController(this, gameView);
    this.gameView = gameView;
  }

  /**
   * Adds a list of items to the container view.
   * Each item is displayed with appropriate controls based on whether it is in the hero's backpack.
   *
   * @param isHerosBackpack true if the items are in the hero's backpack, false otherwise
   * @param itemList the list of item names to display
   */
  public void addItemList(boolean isHerosBackpack, ArrayList<String> itemList) {
    if (itemList == null || itemList.isEmpty()) {
      return;
    }
    int index = 0;
    for (String item : itemList) {
      HBox li = new HBox(10);
      Label label = new Label(item);
      label.setId("" + index);
      label.getStyleClass().add("modelItem-label");
      label.setStyle("-fx-text-fill: #000000;");
      li.setAlignment(Pos.CENTER_RIGHT);

      String itemName = item.split(" ")[0];

      GameView gameView = this.getGameView();
      Button equipButton = new EquipView(gameView).equipButton(
        isHerosBackpack,
        itemName,
        index
      );

      if (isHerosBackpack) {
        UseView useView = new UseView(gameView, itemName, index);
        DropView dropView = new DropView(gameView, index);

        li.getChildren().addAll(label, useView, equipButton, dropView);
      } else {
        Button takeButton = new TakeView(gameView).takeViewButton(index);

        li.getChildren().addAll(label, equipButton, takeButton);
      }

      this.getChildren().add(li);

      index++;
    }
  }

  /**
   * Updates the content of the container view.
   * This method delegates to the container controller to refresh the display of items
   * in the container. It clears existing items and repopulates the view with current data.
   *
   * @param isBackpack true if updating the hero's backpack view, false if updating an external container
   */
  public void updateContainerView(boolean isBackpack) {
    this.getContainerController().updateContainerView(isBackpack);
  }

  /**
   * Gets the x-coordinate of the container.
   *
   * @return the x-coordinate
   */
  public int getX() {
    return this.x;
  }

  /**
   * Gets the y-coordinate of the container.
   *
   * @return the y-coordinate
   */
  public int getY() {
    return this.y;
  }

  /**
   * Gets the GameView instance associated with this container view.
   *
   * @return the GameView instance
   */
  public GameView getGameView() {
    return this.gameView;
  }

  /**
   * Gets the ContainerController instance associated with this container view.
   *
   * @return the ContainerController instance
   */
  public ContainerController getContainerController() {
    return this.containerController;
  }

  /**
   * Sets the x-coordinate of the container.
   *
   * @param x the x-coordinate to set
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * Sets the y-coordinate of the container.
   *
   * @param y the y-coordinate to set
   */
  public void setY(int y) {
    this.y = y;
  }
}
