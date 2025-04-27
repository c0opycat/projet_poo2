package controller.controllerItem;

import java.util.ArrayList;
import model.modelItem.ItemModel;
import model.modelItem.modelContainer.BackpackModel;
import model.modelItem.modelContainer.ContainerModel;
import view.viewContainer.ContainerView;
import view.viewGame.GameView;

public class ContainerController {

  private final ContainerView containerView;
  private ContainerModel containerModel;

  public ContainerController(ContainerView containerView, GameView gameView) {
    this.containerView = containerView;

    //Pour récupérer le modelContainer :
    //On récupère la hashmap contenant les items gameView.getGameController().getGameModel().getCurLocation().getItemsMap();
    //Puis on récupère le modelContainer que l'on veut avec les coordonnées de la case (qui seront donc la même dans la hashmap).
    this.containerModel = new BackpackModel();
    this.containerModel.fillContainer(6);
  }

  public ContainerView getContainerView() {
    return this.containerView;
  }

  public ContainerModel getContainerModel() {
    return this.containerModel;
  }

  public void setContainerModel(ContainerModel container) {
    this.containerModel = container;
  }

  //Fonction pour récupérer ce qu'il y a dans le modelContainer

  public ArrayList<ItemModel> getItems() {
    return containerModel.getItemList();
  }

  public void setItemsContainer() {
    containerView.addItemList(getItems());
  }
}
