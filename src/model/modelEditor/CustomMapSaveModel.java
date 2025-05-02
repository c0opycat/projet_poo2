package model.modelEditor;

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
import model.modelLocation.LocationModel;
import model.modelLocation.ExitModel;
import model.modelLocation.StepModel;
import model.modelItem.ItemModel;
import model.modelItem.CrowbarModel;
import model.modelCharacter.modelMonster.MonsterModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

/**
 * CustomMapSaveModel allows saving and loading a complete list of locations including their attributes to/from a JSON file.
 */
public class CustomMapSaveModel {
    public static ArrayList<LocationModel> locations = new ArrayList<>();
    public final static String filepath = "./save/custommaps.json";

    public CustomMapSaveModel() {
        java.io.File file = new java.io.File(filepath);

        if (file.exists())
        {
            locations = loadFromJson();
        }
        else {locations = new ArrayList<>();}
    }

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

    // Load
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

                LocationModel loc = new LocationModel(name, width, height, new HashMap<>(), new HashMap<>(), new ArrayList<>(), null);
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
                    LocationModel destination = loadedLocations.stream()
                            .filter(l -> l.name.equals(destinationName))
                            .findFirst().orElse(null);

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
                    String monsterType = obj.getString("monster");
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
                        LocationModel destination = loadedLocations.stream()
                                .filter(l -> l.name.equals(destinationName))
                                .findFirst().orElse(null);
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

    private static ItemModel createItemByType(String type) {
        return switch (type) {
            case "CrowbarModel" -> new CrowbarModel();
            case "CakeModel" -> new CakeModel();
            case "FruitModel" -> new FruitModel();
            case "MeatModel" -> new MeatModel();
            case "MedicineModel" -> new MedicineModel();
            case "BackpackModel" -> new BackpackModel();
            case "ChestModel"-> new ChestModel();
            case "CrateModel"-> new CrateModel();
            case "BaseballBatModel"-> new BaseballBatModel();
            case "DoggoModel" -> new DoggoModel("doggo");
            case "GunModel"-> new GunModel();
            case "SwordModel"-> new SwordModel();
            case "ProtectionModel" -> new ProtectionModel();
            default -> null;
        };
    }
}
