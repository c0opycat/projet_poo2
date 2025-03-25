package model.character.monster;

import java.util.Random;

import model.character.CharacterM;
import model.game.MessageM;
import model.item.Protection;
import model.item.weapon.*;

public abstract class Monster extends CharacterM {
    // Monsters either do damages with his hands (for instance)
    // or with a weapon.
    // If he has a weapon, he does damages with it,
    // if he has not, he does with this int.
    protected int monsterDamage;

    public Monster(int monsterDamage, int health, int maxHealth) {
        super(health, maxHealth, null, 5, 10);
        this.monsterDamage = monsterDamage;
        int weapon = (int)(Math.random() * 4);
        switch (weapon) {
            case 0:
                this.weapon = new BaseballBat();
                break;
            case 1:
                this.weapon = new Gun();
                break;
            case 2:
                this.weapon = new Sword();
                break;
            default:
                this.weapon = null;
                break;
        }
        int shield = (int)(Math.random() * 2);
        if (shield == 0){
            this.shield = new Protection();
        } else {
            this.shield = null;
        }
    }

    public int getMonsterDamage(){return monsterDamage;}

    public int getDamage()
    {
        if(this.weapon == null)
        {
            return this.getMonsterDamage();
        }
        else
        {
            return this.weapon.getDamage();
        }
    }

    public void attack(CharacterM c){
        if (weapon == null){
            if (c.getShield() == null){
                c.setHealth(c.getHealth() - this.getDamage());
            } else {
                c.setHealth(c.getHealth() - c.getShield().protect(this.getDamage(), null));
            }
        } else {
            if (c.getShield() == null){
                c.setHealth(c.getHealth() - weapon.getDamage());
            } else {
                c.setHealth(c.getHealth() - c.getShield().protect(weapon.getDamage(), weapon.getType()));
            }
        }

        System.out.println(MessageM.monsterAttack(this));
    }

    public static Monster randMonster()
    {
        Monster res;

        Random r = new Random();

        int nbMonster = r.nextInt(3);

        switch(nbMonster)
        {
            case 0:
            {
                res = new AngryM();
                break;
            }
            case 1:
            {
                res = new ColossusM();
                break;
            }
            default:
            {
                res = new DriedM();
                break;
            }
        }

        return res;
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName() + " (" + this.getHealth() + " HP)";
    }
}
