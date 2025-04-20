package model.location;

public class Exit {
    public LocationM destination;
    public LocationM start;
    private int strtX;
    private int strtY;
    private int destX;
    private int destY;
    
    public Exit(LocationM start, LocationM destination, int stx, int sty, int dstx, int dsty){
        this.strtX = stx;
        this.strtY = sty;
        this.destX = dstx;
        this.destY = dsty;
        this.destination = destination;
        this.start = start;
    }

    @Override
    public String toString() {
        return "To " + destination;
    }
    //can be used with indication popups
}
