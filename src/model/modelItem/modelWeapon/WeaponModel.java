package model.modelItem.modelWeapon;

import java.util.Random;

import model.modelGame.MessageEnModel;
import model.modelItem.ItemModel;

/**
 * Represents a weapon that can be equipped and used by characters.
 * <p>
 * A weapon has a damage value and a specific type (e.g. blunt, keen, piercing).
 * This class provides a method to generate a random weapon and stores basic weapon data.
 * </p>
 */
public abstract class WeaponModel extends ItemModel {

    /** The damage this weapon deals. */
    private final int damage;

    /** The type of damage this weapon inflicts (BLUNT, KEEN, or PIERCING). */
    private final DamageTypeModel type;

    /**
     * Constructs a WeaponModel with specific damage, weight and damage type.
     * @param damage the amount of damage this weapon inflicts
     * @param weight the weight of the weapon (used in inventory mechanics)
     * @param type the type of damage this weapon inflicts
     */
    public WeaponModel(int damage, int weight, DamageTypeModel type){
        super(weight);
        this.damage = damage;
        this.type = type;
    }

    /**
     * Gets the amount of damage this weapon deals.
     * @return the damage value
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Generates and returns a random weapon.
     * <p>
     * Possible weapons are:
     * <ul>
     *   <li>Gun (PIERCING)</li>
     *   <li>Baseball bat (BLUNT)</li>
     *   <li>Sword (KEEN)</li>
     *   <li>Doggo (KEEN, random name)</li>
     * </ul>
     * </p>
     * @return a randomly selected {@link WeaponModel}
     */
    public static WeaponModel randWeapon(){
        Random rand = new Random();
        WeaponModel res = null;
        int i = rand.nextInt(3);
        switch(i)
        {
            case 0: {
                res = new GunModel(); break;
            }
            case 1: {
                res = new BaseballBatModel();
                break;
            }
            case 2: {
                res = new SwordModel(); break;
            }
            default: {
                res = new DoggoModel(MessageEnModel.randName()); break;
            }
        }
        return res;
    }

    /**
     * Gets the type of damage this weapon inflicts.
     * @return the damage type
     */
    public DamageTypeModel getType() {
        return type;
    }
}
