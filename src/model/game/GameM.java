package model.game;

import model.character.heros.HeroM;
import model.location.*;

public class GameM {
    private final HeroM HEROM;
    public GameMapM map;
    private Location currentLocation;
    private boolean isEnd;
    private boolean isWon;

    public GameM()
    {
        this.HEROM = new HeroM();
        this.map = new GameMapM();
        this.currentLocation = map.getStartLoc();
        this.isEnd = false;
        this.isWon = false;
    }

    public void forfeit(boolean b) {
        this.isEnd = b;
        this.isWon = false;
    }

    public HeroM getHero(){
        return this.HEROM;
    }

    //Displays the start of game
    public void start()
    {
        System.out.println(MessageM.startGame(map.getStartLoc(), map.getEndLoc()));
        System.out.println(MessageM.getDescription(this.getCurLocation()));
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
            System.out.println(MessageM.gameWon());
        }
        else
        {
            System.out.println(MessageM.gameLost());
        }
    }
}
