package model.modelItem.modelWeapon;

import java.util.Random;

import model.modelGame.MessageEnModel;
import model.modelItem.ItemModel;

public abstract class WeaponModel extends ItemModel {
    private final int damage;
    private final DamageTypeModel type;

    public WeaponModel(int damage, int weight, DamageTypeModel type){
        super(weight);
        this.damage = damage;
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public static WeaponModel randWeapon(){
        Random rand = new Random();
        WeaponModel res = null;
        int i = rand.nextInt(3);
        switch(i)
        {
            case 0: {
                res = new GunModel(); break;
            }
            case 1: {
                res = new BaseballBatModel();
                break;
            }
            case 2: {
                res = new SwordModel(); break;
            }
            default: {
                res = new DoggoModel(MessageEnModel.randName()); break;
            }
        }
        return res;
    }

    public DamageTypeModel getType() {
        return type;
    }
}
