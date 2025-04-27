package model.modelItem.modelConsumable;

import model.modelGame.MessageEnModel;
import model.modelItem.ItemModel;
import model.modelCharacter.modelHeros.HeroModel;

public abstract class ConsumableModel extends ItemModel
{
    //Corresponds to the amount of HP restored by eating the modelConsumable
    public final int HP_RESTORED;


    public ConsumableModel(int hp, int weight)
    {
        super(weight);
        this.HP_RESTORED = hp;
    }

    //Adds HP_RESTORED hp to the hero's hp (without exceeding his max health) and remove the modelConsumable from the hero's backpack
    public void consume(HeroModel h)
    {
        int new_hp = h.getHealth() + this.HP_RESTORED;

        if(new_hp > h.MAXHEALTH)
        {
            h.setHealth(h.MAXHEALTH);
        }
        else
        {
            h.setHealth(h.getHealth() + this.HP_RESTORED);
        }

        HeroModel.gBackpack().removeItem(this);
        System.out.println(MessageEnModel.herosHP(h));
    }
}
