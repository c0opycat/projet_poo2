package view;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javafx.scene.input.KeyCode;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Keybinds {

  private HashMap<String, String> keybinds;

  public Keybinds() {
    this.keybinds = this.loadKeybinds();
  }

  public HashMap<String, String> getKeybinds() {
    return this.keybinds;
  }

  public void setKeybinds(HashMap<String, String> keybinds) {
    this.keybinds = keybinds;
  }

  public HashMap<String, KeyCode> getKeyCodes() {
    HashMap<String, KeyCode> keycodes = new HashMap<>();

    HashMap<String, String> keybinds = this.getKeybinds();
    for (Map.Entry<String, String> row : keybinds.entrySet()) {
      keycodes.put(row.getKey(), KeyCode.getKeyCode(row.getValue()));
    }

    return keycodes;
  }

  public Set<String> getKeybindsSet() {
    Set<String> keybindsSet = new HashSet<>();

    for (String k : this.getKeybinds().values()) {
      keybindsSet.add(k);
    }

    return keybindsSet;
  }

  public HashMap<String, String> loadKeybinds() {
    HashMap<String, String> keybinds = new HashMap<>();

    try {
      FileReader reader = new FileReader("../save/keybinds.json");
      JSONObject keybindsObject = new JSONObject(new JSONTokener(reader));

      Iterator<String> entries = keybindsObject.keys();

      while (entries.hasNext()) {
        String entry = entries.next();
        String key = keybindsObject.getString(entry);
        keybinds.put(entry, key);
      }
    } catch (Exception e) {
      System.err.println("File 'keybinds.json' not found.");
    }

    return keybinds;
  }

  public void saveKeybinds(HashMap<String, String> keybinds) {
    JSONObject keybindsObject = new JSONObject();

    for (Map.Entry<String, String> row : keybinds.entrySet()) {
      keybindsObject.put(row.getKey(), row.getValue());
    }

    try (FileWriter file = new FileWriter("../save/keybinds.json")) {
      file.write(keybindsObject.toString(4));
      this.setKeybinds(keybinds);
      MyAlert alert = new MyAlert(
        "Keybinds",
        "Keybinds saved",
        "Keybinds saved successfully"
      );
      alert.showInformation();
    } catch (IOException e) {
      MyAlert alert = new MyAlert(
        "Keybinds",
        "Error",
        "Error while saving keybinds"
      );
      alert.showInformation();
      e.printStackTrace();
    }
  }

  public String getSpecKeybind(String key) {
    HashMap<String, String> keybinds = this.getKeybinds();

    return keybinds.get(key);
  }

  public KeyCode getSpecKeyCode(String key) {
    HashMap<String, KeyCode> keyCodes = this.getKeyCodes();

    return keyCodes.get(key);
  }

  public boolean isKeybindPresent(String keybind) {
    return this.getKeybinds().containsValue(keybind);
  }
}
