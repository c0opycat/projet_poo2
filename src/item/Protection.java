package item;

import item.weapon.DamageType;

public class Protection extends Item
{
    public static final int DEFAULT_WEIGHT = 30;
    public static final double DEFAULT_DAMAGE_REDUCTION = 0.2;

    //Corresponds to the percentage of damage reduced when the protection is equipped
    public final double DAMAGE_REDUCTION;
    

    public Protection()
    {
        super(DEFAULT_WEIGHT);
        this.DAMAGE_REDUCTION = DEFAULT_DAMAGE_REDUCTION;
    }

    //Returns the damage taken with the protection depending on the damage type
    public int protect(int damage, DamageType dt)
    {
        int res;

        if(dt != null)
        {
            res = switch (dt) {
                case PIERCING -> damage; //piercing damage goes through the protection
                case KEEN ->
                        damage - (int) ((float) damage * (this.DAMAGE_REDUCTION / 2.)); //keen damage get reduced by half of the protection's damage reduction
                default ->
                        damage - (int) ((float) damage * this.DAMAGE_REDUCTION); //any other type of damage doesn't go through the protection
            };
        }
        else
        {
            res = damage - (int)((float)damage * this.DAMAGE_REDUCTION);
        }
        
        return res;
    }
}
