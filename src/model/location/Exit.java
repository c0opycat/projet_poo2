package model.location;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents an exit connecting two locations in the game map.
 * Each exit links a start location to a destination and contains coordinates
 * for where the exit is placed within each location's map.
 * <p>
 * An exit is restricted to a maximum of four per location. Coordinates are
 * randomly placed along the edges to simulate doors, roads, or pathways.
 */
public class Exit {

    /**
     * The destination location of this exit.
     */
    public LocationM destination;

    /**
     * The starting location of this exit.
     */
    public LocationM start;

    /**
     * The X coordinate of the exit on the start location.
     */
    private int strtX;

    /**
     * The Y coordinate of the exit on the start location.
     */
    private int strtY;

    /**
     * The X coordinate of the exit on the destination location.
     */
    private int destX;

    /**
     * The Y coordinate of the exit on the destination location.
     */
    private int destY;

    /**
     * Constructs a new Exit from one location to another.
     * <p>
     * Coordinates are randomly chosen along the borders of each location based
     * on the current number of existing exits. A location cannot have more than
     * four exits.
     *
     * @param start       the starting location of the exit
     * @param destination the destination location of the exit
     * @throws IllegalArgumentException if either location already has 4 exits
     */
    public Exit(LocationM start, LocationM destination) {
        ArrayList<Exit> startExits = start.getExits();
        int stExNb = startExits.size();
        ArrayList<Exit> destExits = destination.getExits();
        int deExNb = destExits.size();

        if (stExNb >= 4) {
            throw new IllegalArgumentException("you can't add more exits to the starting location");
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
                // Left start → Right destination
                strtX = 0;
                strtY = r.nextInt(heightStart);
                destX = widthDest - 1;
                destY = r.nextInt(heightDest);
            }
            case 1 -> {
                // Top start → Bottom destination
                strtX = r.nextInt(widthStart);
                strtY = 0;
                destX = r.nextInt(widthDest);
                destY = heightDest - 1;
            }
            case 2 -> {
                // Right start → Left destination
                strtX = widthStart - 1;
                strtY = r.nextInt(heightStart);
                destX = 0;
                destY = r.nextInt(heightDest);
            }
            case 3 -> {
                // Bottom start → Top destination
                strtX = r.nextInt(widthStart);
                strtY = heightStart - 1;
                destX = r.nextInt(widthDest);
                destY = 0;
            }
        }
        start.setNewStep(strtX,strtY,this);
        destination.setNewStep(destX,destY,this);
    }

    /**
     * Returns a string representation of the exit, showing only the destination.
     *
     * @return a string describing the destination location
     */
    @Override
    public String toString() {
        return "To " + destination;
    }
}
