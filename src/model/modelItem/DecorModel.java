package model.modelItem;

/**
 * Represents a decorative element in the game world.
 * <p>
 * A {@code DecorModel} does not provide any gameplay effect and serves as a flavor or ambiance item.
 * It simply holds a name describing the decoration.
 * </p>
 */
public class DecorModel {

    /**
     * The name or description of the decorative element.
     */
    private String name;

    /**
     * Constructs a {@code DecorModel} with a default description.
     * <p>
     * The default name is set to "just a beautiful thing".
     * </p>
     */
    public DecorModel() {
        this.name = "just a beautiful thing";
    }

    /**
     * Constructs a {@code DecorModel} with a custom name.
     * @param name the name or description of the decoration
     */
    public DecorModel(String name) {
        this.name = name;
    }
}
