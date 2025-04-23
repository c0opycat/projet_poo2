package controller.controllerGame.controllerCommand.controllerItemCommand;

import model.game.commandM.itemComM.Equip;
import view.viewGame.GameView;
import view.viewGame.viewCommand.viewItemCommand.EquipView;

public class EquipController {

  private final EquipView equipView;
  private final Equip equipModel;

  public EquipController(EquipView equipView, GameView gameView) {
    this.equipView = equipView;
    this.equipModel = null;
  }
}
