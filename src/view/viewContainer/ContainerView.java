package view.viewContainer;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.item.Item;

public class ContainerView extends VBox {

  public ContainerView() {
    super(20);
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

      Button use = new Button("Use");
      use.getStyleClass().add("button-Commande");
      Button equip = new Button("Equip");
      equip.getStyleClass().add("button-Commande");

      li.setAlignment(Pos.CENTER);
      li.getChildren().addAll(label, use, equip);

      this.getChildren().add(li);
    }
  }
}
