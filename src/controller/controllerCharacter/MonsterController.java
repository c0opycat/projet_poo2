package controller.controllerCharacter;

import model.modelCharacter.modelMonster.MonsterModel;
import view.viewCharacter.HeroView;
import view.viewCharacter.MonsterView;
import view.viewGame.GameView;
import view.viewLocation.LocationView;

/**
 * Controller class for managing monsters in the game.
 * This class acts as an intermediary between the MonsterModel and MonsterView.
 * It handles all monster-related operations including retrieving monster attributes,
 * generating descriptions, updating the UI, and executing monster attacks.
 * @author L. Cooper
 */
public class MonsterController {

  /** The view that displays the monster in the game interface. */
  private final MonsterView monsterView;

  /** The model containing the monster's data and business logic. */
  private final MonsterModel monsterModel;

  /** Reference to the main game view for updating UI elements. */
  private final GameView gameView;

  /**
   * Constructs a new MonsterController with the specified game components.
   * Initializes the controller with references to the game view, monster view,
   * and retrieves the monster model from the current location model.
   *
   * @param gameView the main game view
   * @param monsterView the view representing this monster
   * @param locationView the location view where this monster resides
   */
  public MonsterController(
    GameView gameView,
    MonsterView monsterView,
    LocationView locationView
  ) {
    this.monsterView = monsterView;
    this.monsterModel = locationView
      .getLocationController()
      .getLocationModel()
      .getMonster();
    this.gameView = gameView;
  }

  /**
   * Gets the monster view associated with this controller.
   *
   * @return the MonsterView instance
   */
  public MonsterView getMonsterView() {
    return this.monsterView;
  }

  /**
   * Gets the monster model associated with this controller.
   *
   * @return the MonsterModel instance
   */
  public MonsterModel getMonsterModel() {
    return this.monsterModel;
  }

  /**
   * Gets the game view associated with this controller.
   *
   * @return the GameView instance
   */
  public GameView getGameView() {
    return this.gameView;
  }

  /**
   * Checks if the monster model exists.
   *
   * @return true if the monster view is not null, false otherwise
   */
  public boolean hasMonsterModel() {
    return this.monsterView != null;
  }

  /**
   * Checks if the monster is knocked out (KO).
   *
   * @return true if the monster is knocked out, false otherwise
   */
  public boolean isKo() {
    return this.getMonsterModel().isKO();
  }

  /**
   * Gets the monster type based on the class name of the monster model.
   * Extracts the monster type by removing the "Model" suffix from the class name.
   * For example, "ColossusModel" becomes "Colossus".
   *
   * @return a string representing the monster's type
   */
  public String getType() {
    String className = this.getMonsterModel().getClass().getSimpleName();
    return className.substring(0, className.length() - 5);
  }

  /**
   * Gets the monster's current health points.
   *
   * @return a string representing the monster's health
   */
  public String getHP() {
    return String.valueOf(this.getMonsterModel().getHealth());
  }

  /**
   * Gets the weapon currently used by the monster.
   *
   * @return a string representation of the monster's weapon
   */
  public String getWeapon() {
    return this.getMonsterModel().getWeapon() == null
      ? ""
      : this.getMonsterModel().getWeapon().toString();
  }

  /**
   * Gets the protection (shield) currently used by the monster.
   *
   * @return a string representation of the monster's shield
   */
  public String getProtection() {
    return this.getMonsterModel().getShield() == null
      ? ""
      : this.getMonsterModel().getShield().toString();
  }

  /**
   * Gets a formatted description of the monster.
   * This method generates a multi-line string containing the monster's key information:
   * type, current health points (HP), weapon, and protection.
   * Each piece of information is separated by a new line for better readability in the UI.
   *
   * @return a formatted string containing the monster's description
   */
  public String getDescription() {
    String newLine = System.getProperty("line.separator");

    return (
      this.getType() +
      " : " +
      newLine +
      this.getHP() +
      " HP" +
      newLine +
      this.getWeapon() +
      newLine +
      this.getProtection() +
      newLine
    );
  }

  /**
   * Updates the monster description in the game interface.
   * Clears the current monster info display and sets it with the latest
   * monster description information.
   */
  public void updateMonsterDescription() {
    if (this.getGameView() != null) {
      this.getGameView().getMonsterInfos().clear();
      this.getGameView().getMonsterInfos().setText(getDescription());
    }
  }

  /**
   * Executes a monster attack against the hero.
   * The monster attacks the hero through the model layer, potentially causing
   * damage. If the attack results in the hero being defeated, triggers the
   * game end sequence.
   */
  public void attack() {
    HeroView heroView = this.getGameView().getHeroView();
    this.getMonsterModel().attack(heroView.getHeroController().getHeroModel());

    if (this.getGameView().getGameController().isEnd()) {
      this.getGameView().endGame();
    }
  }
}
