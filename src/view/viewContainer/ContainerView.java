package view.viewContainer;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
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
    for (Item item : itemList) {
      Label label = new Label(item.toString());
      label.getStyleClass().add("item-label");
      this.getChildren().add(label);
    }
  }
}
