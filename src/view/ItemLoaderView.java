package view;


import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ItemLoaderView {
    /**
   * The HashMap that stores items.
   * Maps item names to their respective values.
   */
  private String[] type;
  private ArrayList<ArrayList<String>> nomsItems = new ArrayList<>();

  /**
   * Constructs a new Keybinds instance and loads keybindings from a file.
   */
    public ItemLoaderView(String name){
        this.loadItems(name);
    }
    /**
    * Loads elements from a JSON file.
    *
    * @return a HashMap containing the loaded items.
    */
    public void loadItems(String name) {
        try {
            FileReader reader = new FileReader("./save/"+ name +".json");
            JSONObject itemsObject = new JSONObject(new JSONTokener(reader));

            if (itemsObject.has("Types")){
                // Pour "types"
                JSONArray typeArray = itemsObject.getJSONArray("Types");
                type = new String[typeArray.length()];
                ArrayList<String> typeList = new ArrayList<>();
                for (int i = 0; i < typeArray.length(); i++) {
                    type[i] = typeArray.getString(i);
                    typeList.add(type[i]);
                }
            }
    
            if (itemsObject.has("Decor")){
                // Pour "Decors"
                JSONArray dArray = itemsObject.getJSONArray("Decors");
                ArrayList<String> dList = new ArrayList<>();
                for (int i = 0; i < dArray.length(); i++) {
                    dList.add(dArray.getString(i));
                }
                nomsItems.add(dList);
            }

            if (itemsObject.has("Portes")){
                // Pour "Portes"
                convertItemsWithAttribut(itemsObject, "image","Portes");
            }

            if (itemsObject.has("Items")){
                // Pour "Items"
                convertItemsWithAttribut(itemsObject, "item", "Items");
            }
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de" + name + ".json : " + e.getMessage());
        }
    }
    
    private void convertItemsWithAttribut(JSONObject itemsObject, String defaultVal, String name){
        JSONArray iArray = itemsObject.getJSONArray(name);
        ArrayList<String> iList = new ArrayList<>();
        
        for (int i = 0; i < iArray.length(); i++) {
            JSONObject obj = iArray.getJSONObject(i);
            StringBuilder sb = new StringBuilder();
            String base = obj.optString(defaultVal, "");
            sb.append(base);
            for (String key : obj.keySet()) {
                if (!key.equals(defaultVal)) {
                    sb.append(" ; ").append(key).append(" : ").append(obj.get(key));
                }
            }
            iList.add(sb.toString());
        }
        nomsItems.add(iList);

    }
    // Accesseurs utiles
    public String[] getType() {
        return type;
    }

    public ArrayList<ArrayList<String>> getNomsItems() {
        return nomsItems;
    }
}