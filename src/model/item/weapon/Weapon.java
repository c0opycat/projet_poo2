package model.item.weapon;

import java.util.Random;

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
    public Weapon randWeapon(){

    }

    public DamageType getType() {
        return type;
    }
}
