package model.modelLocation;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents an exit connecting two locations in the modelGame map.
 * Each exit links a start modelLocation to a destination and contains coordinates
 * for where the exit is placed within each modelLocation's map.
 * <p>
 * An exit is restricted to a maximum of four per modelLocation. Coordinates are
 * randomly placed along the edges to simulate doors, roads, or pathways.
 */
public class ExitModel {

    /**
     * The destination modelLocation of this exit.
     */
    public LocationModel destination;

    /**
     * The starting modelLocation of this exit.
     */
    public LocationModel start;

    /**
     * The X coordinate of the exit on the start modelLocation.
     */
    private int strtX;

    /**
     * The Y coordinate of the exit on the start modelLocation.
     */
    private int strtY;

    /**
     * The X coordinate of the exit on the destination modelLocation.
     */
    private int destX;

    /**
     * The Y coordinate of the exit on the destination modelLocation.
     */
    private int destY;

    /**
     * Constructs a new ExitModel from one modelLocation to another.
     * <p>
     * Coordinates are randomly chosen along the borders of each modelLocation based
     * on the current number of existing exits. A modelLocation cannot have more than
     * four exits.
     *
     * @param start       the starting modelLocation of the exit
     * @param destination the destination modelLocation of the exit
     * @throws IllegalArgumentException if either modelLocation already has 4 exits
     */
    public ExitModel(LocationModel start, LocationModel destination) {
        ArrayList<ExitModel> startExits = start.getExits();
        int stExNb = startExits.size();
        ArrayList<ExitModel> destExits = destination.getExits();
        int deExNb = destExits.size();

        if (stExNb >= 4) {
            throw new IllegalArgumentException("you can't add more exits to the starting modelLocation");
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
     * @return a string describing the destination modelLocation
     */
    @Override
    public String toString() {
        return "To " + destination;
    }
}
