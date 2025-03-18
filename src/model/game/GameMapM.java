package model.game;

import java.util.ArrayList;
import model.location.*;

public class GameMapM {
    public static ArrayList<Location> locations;
    

    public GameMapM()
    {
        GameMapM.locations = new ArrayList<>();
        buildMap();
    }

    public static void buildMap(){
        Location northPoitiers = new Location(LocationName.NORTH_POITIERS);
        GameMapM.locations.add(northPoitiers);
        Location beaulieu = new Location(LocationName.BEAULIEU);
        GameMapM.locations.add(beaulieu);
        Location cityCenter = new Location(LocationName.CITY_CENTER);
        GameMapM.locations.add(cityCenter);
        Location couronneries = new Location(LocationName.COURONNERIES);
        GameMapM.locations.add(couronneries);
        Location gibauderie = new Location(LocationName.GIBAUDERIE);
        GameMapM.locations.add(gibauderie);
        Location westPoitiers = new Location(LocationName.WEST_POITIERS);
        GameMapM.locations.add(westPoitiers);
        Location southPoitiers = new Location(LocationName.SOUTH_POITIERS);
        GameMapM.locations.add(southPoitiers);
        Location pontNeuf = new Location(LocationName.PONT_NEUF);
        GameMapM.locations.add(pontNeuf);
        Location saintEloi = new Location(LocationName.SAINT_ELOI);
        GameMapM.locations.add(saintEloi);
        Location troisCites = new Location(LocationName.TROIS_CITES);
        GameMapM.locations.add(troisCites);
        Location montbernage = new Location(LocationName.MONTBERNAGE);
        GameMapM.locations.add(montbernage);
        Location miletrie = new Location(LocationName.MILETRIE);
        GameMapM.locations.add(miletrie);
        Location finalExit = new Location(LocationName.FINAL_EXIT);
        GameMapM.locations.add(finalExit);

        Location.northPoitiers(GameMapM.locations.get(0));
        Location.beaulieu(GameMapM.locations.get(1));
        Location.cityCenter(GameMapM.locations.get(2));
        Location.couronneries(GameMapM.locations.get(3));
        Location.gibauderie(GameMapM.locations.get(4));
        Location.westPoitiers(GameMapM.locations.get(5));
        Location.southPoitiers(GameMapM.locations.get(6));
        Location.pontNeuf(GameMapM.locations.get(7));
        Location.saintEloi(GameMapM.locations.get(8));
        Location.troisCites(GameMapM.locations.get(9));
        Location.montbernage(GameMapM.locations.get(10));
        Location.miletrie(GameMapM.locations.get(11));
        Location.finalExit(GameMapM.locations.get(12));
    }

    public Location getStartLoc(){
        return locations.get(0);
    }
    
    public Location getEndLoc(){
        return locations.get(locations.size() - 1);
    }
}
