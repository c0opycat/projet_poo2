package model.modelItem.modelWeapon;

/**
 * Represents a special weapon called "Doggo" that can be equipped by the hero.
 * <p>
 * This weapon deals keen damage and has no weight, making it a unique weapon in the game.
 * The Doggo has a name which can be customized at creation.
 * </p>
 */
public class DoggoModel extends WeaponModel {

    /**
     * The name of the Doggo.
     */
    public String name;

    /**
     * Constructs a new DoggoModel with a given name.
     * <p>
     * The Doggo deals 10 keen damage and has a weight of 0.
     * </p>
     * @param name The name of the Doggo.
     */
    public DoggoModel(String name) {
        super(10, 0, DamageTypeModel.KEEN);
        this.name = name;
    }
}
