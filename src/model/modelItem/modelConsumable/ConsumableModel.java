package model.modelItem.modelConsumable;

import model.modelCharacter.modelHeros.HeroModel;
import model.modelGame.MessageModel;
import model.modelItem.ItemModel;

/**
 * Represents a consumable item that can be used to restore the player's health.
 * <p>This is an abstract class that serves as a base for specific consumable items like food or potions.</p>
 * <p>Each consumable restores a predefined amount of health points (HP) when consumed,
 * and is removed from the hero's backpack after use.</p>
 */
public abstract class ConsumableModel extends ItemModel {

  /**
   * The amount of health points this consumable restores when used.
   */
  public final int HP_RESTORED;

  /**
   * Constructs a new ConsumableModel with the specified amount of health restoration and weight.
   * @param hp the amount of HP restored when consumed
   * @param weight the weight of the consumable in the inventory
   */
  public ConsumableModel(int hp, int weight) {
    super(weight);
    this.HP_RESTORED = hp;
  }

  /**
   * Consumes this item, restoring health to the hero and removing the consumable from the hero's backpack.
   * <p>If the healing exceeds the hero's maximum health, it is capped at MAXHEALTH.</p>
   * @param h the hero who consumes the item
   */
  public void consume(HeroModel h) {
    int new_hp = h.getHealth() + this.HP_RESTORED;

    if (new_hp > h.MAXHEALTH) {
      h.setHealth(h.MAXHEALTH);
    } else {
      h.setHealth(h.getHealth() + this.HP_RESTORED);
    }

    HeroModel.gBackpack().removeItem(this);
    System.out.println(MessageModel.herosHP(h));
  }
}
