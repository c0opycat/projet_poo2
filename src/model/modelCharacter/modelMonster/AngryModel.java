package model.modelCharacter.modelMonster;

/**
 * Represents an "Angry" type monster.
 * <p>
 * This monster has a moderate amount of health and deals damage using its own abilities
 * or possibly with a weapon inherited from {@link MonsterModel}.
 */
public class AngryModel extends MonsterModel {
    /**
     * Constructs a new AngryModel monster with default stats.
     * <p>
     * The Angry is initialized with:
     *      * <ul>
     *      *     <li>30 base monster damage</li>
     *      *     <li>30 current health</li>
     *      *     <li>30 maximum health</li>
     *      * </ul>
     */
    public AngryModel() {
        super(30, 30, 30);
    }

    /**
     * Returns a simple string representation of the monster.
     * @return the string "angry"
     */
    @Override
    public String toString() {
        return "angry";
    }
}
