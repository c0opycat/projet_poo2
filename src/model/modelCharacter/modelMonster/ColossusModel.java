package model.modelCharacter.modelMonster;

/**
 * Represents a Colossus monster in the game.
 * <p>
 * The Colossus is a powerful type of {@link MonsterModel} characterized by its high health
 * and base monster damage. It uses predefined attributes and overrides the {@code toString}
 * method to provide a human-readable name.</p>
 * N.B: Colossus are the only monsters that are not restraining the player from living the {@link model.modelLocation.LocationModel}
 */

public class ColossusModel extends MonsterModel {

    /**
     * Constructs a new Colossus monster with predefined monster damage and health.
     * <p>
     * The Colossus is initialized with:
     * <ul>
     *     <li>50 base monster damage</li>
     *     <li>50 current health</li>
     *     <li>50 maximum health</li>
     * </ul>
     */
    public ColossusModel() {
        super(50, 50, 50);
    }
    @Override
    public String toString(){
        return "colossus";
    }
}
