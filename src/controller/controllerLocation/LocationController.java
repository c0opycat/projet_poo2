package controller.controllerLocation;

import java.awt.Point;
import java.util.HashMap;
import model.modelLocation.LocationModel;
import model.modelLocation.StepModel;
import view.Cell;
import view.viewLocation.LocationView;

/**
 * Controller class for managing locations in the game.
 * This class acts as an intermediary between the LocationModel and LocationView.
 * It handles operations related to location data, rendering map elements,
 * updating the visual representation, and managing location-specific entities.
 * @author L. Cooper
 * @author A. Bertrand-Bernard
 */
public class LocationController {

  /** The model containing the location's data and game elements. */
  private final LocationModel locationModel;

  /** The view that displays this location in the game interface. */
  private final LocationView locationView;

  /**
   * Constructs a new LocationController with the specified location model.
   * Initializes the controller with a reference to the location model and creates
   * a new LocationView that will be managed by this controller.
   *
   * @param locationModel the model containing the location's data
   */
  public LocationController(LocationModel locationModel) {
    this.locationModel = locationModel;
    this.locationView = new LocationView(this);
  }

  /**
   * Gets the location view associated with this controller.
   *
   * @return the LocationView instance
   */
  public LocationView getLocationView() {
    return this.locationView;
  }

  /**
   * Gets the location model associated with this controller.
   *
   * @return the LocationModel instance
   */
  public LocationModel getLocationModel() {
    return this.locationModel;
  }

  /**
   * Gets the width of this location's map grid.
   *
   * @return the width as an integer
   */
  public int getWidth() {
    return this.getLocationModel().getWidth();
  }

  /**
   * Gets the height of this location's map grid.
   *
   * @return the height as an integer
   */
  public int getHeight() {
    return this.getLocationModel().getHeight();
  }

  /**
   * Gets the name of this location.
   *
   * @return a string representing the location's name
   */
  public String getName() {
    return this.getLocationModel().toString();
  }

  /**
   * Converts the location's model elements to view cell elements.
   * Maps each point in the location to its corresponding visual Cell,
   * including items and exits with their appropriate representations.
   *
   * @return a HashMap mapping coordinate points to Cell objects
   */
  public HashMap<Point, Cell> getLocElements() {
    HashMap<Point, StepModel> locMap = this.getLocationModel().getLocMap();

    HashMap<Point, Cell> elements = new HashMap<>();

    for (Point point : locMap.keySet()) {
      StepModel step = locMap.get(point);
      Cell cell;

      if (step.getItem() != null) {
        String elemName = step.getItem().getClass().getSimpleName();
        String elemNameSub = elemName.substring(0, elemName.length() - 5);
        cell = new Cell(elemNameSub);
      } else {
        String elemName = step.getExit().getClass().getSimpleName();
        String elemNameSub = elemName.substring(0, elemName.length() - 5);
        cell = new Cell(elemNameSub);
        cell.addImage(step.getExit().getExitController().getExitView());
      }

      elements.put(point, cell);
    }

    return elements;
  }

  /**
   * Initializes the location view if it hasn't been set up yet.
   * Checks if the grid has been initialized and, if not, populates
   * it with the appropriate map elements.
   */
  public void loadLocation() {
    if (
      this.getLocationView().getColumnCount() == 0 &&
      this.getLocationView().getRowCount() == 0
    ) {
      this.getLocationView().addElements();
    }
  }

  /**
   * Updates the visual representation of items in the location.
   * Refreshes the view by checking all points in the location model
   * and adding visual items where they exist in the model but not in the view.
   */
  public void updateItems() {
    HashMap<Point, StepModel> locMap = this.getLocationModel().getLocMap();

    for (Point point : locMap.keySet()) {
      int x = (int) point.getX();
      int y = (int) point.getY();
      StepModel step = locMap.get(point);

      if (step.getItem() != null) {
        if (this.getLocationView().getCell(x, y).getImageView() == null) {
          String elemName = step.getItem().getClass().getSimpleName();
          String elemNameSub = elemName.substring(0, elemName.length() - 5);
          this.getLocationView().addItem(elemNameSub, point);
        }
      }
    }
  }

  /**
   * Checks if this location contains a monster.
   *
   * @return true if a monster exists in this location, false otherwise
   */
  public boolean hasMonster() {
    return this.getLocationModel().getMonster() != null;
  }

  /**
   * Removes the monster from this location.
   * Sets the monster reference in the location model to null,
   * effectively removing the monster from the game world.
   */
  public void removeMonster() {
    this.getLocationModel().setMonster(null);
  }
}
