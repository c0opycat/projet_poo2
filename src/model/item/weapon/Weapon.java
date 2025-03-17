package model.item.weapon;

import java.util.Random;

import model.game.Message;
import model.item.Item;

public abstract class Weapon extends Item{
    private final int damage;
    private final DamageType type;

    public Weapon(int damage, int weight, DamageType type){
        super(weight);
        this.damage = damage;
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public static Weapon randWeapon(){
        Random rand = new Random();
        Weapon res = null;
        int i = rand.nextInt(3);
        switch(i)
        {
            case 0: {
                res = new Gun(); break;
            }
            case 1: {
                res = new BaseballBat();
                break;
            }
            case 2: {
                res = new Sword(); break;
            }
            default: {
                res = new Doggo(Message.randName()); break;
            }
        }
        return res;
    }

    public DamageType getType() {
        return type;
    }
}
