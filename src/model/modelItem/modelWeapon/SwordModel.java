package model.modelItem.modelWeapon;

/**
 * Represents a Sword weapon that can be used by the hero or monsters.
 * <p>
 * This weapon deals keen damage, is relatively heavy, and inflicts moderate damage.
 * </p>
 */
public class SwordModel extends WeaponModel {

    /**
     * Constructs a new SwordModel.
     * <p>
     * The Sword deals 15 keen damage and has a weight of 10.
     * </p>
     */
    public SwordModel() {
        super(15, 10, DamageTypeModel.KEEN);
    }
}
