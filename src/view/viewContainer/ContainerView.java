package view.viewContainer;

import controller.controllerItem.ContainerController;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.Lang;
import view.viewGame.GameView;
import view.viewGame.viewCommand.viewInteractCommand.TakeView;
import view.viewGame.viewCommand.viewItemCommand.EquipView;
import view.viewGame.viewCommand.viewItemCommand.UseView;

/**
 * View class for displaying and managing containers in the game.
 * It provides a graphical interface for interacting with items in containers or the hero's backpack.
 */
public class ContainerView extends VBox {

  private final ContainerController containerController;
  private Lang lang;
  private int x;
  private int y;
  private final GameView gameView;

  /**
   * Constructs a ContainerView with the specified GameView.
   *
   * @param gameView the GameView instance associated with this container view
   */
  public ContainerView(GameView gameView) {
    super(20);
    this.containerController = new ContainerController(this, gameView);
    this.gameView = gameView;
    this.lang = new Lang();
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
      HBox li = new HBox(20);
      Label label = new Label(item);
      label.setId("" + index);
      label.getStyleClass().add("modelItem-label");
      label.setStyle("-fx-text-fill: #000000;");
      li.setAlignment(Pos.CENTER_RIGHT);

      String itemName = item.split(" ")[0];

      if (isHerosBackpack) {
        li
          .getChildren()
          .addAll(
            label,
            new UseView(this.getGameView(), itemName, index),
            new EquipView(this.getGameView()).equipButton(
              isHerosBackpack,
              itemName,
              index
            )
          );
      } else {
        TakeView takeView = new TakeView(this.getGameView());
        li
          .getChildren()
          .addAll(
            label,
            new EquipView(this.getGameView()).equipButton(
              isHerosBackpack,
              itemName,
              index
            ),
            takeView.takeViewButton(itemName, index)
          );
      }

      this.getChildren().add(li);

      index++;
    }
  }

  public void updateContainerView(boolean isBackpack) {
    this.getContainerController().updateContainerView(isBackpack);
  }

  /**
   * Creates and returns a button for equipping items.
   *
   * @return a Button instance for equipping items
   */
  private Button add_Bequip() {
    Button equip = new Button();
    lang.setButtonLang(equip, "Equiper", "Equip");
    equip.getStyleClass().add("button-Commande");
    return equip;
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
