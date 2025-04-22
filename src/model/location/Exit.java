package model.location;
import java.util.ArrayList;

public class Exit {
    public LocationM destination;
    public LocationM start;
    private int strtX;
    private int strtY;
    private int destX;
    private int destY;
    
    public Exit(LocationM start, LocationM destination){
        ArrayList<Exit> startExits = start.getExits();
        int stExNb = startExits.size();
        ArrayList<Exit> destExits = destination.getExits();
        int deExNb = destExits.size();
        if (stExNb >= 4){
           throw new IllegalArgumentException("you can't add more exits to the starting location");
        }
        if (deExNb >= 4){
            throw new IllegalArgumentException("you can't add more exits to the destination location");
        }
        /***/
        this.destination = destination;
        this.start = start;
        /* TO DO
        * get the dimensions of start and destination
        * add a switch case with value 1,2,3,4
        * sets random coordinates for exits
        * in both start and dest
        * so you can set things
        * **/
    }

    @Override
    public String toString() {
        return "To " + destination;
    }
    //can be used with indication popups
}
