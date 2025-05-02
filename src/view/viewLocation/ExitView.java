package view.viewLocation;

import controller.controllerLocation.ExitController;
import java.awt.Point;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.viewGame.GameView;

/**
 * The ExitView class represents a visual door or exit on the game map.
 * This class extends ImageView to display a randomly selected door image.
 * It serves as the visual representation of an exit between locations,
 * and contains references to its controller and related game elements.
 * @author L. Cooper
 * @author A. Bertrand-Bernard
 */
public class ExitView extends ImageView {

  /** The starting coordinates of this exit in its source location. */
  private final Point startCoord;

  /** The controller that manages this exit's model and interactions. */
  private final ExitController exitController;

  /** Reference to the main game view, for navigation between locations. */
  private GameView gameView;

  /**
   * Constructs a new ExitView with the specified controller.
   * Initializes the view with a randomly selected door image from available resources.
   * Sets up the essential properties and coordinates based on the controller's data.
   *
   * @param exitController the controller that manages the exit model this view will display
   * @author L. Cooper
   * @author A. Bertrand-Bernard
   */
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

  /**
   * Gets the game view associated with this exit.
   *
   * @return the GameView instance
   */
  public GameView getGameView() {
    return this.gameView;
  }

  /**
   * Sets the game view for this exit.
   *
   * @param gameView the GameView to associate with this exit
   */
  public void setGameView(GameView gameView) {
    this.gameView = gameView;
  }

  /**
   * Gets the start coordinates of this exit in its source location.
   *
   * @return a Point object containing the exit's coordinates
   */
  public Point getStartoord() {
    return this.startCoord;
  }

  /**
   * Gets the controller for this exit view.
   *
   * @return the ExitController managing this exit
   */
  public ExitController getExitController() {
    return this.exitController;
  }

  /**
   * Gets the name of the starting location for this exit.
   *
   * @return a string representing the name of the source location
   */
  public String getStartName() {
    return this.getExitController().getStartLocationName();
  }

  /**
   * Gets the name of the destination location for this exit.
   *
   * @return a string representing the name of the destination location
   */
  public String getDestinationName() {
    return this.getExitController().getDestinationLocationName();
  }
}
