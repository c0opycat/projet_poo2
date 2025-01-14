package game;

import java.util.ArrayList;
import location.*;

public class GameMap {
    public static ArrayList<Location> locations;
    

    public GameMap()
    {
        GameMap.locations = new ArrayList<>();
        buildMap();
    }

    public static void buildMap(){
        Location northPoitiers = new Location(LocationName.NORTH_POITIERS);
        GameMap.locations.add(northPoitiers);
        Location beaulieu = new Location(LocationName.BEAULIEU);
        GameMap.locations.add(beaulieu);
        Location cityCenter = new Location(LocationName.CITY_CENTER);
        GameMap.locations.add(cityCenter);
        Location couronneries = new Location(LocationName.COURONNERIES);
        GameMap.locations.add(couronneries);
        Location gibauderie = new Location(LocationName.GIBAUDERIE);
        GameMap.locations.add(gibauderie);
        Location westPoitiers = new Location(LocationName.WEST_POITIERS);
        GameMap.locations.add(westPoitiers);
        Location southPoitiers = new Location(LocationName.SOUTH_POITIERS);
        GameMap.locations.add(southPoitiers);
        Location pontNeuf = new Location(LocationName.PONT_NEUF);
        GameMap.locations.add(pontNeuf);
        Location saintEloi = new Location(LocationName.SAINT_ELOI);
        GameMap.locations.add(saintEloi);
        Location troisCites = new Location(LocationName.TROIS_CITES);
        GameMap.locations.add(troisCites);
        Location montbernage = new Location(LocationName.MONTBERNAGE);
        GameMap.locations.add(montbernage);
        Location miletrie = new Location(LocationName.MILETRIE);
        GameMap.locations.add(miletrie);
        Location finalExit = new Location(LocationName.FINAL_EXIT);
        GameMap.locations.add(finalExit);

        Location.northPoitiers(GameMap.locations.get(0));
        Location.beaulieu(GameMap.locations.get(1));
        Location.cityCenter(GameMap.locations.get(2));
        Location.couronneries(GameMap.locations.get(3));
        Location.gibauderie(GameMap.locations.get(4));
        Location.westPoitiers(GameMap.locations.get(5));
        Location.southPoitiers(GameMap.locations.get(6));
        Location.pontNeuf(GameMap.locations.get(7));
        Location.saintEloi(GameMap.locations.get(8));
        Location.troisCites(GameMap.locations.get(9));
        Location.montbernage(GameMap.locations.get(10));
        Location.miletrie(GameMap.locations.get(11));
        Location.finalExit(GameMap.locations.get(12));
    }

    public Location getStartLoc(){
        return locations.get(0);
    }
    
    public Location getEndLoc(){
        return locations.get(locations.size() - 1);
    }
}
