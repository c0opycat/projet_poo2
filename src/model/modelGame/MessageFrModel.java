package model.modelGame;

import model.modelCharacter.CharacterModel;
import model.modelCharacter.modelHeros.HeroModel;
import model.modelGame.modelCommand.CommandModel;
import model.modelItem.ItemModel;

import java.util.Random;

public class MessageFrModel {

    public static String InvalidItem(){
        return "Il n'y a aucun objet de ce type ici.";
    }
    
    public static String invalidCommand()
    {
        return "Commande incorrecte. Tapez HELP pour voir les commandes disponibles.";
    }

    public static String monsterApparition(CharacterModel c)
    {
        return "Un " + c + " est apparu !";
    }

    public static String displayExitsInLoc(){
        return "Sorties visibles :";
    }

    public static String displayItemsInLoc(){
        return "Objets visibles :";
    }

    public static String cantUseItem(ItemModel item1){
        return MessageEnModel.wrongItem("utiliser ")+item1+"sur ceci!";
    }

    public static String cantDo(CommandModel command){
        return "Vous ne pouvez pas "+command+"maintenant.";
    }

    public static String commandOnItem(String verb, ItemModel item){
        return "Vous avez "+verb+"cet objet : "+item;
    }

    public static String heroDescription(HeroModel heroM){
        String wp = "Votre personnage :\n" + MessageEnModel.herosHP(heroM) + "\n";
        if(heroM.getWeapon() != null)
        {
            wp += "Votre arme est celle-ci : " + heroM.getWeapon()+ "\n";
        }
        if(heroM.getShield() != null)
        {
            wp += "Votre protection est celle-ci : " + heroM.getShield() + "\n";
        }
        return wp;
    }
    public static String randName(){
        String name = "doggo";
        Random rand = new Random();
        int i = rand.nextInt(3);
        switch(i)
        {
            case 0: {
                name = "Diamant"; break;
            }
            case 1: {
                name = "Brutus"; break;
            }
            case 2: {
                name = "Nyamba"; break;
            }
            default: {
                name = "Theiere"; break;
            }
        }
        return name;
    }

}

