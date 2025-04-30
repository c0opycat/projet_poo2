package controller.controllerGame.controllerCommand.controllerInterractCommand;

import java.awt.Point;
import model.modelGame.modelCommand.modelInterractCom.TakeModel;
import view.viewGame.GameView;
import view.viewGame.viewCommand.viewInteractCommand.TakeButtonView;

public class TakeButtonController {

  private TakeModel takeModel;
  private final TakeButtonView takeView;
  private final GameView gameView;

  public TakeButtonController(GameView gameView, TakeButtonView takeView) {
    this.takeView = takeView;
    this.gameView = gameView;
  }

  public TakeModel getTakeModel() {
    return this.takeModel;
  }

  public TakeButtonView getTakeView() {
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

  public void removeItemContainerView(int ind) {
    this.getGameView()
      .getContainerView()
      .getContainerController()
      .removeItemContainerView(ind);
  }
}
