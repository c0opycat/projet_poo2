package model.item.consumable;

import model.game.Message;
import model.item.Item;
import model.character.Hero;

public abstract class Consumable extends Item
{
    //Corresponds to the amount of HP restored by eating the consumable
    public final int HP_RESTORED;


    public Consumable(int hp, int weight)
    {
        super(weight);
        this.HP_RESTORED = hp;
    }

    //Adds HP_RESTORED hp to the hero's hp (without exceeding his max health) and remove the consumable from the hero's backpack
    public void consume(Hero h)
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

        Hero.gBackpack().removeItem(this);
        System.out.println(Message.herosHP(h));
    }
}
