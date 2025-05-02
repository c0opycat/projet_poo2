package model.modelCharacter;

import model.modelItem.ProtectionModel;
import model.modelItem.modelWeapon.WeaponModel;
import model.modelLocation.LocationModel;

/**
 * Represents a generic character in the game.
 * A CharacterModel can be any entity with health, a location, a position on a grid,
 * and optional equipment such as a weapon or shield. This class serves as a base
 * class for playable characters and monsters.
 * @author G. Jardin
 */
public abstract class CharacterModel {

  /** The X position of the character on the location grid. */
  public int posx;

  /** The Y position of the character on the location grid. */
  public int posy;

  /** The current health points of the character. */
  protected int health;

  /** The maximum health points the character can have. */
  public final int MAXHEALTH;

  /** The shield equipped by the character, can be null. */
  protected ProtectionModel shield;

  /** The weapon equipped by the character, can be null. */
  protected WeaponModel weapon;

  /**
   * Constructs a new CharacterModel.
   * @param health the starting health points of the character
   * @param maxHealth the maximum health points of the character
   * @param location the current location of the character
   * @param posx the X position of the character on the grid
   * @param posy the Y position of the character on the grid
   */
  public CharacterModel(
    int health,
    int maxHealth,
    LocationModel location,
    int posx,
    int posy
  ) {
    this.posx = posx;
    this.posy = posy;
    this.health = health;
    this.MAXHEALTH = maxHealth;
    this.shield = null;
    this.weapon = null;
  }

  /**
   * Gets the current X position on the grid.
   * @return the X position
   */
  public int getPosx() {
    return posx;
  }

  /**
   * Sets the current X position on the grid.
   * @param posx the X position
   */
  public void setPosx(int posx) {
    this.posx = posx;
  }

  /**
   * Gets the current Y position on the grid.
   * @return the Y position
   */
  public int getPosy() {
    return posy;
  }

  /**
   * Sets the current Y position on the grid.
   * @param posy the Y position
   */
  public void setPosy(int posy) {
    this.posy = posy;
  }

  /**
   * Gets the current health of the character.
   * @return the current health
   */
  public int getHealth() {
    return health;
  }

  /**
   * Gets the shield equipped by the character.
   * @return the shield, or null if none equipped
   */
  public ProtectionModel getShield() {
    return shield;
  }

  /**
   * Gets the weapon equipped by the character.
   * @return the weapon, or null if none equipped
   */
  public WeaponModel getWeapon() {
    return weapon;
  }

  /**
   * Sets the health of the character.
   * <p>
   * If the given health is below 0, it will be set to 0.
   * If the given health exceeds {@code MAXHEALTH}, it will be capped to {@code MAXHEALTH}.
   * @param h the new health value
   */
  public void setHealth(int h) {
    int newHP = h;
    if (h < 0) {
      newHP = 0;
    } else if (h > this.MAXHEALTH) {
      newHP = this.MAXHEALTH;
    }

    this.health = newHP;
  }

  /**
   * Sets the weapon equipped by the character.
   * @param w the weapon to equip
   */
  public void setWeapon(WeaponModel w) {
    this.weapon = w;
  }

  /**
   * Sets the shield equipped by the character.
   *
   * @param s the shield to equip
   */
  public void setShield(ProtectionModel s) {
    this.shield = s;
  }

  /**
   * Checks whether the character is knocked out (health is 0 or less).
   * @return true if health is 0 or less, false otherwise
   */
  public boolean isKO() {
    return health <= 0;
  }
}
