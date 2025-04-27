package controller.controllerGame.controllerCommand.controllerMenuCommand;

import model.modelGame.commandM.modelMenuCom.QuitModel;
import view.viewGame.GameView;
import view.viewGame.viewCommand.viewMenuCommand.QuitView;

public class QuitController {

  private final QuitView quitView;
  private final QuitModel quitModel;

  public QuitController(QuitView qv, GameView gv) {
    this.quitView = qv;
    this.quitModel = new QuitModel(
      new String[] { "QUIT" },
      gv.getGameController().getGameModel()
    );
  }

  public QuitModel getQuitModel() {
    return this.quitModel;
  }

  public QuitView getQuitView() {
    return this.quitView;
  }

  public void forfeit() {
    this.quitModel.execute();
  }
}
