package model.modelCharacter;

import model.modelItem.ProtectionModel;
import model.modelItem.modelWeapon.WeaponModel;
import model.modelLocation.LocationModel;

public abstract class CharacterModel {
    private LocationModel curLoc;
    private LocationModel lastLoc;
    public int posx;
    public int posy;
    protected int health;
    public final int MAXHEALTH;
    protected ProtectionModel shield; //Can be null
    protected WeaponModel weapon; //Can be null

    public CharacterModel(int health, int maxHealth, LocationModel location, int posx, int posy) {
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
    //Returns the modelCharacter's health
    public int getHealth(){
        return health;
    }

    //Returns the modelCharacter's shield
    public ProtectionModel getShield(){
        return shield;
    }

    //Returns the modelCharacter's modelWeapon
    public WeaponModel getWeapon(){
        return weapon;
    }

    //Set the modelCharacter's health
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

    public void setWeapon(WeaponModel w)
    {
        this.weapon = w;
    }

    public void setShield(ProtectionModel s)
    {
        this.shield = s;
    }

    //Returns true if the modelCharacter has 0 or less health
    public boolean isKO()
    {
        return health <= 0;
    }
}
