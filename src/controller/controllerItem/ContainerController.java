package controller.controllerItem;

import model.item.container.Container;
import view.viewContainer.ContainerView;

public class ContainerController {

  private final ContainerView containerView;
  private final Container containerModel;

  public ContainerController(ContainerView containerView) {
    this.containerView = containerView;
  }
}
