package model.location;

public class Exit {
    public LocationM destination;
    public LocationM start;
    public int strtX;
    public int strtY;
    public int destX;
    public int destY;
    
    public Exit(LocationM start, LocationM destination){
        this.destination = destination;
        this.start = start;
    }

    @Override
    public String toString() {
        return start + " to " + destination;
    }
}
