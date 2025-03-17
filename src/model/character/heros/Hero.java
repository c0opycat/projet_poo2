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
import model.character.Character;
import model.game.Message;

import static model.character.heros.Job.STARTUP;

public class Hero extends Character {
    private static Backpack backpack;
    public String names;
    public Job job;
    private String DEFAULT_NAME = "Herbert-Gontran DeNeuneu";
    private Job DEFAULT_JOB = STARTUP;

    public Hero(String name, Job job) {
        super(100, 100);
        Hero.backpack = new Backpack();
        this.names = name;
        this.job = job;
        this.jobEffect();
    }
    public Hero() {
        super(30, 60);
        Hero.backpack = new Backpack();
        this.names = DEFAULT_NAME;
        this.job = DEFAULT_JOB;
    }

    public Job getJob() {
        return job;
    }

    public void jobEffect() {
        Job j = getJob();
        switch (j){
            case STARTUP:setHealth(30);break;
            case MEDIC:Hero.backpack.addItem(new Medicine());break;
            case TEACHER:;break;
            case DELIVERY:;break;
            case SECURITY:Hero.backpack.addItem(Weapon.randWeapon());break;
            case VETERINARY:this.weapon = new Doggo(Message.randName());break;
        }
    }

    public static Backpack gBackpack()
    {
        return Hero.backpack;
    }

    //Returns true if the hero has succeeded his attack
    public boolean attack(Character c){
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
        else System.out.println(Message.handFull(this.getWeapon()));
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
        Backpack toDrop = Hero.backpack;
        l.addItem(toDrop);
        Hero.backpack = BP;
    }

    @Override
    public String toString() {
        String msg =  super.toString();
        msg += "\nBackpack: \n" + backpack.toString();
        return msg;
    }
}
