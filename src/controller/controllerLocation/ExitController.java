package controller.controllerLocation;

import java.awt.Point;
import model.modelGame.modelCommand.modelInterractCom.GoModel;
import model.modelLocation.ExitModel;
import view.viewLocation.ExitView;

public class ExitController {

  private final ExitModel exitModel;
  private final ExitView exitView;

  public ExitController(ExitModel exitModel) {
    this.exitModel = exitModel;
    this.exitView = new ExitView(this);
  }

  public ExitModel getExitModel() {
    return this.exitModel;
  }

  public ExitView getExitView() {
    return this.exitView;
  }

  public String getStartLocationName() {
    return this.getExitModel().getStart().getName();
  }

  public String getDestinationLocationName() {
    return this.getExitModel().getDestination().getName();
  }

  public Point getStartCoord() {
    return new Point(
      this.getExitModel().getStartX(),
      this.getExitModel().getStartY()
    );
  }

  public boolean go() {
    GoModel goModel = new GoModel(
      new String[] { "GO" },
      this.getExitView().getGameView().getGameController().getGameModel()
    );
    return goModel.execute(getStartCoord());
  }
}
