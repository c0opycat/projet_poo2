package model.location;
import java.util.ArrayList;
import java.util.Random;

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
        this.destination = destination;
        this.start = start;
        int widthStart = start.getWidth();
        int heightStart = start.getHeight();
        int widthDest = destination.getWidth();
        int heightDest = destination.getHeight();

        Random r = new Random();

        switch (stExNb) {
            case 0 -> {
                // Left start
                strtX = 0;
                strtY = r.nextInt(heightStart);
                // Right destination
                destX = widthDest - 1;
                destY = r.nextInt(heightDest);
            }
            case 1 -> {
                // Top start
                strtX = r.nextInt(widthStart);
                strtY = 0;
                // Bottom destination
                destX = r.nextInt(widthDest);
                destY = heightDest - 1;
            }
            case 2 -> {
                // Right start
                strtX = widthStart - 1;
                strtY = r.nextInt(heightStart);
                // Left destination
                destX = 0;
                destY = r.nextInt(heightDest);
            }
            case 3 -> {
                // Bottom start
                strtX = r.nextInt(widthStart);
                strtY = heightStart - 1;
                // Top destination
                destX = r.nextInt(widthDest);
                destY = 0;
            }
        }
    }

    @Override
    public String toString() {
        return "To " + destination;
    }
    //can be used with indication popups
}
