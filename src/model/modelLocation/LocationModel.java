package model.modelLocation;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import model.modelCharacter.modelMonster.MonsterModel;
import model.modelItem.*;
import model.modelGame.GameMapModel;
import model.modelGame.MessageEnModel;

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
        boolean hasContainer = false;

        for (int i = 0; i < 3; i++) {
            ItemModel item = hasContainer ? ItemModel.NonContainerRI() : ItemModel.randomItem();
            if (!hasContainer && ItemModel.isContainer(item)) {
                hasContainer = true;
            }
            this.addItem(item,this.getRandomFreeStepCoord());
        }

        this.monster = new Random().nextInt(2) == 0 ? null : MonsterModel.randMonster();
    }
    /**
     * Constructs a new modelLocation with a name, random items, and possibly a random monster.
     * @param name : the name of the modelLocation
     */
    public LocationModel(String name, int width, int height, Map<Integer, ExitModel> exits, HashMap<Point, StepModel> locMap, ArrayList<ItemModel> itemList, MonsterModel monster) {
        this.width = width;
        this.height = height;
        this.name = name;
        this.exits = exits;
        this.locMap = locMap;
        this.itemList = itemList;
        this.monster = monster;
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
     * Removes an modelItem from the modelLocation.
     * @param item the modelItem to remove
     */
    public void removeItem(ItemModel item) {
        this.locMap.remove(item);
        this.itemList.remove(item);
    }

    /**
     * Adds an modelItem to the modelLocation.
     * @param item the modelItem to add
     */
    public void addItem(ItemModel item, Point coord) {
        this.itemList.add(item);
        if (this.locMap.containsKey(coord)) {
            throw new RuntimeException("place already occupied");
        }
        else this.locMap.put(coord,new StepModel(item));
    }

    public Point getRandomFreeStepCoord() {
        int x = new Random().nextInt(width);
        int y = new Random().nextInt(height);
        Point p = new Point(x, y);
        for (int i = 0; i < height*width; i++) {
            if (this.locMap.containsKey(p)) {
                this.getRandomFreeStepCoord();
            }
            else return p;break;
        }
        throw new RuntimeException("wtf, the modelLocation is full, how did you manage to do that???");
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

    public int getHeight() {
        return this.height;
    }

    public void setNewStep(int x, int y,Object obj) {
        this.locMap.put(new Point(x,y),new StepModel(obj));
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
        location.setExits(new ExitModel[]{e1});
    }

    /**
     * Sets exits for Beaulieu.
     */
    public static void beaulieu(LocationModel location) {
        ExitModel[] exits = {
                new ExitModel(location, GameMapModel.locations.get(7)),
                new ExitModel(location, GameMapModel.locations.get(8)),
                new ExitModel(location, GameMapModel.locations.get(10)),
                new ExitModel(location, GameMapModel.locations.get(11))
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
                new ExitModel(location, GameMapModel.locations.get(7))
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
                new ExitModel(location, GameMapModel.locations.get(8))
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
                new ExitModel(location, GameMapModel.locations.get(7))
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
                new ExitModel(location, GameMapModel.locations.get(6))
        };
        location.setExits(exits);
    }

    /**
     * Sets exits for South Poitiers.
     */
    public static void southPoitiers(LocationModel location) {
        ExitModel[] exits = {
                new ExitModel(location, GameMapModel.locations.get(5)),
                new ExitModel(location, GameMapModel.locations.get(9))
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
                new ExitModel(location, GameMapModel.locations.get(10))
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
                new ExitModel(location, GameMapModel.locations.get(1))
        };
        location.setExits(exits);
    }

    /**
     * Sets exits for Trois Cités.
     */
    public static void troisCites(LocationModel location) {
        ExitModel[] exits = {
                new ExitModel(location, GameMapModel.locations.get(2)),
                new ExitModel(location, GameMapModel.locations.get(4))
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
                new ExitModel(location, GameMapModel.locations.get(8))
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
                new ExitModel(location, GameMapModel.locations.get(12))
        };
        location.setExits(exits);
    }

    /**
     * Sets exits for Final ExitModel.
     */
    public static void finalExit(LocationModel location) {
        ExitModel[] exits = {
                new ExitModel(location, GameMapModel.locations.get(11))
        };
        location.setExits(exits);
    }
}
