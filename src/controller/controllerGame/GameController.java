package controller.controllerGame;

import java.io.PrintStream;

import model.game.GameM;
import view.viewGame.GameInfosStream;
import view.viewGame.GameView;

public class GameController {
    private final GameM gameModel;
    private final GameView gameView;

    public GameController(GameView gameView)
    {
        this.gameView = gameView;
        this.gameModel = new GameM();
    }

    public GameM getGameModel()
    {
        return this.gameModel;
    }

    public GameView getGameView()
    {
        return this.gameView;
    }

    public void start()
    {
        PrintStream newOut = new PrintStream(new GameInfosStream(this.getGameView().getGameInfos()));
        System.setOut(newOut);

        this.getGameModel().start();
    }

    public void end()
    {
        System.setOut(System.out);
    }
}
