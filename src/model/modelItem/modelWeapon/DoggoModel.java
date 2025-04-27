package model.modelItem.modelWeapon;

public class DoggoModel extends WeaponModel {
    public String name;
    public DoggoModel(String name){
        super(10,0, DamageTypeModel.KEEN);
        this.name = name;
    }
}
