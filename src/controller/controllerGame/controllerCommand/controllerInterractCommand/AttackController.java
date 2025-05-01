package controller.controllerGame.controllerCommand.controllerInterractCommand;

import model.modelGame.modelCommand.modelInterractCom.AttackModel;
import view.viewGame.GameView;

public class AttackController {

  private final AttackModel attackModel;
  private final GameView gameView;

  public AttackController(GameView gameView) {
    this.gameView = gameView;
    this.attackModel = new AttackModel(
      new String[] { "ATTACK" },
      this.getGameView().getGameController().getGameModel()
    );
  }

  public AttackModel getAttackModel() {
    return this.attackModel;
  }

  public GameView getGameView() {
    return this.gameView;
  }

  public boolean execute() {
    return this.getAttackModel().execute();
  }
}
