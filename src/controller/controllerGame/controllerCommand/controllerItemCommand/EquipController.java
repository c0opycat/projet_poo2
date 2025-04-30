package controller.controllerGame.controllerCommand.controllerItemCommand;

import java.awt.Point;
import model.modelGame.modelCommand.modelItemCom.EquipModel;
import view.viewGame.GameView;
import view.viewGame.viewCommand.viewItemCommand.EquipView;

public class EquipController {

  private final EquipView equipView;
  private EquipModel equipModel;
  private final GameView gameView;

  public EquipController(EquipView equipView, GameView gameView) {
    this.equipView = equipView;
    this.equipModel = null;
    this.gameView = gameView;
  }

  public EquipView getEquipView() {
    return this.equipView;
  }

  public EquipModel getEquipModel() {
    return this.equipModel;
  }

  public GameView getGameView() {
    return this.gameView;
  }

  public void setEquipModel(int zoneInd, String ind) {
    System.out.println(zoneInd);
    this.equipModel = new EquipModel(
      new String[] { "EQUIP", ind, String.valueOf(zoneInd) },
      this.getGameView().getGameController().getGameModel()
    );
  }

  public boolean execute(Point p) {
    return this.getEquipModel() == null
      ? false
      : this.getEquipModel().execute(p);
  }
}
