package model.game;

import java.util.ArrayList;
import model.location.*;

public class GameMapM {
    public static ArrayList<LocationM> locations;
    

    public GameMapM()
    {
        GameMapM.locations = new ArrayList<>();
        buildMap();
    }
    public static void buildMap(){
        LocationM northPoitiers = new LocationM(LocationName.NORTH_POITIERS);
        GameMapM.locations.add(northPoitiers);
        LocationM beaulieu = new LocationM(LocationName.BEAULIEU);
        GameMapM.locations.add(beaulieu);
        LocationM cityCenter = new LocationM(LocationName.CITY_CENTER);
        GameMapM.locations.add(cityCenter);
        LocationM couronneries = new LocationM(LocationName.COURONNERIES);
        GameMapM.locations.add(couronneries);
        LocationM gibauderie = new LocationM(LocationName.GIBAUDERIE);
        GameMapM.locations.add(gibauderie);
        LocationM westPoitiers = new LocationM(LocationName.WEST_POITIERS);
        GameMapM.locations.add(westPoitiers);
        LocationM southPoitiers = new LocationM(LocationName.SOUTH_POITIERS);
        GameMapM.locations.add(southPoitiers);
        LocationM pontNeuf = new LocationM(LocationName.PONT_NEUF);
        GameMapM.locations.add(pontNeuf);
        LocationM saintEloi = new LocationM(LocationName.SAINT_ELOI);
        GameMapM.locations.add(saintEloi);
        LocationM troisCites = new LocationM(LocationName.TROIS_CITES);
        GameMapM.locations.add(troisCites);
        LocationM montbernage = new LocationM(LocationName.MONTBERNAGE);
        GameMapM.locations.add(montbernage);
        LocationM miletrie = new LocationM(LocationName.MILETRIE);
        GameMapM.locations.add(miletrie);
        LocationM finalExit = new LocationM(LocationName.FINAL_EXIT);
        GameMapM.locations.add(finalExit);

        LocationM.northPoitiers(GameMapM.locations.get(0));
        LocationM.beaulieu(GameMapM.locations.get(1));
        LocationM.cityCenter(GameMapM.locations.get(2));
        LocationM.couronneries(GameMapM.locations.get(3));
        LocationM.gibauderie(GameMapM.locations.get(4));
        LocationM.westPoitiers(GameMapM.locations.get(5));
        LocationM.southPoitiers(GameMapM.locations.get(6));
        LocationM.pontNeuf(GameMapM.locations.get(7));
        LocationM.saintEloi(GameMapM.locations.get(8));
        LocationM.troisCites(GameMapM.locations.get(9));
        LocationM.montbernage(GameMapM.locations.get(10));
        LocationM.miletrie(GameMapM.locations.get(11));
        LocationM.finalExit(GameMapM.locations.get(12));
    }

    public LocationM getStartLoc(){
        return locations.get(0);
    }
    
    public LocationM getEndLoc(){
        return locations.get(locations.size() - 1);
    }
}
