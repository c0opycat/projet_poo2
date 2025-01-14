package character;

import item.Protection;
import item.weapon.Weapon;

public abstract class Character {
    protected int health;
    public final int MAXHEALTH;
    protected Protection shield; //Can be null
    protected Weapon weapon; //Can be null

    public Character(int health, int maxHealth) {
        this.health = health;
        this.MAXHEALTH = maxHealth;
        this.shield = null;
        this.weapon = null;
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
