package controller.controllerEditor;

import java.util.ArrayList;
import model.modelGame.GameMapModel;
import model.modelLocation.LocationModel;
import view.viewEditor.SelectTypeElem;

/** * Controller class for managing map elements in the game editor.
 * This class acts as an intermediary between the GameMapModel and the SelectTypeElem view.
 * It provides access to game locations and their elements for the map editor interface.
 * @author C. Besançon
 * @author G. Jardin
 */
public class ControllerElem {

  /** The model containing all game map data and location information. */
  private final GameMapModel mapModel;

  /** The view for selecting element types in the editor interface. */
  private final SelectTypeElem selectTypeElem;

  /**
   * Constructs a new ControllerElem with the specified element selection view.
   * Initializes the controller with a reference to the element selection view
   * and creates a new GameMapModel to access location data.
   *
   * @param selectTypeElem the view for selecting element types
   */
  public ControllerElem(SelectTypeElem selectTypeElem) {
    this.selectTypeElem = selectTypeElem;
    this.mapModel = new GameMapModel();
  }

  /**
   * Gets an array of all location names from the game map.
   * Retrieves the names of all locations in the map model and
   * returns them as a string array for display in the editor.
   *
   * @return an array of strings containing all location names
   */
  public String[] getLevelName() {
    ArrayList<LocationModel> listLocation = this.mapModel.getLocations();
    String[] names = new String[listLocation.size()];

    // Boucle pour remplir le tableau avec les noms des locations
    for (int i = 0; i < listLocation.size(); i++) {
      names[i] = listLocation.get(i).getName();
    }
    return names;
  }
}
