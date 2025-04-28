package view.viewLocation;

import controller.controllerLocation.LocationController;
import java.awt.Point;
import java.util.HashMap;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import view.Cell;
import view.MainScene;
import view.viewCharacter.HeroView;
import view.viewGame.MovementsView;

public class LocationView extends GridPane {

  private final LocationController locationController;
  private HeroView heroView;
  private final MovementsView movementsView;

  public LocationView(LocationController locationController) {
    this.locationController = locationController;
    this.heroView = null;
    this.movementsView = new MovementsView(this);
  }

  public MovementsView getMovementsView() {
    return this.movementsView;
  }

  public void addHandlers(MainScene scene) {
    this.getMovementsView().addHandlers(scene);
  }

  public void removeHandlers(MainScene mainScene) {
    this.getMovementsView().removeHandlers(mainScene);
  }

  public HeroView getHeroView() {
    return this.heroView;
  }

  public void setHeroView(HeroView heroView) {
    this.heroView = heroView;
  }

  public LocationController getLocationController() {
    return this.locationController;
  }

  public Cell getCell(int x, int y) {
    int width = this.getLocationController().getWidth();
    int height = this.getLocationController().getHeight();

    if (x < 0 || x >= width || y < 0 || y >= height) {
      return null;
    }

    int index = y * width + x;

    if (index >= getChildren().size()) {
      return null;
    }

    if (getChildren().get(index) instanceof Cell) {
      return (Cell) getChildren().get(index);
    } else {
      return null;
    }
  }

  public HashMap<Point, Cell> getLocElements() {
    return this.getLocationController().getLocElements();
  }

  public void addElements() {
    int width = this.getLocationController().getWidth();
    int height = this.getLocationController().getHeight();
    HashMap<Point, Cell> elements = this.getLocElements();
    this.setGridConstraints(width, height);
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        Cell cell = new Cell();
        cell.setMinWidth(25);
        cell.setMinHeight(25);
        this.add(cell, i, j);
      }
    }

    for (Point point : elements.keySet()) {
      Cell cell = this.getCell((int) point.getX(), (int) point.getY());
      cell.setElement(elements.get(point).getElement());
      cell.addImage();
    }
  }

  private void setGridConstraints(int width, int height) {
    for (int i = 0; i < width; i++) {
      ColumnConstraints colConst = new ColumnConstraints(25); // largeur fixe
      this.getColumnConstraints().add(colConst);
    }
    for (int i = 0; i < height; i++) {
      RowConstraints rowConst = new RowConstraints(25); // hauteur fixe
      this.getRowConstraints().add(rowConst);
    }
  }

  public void initHero(HeroView heroView) {
    int width = this.getLocationController().getWidth();
    int height = this.getLocationController().getHeight();
    Point def_coord = heroView.getDefaultCoord();

    Cell cell = this.getCell((int) def_coord.getX(), (int) def_coord.getY());
    if (cell.getChildren().isEmpty()) {
      cell.addImage(heroView);
      cell.setElement(heroView.getName());
      this.setHeroView(heroView);
    } else {
      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          cell = this.getCell(i, j);

          if (cell.getChildren().isEmpty()) {
            cell.addImage(heroView);
            cell.setElement(heroView.getName());

            this.setHeroView(heroView);
            heroView.setActualCoord(new Point(i, j));
            break;
          }
        }
      }
    }
  }

  public void removeHero() {
    Cell cell =
      this.getCell(
          (int) this.getHeroView().getActualCoord().getX(),
          (int) this.getHeroView().getActualCoord().getY()
        );
    cell.deleteImage(this.getHeroView());
    cell.removeElement();
  }

  public void moveHero(String direction) {
    int heroCoordX = (int) this.getHeroView().getActualCoord().getX();
    int heroCoordY = (int) this.getHeroView().getActualCoord().getY();

    Cell newCell = null;
    Point newCoord = null;

    if (direction == "North") {
      newCell = this.getCell(heroCoordX - 1, heroCoordY);
      newCoord = new Point(heroCoordX - 1, heroCoordY);
    } else if (direction == "South") {
      newCell = this.getCell(heroCoordX + 1, heroCoordY);
      newCoord = new Point(heroCoordX + 1, heroCoordY);
    } else if (direction == "East") {
      newCell = this.getCell(heroCoordX, heroCoordY + 1);
      newCoord = new Point(heroCoordX, heroCoordY + 1);
    } else if (direction == "West") {
      newCell = this.getCell(heroCoordX, heroCoordY - 1);
      newCoord = new Point(heroCoordX, heroCoordY - 1);
    } else {
      System.out.println("Invalid direction");
      return;
    }

    if (newCell != null && newCell.getChildren().isEmpty()) {
      this.removeHero();
      newCell.addImage(this.getHeroView());
      newCell.setElement(this.getHeroView().getName());
      this.getHeroView().setActualCoord(newCoord);
    } else {
      System.out.println("Invalid move");
    }
  }
}
