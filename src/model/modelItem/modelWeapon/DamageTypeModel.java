package model.modelItem.modelWeapon;

/**
 * Enumeration representing the possible types of damage that a weapon can inflict.
 * <p>
 * The type of damage can affect how effective a weapon is against different kinds of enemies or defenses.
 * </p>
 * <ul>
 *   <li>{@link #BLUNT} - Represents blunt damage (e.g., clubs, baseball bats).</li>
 *   <li>{@link #KEEN} - Represents keen damage (e.g., sharp weapons like swords).</li>
 *   <li>{@link #PIERCING} - Represents piercing damage (e.g., guns, arrows).</li>
 * </ul>
 */
public enum DamageTypeModel {

    /**
     * Blunt damage type, typically from heavy or crushing weapons.
     */
    BLUNT,

    /**
     * Keen damage type, typically from sharp-edged weapons.
     */
    KEEN,

    /**
     * Piercing damage type, typically from pointed weapons.
     */
    PIERCING
}
