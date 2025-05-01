package model.modelLocation;

import controller.controllerLocation.ExitController;
import java.util.ArrayList;

/**
 * Represents an exit connecting two locations in the modelGame map.
 * Each exit links a start modelLocation to a destination and contains coordinates
 * for where the exit is placed within each modelLocation's map.
 * <p>
 * An exit is restricted to a maximum of four per modelLocation. Coordinates are
 * randomly placed along the edges to simulate doors, roads, or pathways.
 */
public class ExitModel {

  /**
   * The controller associated with the exit.
   */
  public final ExitController exitController;

  /**
   * The destination modelLocation of this exit.
   */
  public LocationModel destination;

  /**
   * The starting modelLocation of this exit.
   */
  public LocationModel start;

  /**
   * The X coordinate of the exit on the start modelLocation.
   */
  private int strtX;

  /**
   * The Y coordinate of the exit on the start modelLocation.
   */
  private int strtY;

  /**
   * Constructs a new ExitModel from one modelLocation to another.
   * <p>
   * Coordinates are randomly chosen along the borders of each modelLocation based
   * on the current number of existing exits. A modelLocation cannot have more than
   * four exits.
   *
   * @param start       the starting modelLocation of the exit
   * @param destination the destination modelLocation of the exit
   */
  public ExitModel(LocationModel start, LocationModel destination) {
    ArrayList<ExitModel> startExits = start.getExits();
    int stExNb = startExits.size();

    if (stExNb >= 4) {
      System.out.println(
        "you can't add more exits to the starting modelLocation"
      );
    } else {
      this.destination = destination;
      this.start = start;
    }
    this.exitController = new ExitController(this);
  }

  /**
   * Retrieves the LocationModel representing the starting point of this exit.
   *
   * @return the LocationModel instance.
   */
  public LocationModel getStart() {
    return this.start;
  }

  /**
   * Retrieves the LocationModel representing the destination point of this exit.
   *
   * @return the LocationModel instance.
   */
  public LocationModel getDestination() {
    return this.destination;
  }

  /**
   * Retrieves the ExitController associated with this model.
   *
   * @return the ExitController instance.
   */
  public ExitController getExitController() {
    return this.exitController;
  }

  /**
   * Returns the X coordinate of the exit on the start location.
   *
   * @return the X coordinate on the start location's grid
   */
  public int getStartX() {
    return this.strtX;
  }

  /**
   * Returns the Y coordinate of the exit on the start location.
   *
   * @return the Y coordinate on the start location's grid
   */
  public int getStartY() {
    return this.strtY;
  }

  /**
   * Sets the X coordinate of the exit on the start location.
   *
   * @param x the X coordinate on the start location's grid
   */
  public void setStartX(int x) {
    this.strtX = x;
  }

  /**
   * Sets the Y coordinate of the exit on the start location.
   *
   * @param y the Y coordinate on the start location's grid
   */
  public void setStartY(int y) {
    this.strtY = y;
  }

  /**
   * Returns a string representation of the exit, showing only the destination.
   *
   * @return a string describing the destination modelLocation
   */
  @Override
  public String toString() {
    return "To " + destination;
  }
}
