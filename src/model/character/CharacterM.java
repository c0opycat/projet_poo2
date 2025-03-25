package model.character;

import model.item.Protection;
import model.item.weapon.Weapon;
import model.location.LocationM;

public abstract class CharacterM {
    private LocationM curLoc;
    private LocationM lastLoc;
    public int posx;
    public int posy;
    protected int health;
    public final int MAXHEALTH;
    protected Protection shield; //Can be null
    protected Weapon weapon; //Can be null

    public CharacterM(int health, int maxHealth, LocationM location, int posx, int posy) {
        this.posx = posx;
        this.posy = posy;
        this.lastLoc = null;
        this.curLoc = location;
        this.health = health;
        this.MAXHEALTH = maxHealth;
        this.shield = null;
        this.weapon = null;
    }
    //GET and SET position of charcter ont the
    public int getPosx() {
        return posx;
    }
    public void setPosx(int posx) {
        this.posx = posx;
    }
    public int getPosy() {
        return posy;
    }
    public void setPosy(int posy) {
        this.posy = posy;
    }
    //Returns the character's health
    public int getHealth(){
        return health;
    }

    //Returns the character's shield
    public Protection getShield(){
        return shield;
    }

    //Returns the character's weapon
    public Weapon getWeapon(){
        return weapon;
    }

    //Set the character's health
    public void setHealth(int h)
    {
        int newHP = h;
        if(h < 0)
        {
            newHP = 0;
        }
        else if(h > this.MAXHEALTH)
        {
            newHP = this.MAXHEALTH;
        }

        this.health = newHP;
    }

    public void setWeapon(Weapon w)
    {
        this.weapon = w;
    }

    public void setShield(Protection s)
    {
        this.shield = s;
    }

    //Returns true if the character has 0 or less health
    public boolean isKO()
    {
        return health <= 0;
    }
}
