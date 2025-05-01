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
    this.locationView = new LocationView(this);
  }

  public LocationView getLocationView() {
    return this.locationView;
  }

  public LocationModel getLocationModel() {
    return this.locationModel;
  }

  public int getWidth() {
    return this.getLocationModel().getWidth();
  }

  public int getHeight() {
    return this.getLocationModel().getHeight();
  }

  public String getName() {
    return this.getLocationModel().toString();
  }

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

  public void loadLocation() {
    if (
      this.getLocationView().getColumnCount() == 0 &&
      this.getLocationView().getRowCount() == 0
    ) {
      this.getLocationView().addElements();
    }
  }

  public boolean hasMonster() {
    return this.getLocationModel().getMonster() != null;
  }
}
