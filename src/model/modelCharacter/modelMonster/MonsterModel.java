package model.modelCharacter.modelMonster;

import java.util.Random;

import model.modelCharacter.CharacterModel;
import model.modelGame.MessageEnModel;
import model.modelItem.ProtectionModel;
import model.modelItem.modelWeapon.*;

public abstract class MonsterModel extends CharacterModel {
    // Monsters either do damages with his hands (for instance)
    // or with a modelWeapon.
    // If he has a modelWeapon, he does damages with it,
    // if he has not, he does with this int.
    protected int monsterDamage;

    public MonsterModel(int monsterDamage, int health, int maxHealth) {
        super(health, maxHealth, null, 5, 10);
        this.monsterDamage = monsterDamage;
        int weapon = (int)(Math.random() * 4);
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
        int shield = (int)(Math.random() * 2);
        if (shield == 0){
            this.shield = new ProtectionModel();
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

    public void attack(CharacterModel c){
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

        System.out.println(MessageEnModel.monsterAttack(this));
    }

    public static MonsterModel randMonster()
    {
        MonsterModel res;

        Random r = new Random();

        int nbMonster = r.nextInt(3);

        switch(nbMonster)
        {
            case 0:
            {
                res = new AngryModel();
                break;
            }
            case 1:
            {
                res = new ColossusModel();
                break;
            }
            default:
            {
                res = new DriedModel();
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
