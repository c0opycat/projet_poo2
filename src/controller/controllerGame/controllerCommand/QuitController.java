package controller.controllerGame.controllerCommand;

import model.game.commandM.menuCommandM.Quit;
import view.viewGame.GameView;
import view.viewGame.viewCommand.viewMenuCommand.QuitView;

public class QuitController {
    private final QuitView quitView;
    private final Quit quitModel;

    public QuitController(QuitView qv, GameView gv)
    {
        this.quitView = qv;
        this.quitModel = new Quit(new String[]{"QUIT"}, gv.getGameController().getGameModel());
    }

    public Quit getQuitModel()
    {
        return this.quitModel;
    }

    public QuitView getQuitView()
    {
        return this.quitView;
    }

    public void forfeit()
    {
        this.quitModel.execute();
    }
}
