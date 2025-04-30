package controller.controllerGame.controllerCommand.controllerInterractCommand;

import java.awt.Point;
import model.modelGame.modelCommand.modelInterractCom.TakeModel;
import view.viewGame.GameView;
import view.viewGame.viewCommand.viewInteractCommand.TakeView;

public class TakeController {

  private TakeModel takeModel;
  private final TakeView takeView;
  private final GameView gameView;

  public TakeController(GameView gameView, TakeView takeView) {
    this.takeView = takeView;
    this.gameView = gameView;
  }

  public TakeModel getTakeModel() {
    return this.takeModel;
  }

  public TakeView getTakeView() {
    return this.takeView;
  }

  public GameView getGameView() {
    return this.gameView;
  }

  public void setTakeModel(String ind) {
    this.takeModel = new TakeModel(
      new String[] { "TAKE", ind },
      this.getGameView().getGameController().getGameModel()
    );
  }

  public boolean execute(Point p) {
    return this.getTakeModel() == null ? false : this.getTakeModel().execute(p);
  }
}
