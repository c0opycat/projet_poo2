package view.viewContainer;

import controller.controllerItem.ContainerController;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.item.Item;
import view.viewGame.GameView;

public class ContainerView extends VBox {

  private final ContainerController containerController;
  private int x;
  private int y;

  public ContainerView(GameView gameView) {
    super(20);
    this.containerController = new ContainerController(this, gameView);
    initComp();
  }

  private void initComp() {
    Label uTitle = new Label("Container : ");
    uTitle.getStyleClass().add("under-title");
    this.setAlignment(Pos.TOP_CENTER);
    this.getChildren().add(uTitle);
  }

  public void addItemList(ArrayList<Item> itemList) {
    int index = 0;
    for (Item item : itemList) {
      HBox li = new HBox(20);
      Label label = new Label(item.toString());

      label.setId("" + index);
      label.getStyleClass().add("item-label");
      label.setStyle("-fx-text-fill: #000000;");
      li.setAlignment(Pos.CENTER_RIGHT);
      li.getChildren().addAll(label, add_Buse(), add_Bequip());

      this.getChildren().add(li);
    }
  }

  private Button add_Buse() {
    Button use = new Button("Use");
    use.getStyleClass().add("button-Commande");
    return use;
  }

  private Button add_Bequip() {
    Button equip = new Button("Equip");
    equip.getStyleClass().add("button-Commande");
    return equip;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public ContainerController getContainerController() {
    return this.containerController;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }
}
