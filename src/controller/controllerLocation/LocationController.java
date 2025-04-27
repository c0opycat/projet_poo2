package controller.controllerLocation;

import java.awt.Point;
import java.util.HashMap;
import model.modelLocation.LocationModel;
import model.modelLocation.StepModel;
import view.Cell;
import view.viewLocation.LocationView;

public class LocationController {

  private final LocationModel locationModel;
  private final LocationView locationView;

  public LocationController(LocationModel locationModel) {
    this.locationModel = locationModel;
    this.locationView = new LocationView();
  }

  public LocationView getLocationView() {
    return this.locationView;
  }

  public LocationModel getLocationModel() {
    return this.locationModel;
  }

  public HashMap<Point, Cell> getLocElements() {
    HashMap<Point, StepModel> locMap = this.getLocationModel().getLocMap();

    HashMap<Point, Cell> elements = new HashMap<>();

    for (Point point : locMap.keySet()) {
      StepModel step = locMap.get(point);
      Cell cell;

      if (step.getItem() != null) {
        cell = new Cell(step.getItem().getClass().getName());
      } else {
        cell = new Cell(step.getExit().getClass().getName());
      }

      elements.put(point, cell);
    }

    return elements;
  }
}
