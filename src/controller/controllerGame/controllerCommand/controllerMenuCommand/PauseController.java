package controller.controllerGame.controllerCommand.controllerMenuCommand;

import model.modelGame.modelCommand.modelMenuCom.PauseModel;
import view.viewGame.GameView;
import view.viewGame.viewCommand.viewMenuCommand.PauseView;

public class PauseController {

  private final PauseView pauseView;
  private final PauseModel pauseModel;

  public PauseController(GameView gameView, PauseView pauseView) {
    this.pauseView = pauseView;
    this.pauseModel = new PauseModel(
      new String[] { "PAUSE" },
      gameView.getGameController().getGameModel()
    );
  }

  public PauseView getPauseView() {
    return this.pauseView;
  }

  public PauseModel getPauseModel() {
    return this.pauseModel;
  }

  public boolean execute() {
    return this.getPauseModel().execute();
  }
}
