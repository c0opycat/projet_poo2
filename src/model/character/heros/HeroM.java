/*
 * DESCRIPTION :
 * Hero class that extends character therefore has
 * health, MAXHEALTH, weapon,shield
 * and adds
 * backpack, job
 * Hero is the playable character
 */

package model.character.heros;


import model.item.consumable.Medicine;
import model.item.container.*;
import model.item.weapon.*;
import model.location.Location;
import model.character.CharacterM;
import model.game.MessageM;

import static model.character.heros.JobM.STARTUP;

public class HeroM extends CharacterM {
    private static Backpack backpack;
    public String names;
    public JobM jobM;
    private String DEFAULT_NAME = "Herbert-Gontran DeNeuneu";
    private JobM DEFAULT_JOBM = STARTUP;

    public HeroM(String name, JobM jobM) {
        super(100, 100);
        HeroM.backpack = new Backpack();
        this.names = name;
        this.jobM = jobM;
        this.jobEffect();
    }
    public HeroM() {
        super(30, 60);
        HeroM.backpack = new Backpack();
        this.names = DEFAULT_NAME;
        this.jobM = DEFAULT_JOBM;
    }

    public JobM getJob() {
        return jobM;
    }

    public void jobEffect() {
        JobM j = getJob();
        switch (j){
            case STARTUP:setHealth(30);break;
            case MEDIC:
                HeroM.backpack.addItem(new Medicine());break;
            case TEACHER:;break;
            case DELIVERY:;break;
            case SECURITY:
                HeroM.backpack.addItem(Weapon.randWeapon());break;
            case VETERINARY:this.weapon = new Doggo(MessageM.randName());break;
        }
    }

    public static Backpack gBackpack()
    {
        return HeroM.backpack;
    }

    //Returns true if the hero has succeeded his attack
    public boolean attack(CharacterM c){
        boolean res = false;

        if(this.getWeapon() != null)
        {
            if (c.getShield() == null){
                c.setHealth(c.getHealth() - weapon.getDamage());
            } else {
                c.setHealth(c.getHealth() - c.getShield().protect(weapon.getDamage(), weapon.getType()));
            }

            res = true;
        }

        return res;
    }

    public void equipWeapon(Weapon weapon){
        if(this.getWeapon() == null)
        {this.setWeapon(weapon);}
        else System.out.println(MessageM.handFull(this.getWeapon()));
    }

    public void dropWeapon(Location l){
        if(this.getWeapon() != null)
        {
            l.addItem(this.weapon);
            this.setWeapon(null);
        }
    }

    public void dropProtection(Location l){
        if(this.getShield() != null)
        {
            l.addItem(this.getShield());
            this.setShield(null);
        }
    }

    public void switchBackpack(Backpack BP, Location l){
        l.removeItem(BP);
        Backpack toDrop = HeroM.backpack;
        l.addItem(toDrop);
        HeroM.backpack = BP;
    }

    @Override
    public String toString() {
        String msg =  super.toString();
        msg += "\nBackpack: \n" + backpack.toString();
        return msg;
    }
}
