package controller.locationC;
import model.location.LocationM;
import view.viewLocation.LocationView;

import javax.lang.model.element.Name;

public class LocationC {
    public LocationM locationM;
    public LocationView locationView;

    public LocationC(String name) {
        this.locationM = new LocationM(new Name());
        
    }
}
