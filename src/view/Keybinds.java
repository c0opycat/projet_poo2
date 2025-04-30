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

/**
 * A class to manage keybindings for the application.
 * Provides functionality to load, save, and retrieve keybindings.
 * @author L. Cooper
 * @author A. Bertrand-Bernard
 */
public class Keybinds {

  /**
   * The HashMap that stores keybindings.
   * Maps action names to their respective keybinding values.
   */
  private HashMap<String, String> keybinds;

  /**
   * Constructs a new Keybinds instance and loads keybindings from a file.
   */
  public Keybinds() {
    this.keybinds = this.loadKeybinds();
  }

  /**
   * Retrieves the current keybindings.
   *
   * @return a HashMap containing the keybindings.
   */
  public HashMap<String, String> getKeybinds() {
    return this.keybinds;
  }

  /**
   * Sets the keybindings.
   *
   * @param keybinds a HashMap containing the new keybindings.
   */
  public void setKeybinds(HashMap<String, String> keybinds) {
    this.keybinds = keybinds;
  }

  /**
   * Converts the keybindings to a map of KeyCode objects.
   *
   * @return a HashMap mapping actions to their respective KeyCode objects.
   */
  public HashMap<String, KeyCode> getKeyCodes() {
    HashMap<String, KeyCode> keycodes = new HashMap<>();

    HashMap<String, String> keybinds = this.getKeybinds();
    for (Map.Entry<String, String> row : keybinds.entrySet()) {
      keycodes.put(row.getKey(), KeyCode.getKeyCode(row.getValue()));
    }

    return keycodes;
  }

  /**
   * Retrieves a set of all keybinding values.
   *
   * @return a Set containing all keybinding values.
   */
  public Set<String> getKeybindsSet() {
    Set<String> keybindsSet = new HashSet<>();

    for (String k : this.getKeybinds().values()) {
      keybindsSet.add(k);
    }

    return keybindsSet;
  }

  /**
   * Loads keybindings from a JSON file.
   *
   * @return a HashMap containing the loaded keybindings.
   */
  public HashMap<String, String> loadKeybinds() {
    HashMap<String, String> keybinds = new HashMap<>();

    try {
      FileReader reader = new FileReader("./save/keybinds.json");
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

  /**
   * Saves the current keybindings to a JSON file.
   *
   * @param keybinds a HashMap containing the keybindings to save.
   */
  public void saveKeybinds(HashMap<String, String> keybinds) {
    JSONObject keybindsObject = new JSONObject();

    for (Map.Entry<String, String> row : keybinds.entrySet()) {
      keybindsObject.put(row.getKey(), row.getValue());
    }

    try (FileWriter file = new FileWriter("./save/keybinds.json")) {
      file.write(keybindsObject.toString(4));
      this.setKeybinds(keybinds);
      MyAlert alert = new MyAlert(
        "Keybinds",
        "Keybinds saved",
        "Keybinds saved successfully"
      );
      alert.showInformation();

      this.keybinds = keybinds;
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

  /**
   * Retrieves the keybinding value for a specific action.
   *
   * @param key the action for which the keybinding is requested.
   * @return the keybinding value as a String, or null if not found.
   */
  public String getSpecKeybind(String key) {
    HashMap<String, String> keybinds = this.getKeybinds();

    return keybinds.get(key);
  }

  /**
   * Retrieves the KeyCode for a specific action.
   *
   * @param key the action for which the KeyCode is requested.
   * @return the KeyCode associated with the action, or null if not found.
   */
  public KeyCode getSpecKeyCode(String key) {
    HashMap<String, KeyCode> keyCodes = this.getKeyCodes();

    return keyCodes.get(key);
  }

  /**
   * Checks if a specific keybinding is present.
   *
   * @param keybind the keybinding value to check.
   * @return true if the keybinding is present, false otherwise.
   */
  public boolean isKeybindPresent(String keybind) {
    return this.getKeybinds().containsValue(keybind);
  }
}
