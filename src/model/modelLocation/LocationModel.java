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
import model.modelGame.MessageEnModel;
import model.modelItem.*;

/**
 * Represents a modelLocation in the modelGame world.
 * A modelLocation can have items, a monster, and exits to other locations.
 */
public class LocationModel {

  private static final int DEF_WIDTH = 30;
  private static final int DEF_HEIGHT = 20;
  private int width;
  private int height;
  public String name;
  public HashMap<Point, StepModel> locMap;
  public Map<Integer, ExitModel> exits;
  public ArrayList<ItemModel> itemList;
  public String description;
  public MonsterModel monster;
  private final LocationController locationController;

  /**
   * Constructs a new modelLocation with a name, random items, and possibly a random monster.
   * @param name : the name of the modelLocation
   */
  public LocationModel(String name) {
    this.width = DEF_WIDTH;
    this.height = DEF_HEIGHT;
    this.exits = new HashMap<>();
    this.name = name;
    this.locMap = new HashMap<>();
    this.itemList = new ArrayList<>();
    this.locationController = new LocationController(this);

    boolean hasContainer = false;

    for (int i = 0; i < 3; i++) {
      System.out.println(i);
      ItemModel item = hasContainer
        ? ItemModel.NonContainerRI()
        : ItemModel.randomItem();
      if (!hasContainer && ItemModel.isContainer(item)) {
        hasContainer = true;
      }
      Point p = this.getRandomFreeStepCoord();
      System.out.println(p);
      if (p != null) {
        this.addItem(item, p);
      }
    }

    this.monster = new Random().nextInt(2) == 0
      ? null
      : MonsterModel.randMonster();
  }

  /**
   * Constructs a new modelLocation with a name, random items, and possibly a random monster.
   * @param name : the name of the modelLocation
   */
  public LocationModel(
    String name,
    int width,
    int height,
    Map<Integer, ExitModel> exits,
    HashMap<Point, StepModel> locMap,
    ArrayList<ItemModel> itemList,
    MonsterModel monster
  ) {
    this.width = width;
    this.height = height;
    this.name = name;
    this.exits = exits;
    this.locMap = locMap;
    this.itemList = itemList;
    this.monster = monster;
    this.locationController = new LocationController(this);
  }

  /**
   * Adds an exit to the modelLocation, only if the key isn't used and the exit starts from this modelLocation.
   * @param e the exit to add
   * @param key the unique key for this exit
   */
  public void addExit(ExitModel e, Integer key) {
    if (e.start == this && !this.exits.containsKey(key)) {
      this.exits.put(key, e);
    }
  }

  /**
   * Sets the exits for the modelLocation.
   * @param exits array of exits
   */
  public void setExits(ExitModel[] exits) {
    for (int i = 0; i < exits.length; i++) {
      this.addExit(exits[i], i);
    }
  }

  /**
   * Gets all the exits from the modelLocation.
   * @return a list of exits
   */
  public ArrayList<ExitModel> getExits() {
    ArrayList<ExitModel> res = new ArrayList<>();
    for (int i = 0; i < this.exits.size(); i++) {
      res.add(this.exits.get(i));
    }
    return res;
  }

  /**
   * Returns the monster in the modelLocation, if any.
   * @return the monster
   */
  public MonsterModel getMonster() {
    return this.monster;
  }

  /**
   * Removes the monster if it has been knocked out.
   */
  public void removeMonsterIfKO() {
    if (this.monster != null && this.monster.isKO()) {
      this.monster = null;
    }
  }

  /**
   * Sets a monster in this modelLocation.
   * @param m the monster to set
   */
  public void setMonster(MonsterModel m) {
    this.monster = m;
  }

  /**
   * Removes an item from the location.
   * @param point the coordinates that contains the item to remove
   */
  public void removeItem(Point point) {
    if (this.getLocMap().get(point).getItem() != null) {
      this.getLocMap().remove(point);
    }
  }

  /**
   * Adds an item to the location.
   * @param item the item to add
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

    this.locMap.put(p, new StepModel(item));
  }

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

    for (Point p : allPoints) {
      System.out.println(p);
    }

    return allPoints;
  }

  public Point getRandomFreeStepCoord() {
    ArrayList<Point> allPoints = this.getAllFreePoints();

    Point p = null;

    for (int i = 0; i < allPoints.size(); i++) {
      p = allPoints.get(i);
      if (isPointFree(p)) {
        break;
      }
    }

    return p;
  }

  public boolean isPointFree(Point coord) {
    return this.getLocMap().containsKey(coord);
  }

  /**
   * Displays the description of the modelLocation (not used anymore)
   */
  public void displayLocation() {
    System.out.println(MessageEnModel.locationDescription(this));
  }

  /**
   * Gets the name of the modelLocation.
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns a human-readable name based on the modelLocation's identifier.
   * @return the modelLocation name as a string
   */

  public int getWidth() {
    return this.width;
  }

  public HashMap<Point, StepModel> getLocMap() {
    return this.locMap;
  }

  public int getHeight() {
    return this.height;
  }

  public LocationController getLocationController() {
    return this.locationController;
  }

  public void setNewStep(int x, int y, ItemModel item) {
    this.locMap.put(new Point(x, y), new StepModel(item));
  }

  public void setNewStep(int x, int y, ExitModel exit) {
    this.locMap.put(new Point(x, y), new StepModel(exit));
  }

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
   * Sets exits for North Poitiers.
   */
  public static void northPoitiers(LocationModel location) {
    ExitModel e1 = new ExitModel(location, GameMapModel.locations.get(5));
    location.setExits(new ExitModel[] { e1 });
  }

  /**
   * Sets exits for Beaulieu.
   */
  public static void beaulieu(LocationModel location) {
    ExitModel[] exits = {
      new ExitModel(location, GameMapModel.locations.get(7)),
      new ExitModel(location, GameMapModel.locations.get(8)),
      new ExitModel(location, GameMapModel.locations.get(10)),
      new ExitModel(location, GameMapModel.locations.get(11)),
    };
    location.setExits(exits);
  }

  /**
   * Sets exits for City Center.
   */
  public static void cityCenter(LocationModel location) {
    ExitModel[] exits = {
      new ExitModel(location, GameMapModel.locations.get(5)),
      new ExitModel(location, GameMapModel.locations.get(6)),
      new ExitModel(location, GameMapModel.locations.get(7)),
    };
    location.setExits(exits);
  }

  /**
   * Sets exits for Couronneries.
   */
  public static void couronneries(LocationModel location) {
    ExitModel[] exits = {
      new ExitModel(location, GameMapModel.locations.get(10)),
      new ExitModel(location, GameMapModel.locations.get(5)),
      new ExitModel(location, GameMapModel.locations.get(8)),
    };
    location.setExits(exits);
  }

  /**
   * Sets exits for Gibauderie.
   */
  public static void gibauderie(LocationModel location) {
    ExitModel[] exits = {
      new ExitModel(location, GameMapModel.locations.get(11)),
      new ExitModel(location, GameMapModel.locations.get(1)),
      new ExitModel(location, GameMapModel.locations.get(7)),
    };
    location.setExits(exits);
  }

  /**
   * Sets exits for West Poitiers.
   */
  public static void westPoitiers(LocationModel location) {
    ExitModel[] exits = {
      new ExitModel(location, GameMapModel.locations.get(0)),
      new ExitModel(location, GameMapModel.locations.get(2)),
      new ExitModel(location, GameMapModel.locations.get(6)),
    };
    location.setExits(exits);
  }

  /**
   * Sets exits for South Poitiers.
   */
  public static void southPoitiers(LocationModel location) {
    ExitModel[] exits = {
      new ExitModel(location, GameMapModel.locations.get(5)),
      new ExitModel(location, GameMapModel.locations.get(9)),
    };
    location.setExits(exits);
  }

  /**
   * Sets exits for Pont Neuf.
   */
  public static void pontNeuf(LocationModel location) {
    ExitModel[] exits = {
      new ExitModel(location, GameMapModel.locations.get(9)),
      new ExitModel(location, GameMapModel.locations.get(4)),
      new ExitModel(location, GameMapModel.locations.get(10)),
    };
    location.setExits(exits);
  }

  /**
   * Sets exits for Saint Éloi.
   */
  public static void saintEloi(LocationModel location) {
    ExitModel[] exits = {
      new ExitModel(location, GameMapModel.locations.get(3)),
      new ExitModel(location, GameMapModel.locations.get(10)),
      new ExitModel(location, GameMapModel.locations.get(1)),
    };
    location.setExits(exits);
  }

  /**
   * Sets exits for Trois Cités.
   */
  public static void troisCites(LocationModel location) {
    ExitModel[] exits = {
      new ExitModel(location, GameMapModel.locations.get(2)),
      new ExitModel(location, GameMapModel.locations.get(4)),
    };
    location.setExits(exits);
  }

  /**
   * Sets exits for Montbernage.
   */
  public static void montbernage(LocationModel location) {
    ExitModel[] exits = {
      new ExitModel(location, GameMapModel.locations.get(2)),
      new ExitModel(location, GameMapModel.locations.get(5)),
      new ExitModel(location, GameMapModel.locations.get(7)),
      new ExitModel(location, GameMapModel.locations.get(8)),
    };
    location.setExits(exits);
  }

  /**
   * Sets exits for Milétrie.
   */
  public static void miletrie(LocationModel location) {
    ExitModel[] exits = {
      new ExitModel(location, GameMapModel.locations.get(4)),
      new ExitModel(location, GameMapModel.locations.get(1)),
      new ExitModel(location, GameMapModel.locations.get(12)),
    };
    location.setExits(exits);
  }

  /**
   * Sets exits for Final ExitModel.
   */
  public static void finalExit(LocationModel location) {
    ExitModel[] exits = {
      new ExitModel(location, GameMapModel.locations.get(11)),
    };
    location.setExits(exits);
  }
}
