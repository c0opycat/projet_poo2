package controller.controllerGame.controllerCommand.controllerMenuCommand;

import model.modelGame.commandM.modelMenuCom.HelpModel;
import view.viewGame.GameView;
import view.viewGame.viewCommand.viewMenuCommand.HelpView;

public class HelpController {

  private final HelpView helpView;
  private final HelpModel helpModel;

  public HelpController(HelpView helpView, GameView gameView) {
    this.helpView = helpView;
    this.helpModel = new HelpModel(gameView.getGameController().getGameModel());
  }

  public HelpView getHelpView() {
    return this.helpView;
  }

  public HelpModel getHelpModel() {
    return this.helpModel;
  }

  public void help() {
    this.getHelpModel().execute();
  }
}
