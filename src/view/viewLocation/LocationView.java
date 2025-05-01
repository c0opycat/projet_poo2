package view.viewLocation;

import controller.controllerLocation.LocationController;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import view.Cell;
import view.viewCharacter.HeroView;
import view.viewCharacter.MonsterView;
import view.viewContainer.ContainerView;
import view.viewGame.GameView;
import view.viewGame.viewCommand.CommandsView;

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
  private GameView gameView;
  private CommandsView commandsView;
  private MonsterView monsterView;
  private Boolean isContainerOpen;

  public LocationView(LocationController locationController) {
    this.locationController = locationController;
    this.gameView = null;
    this.commandsView = null;
    this.isContainerOpen = false;
    this.monsterView = null;
  }

  /**
   * getMovementsView is a method that returns the MovementsView object.
   * @return CommandsView object
   */
  public CommandsView getCommandsView() {
    return this.commandsView;
  }

  /**
   * getHeroView is a method that returns the GameView object.
   * @return GameView object
   */
  public GameView getGameView() {
    return this.gameView;
  }

  /**
   * getHeroView is a method that returns the HeroView object.
   * @return HeroView object
   */
  public HeroView getHeroView() {
    return this.getGameView().getHeroView();
  }

  /**
   * getHeroView is a method that returns the MonsterView object.
   * @return MonsterView object
   */
  public MonsterView getMonsterView() {
    return this.monsterView;
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

    int index = x * width + y;

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

  public Boolean getIsContainerOpen() {
    return this.isContainerOpen;
  }

  /**
   * Sets the CommandsView instance for this location view.
   *
   * @param commandsView the CommandsView instance to set
   */
  public void setCommandsView(CommandsView commandsView) {
    this.commandsView = commandsView;
  }

  /**
   * Sets the MonsterView instance for this location view.
   *
   * @param monsterView the MonsterView instance to set
   */
  public void setMonsterView(MonsterView monsterView) {
    this.monsterView = monsterView;
  }

  /**
   * setGameView is a method that sets the GameView object.
   * @param heroView the GamView object
   */
  public void setGameView(GameView gameView) {
    this.gameView = gameView;
    this.initHero(this.getGameView().getHeroView());

    if (this.getLocationController().hasMonster()) {
      this.getGameView().getCurrentLocationView().placeMonster();
    }
  }

  /**
   * Sets whether the container is open or not.
   *
   * @param b true if the container is open, false otherwise
   */
  public void setIsContainerOpen(boolean b) {
    this.isContainerOpen = b;
  }

  /**
   * setGridConstraints is a method that sets the constraints of the GridPane.
   * It sets the width and height of the cells in the GridPane.
   * @param width the width of the GridPane
   * @param height the height of the GridPane
   */
  private void setGridConstraints(int width, int height) {
    for (int i = 0; i < width; i++) {
      ColumnConstraints colConst = new ColumnConstraints(width > 10 ? 25 : 30); // largeur fixe
      this.getColumnConstraints().add(colConst);
    }
    for (int i = 0; i < height; i++) {
      RowConstraints rowConst = new RowConstraints(height > 10 ? 25 : 30); // hauteur fixe
      this.getRowConstraints().add(rowConst);
    }
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
        cell.setMinWidth(width > 10 ? 25 : 30);
        cell.setMinHeight(height > 10 ? 25 : 30);
        this.add(cell, i, j);
      }
    }

    for (Point point : elements.keySet()) {
      Cell cell = this.getCell((int) point.getX(), (int) point.getY());

      String element = elements.get(point).getElement();
      cell.setElement(element);

      if (element.equals("Exit")) {
        ExitView exitView = (ExitView) elements.get(point).getImageView();
        cell.addImage(exitView);
      } else {
        cell.addImage();
      }
      int i = (int) (point.getX());
      int j = (int) (point.getY());
      if (
        cell.getElement() != null &&
        (cell.getElement().equalsIgnoreCase("chest") ||
          cell.getElement().equalsIgnoreCase("crate") ||
          cell.getElement().equalsIgnoreCase("backpack"))
      ) {
        this.addContainerHandler(cell, i, j);
      }
    }
  }

  /**
   * addContainerHandler is a method that adds a mouse click handler to a cell for interacting with containers.
   * @param cell the cell to add the handler to
   * @param i the x-coordinate of the cell
   * @param j the y-coordinate of the cell
   */
  private void addContainerHandler(Cell cell, int i, int j) {
    cell.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
      HeroView heroView = this.getHeroView();
      Point heroCoord = heroView.getActualCoord();
      int heroX = (int) heroCoord.getX();
      int heroY = (int) heroCoord.getY();

      VBox containerBox = this.getGameView().getContainerBox();
      Label containerLabel = (Label) containerBox.getChildren().get(0);
      ContainerView containerView = (ContainerView) containerBox
        .getChildren()
        .get(1);
      if (
        !this.getIsContainerOpen() && isHeroBesidesContainer(heroX, heroY, i, j)
      ) {
        if (!this.getCommandsView().getIsBackpackOpen()) {
          containerView
            .getContainerController()
            .setContainerModel(new Point(i, j));

          containerLabel.setText(cell.getElement());
          this.setIsContainerOpen(true);
          containerView.setX(i);
          containerView.setY(j);
          containerView.getChildren().clear();
          containerView.addItemList(
            false,
            containerView.getContainerController().getItems(new Point(i, j))
          );
        }
      } else if (
        this.getIsContainerOpen() && isHeroBesidesContainer(heroX, heroY, i, j)
      ) {
        if (!this.getCommandsView().getIsBackpackOpen()) {
          containerView.getContainerController().setContainerModel(null);

          this.getCommandsView().setIsBackpackOpen(false);
          this.setIsContainerOpen(false);
          containerView.getChildren().clear();
          containerLabel.setText(null);
        }
      }

      e.consume();
    });
  }

  /**
   * Checks if the hero is adjacent to a container.
   *
   * @param heroX the x-coordinate of the hero
   * @param heroY the y-coordinate of the hero
   * @param contX the x-coordinate of the container
   * @param contY the y-coordinate of the container
   * @return true if the hero is adjacent to the container, false otherwise
   */
  public boolean isHeroBesidesContainer(
    int heroX,
    int heroY,
    int contX,
    int contY
  ) {
    return (
      (contX - 1 == heroX && contY == heroY) ||
      (contX == heroX && contY - 1 == heroY) ||
      (contX + 1 == heroX && contY == heroY) ||
      (contX == heroX && contY + 1 == heroY)
    );
  }

  /**
   * Gets the items adjacent to the hero.
   *
   * @param heroX the x-coordinate of the hero
   * @param heroY the y-coordinate of the hero
   * @return a list of directions where items are located
   */
  public ArrayList<String> getItemsBesidesHero(int heroX, int heroY) {
    ArrayList<String> cellList = new ArrayList<>();

    String lang = this.getGameView().getLang().getCurr_lang();

    if (
      getCell(heroX - 1, heroY) != null &&
      !getCell(heroX - 1, heroY).getChildren().isEmpty()
    ) {
      String elem = getCell(heroX - 1, heroY).getElement();
      if (
        !elem.equals("Exit") &&
        !elem.equals("Chest") &&
        !elem.equals("Backpack")
      ) {
        cellList.add(lang.equals("EN") ? "left" : "gauche");
      }
    }
    if (
      getCell(heroX, heroY - 1) != null &&
      !getCell(heroX, heroY - 1).getChildren().isEmpty()
    ) {
      String elem = getCell(heroX, heroY - 1).getElement();
      if (
        !elem.equals("Exit") &&
        !elem.equals("Chest") &&
        !elem.equals("Backpack")
      ) {
        cellList.add(lang.equals("EN") ? "above" : "au-dessus");
      }
    }
    if (
      getCell(heroX + 1, heroY) != null &&
      !getCell(heroX + 1, heroY).getChildren().isEmpty()
    ) {
      String elem = getCell(heroX + 1, heroY).getElement();
      if (
        !elem.equals("Exit") &&
        !elem.equals("Chest") &&
        !elem.equals("Backpack")
      ) {
        cellList.add(lang.equals("EN") ? "right" : "droit");
      }
    }
    if (
      getCell(heroX, heroY + 1) != null &&
      !getCell(heroX, heroY + 1).getChildren().isEmpty()
    ) {
      String elem = getCell(heroX, heroY + 1).getElement();
      if (
        !elem.equals("Exit") &&
        !elem.equals("Chest") &&
        !elem.equals("Backpack")
      ) {
        cellList.add(lang.equals("EN") ? "below" : "en dessous");
      }
    }

    return cellList;
  }

  /**
   * Gets the exits adjacent to the hero.
   *
   * @param heroX the x-coordinate of the hero
   * @param heroY the y-coordinate of the hero
   * @return a list of directions where exits are located
   */
  public HashMap<String, String> getExitsBesidesHero(int heroX, int heroY) {
    HashMap<String, String> cellList = new HashMap<>();

    String lang = this.getGameView().getLang().getCurr_lang();

    if (
      getCell(heroX - 1, heroY) != null &&
      !getCell(heroX - 1, heroY).getChildren().isEmpty()
    ) {
      String elem = getCell(heroX - 1, heroY).getElement();
      if (elem.equals("Exit")) {
        ExitView exitView =
          ((ExitView) getCell(heroX - 1, heroY).getImageView());
        cellList.put(
          exitView.getDestinationName(),
          lang.equals("EN") ? "left" : "gauche"
        );
      }
    }
    if (
      getCell(heroX, heroY - 1) != null &&
      !getCell(heroX, heroY - 1).getChildren().isEmpty()
    ) {
      String elem = getCell(heroX, heroY - 1).getElement();
      if (elem.equals("Exit")) {
        ExitView exitView =
          ((ExitView) getCell(heroX, heroY - 1).getImageView());
        cellList.put(
          exitView.getDestinationName(),
          lang.equals("EN") ? "above" : "au-dessus"
        );
      }
    }
    if (
      getCell(heroX + 1, heroY) != null &&
      !getCell(heroX + 1, heroY).getChildren().isEmpty()
    ) {
      String elem = getCell(heroX + 1, heroY).getElement();

      if (elem.equals("Exit")) {
        ExitView exitView =
          ((ExitView) getCell(heroX + 1, heroY).getImageView());
        cellList.put(
          exitView.getDestinationName(),
          lang.equals("EN") ? "right" : "droite"
        );
      }
    }
    if (
      getCell(heroX, heroY + 1) != null &&
      !getCell(heroX, heroY + 1).getChildren().isEmpty()
    ) {
      String elem = getCell(heroX, heroY + 1).getElement();

      if (elem.equals("Exit")) {
        ExitView exitView =
          ((ExitView) getCell(heroX, heroY + 1).getImageView());
        cellList.put(
          exitView.getDestinationName(),
          lang.equals("EN") ? "below" : "en dessous"
        );
      }
    }

    return cellList;
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
    heroView.setActualCoord(def_coord);

    Cell cell = this.getCell((int) def_coord.getX(), (int) def_coord.getY());
    if (cell.getChildren().isEmpty()) {
      cell.addImage(heroView);
      cell.setElement(heroView.getName());
    } else {
      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          cell = this.getCell(i, j);

          if (cell.getChildren().isEmpty()) {
            cell.addImage(heroView);
            cell.setElement(heroView.getName());

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

  public Point placeMonster() {
    MonsterView monsterView = new MonsterView(this.getGameView(), this);
    this.setMonsterView(monsterView);

    Random random = new Random();

    int x = (int) this.getColumnCount() / 2;
    int y = (int) this.getRowCount() / 2;

    Cell cell = this.getCell(x, y);

    int i = 0;
    int max = this.getColumnCount() * this.getRowCount();

    while (i <= max && cell.getElement() != null) {
      cell = this.getCell(x, y);

      x = random.nextInt(this.getColumnCount());
      y = random.nextInt(this.getRowCount());

      i++;
    }

    if (cell != null) {
      cell.setElement(monsterView.getType());
      cell.addImage(monsterView);
      monsterView.setCoord(new Point(x, y));
      monsterView.getMonsterController().updateMonsterDescription();
    }

    return null;
  }

  public void removeMonster() {
    MonsterView monsterView = this.getMonsterView();

    int x = (int) monsterView.getCoord().getX();
    int y = (int) monsterView.getCoord().getY();

    Cell monsterCell = this.getCell(x, y);

    monsterCell.removeElement();
    monsterCell.deleteImage(monsterView);

    this.setMonsterView(null);
    this.getLocationController().removeMonster();
  }

  public void addItem(String elem, Point point) {
    int x = (int) point.getX();
    int y = (int) point.getY();

    Cell cell = getCell(x, y);

    cell.setElement(elem);
    cell.addImage();
  }

  public void removeItem(Point point) {
    int x = (int) point.getX();
    int y = (int) point.getY();

    Cell cell = getCell(x, y);
    cell.getChildren().remove(cell.getChildren().size() - 1);
    cell.removeElement();
  }

  public void updateItems() {
    this.getLocationController().updateItems();
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
      newCell = this.getCell(heroCoordX, heroCoordY - 1);
      newCoord = new Point(heroCoordX, heroCoordY - 1);
    } else if (direction == "South") {
      newCell = this.getCell(heroCoordX, heroCoordY + 1);
      newCoord = new Point(heroCoordX, heroCoordY + 1);
    } else if (direction == "East") {
      newCell = this.getCell(heroCoordX + 1, heroCoordY);
      newCoord = new Point(heroCoordX + 1, heroCoordY);
    } else if (direction == "West") {
      newCell = this.getCell(heroCoordX - 1, heroCoordY);
      newCoord = new Point(heroCoordX - 1, heroCoordY);
    } else {
      System.out.println("Invalid direction");
      return;
    }

    if (newCell != null && newCell.getChildren().isEmpty()) {
      this.removeHero();
      newCell.addImage(this.getHeroView());
      newCell.setElement(this.getHeroView().getName());
      this.getHeroView().setActualCoord(newCoord);

      if (
        this.getMonsterView() != null && this.getMonsterView().isInAttackRange()
      ) {
        this.getMonsterView().attack();
      }
    }
  }
}
