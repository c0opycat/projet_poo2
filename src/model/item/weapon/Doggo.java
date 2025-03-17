package model.item.weapon;

public class Doggo extends Weapon{
    public String name;
    public Doggo(String name){
        super(10,0,DamageType.KEEN);
        this.name = name;
    }
}
