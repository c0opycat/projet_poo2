package model.modelCharacter.modelMonster;

import java.util.Random;
import model.modelCharacter.CharacterModel;
import model.modelGame.MessageModel;
import model.modelItem.ProtectionModel;
import model.modelItem.modelWeapon.*;

/**
 * Abstract class that represents the group monster in the game.
 * <p>
 * A monster is a type of character that may attack using either its innate monster damage
 * or a randomly assigned weapon. Monsters can also be equipped with a shield.
 * </p>
 */
public abstract class MonsterModel extends CharacterModel {

  /** The base damage dealt by the monster when no weapon is equipped. */
  protected int monsterDamage;

  /**
   * Constructor used by the child classes of MonsterModel with given attributes.
   * A random weapon and shield may also be assigned during initialization.
   * @param monsterDamage the damage inflicted when no weapon is equipped
   * @param health the initial health of the monster
   * @param maxHealth the maximum health of the monster
   */
  public MonsterModel(int monsterDamage, int health, int maxHealth) {
    super(health, maxHealth, null, 5, 10);
    this.monsterDamage = monsterDamage;

    // Assign random weapon
    int weapon = (int) (Math.random() * 4);
    switch (weapon) {
      case 0:
        this.weapon = new BaseballBatModel();
        break;
      case 1:
        this.weapon = new GunModel();
        break;
      case 2:
        this.weapon = new SwordModel();
        break;
      default:
        this.weapon = null;
        break;
    }

    //Assign random shield
    int shield = (int) (Math.random() * 2);
    if (shield == 0) {
      this.shield = new ProtectionModel();
    } else {
      this.shield = null;
    }
  }

  /**
   * Returns the monster's innate damage (when no weapon is equipped).
   * @return the base monster damage
   */
  private int getMonsterDamage() {
    return monsterDamage;
  }

  /**
   * Returns the damage dealt by the monster.
   * This may come from its weapon or from its base monster damage.
   * @return the amount of damage dealt
   */
  public int getDamage() {
    if (this.weapon == null) {
      return this.getMonsterDamage();
    } else {
      return this.weapon.getDamage();
    }
  }

  /**
   * Attacks the given character and applies damage considering possible protection.
   * @param c the character to attack
   */
  public void attack(CharacterModel c) {
    if (weapon == null) {
      if (c.getShield() == null) {
        c.setHealth(c.getHealth() - this.getDamage());
      } else {
        c.setHealth(
          c.getHealth() - c.getShield().protect(this.getDamage(), null)
        );
      }
    } else {
      if (c.getShield() == null) {
        c.setHealth(c.getHealth() - weapon.getDamage());
      } else {
        c.setHealth(
          c.getHealth() -
          c.getShield().protect(weapon.getDamage(), weapon.getType())
        );
      }
    }

    System.out.println(MessageModel.monsterAttack(this));
  }

  /**
   * Generates and returns a random instance of a child class of monster
   * @return a random {@code MonsterModel} instance (AngryModel, ColossusModel, or DriedModel)
   */
  public static MonsterModel randMonster() {
    MonsterModel res;

    Random r = new Random();

    int nbMonster = r.nextInt(3);

    switch (nbMonster) {
      case 0: {
        res = new AngryModel();
        break;
      }
      case 1: {
        res = new ColossusModel();
        break;
      }
      default: {
        res = new DriedModel();
        break;
      }
    }

    return res;
  }

  /**
   * Returns a string representation of the monster showing its class name and current HP.
   * @return a string representing the monster
   */
  @Override
  public String toString() {
    return this.getClass().getSimpleName() + " (" + this.getHealth() + " HP)";
  }
}
