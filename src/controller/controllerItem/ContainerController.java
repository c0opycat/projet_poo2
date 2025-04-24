package controller.controllerItem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import model.item.Item;
import model.item.container.Backpack;
import model.item.container.Container;
import view.viewContainer.ContainerView;
import view.viewGame.GameView;

public class ContainerController {

  private final ContainerView containerView;
  private Container containerModel;

  public ContainerController(ContainerView containerView, GameView gameView) {
    this.containerView = containerView;

    //Pour récupérer le container :
    //On récupère la hashmap contenant les items gameView.getGameController().getGameModel().getCurLocation().getItemsMap();
    //Puis on récupère le container que l'on veut avec les coordonnées de la case (qui seront donc la même dans la hashmap).
    this.containerModel = new Backpack();
    this.containerModel.fillContainer(6);
  }

  public ContainerView getContainerView() {
    return this.containerView;
  }

  public Container getContainerModel() {
    return this.containerModel;
  }

  public void setContainerModel(Container container) {
    this.containerModel = container;
  }

  //Fonction pour récupérer ce qu'il y a dans le container

  public ArrayList<Item> getItems() {
    return containerModel.getItemList();
  }

  public void setItemsContainer() {
    containerView.addItemList(getItems());
  }
}
