package model.modelEditor;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import model.modelCharacter.modelMonster.MonsterModel;
import model.modelItem.CrowbarModel;
import model.modelItem.ItemModel;
import model.modelItem.ProtectionModel;
import model.modelItem.modelConsumable.MedicineModel;
import model.modelItem.modelConsumable.modelFood.CakeModel;
import model.modelItem.modelConsumable.modelFood.FruitModel;
import model.modelItem.modelConsumable.modelFood.MeatModel;
import model.modelItem.modelContainer.BackpackModel;
import model.modelItem.modelContainer.ChestModel;
import model.modelItem.modelContainer.CrateModel;
import model.modelItem.modelWeapon.BaseballBatModel;
import model.modelItem.modelWeapon.DoggoModel;
import model.modelItem.modelWeapon.GunModel;
import model.modelItem.modelWeapon.SwordModel;
import model.modelLocation.ExitModel;
import model.modelLocation.LocationModel;
import model.modelLocation.StepModel;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Model class for saving and loading custom maps created in the game editor.
 * This class provides functionality to persist and retrieve map data in JSON format.
 * It handles serialization of complex game objects like locations, items, exits, and monsters
 * to and from JSON representations, maintaining their relationships and properties.
 * @author G. Jardin
 */
public class CustomMapSaveModel {

  /** The collection of all loaded location models. */
  public static ArrayList<LocationModel> locations = new ArrayList<>();

  /** The file path where custom maps are saved. */
  public static final String filepath = "./save/custommaps.json";

  /**
   * Constructs a new CustomMapSaveModel.
   * Initializes the model and checks if saved custom maps exist.
   * If they do, loads them from the JSON file into memory.
   */
  public CustomMapSaveModel() {
    java.io.File file = new java.io.File(filepath);

    if (file.exists()) {
      locations = loadFromJson();
    } else {
      locations = new ArrayList<>();
    }
  }

  /**
   * Saves a new location model to the JSON file.
   * Adds the provided location to the collection and then serializes all locations
   * to JSON format. Each location's properties (name, dimensions, descriptions) and
   * relationships (exits, items, monsters) are preserved in the JSON structure.
   *
   * @param newLoc the location model to be saved
   */
  public void saveToJson(LocationModel newLoc) {
    JSONArray jsonArray = new JSONArray();

    locations.add(newLoc);
    for (LocationModel loc : locations) {
      JSONObject obj = new JSONObject();
      obj.put("name", loc.name);
      obj.put("width", loc.getWidth());
      obj.put("height", loc.getHeight());
      obj.put("description", loc.description != null ? loc.description : "");

      //exits
      JSONArray exitsArray = new JSONArray();
      for (Map.Entry<Integer, ExitModel> entry : loc.exits.entrySet()) {
        JSONObject exitObj = new JSONObject();
        exitObj.put("id", entry.getKey());
        exitObj.put("destination", entry.getValue().destination.name);
        exitsArray.put(exitObj);
      }
      obj.put("exits", exitsArray);

      //items
      JSONArray itemsArray = new JSONArray();
      for (ItemModel item : loc.itemList) {
        JSONObject itemObj = new JSONObject();
        itemObj.put("type", item.getClass().getSimpleName());
        itemObj.put("name", item.toString());
        itemsArray.put(itemObj);
      }
      obj.put("items", itemsArray);

      //monster
      if (loc.monster != null) {
        obj.put("monster", loc.monster.getClass().getSimpleName());
      } else {
        obj.put("monster", JSONObject.NULL);
      }

      //locMap
      JSONArray locMapArray = new JSONArray();
      for (Map.Entry<Point, StepModel> entry : loc.locMap.entrySet()) {
        JSONObject mapObj = new JSONObject();
        mapObj.put("x", entry.getKey().x);
        mapObj.put("y", entry.getKey().y);
        StepModel step = entry.getValue();
        if (step.getItem() != null) {
          mapObj.put("item", step.getItem().getClass().getSimpleName());
        } else if (step.getExit() != null) {
          mapObj.put("exit", step.getExit().destination.name);
        }
        locMapArray.put(mapObj);
      }
      obj.put("locMap", locMapArray);

      jsonArray.put(obj);
    }

    try (FileWriter file = new FileWriter(filepath)) {
      file.write(jsonArray.toString(4));
      System.out.println("Map saved successfully to " + filepath);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Loads location models from the JSON file.
   * Parses the saved JSON data and reconstructs location objects with all their
   * properties and relationships. The loading process happens in two phases:
   * first creating the basic location objects, then establishing connections
   * between them (exits, contained items, etc.).
   *
   * @return an ArrayList of LocationModel objects loaded from the file
   */
  public ArrayList<LocationModel> loadFromJson() {
    ArrayList<LocationModel> loadedLocations = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
      StringBuilder jsonText = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        jsonText.append(line);
      }

      JSONArray jsonArray = new JSONArray(jsonText.toString());

      //1 Create location
      for (int i = 0; i < jsonArray.length(); i++) {
        JSONObject obj = jsonArray.getJSONObject(i);

        String name = obj.getString("name");
        int width = obj.getInt("width");
        int height = obj.getInt("height");
        String description = obj.getString("description");

        LocationModel loc = new LocationModel(
          name,
          width,
          height,
          new HashMap<>(),
          new HashMap<>(),
          new ArrayList<>(),
          null
        );
        loc.description = description;

        loadedLocations.add(loc);
      }

      //2 Add exits and items
      for (int i = 0; i < jsonArray.length(); i++) {
        JSONObject obj = jsonArray.getJSONObject(i);
        LocationModel loc = loadedLocations.get(i);

        // 1 exits
        JSONArray exitsArray = obj.getJSONArray("exits");
        for (int j = 0; j < exitsArray.length(); j++) {
          JSONObject exitObj = exitsArray.getJSONObject(j);
          String destinationName = exitObj.getString("destination");
          LocationModel destination = loadedLocations
            .stream()
            .filter(l -> l.name.equals(destinationName))
            .findFirst()
            .orElse(null);

          if (destination != null) {
            ExitModel exit = new ExitModel(loc, destination);
            loc.exits.put(exitObj.getInt("id"), exit);
          }
        }

        // 2 items
        JSONArray itemsArray = obj.getJSONArray("items");
        for (int j = 0; j < itemsArray.length(); j++) {
          JSONObject itemObj = itemsArray.getJSONObject(j);
          String type = itemObj.getString("type");

          ItemModel item = createItemByType(type);
          if (item != null) loc.itemList.add(item);
        }

        //monster
        if (!obj.isNull("monster")) {
          loc.monster = MonsterModel.randMonster(); // Change here if you want a specific monster type loading
        }

        //locMap
        JSONArray locMapArray = obj.getJSONArray("locMap");
        for (int j = 0; j < locMapArray.length(); j++) {
          JSONObject mapObj = locMapArray.getJSONObject(j);
          int x = mapObj.getInt("x");
          int y = mapObj.getInt("y");
          Point p = new Point(x, y);

          if (mapObj.has("item")) {
            String type = mapObj.getString("item");
            ItemModel item = createItemByType(type);
            if (item != null) loc.locMap.put(p, new StepModel(item));
          } else if (mapObj.has("exit")) {
            String destinationName = mapObj.getString("exit");
            LocationModel destination = loadedLocations
              .stream()
              .filter(l -> l.name.equals(destinationName))
              .findFirst()
              .orElse(null);
            if (destination != null) {
              loc.locMap.put(p, new StepModel(new ExitModel(loc, destination)));
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    locations = loadedLocations;
    return loadedLocations;
  }

  /**
   * Creates an item model instance based on the specified type string.
   * Acts as a factory method that instantiates the appropriate item model
   * subclass based on the class name provided. This is used during the loading
   * process to recreate items from their serialized type names.
   *
   * @param type the simple class name of the item to create
   * @return a new ItemModel instance of the specified type, or null if the type is unknown
   */
  private static ItemModel createItemByType(String type) {
    return switch (type) {
      case "CrowbarModel" -> new CrowbarModel();
      case "CakeModel" -> new CakeModel();
      case "FruitModel" -> new FruitModel();
      case "MeatModel" -> new MeatModel();
      case "MedicineModel" -> new MedicineModel();
      case "BackpackModel" -> new BackpackModel();
      case "ChestModel" -> new ChestModel();
      case "CrateModel" -> new CrateModel();
      case "BaseballBatModel" -> new BaseballBatModel();
      case "DoggoModel" -> new DoggoModel("doggo");
      case "GunModel" -> new GunModel();
      case "SwordModel" -> new SwordModel();
      case "ProtectionModel" -> new ProtectionModel();
      default -> null;
    };
  }
}
