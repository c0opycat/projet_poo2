package controller.controllerLocation;

import java.awt.Point;
import java.util.HashMap;
import model.modelLocation.LocationModel;
import model.modelLocation.StepModel;
import view.Cell;
import view.viewCharacter.HeroView;
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

  public HashMap<Point, Cell> getLocElements() {
    HashMap<Point, StepModel> locMap = this.getLocationModel().getLocMap();

    HashMap<Point, Cell> elements = new HashMap<>();

    for (Point point : locMap.keySet()) {
      StepModel step = locMap.get(point);
      Cell cell;

      if (step.getItem() != null) {
        String elemName = step.getItem().getClass().getSimpleName();
        cell = new Cell(elemName.substring(0, elemName.length() - 5));
      } else {
        String elemName = step.getExit().getClass().getSimpleName();
        cell = new Cell(elemName.substring(0, elemName.length() - 5));
      }

      elements.put(point, cell);
    }

    return elements;
  }

  public void loadLocation() {
    this.getLocationView().addElements();
  }

  public void addHero(HeroView heroView) {
    this.getLocationView().initHero(heroView);
  }
}
