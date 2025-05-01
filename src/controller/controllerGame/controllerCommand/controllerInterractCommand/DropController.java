package controller.controllerGame.controllerCommand.controllerInterractCommand;

import model.modelGame.modelCommand.modelInterractCom.DropModel;
import view.viewGame.GameView;
import view.viewGame.viewCommand.viewInteractCommand.DropView;

public class DropController {

  private final DropView dropView;
  private final DropModel dropModel;
  private final GameView gameView;

  public DropController(GameView gameView, DropView dropView, int ind) {
    this.dropView = dropView;
    this.gameView = gameView;
    this.dropModel = new DropModel(
      new String[] { "DROP", String.valueOf(ind) },
      this.getGameView().getGameController().getGameModel()
    );
  }

  public DropView getDropView() {
    return this.dropView;
  }

  public DropModel getDropModel() {
    return this.dropModel;
  }

  public GameView getGameView() {
    return this.gameView;
  }

  public boolean execute() {
    return this.getDropModel() == null ? false : this.getDropModel().execute();
  }
}
