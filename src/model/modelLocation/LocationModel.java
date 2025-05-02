package model.modelLocation;

import controller.controllerLocation.LocationController;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import model.modelCharacter.modelMonster.MonsterModel;
import model.modelGame.GameMapModel;
import model.modelGame.GameModel;
import model.modelGame.MessageEnModel;
import model.modelItem.*;

/**
 * Represents a location in the game world.
 * <p>
 * A LocationModel contains information about its size, name, items, exits,
 * local map structure, and optionally a monster. Locations also support
 * initialization logic and exit/item placement.
 * </p>
 */
public class LocationModel {

  /** Default width of the location grid. */
  private static final int DEF_WIDTH = 10;

  /** Default height of the location grid. */
  private static final int DEF_HEIGHT = 10;

  /** Width of the location grid. */
  private int width;

  /** Height of the location grid. */
  private int height;

  /** Name of the location. */
  public String name;

  /** The grid mapping points to steps (with possible items or exits). */
  public HashMap<Point, StepModel> locMap;

  /** Exits leading to other locations, mapped by integer keys. */
  public Map<Integer, ExitModel> exits;

  /** List of items currently in the location. */
  public ArrayList<ItemModel> itemList;

  /** Description of the location. */
  public String description;

  /** The monster present in this location, if any. */
  public MonsterModel monster;

  /** Controller associated with this location. */
  private final LocationController locationController;

  /**
   * Creates a location with a given name.
   * Random items and possibly a monster are generated.
   * @param name the name of the location
   */
  public LocationModel(String name) {
    this.width = DEF_WIDTH;
    this.height = DEF_HEIGHT;
    this.exits = new HashMap<>();
    this.name = name;
    this.locMap = new HashMap<>();
    this.itemList = new ArrayList<>();
    this.locationController = new LocationController(this);
    this.monster = new Random().nextInt(2) == 0
        ? null
        : MonsterModel.randMonster();
  }

  /**
   * Creates a location with all attributes specified.
   * @param name the name of the location
   * @param width grid width
   * @param height grid height
   * @param exits map of exits
   * @param locMap grid mapping of steps
   * @param itemList list of items
   * @param monster monster present in the location (optional)
   */
  public LocationModel(
      String name,
      int width,
      int height,
      Map<Integer, ExitModel> exits,
      HashMap<Point, StepModel> locMap,
      ArrayList<ItemModel> itemList,
      MonsterModel monster) {
    this.width = width;
    this.height = height;
    this.name = name;
    this.exits = exits;
    this.locMap = locMap;
    this.itemList = itemList;
    this.monster = monster;
    this.locationController = new LocationController(this);
  }

  public LocationModel(
      int width,
      int height,
      String name,
      HashMap<Point, StepModel> locMap,
      Map<Integer, ExitModel> exits,
      ArrayList<ItemModel> itemList,
      String description) {
        this.width = width;
        this.height = height;
        this.name = name;
        this.description = description;
        this.exits = exits;
        this.locMap = locMap;
        this.itemList = itemList;
        this.locationController = new LocationController(this);
  }

  /**
   * Adds an exit to the location.
   * @param e the exit to add
   * @param key unique key for the exit
   * @param x X coordinate where the exit is placed
   * @param y Y coordinate where the exit is placed
   */
  public void addExit(ExitModel e, Integer key, int x, int y) {
    if (e.start == this && !this.exits.containsKey(key)) {
      this.exits.put(key, e);
      this.setNewStep(x, y, e);
      e.setStartX(x);
      e.setStartY(y);
    }
  }

  /**
   * Sets multiple exits in the location.
   * @param exits array of exits to add
   */
  public void setExits(ExitModel[] exits) {
    for (int i = 0; i < exits.length; i++) {
      Point p = this.getRandomEdgeFreeStepCoord();
      if (p != null) {
        int x = (int) p.getX();
        int y = (int) p.getY();
        this.addExit(exits[i], i, x, y);
      }
    }
  }

  /**
   * Initializes the location with three random items.
   * This method ensures that at least one container (like a chest or crate)
   * is placed in the location if possible. After a container is found,
   * only non-container items will be generated for the remaining slots.
   * Each item is placed at a random unoccupied position within the location.
   * If no free positions are available, some items might not be placed.
   */
  public void initItems() {
    boolean hasContainer = false;

    for (int i = 0; i < 3; i++) {
      ItemModel item = hasContainer
          ? ItemModel.NonContainerRI()
          : ItemModel.randomItem();
      if (!hasContainer && ItemModel.isContainer(item)) {
        hasContainer = true;
      }
      Point p = this.getRandomFreeStepCoord();
      if (p != null) {
        this.addItem(item, p);
      }
    }
  }

  /**
   * Returns the list of exits in this location.
   * @return list of ExitModel
   */
  public ArrayList<ExitModel> getExits() {
    ArrayList<ExitModel> res = new ArrayList<>();
    for (int i = 0; i < this.exits.size(); i++) {
      res.add(this.exits.get(i));
    }
    return res;
  }

  /**
   * Returns the monster present in the location, if any.
   * @return the monster
   */
  public MonsterModel getMonster() {
    return this.monster;
  }

  /**
   * Removes the monster if it has been knocked out.
   * @param game the game model
   */
  public void removeMonsterIfKO(GameModel game) {
    if (this.monster != null && this.monster.isKO()) {
      game.addKilledMonster(this.monster);
      this.monster = null;
    }
  }

  /**
   * Sets a monster in this modelLocation.
   * 
   * @param m the monster to set
   */
  public void setMonster(MonsterModel m) {
    this.monster = m;
  }

  /**
   * Removes an item from a specific point in the location.
   * @param point the coordinates that contains the item to remove
   */
  public void removeItem(Point point) {
    if (this.getLocMap().get(point).getItem() != null) {
      this.getLocMap().remove(point);
    }
  }

  /**
   * Adds an item to the location.
   * 
   * @param item  the item to add
   * @param coord the coordinates where the item should be placed
   */
  public void addItem(ItemModel item, Point coord) {
    this.itemList.add(item);
    Point p = coord;
    if (this.locMap.containsKey(p)) {
      p = this.getRandomFreeStepCoord();

      if (p == null) {
        System.out.println(MessageEnModel.locFull());
      }
    }

    // this.locMap.put(p, new StepModel(item));
    this.setNewStep((int) p.getX(), (int) p.getY(), item);
  }

  /**
   * Returns all free points in the location.
   * @return list of free points
   */
  public ArrayList<Point> getAllFreePoints() {
    ArrayList<Point> allPoints = new ArrayList<>();
    for (int i = 0; i < this.getWidth(); i++) {
      for (int j = 0; j < this.getHeight(); j++) {
        Point p = new Point(i, j);
        if (!this.getLocMap().containsKey(p)) {
          allPoints.add(p);
        }
      }
    }

    Collections.shuffle(allPoints);

    return allPoints;
  }

  /**
   * Returns all available edge points for placing exits.
   * @return list of edge points
   */
  public ArrayList<Point> getAllFreeExitsPoints() {
    ArrayList<Point> allPoints = new ArrayList<>();

    for (int i = 0; i < this.getWidth(); i++) {
      allPoints.add(new Point(i, 0));
    }

    for (int i = 0; i < this.getWidth(); i++) {
      allPoints.add(new Point(i, this.getHeight() - 1));
    }

    for (int j = 1; j < this.getHeight() - 1; j++) {
      allPoints.add(new Point(0, j));
    }

    for (int j = 1; j < this.getHeight() - 1; j++) {
      allPoints.add(new Point(this.getWidth() - 1, j));
    }

    Collections.shuffle(allPoints);

    return allPoints;
  }

  /**
   * Returns a random free edge coordinate for an exit.
   * @return random free edge point
   */
  public Point getRandomEdgeFreeStepCoord() {
    ArrayList<Point> allPoints = this.getAllFreeExitsPoints();

    Point p = null;

    for (int i = 0; i < allPoints.size(); i++) {
      p = allPoints.get(i);
      if (isPointFree(p)) {
        break;
      }
    }

    return p;
  }

  /**
   * Returns a random free coordinate.
   * @return random free point
   */
  public Point getRandomFreeStepCoord() {
    ArrayList<Point> allPoints = this.getAllFreePoints();

    Point p = null;

    for (int i = 0; i < allPoints.size(); i++) {
      p = allPoints.get(i);
      if (isPointFree(p)) {
        return p;
      }
    }

    return p;
  }

  /**
   * Checks whether a point in the location is free.
   * @param coord point to check
   * @return true if free
   */
  public boolean isPointFree(Point coord) {
    return !this.getLocMap().containsKey(coord);
  }

  /**
   * Displays the description of the location
   */
  public void displayLocation() {
    System.out.println(MessageEnModel.locationDescription(this));
  }

  /**
   * Gets the name of the location.
   * @return location name
   */
  public String getName() {
    return this.name;
  }

  /**Gets the description of the modelLocation 
   * @return the description
  */
  public String getDescription(){
    return this.description;
  }

  /**
   * Gets the width of the location
   * @return location width
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Returns the map of the location.
   * The map associates grid points with steps (which may contain items or exits).
   * @return the location's map of points to StepModel
   */
  public HashMap<Point, StepModel> getLocMap() {
    return this.locMap;
  }

  /**
   * Returns the height of the location grid.
   * @return the height of the location
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Returns the controller responsible for managing the location.
   * @return the LocationController instance
   */
  public LocationController getLocationController() {
    return this.locationController;
  }

  /**
   * Adds or updates a step at the specified coordinates with an item.
   * @param x the x-coordinate
   * @param y the y-coordinate
   * @param item the item to be placed at the given coordinates
   */
  public void setNewStep(int x, int y, ItemModel item) {
    this.locMap.put(new Point(x, y), new StepModel(item));
  }

  /**
   * Adds or updates a step at the specified coordinates with an exit.
   * @param x the x-coordinate
   * @param y the y-coordinate
   * @param exit the exit to be placed at the given coordinates
   */
  public void setNewStep(int x, int y, ExitModel exit) {
    this.locMap.put(new Point(x, y), new StepModel(exit));
  }

  /**
   * Returns a human-readable name representing this location.
   * This overrides the default toString method and returns a more user-friendly name
   * based on the location's identifier.
   * @return the string representation of the location
   */
  @Override
  public String toString() {
    return switch (name) {
      case "BEAULIEU" -> "Beaulieu ";
      case "MILETRIE" -> "Milétrie";
      case "CITY_CENTER" -> "the city center ";
      case "COURONNERIES" -> "the Couronneries ";
      case "GIBAUDERIE" -> "the Gibauderie ";
      case "WEST_POITIERS" -> "west Poitiers ";
      case "SOUTH_POITIERS" -> "south Poitiers ";
      case "PONT_NEUF" -> "the Pont neuf ";
      case "SAINT_ELOI" -> "Saint Éloi ";
      case "TROIS_CITES" -> "the trois cités ";
      case "NORTH_POITIERS" -> "north Poitiers ";
      case "MONTBERNAGE" -> "Montbernage ";
      case "FINAL_EXIT" -> "the limits of the city ";
      default -> name;
    };
  }

  // Location initialization methods for each named modelLocation
  /**
   * Sets exits and items for North Poitiers.
   */
  public static void northPoitiers(LocationModel location) {
    ExitModel e1 = new ExitModel(location, GameMapModel.locations.get(5));
    location.setExits(new ExitModel[] { e1 });
    location.initItems();
  }

  /**
   * Sets exits and items for Beaulieu.
   */
  public static void beaulieu(LocationModel location) {
    ExitModel[] exits = {
        new ExitModel(location, GameMapModel.locations.get(7)),
        new ExitModel(location, GameMapModel.locations.get(8)),
        new ExitModel(location, GameMapModel.locations.get(10)),
        new ExitModel(location, GameMapModel.locations.get(11)),
    };
    location.setExits(exits);
    location.initItems();
  }

  /**
   * Sets exits and items for City Center.
   */
  public static void cityCenter(LocationModel location) {
    ExitModel[] exits = {
        new ExitModel(location, GameMapModel.locations.get(5)),
        new ExitModel(location, GameMapModel.locations.get(6)),
        new ExitModel(location, GameMapModel.locations.get(7)),
    };
    location.setExits(exits);
    location.initItems();
  }

  /**
   * Sets exits and items for Couronneries.
   */
  public static void couronneries(LocationModel location) {
    ExitModel[] exits = {
        new ExitModel(location, GameMapModel.locations.get(10)),
        new ExitModel(location, GameMapModel.locations.get(5)),
        new ExitModel(location, GameMapModel.locations.get(8)),
    };
    location.setExits(exits);
    location.initItems();
  }

  /**
   * Sets exits and items for Gibauderie.
   */
  public static void gibauderie(LocationModel location) {
    ExitModel[] exits = {
        new ExitModel(location, GameMapModel.locations.get(11)),
        new ExitModel(location, GameMapModel.locations.get(1)),
        new ExitModel(location, GameMapModel.locations.get(7)),
    };
    location.setExits(exits);
    location.initItems();
  }

  /**
   * Sets exits and items or West Poitiers.
   */
  public static void westPoitiers(LocationModel location) {
    ExitModel[] exits = {
        new ExitModel(location, GameMapModel.locations.get(0)),
        new ExitModel(location, GameMapModel.locations.get(2)),
        new ExitModel(location, GameMapModel.locations.get(6)),
    };
    location.setExits(exits);
    location.initItems();
  }

  /**
   * Sets exits and items for South Poitiers.
   */
  public static void southPoitiers(LocationModel location) {
    ExitModel[] exits = {
        new ExitModel(location, GameMapModel.locations.get(5)),
        new ExitModel(location, GameMapModel.locations.get(9)),
    };
    location.setExits(exits);
    location.initItems();
  }

  /**
   * Sets exits and items for Pont Neuf.
   */
  public static void pontNeuf(LocationModel location) {
    ExitModel[] exits = {
        new ExitModel(location, GameMapModel.locations.get(9)),
        new ExitModel(location, GameMapModel.locations.get(4)),
        new ExitModel(location, GameMapModel.locations.get(10)),
    };
    location.setExits(exits);
    location.initItems();
  }

  /**
   * Sets exits and items for Saint Éloi.
   */
  public static void saintEloi(LocationModel location) {
    ExitModel[] exits = {
        new ExitModel(location, GameMapModel.locations.get(3)),
        new ExitModel(location, GameMapModel.locations.get(10)),
        new ExitModel(location, GameMapModel.locations.get(1)),
    };
    location.setExits(exits);
    location.initItems();
  }

  /**
   * Sets exits and items or Trois Cités.
   */
  public static void troisCites(LocationModel location) {
    ExitModel[] exits = {
        new ExitModel(location, GameMapModel.locations.get(2)),
        new ExitModel(location, GameMapModel.locations.get(4)),
    };
    location.setExits(exits);
    location.initItems();
  }

  /**
   * Sets exits and items for Montbernage.
   */
  public static void montbernage(LocationModel location) {
    ExitModel[] exits = {
        new ExitModel(location, GameMapModel.locations.get(2)),
        new ExitModel(location, GameMapModel.locations.get(5)),
        new ExitModel(location, GameMapModel.locations.get(7)),
        new ExitModel(location, GameMapModel.locations.get(8)),
    };
    location.setExits(exits);
    location.initItems();
  }

  /**
   * Sets exits and items for Milétrie.
   */
  public static void miletrie(LocationModel location) {
    ExitModel[] exits = {
        new ExitModel(location, GameMapModel.locations.get(4)),
        new ExitModel(location, GameMapModel.locations.get(1)),
        new ExitModel(location, GameMapModel.locations.get(12)),
    };
    location.setExits(exits);
    location.initItems();
  }

  /**
   * Sets exits and items for Final ExitModel.
   */
  public static void finalExit(LocationModel location) {
    ExitModel[] exits = {
        new ExitModel(location, GameMapModel.locations.get(11)),
    };
    location.setExits(exits);
    location.initItems();
  }

  /**
   * Sets the size of the location.
   * @param width new width
   * @param height new height
   */
  public void setSize(int width, int height) {
    this.width = width;
    this.height = height;
  }
}
