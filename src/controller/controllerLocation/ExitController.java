package controller.controllerLocation;

import java.awt.Point;
import model.modelGame.modelCommand.modelInterractCom.GoModel;
import model.modelLocation.ExitModel;
import view.viewLocation.ExitView;

/**
 * Controller class for managing exits between locations in the game.
 * This class acts as an intermediary between the ExitModel and ExitView.
 * It handles all operations related to exits, including retrieving location names,
 * coordinates, and executing the movement between locations.
 * @author L. Cooper
 * @author A. Bertrand-Bernard
 */
public class ExitController {

  /** The model containing the exit's data and connectivity information. */
  private final ExitModel exitModel;

  /** The view that displays this exit in the game interface. */
  private final ExitView exitView;

  /**
   * Constructs a new ExitController with the specified exit model.
   * Initializes the controller with a reference to the exit model and creates
   * a new ExitView that will be managed by this controller.
   *
   * @param exitModel the model containing the exit's data
   */
  public ExitController(ExitModel exitModel) {
    this.exitModel = exitModel;
    this.exitView = new ExitView(this);
  }

  /**
   * Gets the exit model associated with this controller.
   *
   * @return the ExitModel instance
   */
  public ExitModel getExitModel() {
    return this.exitModel;
  }

  /**
   * Gets the exit view associated with this controller.
   *
   * @return the ExitView instance
   */
  public ExitView getExitView() {
    return this.exitView;
  }

  /**
   * Gets the name of the starting location for this exit.
   *
   * @return a string representing the name of the source location
   */
  public String getStartLocationName() {
    return this.getExitModel().getStart().getName();
  }

  /**
   * Gets the name of the destination location for this exit.
   *
   * @return a string representing the name of the destination location
   */
  public String getDestinationLocationName() {
    return this.getExitModel().getDestination().getName();
  }

  /**
   * Gets the coordinates of this exit in its source location.
   *
   * @return a Point object containing the exit's coordinates
   */
  public Point getStartCoord() {
    return new Point(
      this.getExitModel().getStartX(),
      this.getExitModel().getStartY()
    );
  }

  /**
   * Executes a movement through this exit.
   * Creates a GoModel to handle the transition between locations and executes
   * the movement from the current location to the destination location.
   *
   * @return true if the movement was successful, false otherwise
   */
  public boolean go() {
    GoModel goModel = new GoModel(
      new String[] { "GO" },
      this.getExitView().getGameView().getGameController().getGameModel()
    );
    return goModel.execute(getStartCoord());
  }
}
