package controller.controllerGame;

import model.game.GameM;
import view.viewGame.GameView;

public class GameController {
    private final GameM gameModel;
    private final GameView gameView;

    public GameController(GameView gameView)
    {
        this.gameView = gameView;
        this.gameModel = new GameM();
    }

    public GameM getGameM()
    {
        return this.gameModel;
    }

    public GameView getGameView()
    {
        return this.gameView;
    }
}
