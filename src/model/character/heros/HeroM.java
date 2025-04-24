/**
 * Represents the playable Hero character in the game.
 * <p>
 * The Hero extends {@link CharacterM} and includes additional functionality such as:
 * - A {@link Backpack} for carrying items
 * - A specific {@link JobM} that modifies starting attributes or equipment
 */

package model.character.heros;

import model.item.consumable.Medicine;
import model.item.container.Backpack;
import model.item.weapon.*;
import model.location.LocationM;
import model.character.CharacterM;
import model.game.MessageM;

import static model.character.heros.JobM.STARTUP;

public class HeroM extends CharacterM {

    /**
     * The backpack that holds the Hero's inventory.
     */
    private static Backpack backpack;

    /**
     * The name of the Hero.
     */
    public String names;

    /**
     * The job assigned to this Hero.
     */
    public JobM jobM;

    private final String DEFAULT_NAME = "Herbert-Gontran DeNeuneu";
    private final JobM DEFAULT_JOBM = STARTUP;

    /**
     * Constructs a Hero with a specified name and job.
     *
     * @param name  the name of the Hero
     * @param jobM  the selected job
     */
    public HeroM(String name, JobM jobM) {
        super(100, 100, null, 5, 10);
        HeroM.backpack = new Backpack();
        this.names = name;
        this.jobM = jobM;
        this.jobEffect();
    }

    /**
     * Constructs a Hero with default name and job.
     */
    public HeroM() {
        super(30, 60, null, 5, 15);
        HeroM.backpack = new Backpack();
        this.names = DEFAULT_NAME;
        this.jobM = DEFAULT_JOBM;
    }

    /**
     * Gets the job of the Hero.
     * @return the Hero's job
     */
    public JobM getJob() {
        return jobM;
    }

    /**
     * Applies effects based on the Hero's job.
     * This may include setting initial health or adding items to the backpack.
     */
    public void jobEffect() {
        JobM j = getJob();
        switch (j) {
            case STARTUP -> setHealth(30);
            case MEDIC -> HeroM.backpack.addItem(new Medicine());
            case SECURITY -> HeroM.backpack.addItem(Weapon.randWeapon());
            case VETERINARY -> this.weapon = new Doggo(MessageM.randName());
            // Future jobs like TEACHER and DELIVERY can be added here
        }
    }

    /**
     * Gets the Hero's backpack.
     * @return the shared backpack instance
     */
    public static Backpack gBackpack() {
        return HeroM.backpack;
    }

    /**
     * Attacks another character. Returns true if the Hero successfully attacked.
     * @param c the character to attack
     * @return true if an attack was made; false otherwise
     */
    public boolean attack(CharacterM c) {
        boolean res = false;
        if (this.getWeapon() != null) {
            if (c.getShield() == null) {
                c.setHealth(c.getHealth() - weapon.getDamage());
            } else {
                c.setHealth(c.getHealth() - c.getShield().protect(weapon.getDamage(), weapon.getType()));
            }
            res = true;
        }
        return res;
    }

    /**
     * Equips a weapon if the Hero's hands are free.
     * @param weapon the weapon to equip
     */
    public void equipWeapon(Weapon weapon) {
        if (this.getWeapon() == null) {
            this.setWeapon(weapon);
        } else {
            System.out.println(MessageM.handFull(this.getWeapon()));
        }
    }

    /**
     * Drops the Hero's current weapon into the given location.
     * @param l the location to drop the weapon in
     */
    public void dropWeapon(LocationM l) {
        if (this.getWeapon() != null) {
            l.addItem(this.weapon);
            this.setWeapon(null);
        }
    }

    /**
     * Drops the Hero's current shield into the given location.
     * @param l the location to drop the protection in
     */
    public void dropProtection(LocationM l) {
        if (this.getShield() != null) {
            l.addItem(this.getShield());
            this.setShield(null);
        }
    }

    /**
     * Switches the current backpack with one in the location.
     * @param BP the new backpack to take
     * @param l  the location from which to take and leave backpacks
     */
    public void switchBackpack(Backpack BP, LocationM l) {
        l.removeItem(BP);
        Backpack toDrop = HeroM.backpack;
        l.addItem(toDrop);
        HeroM.backpack = BP;
    }

    /**
     * Returns a string representation of the Hero,
     * including base character info and backpack contents.
     * @return a string summary of the Hero
     */
    @Override
    public String toString() {
        String msg = super.toString();
        msg += "\nBackpack: \n" + backpack.toString();
        return msg;
    }
}
