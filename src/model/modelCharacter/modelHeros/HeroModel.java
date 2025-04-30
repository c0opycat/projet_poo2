/**
 * Represents the playable Hero modelCharacter in the modelGame.
 * <p>
 * The Hero extends {@link CharacterM} and includes additional functionality such as:
 * - A {@link Backpack} for carrying items
 * - A specific {@link JobM} that modifies starting attributes or equipment
 */

package model.modelCharacter.modelHeros;

import static model.modelCharacter.modelHeros.JobModel.MEDIC;
import static model.modelCharacter.modelHeros.JobModel.STARTUP;

import controller.controllerCharacter.controllerHeros.HeroController;
import java.awt.Point;
import model.modelCharacter.CharacterModel;
import model.modelGame.MessageEnModel;
import model.modelItem.modelConsumable.MedicineModel;
import model.modelItem.modelContainer.BackpackModel;
import model.modelItem.modelWeapon.*;
import model.modelLocation.LocationModel;

public class HeroModel extends CharacterModel {

  /**
   * The controller associated with the hero.
   */
  private final HeroController heroController;
  /**
   * The backpack that holds the Hero's inventory.
   */
  private static BackpackModel backpack;

  /**
   * The name of the Hero.
   */
  public String name;

  /**
   * The job assigned to this Hero.
   */
  public JobModel jobM;

  private final String DEFAULT_NAME = "Herbert-Gontran DeNeuneu";
  private final JobModel DEFAULT_JOBM = STARTUP;

  /**
   * Constructs a Hero with a specified name and job.
   *
   * @param name  the name of the Hero
   * @param jobM  the selected job
   */
  public HeroModel(String name, JobModel jobM) {
    super(100, 100, null, 5, 10);
    HeroModel.backpack = new BackpackModel();
    this.name = name;
    this.jobM = jobM;
    this.heroController = new HeroController(this);
    this.jobEffect();
  }

  /**
   * Constructs a Hero with default name and job.
   */
  public HeroModel() {
    super(30, 60, null, 5, 15);
    HeroModel.backpack = new BackpackModel();
    this.name = DEFAULT_NAME;
    this.jobM = DEFAULT_JOBM;
    this.heroController = new HeroController(this);
  }

  /**
   * Gets the job of the Hero.
   * @return the Hero's job
   */
  public JobModel getJob() {
    return jobM;
  }

  /**
   * Gets the name of the Hero.
   * @return the Hero's name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the Hero's controller.
   * @return the Hero's controller
   */
  public HeroController getHeroController() {
    return this.heroController;
  }

  @Override
  /**
   * Sets the health of the Hero.
   * Ensures the health value is within the valid range (0 to MAXHEALTH).
   * Updates the Hero's description in the associated controller.
   *
   * @param h the new health value to set
   */
  public void setHealth(int h) {
    int newHP = h;
    if (h < 0) {
      newHP = 0;
    } else if (h > this.MAXHEALTH) {
      newHP = this.MAXHEALTH;
    }

    this.health = newHP;

    this.getHeroController().updateDescription();
  }

  /**
   * Applies effects based on the Hero's job.
   * This may include setting initial health or adding items to the backpack.
   */
  public void jobEffect() {
    JobModel j = getJob();
    switch (j) {
      case STARTUP -> setHealth(30);
      case MEDIC -> HeroModel.backpack.addItem(new MedicineModel());
      case SECURITY -> HeroModel.backpack.addItem(WeaponModel.randWeapon());
      case VETERINARY -> this.weapon = new DoggoModel(
        MessageEnModel.randName()
      );
      // Future jobs like TEACHER and DELIVERY can be added here
    }
  }

  /**
   * Gets the Hero's backpack.
   * @return the shared backpack instance
   */
  public static BackpackModel gBackpack() {
    return HeroModel.backpack;
  }

  /**
   * Attacks another modelCharacter. Returns true if the Hero successfully attacked.
   * @param c the modelCharacter to attack
   * @return true if an attack was made; false otherwise
   */
  public boolean attack(CharacterModel c) {
    boolean res = false;
    if (this.getWeapon() != null) {
      if (c.getShield() == null) {
        c.setHealth(c.getHealth() - weapon.getDamage());
      } else {
        c.setHealth(
          c.getHealth() -
          c.getShield().protect(weapon.getDamage(), weapon.getType())
        );
      }
      if (this.getJob() == MEDIC) {
        this.setHealth(this.getHealth() + 5);
      }
      res = true;
    }
    return res;
  }

  /**
   * Equips a modelWeapon if the Hero's hands are free.
   * @param weapon the modelWeapon to equip
   */
  public void equipWeapon(WeaponModel weapon) {
    if (this.getWeapon() == null) {
      this.setWeapon(weapon);
      this.getHeroController().updateDescription();
    } else {
      System.out.println(MessageEnModel.handFull(this.getWeapon()));
    }
  }

  /**
   * Drops the Hero's current modelWeapon into the given modelLocation.
   * @param l the modelLocation to drop the modelWeapon in
   */
  public void dropWeapon(LocationModel l) {
    if (this.getWeapon() != null) {
      Point point = l.getRandomFreeStepCoord();
      if (point != null) {
        l.addItem(this.weapon, l.getRandomFreeStepCoord());
      }
      this.setWeapon(null);
      this.getHeroController().updateDescription();
    }
  }

  /**
   * Drops the Hero's current shield into the given modelLocation.
   * @param l the modelLocation to drop the protection in
   */
  public void dropProtection(LocationModel l) {
    if (this.getShield() != null) {
      Point point = l.getRandomFreeStepCoord();
      if (point != null) {
        l.addItem(this.getShield(), l.getRandomFreeStepCoord());
      }
      this.setShield(null);
      this.getHeroController().updateDescription();
    }
  }

  /**
   * Switches the current backpack with one in the modelLocation.
   * @param BP the new backpack to take
   * @param l  the modelLocation from which to take and leave backpacks
   */
  public void switchBackpack(Point p, LocationModel l) {
    BackpackModel toTake = (BackpackModel) l.getLocMap().get(p).getItem();
    l.removeItem(p);
    BackpackModel toDrop = HeroModel.backpack;
    Point point = l.getRandomFreeStepCoord();
    if (point != null) {
      l.addItem(toDrop, l.getRandomFreeStepCoord());
    }
    HeroModel.backpack = toTake;
  }

  /**
   * Returns a string representation of the Hero,
   * including base modelCharacter info and backpack contents.
   * @return a string summary of the Hero
   */
  @Override
  public String toString() {
    String msg = super.toString();
    msg += "\nBackpackModel: \n" + backpack.toString();
    return msg;
  }
}
