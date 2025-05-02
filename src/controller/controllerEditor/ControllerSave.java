package controller.controllerEditor;

import java.util.ArrayList;
import java.util.HashMap;
import model.modelEditor.CustomMapSaveModel;
import model.modelLocation.LocationModel;
import view.viewEditor.EditorPane;

/**
 * Controller class for managing map saving functionality in the game editor.
 * This class acts as an intermediary between the CustomMapSaveModel and EditorPane.
 * It handles operations related to saving custom maps created in the editor,
 * as well as loading existing maps for editing.
 * @author C. Besançon
 * @author G. Jardin
 */
public class ControllerSave {

  /** The model containing the map saving and loading logic. */
  private final CustomMapSaveModel mapModel;

  /** The location model representing the current map being edited. */
  private LocationModel loc;

  /** The editor pane containing the map design interface. */
  private EditorPane editorPane;

  /**
   * Constructs a new ControllerSave with the specified editor pane.
   * Initializes the controller with a reference to the editor pane
   * and creates a new CustomMapSaveModel to handle map persistence.
   *
   * @param editorPane the editor interface containing the map design
   */
  public ControllerSave(EditorPane editorPane) {
    this.editorPane = editorPane;
    this.mapModel = new CustomMapSaveModel();
  }

  /**
   * Saves the current map design to persistent storage.
   * Creates a new LocationModel based on the current state of the editor pane,
   * including dimensions, name, and descriptions, then delegates to the
   * map model to save it to JSON format.
   */
  public void saveLevel() {
    this.loc = new LocationModel(
      (int) this.editorPane.getWidth(),
      (int) this.editorPane.getHeight(),
      this.editorPane.getName(),
      new HashMap<>(),
      new HashMap<>(),
      new ArrayList<>(),
      (this.editorPane.getDescFr() + "/" + this.editorPane.getDescEn())
    );
    mapModel.saveToJson(loc);
  }

  /**
   * Gets an array of all saved custom level names.
   * Loads all saved locations from JSON and extracts their names
   * for display in the editor interface.
   *
   * @return an array of strings containing all saved level names
   */
  public String[] getLevelName() {
    ArrayList<LocationModel> listLocation = this.mapModel.loadFromJson();
    String[] names = new String[listLocation.size()];

    // Boucle pour remplir le tableau avec les noms des locations
    for (int i = 0; i < listLocation.size(); i++) {
      names[i] = listLocation.get(i).getName();
    }
    return names;
  }
}
