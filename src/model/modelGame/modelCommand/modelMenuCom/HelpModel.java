package model.modelGame.modelCommand.modelMenuCom;

import model.modelGame.GameModel;
import model.modelGame.MessageEnModel;
import model.modelGame.modelCommand.CommandModel;

public class HelpModel extends CommandModel {

  public HelpModel(GameModel gameM) {
    this.gameM = gameM;
  }

  public boolean execute() {
    System.out.println(MessageEnModel.helpCommands());
    return false;
  }
}
