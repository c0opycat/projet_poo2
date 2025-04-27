package model.modelGame;

import java.util.ArrayList;
import model.modelLocation.*;

public class GameMapModel {
    public static ArrayList<LocationModel> locations;
    

    public GameMapModel()
    {
        GameMapModel.locations = new ArrayList<>();
        buildMap();
    }
    public static void buildMap(){
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

    public LocationModel getStartLoc(){
        return locations.get(0);
    }
    
    public LocationModel getEndLoc(){
        return locations.get(locations.size() - 1);
    }
}
