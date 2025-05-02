package model.modelItem;

import model.modelItem.modelWeapon.DamageTypeModel;

/**
 * Represents a protection item (such as armor) that reduces incoming damage.
 * <p>
 * The reduction depends on the type of damage received.
 * Some damage types (like piercing) ignore the protection completely, while others (like keen damage) are partially reduced.
 * </p>
 */
public class ProtectionModel extends ItemModel {

    /**
     * The default weight (in kg) of the protection item.
     */
    public static final int DEFAULT_WEIGHT = 30;

    /**
     * The default percentage of damage reduction (as a decimal value).
     */
    public static final double DEFAULT_DAMAGE_REDUCTION = 0.2;

    /**
     * The percentage of damage reduced when this protection is equipped.
     * This can affect how much incoming damage is mitigated depending on the damage type.
     */
    public final double DAMAGE_REDUCTION;

    /**
     * Constructs a new ProtectionModel with default weight and damage reduction values.
     */
    public ProtectionModel() {
        super(DEFAULT_WEIGHT);
        this.DAMAGE_REDUCTION = DEFAULT_DAMAGE_REDUCTION;
    }

    /**
     * Calculates the final amount of damage taken after applying the protection effect.
     * @param damage the initial damage value
     * @param dt the {@link DamageTypeModel} representing the type of damage dealt
     * @return the reduced damage value based on the protection's reduction rules
     */
    public int protect(int damage, DamageTypeModel dt) {
        int res;

        if (dt != null) {
            res = switch (dt) {
                case PIERCING -> damage; // Piercing damage ignores protection completely
                case KEEN -> damage - (int) ((float) damage * (this.DAMAGE_REDUCTION / 2.)); // Keen damage is reduced by half of the reduction value
                default -> damage - (int) ((float) damage * this.DAMAGE_REDUCTION); // Other damage types use full reduction
            };
        } else {
            res = damage - (int) ((float) damage * this.DAMAGE_REDUCTION); // If no type, use default reduction
        }
        return res;
    }
}
