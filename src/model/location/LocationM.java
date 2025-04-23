package model.location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import model.character.monster.Monster;
import model.game.GameMapM;
import model.game.MessageM;
import model.item.*;

/**
 * Represents a location in the game world.
 * A location can have items, a monster, and exits to other locations.
 */
public class LocationM {

  private static final int DEF_WIDTH = 30;
  private static final int DEF_HEIGHT = 20;
  public int width;
  public int height;
  public String name;
  public Step[][] locMap;
  public Map<Integer, Exit> exits;
  public ArrayList<Item> itemList;
  public String description;
  public Monster monster;

  /**
   * Constructs a new location with a name, random items, and possibly a random monster.
   * @param name : the name of the location
   */
  public LocationM(String name) {
    this.width = DEF_WIDTH;
    this.height = DEF_HEIGHT;
    this.exits = new HashMap<>();
    this.name = name;
    this.locMap = new Step[width][height];
    this.itemList = new ArrayList<>();
    boolean hasContainer = false;

    for (int i = 0; i < 3; i++) {
      Item item = hasContainer ? Item.NonContainerRI() : Item.randomItem();
      if (!hasContainer && Item.isContainer(item)) {
        hasContainer = true;
      }
      this.addItem(item);
    }

    this.monster = new Random().nextInt(2) == 0 ? null : Monster.randMonster();
  }

  /**
   * Adds an exit to the location, only if the key isn't used and the exit starts from this location.
   * @param e the exit to add
   * @param key the unique key for this exit
   */
  public void addExit(Exit e, Integer key) {
    if (e.start == this && !this.exits.containsKey(key)) {
      this.exits.put(key, e);
    }
  }

  /**
   * Sets the exits for the location.
   * @param exits array of exits
   */
  public void setExits(Exit[] exits) {
    for (int i = 0; i < exits.length; i++) {
      this.addExit(exits[i], i);
    }
  }

  /**
   * Gets all the exits from the location.
   * @return a list of exits
   */
  public ArrayList<Exit> getExits() {
    ArrayList<Exit> res = new ArrayList<>();
    for (int i = 0; i < this.exits.size(); i++) {
      res.add(this.exits.get(i));
    }
    return res;
  }

  /**
   * Returns the monster in the location, if any.
   * @return the monster
   */
  public Monster getMonster() {
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
   * Sets a monster in this location.
   * @param m the monster to set
   */
  public void setMonster(Monster m) {
    this.monster = m;
  }

  /**
   * Removes an item from the location.
   * @param item the item to remove
   */
  public void removeItem(Item item) {
    this.itemList.remove(item);
  }

  /**
   * Adds an item to the location.
   * @param item the item to add
   */
  public void addItem(Item item) {
    this.itemList.add(item);
  }

  /**
   * Displays the description of the location (not used anymore)
   */
  public void displayLocation() {
    System.out.println(MessageM.locationDescription(this));
  }

  /**
   * Gets the name of the location.
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns a human-readable name based on the location's identifier.
   * @return the location name as a string
   */
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

  // Location initialization methods for each named location

  /**
   * Sets exits for North Poitiers.
   */
  public static void northPoitiers(LocationM location) {
    Exit e1 = new Exit(location, GameMapM.locations.get(5));
    location.setExits(new Exit[] { e1 });
  }

  /**
   * Sets exits for Beaulieu.
   */
  public static void beaulieu(LocationM location) {
    Exit[] exits = {
      new Exit(location, GameMapM.locations.get(7)),
      new Exit(location, GameMapM.locations.get(8)),
      new Exit(location, GameMapM.locations.get(10)),
      new Exit(location, GameMapM.locations.get(11)),
    };
    location.setExits(exits);
  }

  /**
   * Sets exits for City Center.
   */
  public static void cityCenter(LocationM location) {
    Exit[] exits = {
      new Exit(location, GameMapM.locations.get(5)),
      new Exit(location, GameMapM.locations.get(6)),
      new Exit(location, GameMapM.locations.get(7)),
    };
    location.setExits(exits);
  }

  /**
   * Sets exits for Couronneries.
   */
  public static void couronneries(LocationM location) {
    Exit[] exits = {
      new Exit(location, GameMapM.locations.get(10)),
      new Exit(location, GameMapM.locations.get(5)),
      new Exit(location, GameMapM.locations.get(8)),
    };
    location.setExits(exits);
  }

  /**
   * Sets exits for Gibauderie.
   */
  public static void gibauderie(LocationM location) {
    Exit[] exits = {
      new Exit(location, GameMapM.locations.get(11)),
      //new Exit(location, GameMapM.locations.get(1)),
      new Exit(location, GameMapM.locations.get(7)),
    };
    location.setExits(exits);
  }

  /**
   * Sets exits for West Poitiers.
   */
  public static void westPoitiers(LocationM location) {
    Exit[] exits = {
      new Exit(location, GameMapM.locations.get(0)),
      new Exit(location, GameMapM.locations.get(2)),
      new Exit(location, GameMapM.locations.get(6)),
    };
    location.setExits(exits);
  }

  /**
   * Sets exits for South Poitiers.
   */
  public static void southPoitiers(LocationM location) {
    Exit[] exits = {
      new Exit(location, GameMapM.locations.get(5)),
      new Exit(location, GameMapM.locations.get(9)),
    };
    location.setExits(exits);
  }

  /**
   * Sets exits for Pont Neuf.
   */
  public static void pontNeuf(LocationM location) {
    Exit[] exits = {
      new Exit(location, GameMapM.locations.get(9)),
      new Exit(location, GameMapM.locations.get(4)),
      new Exit(location, GameMapM.locations.get(10)),
    };
    location.setExits(exits);
  }

  /**
   * Sets exits for Saint Éloi.
   */
  public static void saintEloi(LocationM location) {
    Exit[] exits = {
      new Exit(location, GameMapM.locations.get(3)),
      new Exit(location, GameMapM.locations.get(10)),
      //new Exit(location, GameMapM.locations.get(1)),
    };
    location.setExits(exits);
  }

  /**
   * Sets exits for Trois Cités.
   */
  public static void troisCites(LocationM location) {
    Exit[] exits = {
      new Exit(location, GameMapM.locations.get(2)),
      new Exit(location, GameMapM.locations.get(4)),
    };
    location.setExits(exits);
  }

  /**
   * Sets exits for Montbernage.
   */
  public static void montbernage(LocationM location) {
    Exit[] exits = {
      new Exit(location, GameMapM.locations.get(2)),
      new Exit(location, GameMapM.locations.get(5)),
      new Exit(location, GameMapM.locations.get(7)),
      new Exit(location, GameMapM.locations.get(8)),
    };
    location.setExits(exits);
  }

  /**
   * Sets exits for Milétrie.
   */
  public static void miletrie(LocationM location) {
    Exit[] exits = {
      new Exit(location, GameMapM.locations.get(4)),
      //new Exit(location, GameMapM.locations.get(1)),
      new Exit(location, GameMapM.locations.get(12)),
    };
    location.setExits(exits);
  }

  /**
   * Sets exits for Final Exit.
   */
  public static void finalExit(LocationM location) {
    Exit[] exits = { new Exit(location, GameMapM.locations.get(11)) };
    location.setExits(exits);
  }
}
