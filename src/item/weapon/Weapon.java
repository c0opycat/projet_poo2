package item.weapon;

import item.Item;

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

    public DamageType getType() {
        return type;
    }
}
