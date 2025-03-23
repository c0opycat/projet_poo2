package model.character.heros;

public enum JobM {
    /**Start with less HP, because wounded, a shitty backpack but more money (which is useless)*/
    STARTUP,

    /**Starts with meds and the ability so restore small amount of health after a fight*/
    MEDIC,

    /**Can use special shortcuts in the city*/
    DELIVERY,

    /**Has an animal which can attack too*/
    VETERINARY,

    /**Starts with a weapon*/
    SECURITY,

    /**Can run away from any monster*/
    TEACHER
}
