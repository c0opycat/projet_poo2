package controller.controllerGame.controllerCommand;

import model.game.commandM.menuCommandM.Help;
import view.viewGame.GameView;
import view.viewGame.viewCommand.viewMenuCommand.HelpView;

public class HelpController{
    private final HelpView helpView;
    private final Help helpModel;

    public HelpController(HelpView helpView, GameView gameView)
    {
        this.helpView = helpView;
        this.helpModel = new Help(gameView.getGameController().getGameModel());
    }

    public HelpView getHelpView()
    {
        return this.helpView;
    }

    public Help getHelpModel()
    {
        return this.helpModel;
    }

    public void help()
    {
        this.getHelpModel().execute();
    }
}
