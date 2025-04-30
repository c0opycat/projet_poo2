package model.modelGame;

import model.modelCharacter.CharacterModel;
import model.modelCharacter.modelHeros.HeroModel;
import model.modelGame.modelCommand.CommandModel;
import model.modelItem.CrowbarModel;
import model.modelItem.ItemModel;
import model.modelItem.modelConsumable.ConsumableModel;
import model.modelLocation.LocationModel;

import java.util.Random;

public class MessageFrModel {
    //Returns the start of modelGame message
    public static String startGame(LocationModel start, LocationModel end)
    {
        return  "Vous vous réveillez dans une terre dévastée. Une mâtinée banale.\n" +
                "Cette nouvelle journée ne sera pas facile, vous devez fuir la ville infestée.\n" +
                "Vous êtes actuellement à " + start +
                " et vous devez rejoindre " + end + ".";
    }

    //Returns the loss message
    public static String gameLost()
    {
        return "Vous êtes malheureusement mort en tentant de vous échapper.\n"+
        "Votre corps accompagnera la ville dans sa chute.\n";
    }

    //Returns the victory message
    public static String gameWon()
    {
        return "Vous avez réussi! Vous vous êtes échappé\n"+
        "Vous pouvez maintenant contempler les ruines de la ville dans le lointain.\n"+
        "Vous pouvez enfin vous reposer alors que la horde se répend dans les bâtiments\n";
    }

    public static String warning(){
        return "warning! ";
    }

    //Returns a message to tell that the number isn't associated with any modelItem
    public static String InvalidNumber(int i)
    {
        return "Le numero " + i + " n’est associé à aucun objet.";
    }

    //Returns the modelContainer full message
    public static String contFull(ItemModel c){
        return c +" ne peut pas contenir autant d’objets.";
    }

    //Returns a message to tell that you can't use/take an modelItem
    public static String wrongItem(String verb)
    {
        return "Vous ne pouvez pas " + verb + " ceci ";
    }

    //Returns the no space in the modelContainer message
    public static String noSpace(ItemModel c, ItemModel i){
        return warning()+i.toString()+" est trop lourd et ne peut pas être stocké dans "+c.toString()+".";
    }
    
    public static String handFull(ItemModel i){
        return "Vous devez d’abord déposer votre "+i.toString();
    }

    //Return the Location description
    public static String getDescription(LocationModel location){
        String name = location.getName();
        return getDAux(name);
    }

    //Secondary function of the getDescription function
    private static String getDAux(String name){
        return switch (name) {
            case "BEAULIEU" -> "Sur les ruines du centre commercial repose l'épave calcinée d'un avion.\n" +
                            "Il reste peut-être de la nourriture quelque part.\n" +
                            "Vous apercevez quelques mouvements au loin.\n" +
                            "Quoi que vous comptiez faire, mieux vaut faire vite.";
            case "MILETRIE" -> "On dirait que c'est plutôt animé par ici.\n" +
                            "L'hôpital se tient encore sinistrement au coeur des ruines.\n" +
                            "L'explorer serait risqué mais pourrait rapporter gros.\n" +
                            "Des rumeurs terrifiantes circulent sur les abominations y rôderaint.\n";
            case "CITY_CENTER" -> "Vous vous souvenez que des survivants avaient utilisé Notre-Dame comme base pendant un temps.\n" +
                            "Il semble qu'ils l’aient abandonnée, mais peut-être ont-ils laissé des objets utiles.\n" +
                            "Une odeur de chair brûlée vous monte au nez. Qu'a-t-il bien pu se passer ?\n";
            case "COURONNERIES" -> "La majeur partie des bâtiments sont en mauvais état.\n"+
                            "Quelques tours tiennent bon et abritent peut-être des survivants.\n"+
                            "Vous pouvez entendre le son de l'eau qui coule plus loin.\n";
            case "GIBAUDERIE" -> "Il y a de nombreux commerces et bâtiments de la clinique.\n"+
                            "Tout est calme et vous pouvez entendre vos propre pas.\n"+
                            "Il y a probablement des choses intéressantes cachées dans le coin.\n";
            case "WEST_POITIERS" -> "Tout est désormais recouvert d’une forêt dense.\n"+
                            "La vie... trouve toujours un chemin.\n"+
                            "\n";
            case "SOUTH_POITIERS" -> "On dirait que tout a été dévasté par des explosifs.\n"+
                            "La forte concentration de cendres et de produits chimiques vous brûle les poumons.\n"+
                            "Il vaudrait mieux quitter cet endroit.\n";
            case "PONT_NEUF" -> "L'odeur est attroce. Depuis les hauteurs, un glissement de boue a entraîné une marée de cadavres en décomposition jusqu’à la rivière.\n"+
                            "Des engins de chantier sont éparpillés un peu partout.\n"+
                            "Vous souriez en pensant que personne ne verra jamais l'aboutissement de ces incessants travaux.\n";
            case "SAINT_ELOI" -> "Tout est si calme ici, c'est probablement le quartier le moins endommagé de la ville.\n"+
                            "L’aspect soigné des bâtiments récents vous donne une étrange sensation d’isolement.\n"+
                            "\n";
            case "TROIS_CITES" -> "Le petit centre commercial a été fortifié et vous savez qu’une communauté y vit.\n"+
                            "Ils sont réputés pour aider les voyageurs de passage, mais vous ne faites plus confiance à personne.\n"+
                            "\n";
            case "NORTH_POITIERS" -> "Depuis les hauteurs de la vallée du Clain, vous pouvez contempler le panorama.\n"+
                            "La statue silencieuse vous fait face depuis l’autre rive.\n"+
                            "Son côté gauche est noirci par les flammes, son bras droit est manquant.\n";
            case "MONTBERNAGE" -> "Le grand pont de la voie pénétrante enjambe toujours le Clain.\n"+
                            "Sa haute silhouette est un vestige du monde que vous avez connu.\n"+
                            "\n";
            case "FINAL_EXIT" -> "La longue route semble déserte.\n"+
                            "Vous êtes enfin sorti de la ville.\n" + "\n";
            default -> "";
        };
    }
    //returns the description of the modelLocation in parameter by calling the switch case on top
    public static String locationDescription(LocationModel location){
        String intro = "Vous êtes à ";
        String lName = location.getName();
        String desc = getDescription(location);
        return intro+lName+":\n"+desc+"\n";
    }

    //Message used when failing to open a modelContainer
    public static String failOpening(){
        return "Vous n'arrivez pas à ouvrir ";
    }

    //Message used when you try to open a modelContainer (crate) that requires a tool(crowbar) you don't have
    public static String toolRequired(){
        return "La caisse est fermée, peut-être que vous pouvez l'ouvrir avec un outil";
    }

    //Message displayed to ask for validation to use an modelItem
    public static String useItem(ItemModel item){
        return "Voulez vous utiliser ceci : "+item+"?\n Y or N\n";
    }

    public static String itemUsed (ItemModel item){
        return "Vous avez utilisé votre "+item+"pour "+ MessageFrModel.itemEffect(item);
    }
    public static String itemEffect (ItemModel item){
        if (item instanceof CrowbarModel)
        {
            return "ouvrir ";
        }
        else if (item instanceof ConsumableModel)
        {
            return "récupérer ";
        }
        else return "faire un truc incompréhensible";
    }
    public static String herosHP(CharacterModel c)
    {
        return "Vous avez maintenant " + c.getHealth() + " PV.";
    }

    public static String monsterHP(CharacterModel c)
    {
        return "Le " + c +  "a maintenant " + c.getHealth() + " PV.";
    }

    public static String monsterAttack(CharacterModel attacker)
    {
        return "Un " + attacker + " vous attaque!\n";
    }

    public static String NoWeapon()
    {
        return "Vous pensez vraiment pouvoir blesser ce monstre à mains nues ?";
    }

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
        return wrongItem("utiliser ")+item1+"sur ceci!";
    }

    public static String cantDo(CommandModel command){
        return "Vous ne pouvez pas "+command+"maintenant.";
    }

    public static String commandOnItem(String verb, ItemModel item){
        return "Vous avez "+verb+"cet objet : "+item;
    }

    public static String heroDescription(HeroModel heroM){
        String wp = "Votre personnage :\n" + herosHP(heroM) + "\n";
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
                name = "Théière"; break;
            }
        }
        return name;
    }

}

