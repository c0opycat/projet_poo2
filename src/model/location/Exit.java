package model.location;

public class Exit {
    public Location destination;
    public Location start;
    public int strtX;
    public int strtY;
    public int destX;
    public int destY;
    
    public Exit(Location start, Location destination){
        this.destination = destination;
        this.start = start;
    }

    @Override
    public String toString() {
        return start + " to " + destination;
    }
}
