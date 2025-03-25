package controller.controllerGame.controllerCommand;

import model.game.commandM.menuCommandM.Quit;
import view.viewGame.viewCommand.viewMenuCommand.QuitView;

public class QuitController {
    private final QuitView quitView;
    private final Quit quitModel;

    public QuitController(QuitView qv)
    {
        this.quitView = qv;
        this.quitModel = null; // Comment faire ? Car on a besoin de la game
        //this.quitModel = new Quit("Quit", null);
    }

    public Quit getQuitModel()
    {
        return this.quitModel;
    }

    public QuitView getQuitView()
    {
        return this.quitView;
    }
}
