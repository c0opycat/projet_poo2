package model.modelItem.modelWeapon;

/**
 * Represents a Baseball Bat weapon.
 * <p>
 * This weapon inflicts blunt damage and has predefined values for its damage and weight.
 * </p>
 * <p>Specifications:</p>
 * <ul>
 *   <li>Damage: 10</li>
 *   <li>Weight: 7</li>
 *   <li>Damage Type: {@link DamageTypeModel#BLUNT}</li>
 * </ul>
 */
public class BaseballBatModel extends WeaponModel {

    /**
     * Constructs a new {@code BaseballBatModel} with predefined damage, weight, and blunt damage type.
     */
    public BaseballBatModel() {
        super(10, 7, DamageTypeModel.BLUNT);
    }
}
