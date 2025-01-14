package game;

import character.*;
import location.*;

public class Game{
    private final Hero HERO;
    public GameMap map;
    private Location currentLocation;
    private boolean isEnd;
    private boolean isWon;

    public Game()
    {
        this.HERO = new Hero();
        this.map = new GameMap();
        this.currentLocation = map.getStartLoc();
        this.isEnd = false;
        this.isWon = false;
    }

    public void forfeit(boolean b) {
        this.isEnd = b;
        this.isWon = false;
    }

    public Hero getHero(){
        return this.HERO;
    }

    //Displays the start of game
    public void start()
    {
        System.out.println(Message.startGame(map.getStartLoc(), map.getEndLoc()));
        System.out.println(Message.getDescription(this.getCurLocation()));
    }

    //Returns the current Location
    public Location getCurLocation()
    {
        return this.currentLocation;
    }

    //Sets the current location to l
    public void setCurLocation(Location l)
    {
        this.currentLocation = l;
    }

    //Returns true if the game is won
    public boolean isWon()
    {
        return this.isWon;
    }

    //Checks is the game is ended and returns if it is
    public boolean isEnd()
    {
        if(this.getHero().isKO())
        {
            return true;
        }
        else if(this.getCurLocation() == map.getEndLoc())
        {
            this.isWon = true;
            this.isEnd = true;
        }

        return this.isEnd;
    }

    //Displays the end of game
    public void displayEnd()
    {
        if(this.isWon())
        {
            System.out.println(Message.gameWon());
        }
        else
        {
            System.out.println(Message.gameLost());
        }
    }
}
