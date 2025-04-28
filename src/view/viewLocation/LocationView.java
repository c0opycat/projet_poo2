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

/**
 * LocationView is a class that represents the view of a level in the game.
 * It extends GridPane and contains methods to manage the display of the level.
 * It is responsible for displaying the cells of the level and the hero.
 * It also manages the movements of the hero in the level.
 * @author L. Cooper
 * @author A. Bertrand-Bernard
 */

public class LocationView extends GridPane {

  private final LocationController locationController;
  private HeroView heroView;
  private final MovementsView movementsView;

  public LocationView(LocationController locationController) {
    this.locationController = locationController;
    this.heroView = null;
    this.movementsView = new MovementsView(this);
  }

  /**
   * getMovementsView is a method that returns the MovementsView object.
   * @return MovementsView object
   */
  public MovementsView getMovementsView() {
    return this.movementsView;
  }

  /**
   * addHandlers is a method that adds the handlers to the MovementsView object.
   * its use for the manage the movements of the hero in the level.
   * @param scene the MainScene object
   */
  public void addHandlers(MainScene scene) {
    this.getMovementsView().addHandlers(scene);
  }

  /**
   * removeHandlers is a method that removes the handlers from the MovementsView object.
   * its use for remove the handlers at the end of the game.
   * @param mainScene the MainScene object
   */
  public void removeHandlers(MainScene mainScene) {
    this.getMovementsView().removeHandlers(mainScene);
  }

  /**
   * getHeroView is a method that returns the HeroView object.
   * @return HeroView object
   */
  public HeroView getHeroView() {
    return this.heroView;
  }

  /**
   * setHeroView is a method that sets the HeroView object.
   * @param heroView the HeroView object
   */
  public void setHeroView(HeroView heroView) {
    this.heroView = heroView;
  }

  /**
   * getLocationController is a method that returns the LocationController object.
   * @return LocationController object
   */
  public LocationController getLocationController() {
    return this.locationController;
  }

  /**
   * getCell is a method that returns the Cell object at the specified coordinates.
   * @param x the x coordinate
   * @param y the y coordinate
   * @return Cell object at the specified coordinates
   */
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

  /**
   * getLocElements is a method that returns the HashMap of elements in the level.
   * @return HashMap of elements in the level
   */
  public HashMap<Point, Cell> getLocElements() {
    return this.getLocationController().getLocElements();
  }

  /**
   * addElements is a method that adds the elements (exits and items) to the level.
   * It creates the cells and adds them to the GridPane.
   */
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

  /**
   * setGridConstraints is a method that sets the constraints of the GridPane.
   * It sets the width and height of the cells in the GridPane.
   * @param width the width of the GridPane
   * @param height the height of the GridPane
   */
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

  /**
   * initHero is a method that initializes the hero in the level.
   * It adds the hero to the cell at the default coordinates.
   * If the cell is already occupied, it finds an empty cell and adds the hero there.
   * @param heroView the HeroView object
   */
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

  /**
   * removeHero is a method that removes the hero from the level.
   * It deletes the image of the hero and removes the element from the cell.
   */
  public void removeHero() {
    Cell cell =
      this.getCell(
          (int) this.getHeroView().getActualCoord().getX(),
          (int) this.getHeroView().getActualCoord().getY()
        );
    cell.deleteImage(this.getHeroView());
    cell.removeElement();
  }

  /**
   * moveHero is a method that moves the hero in the level.
   * It checks if the new cell is empty and moves the hero there.
   * @param direction the direction to move the hero
   */
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
