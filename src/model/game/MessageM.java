package model.game;

import model.character.heros.HeroM;
import model.game.commandM.Command;
import model.item.Crowbar;
import model.item.Item;
import model.character.CharacterM;
import model.item.consumable.Consumable;
import model.location.LocationM;
import model.location.LocationName;

import java.util.Random;

public class MessageM {
    //Returns the start of game message
    public static String startGame(LocationM start, LocationM end)
    {
        return
        "You're waking up in a devastated land. Another monday.\n"+
        "Today is not gonna be easy as you need to escape the city that have been infested.\n"+
        "You're actually at " + start +
        "and you need to go to " + end;
    }

    //Returns the loss message
    public static String gameLost()
    {
        return "Sadly you died trying to escape.\n"+
        "Your body will follow the city in its downfall.\n";
    }

    //Returns the victory message
    public static String gameWon()
    {
        return "You did it! You escaped!\n"+
        "Now you can contemplate the ruins of the city from far away.\n"+
        "Finally you can rest while the horde invades the buildings.\n";
    }

    public static String warning(){
        return "warning! ";
    }

    //Returns a message to tell that the number isn't associated with any item
    public static String InvalidNumber(int i)
    {
        return "The number " + i + " isn't associated with any item.";
    }

    //Returns the container full message
    public static String contFull(Item c){
        return c +" can't contain that many items.";
    }

    //Returns a message to tell that you can't use/take an item
    public static String wrongItem(String verb)
    {
        return "You can't " + verb + " this ";
    }

    //Returns the no space in the container message
    public static String noSpace(Item c, Item i){
        return warning()+i.toString()+"is too heavy and can't be stored in "+c.toString()+".";
    }
    
    public static String handFull(Item i){
        return "You need to drop your "+i.toString()+" first.";
    }

    //Return the Location description
    public static String getDescription(LocationM location){
        LocationName name = location.getName();
        return getDAux(name);
    }

    //Secondary function of the getDescription function
    private static String getDAux(LocationName name){
        return switch (name) {
            case  BEAULIEU -> "On the ruins of the mall stands the burned remains of an aircraft.\n"+
                            "Maybe there's food left somewhere around.\n"+
                            "You can spot a few movements in the distance.\n"+
                            "Whatever you plan to do, better be quick.";
            case MILETRIE -> "There seems to be a lot of activity there.\n"+
                            "The hospital is still menacingly standing.\n"+
                            "Exploring it would be a high stake but dangerous choice.\n"+
                            "Finding meds would help but you heard rumors about the abominations roaming there.\n";
            case CITY_CENTER -> "You remember that survivors used Notre Dame as a base for some time.\n"+
                            "It seems that they've abandoned it already but maybe they left useful things behind.\n"+
                            "A smell of burned flesh comes to your nose. What happened here?\n";
            case COURONNERIES -> "The main part of the buildings are in bad shape.\n"+
                            "A few towers are still standing, maybe there's still people there.\n"+
                            "You can hear distant sounds of water flowing.\n";
            case GIBAUDERIE -> "There is a lot of abandoned business and care building.\n"+
                            "Everything is quiet and you can ear your own steps on the dirty road.\n"+
                            "There might be some useful stuff hidden there.\n";
            case WEST_POITIERS -> "Everything is now covered by a dense forrest.\n"+
                            "Life... eh... Finds a way.\n"+
                            "\n";
            case SOUTH_POITIERS -> "It looks like it's been devastated by explosives.\n"+
                            "The strong density of ashes and chemicals are hurting your lungs.\n"+
                            "Better get out of here.\n";
            case PONT_NEUF -> "It stinks. Coming from the highs a mud slide dragged a tide of rotting corpse down to the river.\n"+
                            "Construction machines are scattered all around.\n"+
                            "With a smile you think that nobody will ever witness the work done.\n";
            case SAINT_ELOI -> "This is so quiet and probably the area that suffered the least damages.\n"+
                            "The neat aspect of the recent buildings makes you feel eerily isolated.\n"+
                            "\n";
            case TROIS_CITES -> "The small mall has been fortified and you know there is a community leaving there.\n"+
                            "They are known to be helpful to wandering strangers but you don't trust people.\n"+
                            "\n";
            case NORTH_POITIERS -> "On the heights of the Clain valley, you can see the panorama.\n"+
                            "The quiet Statue faces you on the other side.\n"+
                            "Her left side has been darkened by fire and her right arm is missing.\n";
            case MONTBERNAGE -> "The tall bridge supporting the urban motorways still spans the Clain.\n"+
                            "Its tall silhouette is a remain of the world you used to know.\n"+
                            "\n";
            case FINAL_EXIT -> "The long road seems desert.\n"+
                            "You're finally out.\n" + "\n";
        };
    }
    //returns the description of the location in parameter by calling the switch case on top
    public static String locationDescription(LocationM location){
        String intro = "You're at ";
        LocationName lName = location.getName();
        String desc = getDescription(location);
        return intro+lName+":\n"+desc+"\n";
    }

    //Message used when failing to open a container
    public static String failOpening(){
        return "You failed to open ";
    }

    //Message used when you try to open a container (crate) that requires a tool(crowbar) you don't have
    public static String toolRequired(){
        return "The crate is closed. Maybe you need a tool to open that.";
    }

    //Message displayed to ask for validation to use an item
    public static String useItem(Item item){
        return "Do you want to use this "+item+"?\n Y or N\n";
    }

    public static String itemUsed (Item item){
        return "You used your "+item+"to "+ MessageM.itemEffect(item);
    }
    public static String itemEffect (Item item){
        if (item instanceof Crowbar)
        {
            return "to open ";
        }
        else if (item instanceof Consumable)
        {
            return "to restore ";
        }
        else return "for an unknown reason";
    }
    public static String herosHP(CharacterM c)
    {
        return "You now have " + c.getHealth() + " HP left.";
    }

    public static String monsterHP(CharacterM c)
    {
        return "The " + c +  "now have " + c.getHealth() + " HP left.";
    }

    public static String monsterAttack(CharacterM attacker)
    {
        return "A " + attacker + " has attacked you !\n";
    }

    public static String NoWeapon()
    {
        return "Do you really think you can harm this monster with you bare hands ?";
    }

    public static String InvalidItem(){
        return "There is no item like this here.";
    }
    
    public static String invalidCommand()
    {
        return "The command is incorrect. Please write HELP to see the commands.";
    }

    public static String monsterApparition(CharacterM c)
    {
        return "A " + c + " has appeared !";
    }

    public static String displayExitsInLoc(){
        return "Exits you can see :";
    }

    public static String displayItemsInLoc(){
        return "Items you can see :";
    }

    public static String cantUseItem(Item item1){
        return wrongItem("use ")+item1+"on this!";
    }

    public static String cantDo(Command command){
        return "You can't "+command+"right now.";
    }

    public static String commandOnItem(String verb,Item item){
        return "You "+verb+"this "+item;
    }

    public static String heroDescription(HeroM heroM){
        String wp = "Your hero :\n" + herosHP(heroM) + "\n";
        if(heroM.getWeapon() != null)
        {
            wp += "Your equipped weapon is a " + heroM.getWeapon()+ "\n";
        }
        if(heroM.getShield() != null)
        {
            wp += "Your protection is " + heroM.getShield() + "\n";
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
                name = "Diamond"; break;
            }
            case 1: {
                name = "Brutus"; break;
            }
            case 2: {
                name = "Nyamba"; break;
            }
            default: {
                name = "Teapot"; break;
            }
        }
        return name;
    }

}

