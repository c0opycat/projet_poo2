package model.modelGame;

import java.util.ArrayList;
import model.modelEditor.CustomMapSaveModel;
import model.modelLocation.*;

/**
 * The {@code GameMapModel} class represents the complete game map,
 * containing all {@link LocationModel} objects that define the areas the player can visit.
 * <p>
 * This includes both hardcoded locations (such as NORTH_POITIERS, BEAULIEU, etc.)
 * as well as custom user-defined locations loaded from a JSON file using {@link CustomMapSaveModel}.
 * </p>
 */
public class GameMapModel {

  /**
   * The list of all locations in the game map.
   * This list is shared statically across all instances.
   */
  public static ArrayList<LocationModel> locations;

  /**
   * Constructs a new {@code GameMapModel}.
   * <p>
   * Initializes the map by building the default set of locations and loading custom locations
   * from a saved file, if available.
   * </p>
   */
  public GameMapModel() {
    GameMapModel.locations = new ArrayList<>();
    buildMap();
    GameMapModel.locations.addAll(CustomMapSaveModel.locations);
  }

  /**
   * Builds and initializes the default locations for the game map.
   * <p>
   * These are hardcoded locations that define the core map.
   * Each location is created, added to the {@link #locations} list,
   * and initialized using their corresponding static initialization method.
   * </p>
   */
  public static void buildMap() {
    LocationModel northPoitiers = new LocationModel("NORTH_POITIERS");
    GameMapModel.locations.add(northPoitiers);
    LocationModel beaulieu = new LocationModel("BEAULIEU");
    GameMapModel.locations.add(beaulieu);
    LocationModel cityCenter = new LocationModel("CITY_CENTER");
    GameMapModel.locations.add(cityCenter);
    LocationModel couronneries = new LocationModel("COURONNERIES");
    GameMapModel.locations.add(couronneries);
    LocationModel gibauderie = new LocationModel("GIBAUDERIE");
    GameMapModel.locations.add(gibauderie);
    LocationModel westPoitiers = new LocationModel("WEST_POITIERS");
    GameMapModel.locations.add(westPoitiers);
    LocationModel southPoitiers = new LocationModel("SOUTH_POITIERS");
    GameMapModel.locations.add(southPoitiers);
    LocationModel pontNeuf = new LocationModel("PONT_NEUF");
    GameMapModel.locations.add(pontNeuf);
    LocationModel saintEloi = new LocationModel("SAINT_ELOI");
    GameMapModel.locations.add(saintEloi);
    LocationModel troisCites = new LocationModel("TROIS_CITES");
    GameMapModel.locations.add(troisCites);
    LocationModel montbernage = new LocationModel("MONTBERNAGE");
    GameMapModel.locations.add(montbernage);
    LocationModel miletrie = new LocationModel("MILETRIE");
    GameMapModel.locations.add(miletrie);
    LocationModel finalExit = new LocationModel("FINAL_EXIT");
    GameMapModel.locations.add(finalExit);

    LocationModel.northPoitiers(GameMapModel.locations.get(0));
    LocationModel.beaulieu(GameMapModel.locations.get(1));
    LocationModel.cityCenter(GameMapModel.locations.get(2));
    LocationModel.couronneries(GameMapModel.locations.get(3));
    LocationModel.gibauderie(GameMapModel.locations.get(4));
    LocationModel.westPoitiers(GameMapModel.locations.get(5));
    LocationModel.southPoitiers(GameMapModel.locations.get(6));
    LocationModel.pontNeuf(GameMapModel.locations.get(7));
    LocationModel.saintEloi(GameMapModel.locations.get(8));
    LocationModel.troisCites(GameMapModel.locations.get(9));
    LocationModel.montbernage(GameMapModel.locations.get(10));
    LocationModel.miletrie(GameMapModel.locations.get(11));
    LocationModel.finalExit(GameMapModel.locations.get(12));
  }

  /**
   * Returns the starting location of the game map.
   * @return the first {@link LocationModel} in the list
   */
  public LocationModel getStartLoc() {
    return locations.get(0);
  }

  /**
   * Returns the ending location of the game map.
   * @return the last {@link LocationModel} in the list
   */
  public LocationModel getEndLoc() {
    return locations.get(locations.size() - 1);
  }

  /**
   * Returns the list of all locations in the game map.
   * @return an {@link ArrayList} of {@link LocationModel}
   */
  public ArrayList<LocationModel> getLocations() {
    return locations;
  }
}
