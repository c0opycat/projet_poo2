package model.modelCharacter.modelMonster;

/**
 * Represents a Dried monster in the game.
 * <p>
 * The Dried is a fragile but aggressive type of {@link MonsterModel}, characterized
 * by lower health and base monster damage. It overrides the {@code toString} method
 * to provide a human-readable name.
 */
public class DriedModel extends MonsterModel {

    /**
     * Constructs a new Dried monster with predefined monster damage and health.
     * <p>
     * The Dried is initialized with:
     * <ul>
     *     <li>20 base monster damage</li>
     *     <li>20 current health</li>
     *     <li>20 maximum health</li>
     * </ul>
     */
    public DriedModel(){
        super(20, 20, 20);
    }

    @Override
    public String toString() {
        return "dried";
    }
}
