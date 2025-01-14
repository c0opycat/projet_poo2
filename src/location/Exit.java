package location;

public class Exit {
    public Location destination;
    public Location start;
    
    public Exit(Location start, Location destination){
        this.destination = destination;
        this.start = start;
    }

    @Override
    public String toString() {
        return start + " to " + destination;
    }
}
