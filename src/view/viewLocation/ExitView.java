package view.viewLocation;

import controller.controllerLocation.ExitController;
import java.awt.Point;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.viewGame.GameView;

public class ExitView extends ImageView {

  private final Point startCoord;
  private final ExitController exitController;
  private GameView gameView;

  public ExitView(ExitController exitController) {
    super();
    Random random = new Random();
    int nbDoor = random.nextInt(6);
    this.setImage(
        new Image(
          "file:./resources/image/door" + nbDoor + ".png",
          25,
          25,
          true,
          true
        )
      );

    this.exitController = exitController;
    this.gameView = null;
    this.startCoord = this.getExitController().getStartCoord();
  }

  public GameView getGameView() {
    return this.gameView;
  }

  public void setGameView(GameView gameView) {
    this.gameView = gameView;
  }

  public Point getStartoord() {
    return this.startCoord;
  }

  public ExitController getExitController() {
    return this.exitController;
  }

  public String getStartName() {
    return this.getExitController().getStartLocationName();
  }

  public String getDestinationName() {
    return this.getExitController().getDestinationLocationName();
  }
}
