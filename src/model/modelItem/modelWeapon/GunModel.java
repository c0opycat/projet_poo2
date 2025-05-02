package model.modelItem.modelWeapon;

/**
 * Represents a Gun weapon that can be used by the hero or monsters.
 * <p>
 * This weapon deals piercing damage, has a moderate weight, and inflicts significant damage.
 * </p>
 */
public class GunModel extends WeaponModel {

    /**
     * Constructs a new GunModel.
     * <p>
     * The Gun deals 20 piercing damage and has a weight of 5.
     * </p>
     */
    public GunModel() {
        super(20, 5, DamageTypeModel.PIERCING);
    }
}
