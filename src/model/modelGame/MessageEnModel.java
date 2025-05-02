package model.modelGame;

import java.util.Objects;
import java.util.Random;
import model.modelCharacter.CharacterModel;
import model.modelCharacter.modelHeros.HeroModel;
import model.modelGame.modelCommand.CommandModel;
import model.modelItem.CrowbarModel;
import model.modelItem.ItemModel;
import model.modelItem.modelConsumable.ConsumableModel;
import model.modelLocation.LocationModel;

public class MessageEnModel{

  //Returns the start of modelGame message
  public static String startGame(LocationModel start, LocationModel end) {
    String lang = GameModel.loadLanguage();
    if (Objects.equals(lang, "EN")) {
      return (
        "You're waking up in a devastated land. Another monday.\n" +
        "Today is not gonna be easy as you need to escape the city that have been infested.\n" +
        "You're actually at " +
        start +
        "and you need to go to " +
        end
      );
    } else {
      return (
        "Vous vous reveillez dans une terre devastee. Un autre lundi.\n" +
        "Aujourd'hui ne va pas etre facile car vous devez echapper a la ville qui a ete infestee.\n" +
        "Vous etes en fait a " +
        start +
        "et vous devez aller a " +
        end
      );
    }
  }

  //Returns the loss message
  public static String gameLost() {
    String lang = GameModel.loadLanguage();
    if (Objects.equals(lang, "EN")) {
    return """
            Sadly you died trying to escape.
            Your body will follow the city in its downfall.
            """;
    } else {
      return """
              Vous etes malheureusement mort en tentant de vous echapper.
              Votre corps accompagnera la ville dans sa chute.
              """;
    }
  }

  //Returns the victory message
  public static String gameWon() {
    String lang = GameModel.loadLanguage();
    if (Objects.equals(lang, "EN")) {
      return """
              You did it! You escaped!
              Now you can contemplate the ruins of the city from far away.
              Finally you can rest while the horde invades the buildings.
              """;
    }
    else {
      return """
              Vous avez reussi! Vous vous etes echappe
              Vous pouvez maintenant contempler les ruines de la ville dans le lointain.
              Vous pouvez enfin vous reposer alors que la horde se repend dans les batiments
              """;
    }
  }

  public static String helpCommands() {
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")) {
      return """
              Help :
                 ATTACK: to attack the monster
                 BACKPACK: to see what is in your backpack
                 click on a chest/crate/backpack: to see what is in the container
                 DROP: to drop an item from your inventory
                 GO: to go to an other place, you can use it to run away from big and slow dangers
                 USE:t o use an item from your backpack
                 TAKE: to take an item
                 EQUIP: to equip an item""";
    }
    else {
      return """
              Aide(HELP) :
                 ATTACK: permet d'attaquer l'enemi
                 BACKPACK: permet de voir le contenu de votre sac a dos
                 cliquez sur un(e) chest(coffre)/crate(caisse)/backpack(sac a dos) pour regarder ce qu'il contient
                 DROP: pour deposer un objet de votre inventaire
                 GO: pour aller a un autre endroit, vous pouvez l'utiliser pour fuir un danger imposant et lent
                 USE:pour utiliser un item(objet) de votre sac a dos
                 TAKE: pour prendre un item(objet)
                 EQUIP: pour equiper un item(objet)""";
    }
  }

  public static String warning() {
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")){
    return "warning! ";
    } else {
      return "Attention!";
    }
  }

  public static String cantExit() {
    return "You have to kill all the monsters in the room before you can go to another room";
  }

  //Returns a message to tell that the number isn't associated with any modelItem
  public static String InvalidNumber(int i) {
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")) {
    return "The number " + i + " isn't associated with any modelItem.";
    } else {
      return "Le numero " + i + " n’est associe a aucun objet.";
    }
  }

  //Returns the modelContainer full message
  public static String contFull(ItemModel c) {
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")) {
    return c + " can't contain that many items.";
    } else {
      return c +" ne peut pas contenir autant d’objets.";
    }
  }

  public static String locFull() {
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")) {
      return "The current level is full.";
    } else {
      return "Le niveau est plein!";
    }
  }

  //Returns a message to tell that you can't use/take an modelItem
  public static String wrongItem(String verb) {
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")) {
      return "You can't " + verb + " this ";
    } else {
      return "Vous ne pouvez pas " + verb + " ceci ";
    }
  }

  //Returns the no space in the modelContainer message
  public static String noSpace(ItemModel c, ItemModel i) {
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")) {
      return warning() +
              i.toString() +
              "is too heavy and can't be stored in " +
              c.toString() +
              ".";
    } else {
      return warning() +
              i.toString()+
              " est trop lourd et ne peut pas etre stocke dans "+
              c.toString()+
              ".";
    }
  }

  public static String notAnExit() {
    return "You can't get out other than through a door";
  }

  public static String handFull(ItemModel i) {
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")) {
      return "You need to drop your " + i.toString() + " first.";
    } else {
      return "Vous devez d’abord deposer votre "+i.toString();
    }
  }

  //Return the Location description
  public static String getDescription(LocationModel location) {
    String name = location.getName();
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")) {
      return getDAux(name);
    } else {
      return getDAuxFr(name);
    }
  }

  //Secondary function of the getDescription function
  private static String getDAux(String name) {
    return switch (name) {
      case "BEAULIEU" -> "On the ruins of the mall stands the burned remains of an aircraft.\n" +
      "Maybe there's modelFood left somewhere around.\n" +
      "You can spot a few movements in the distance.\n" +
      "Whatever you plan to do, better be quick.";
      case "MILETRIE" -> "There seems to be a lot of activity there.\n" +
      "The hospital is still menacingly standing.\n" +
      "Exploring it would be a high stake but dangerous choice.\n" +
      "Finding meds would help but you heard rumors about the abominations roaming there.\n";
      case "CITY_CENTER" -> "You remember that survivors used Notre Dame as a base for some time.\n" +
      "It seems that they've abandoned it already but maybe they left useful things behind.\n" +
      "A smell of burned flesh comes to your nose. What happened here?\n";
      case "COURONNERIES" -> "The main part of the buildings are in bad shape.\n" +
      "A few towers are still standing, maybe there's still people there.\n" +
      "You can hear distant sounds of water flowing.\n";
      case "GIBAUDERIE" -> "There is a lot of abandoned business and care building.\n" +
      "Everything is quiet and you can ear your own steps on the dirty road.\n" +
      "There might be some useful stuff hidden there.\n";
      case "WEST_POITIERS" -> "Everything is now covered by a dense forrest.\n" +
      "Life... eh... Finds a way.\n" +
      "\n";
      case "SOUTH_POITIERS" -> "It looks like it's been devastated by explosives.\n" +
      "The strong density of ashes and chemicals are hurting your lungs.\n" +
      "Better get out of here.\n";
      case "PONT_NEUF" -> "It stinks. Coming from the highs a mud slide dragged a tide of rotting corpse down to the river.\n" +
      "Construction machines are scattered all around.\n" +
      "With a smile you think that nobody will ever witness the work done.\n";
      case "SAINT_ELOI" -> "This is so quiet and probably the area that suffered the least damages.\n" +
      "The neat aspect of the recent buildings makes you feel eerily isolated.\n" +
      "\n";
      case "TROIS_CITES" -> "The small mall has been fortified and you know there is a community leaving there.\n" +
      "They are known to be helpful to wandering strangers but you don't trust people.\n" +
      "\n";
      case "NORTH_POITIERS" -> "On the heights of the Clain valley, you can see the panorama.\n" +
      "The quiet Statue faces you on the other side.\n" +
      "Her left side has been darkened by fire and her right arm is missing.\n";
      case "MONTBERNAGE" -> "The tall bridge supporting the urban motorways still spans the Clain.\n" +
      "Its tall silhouette is a remain of the world you used to know.\n" +
      "\n";
      case "FINAL_EXIT" -> "The long road seems desert.\n" +
      "You're finally out.\n" +
      "\n";
      default -> "";
    };
  }

  private static String getDAuxFr(String name){
    return switch (name) {
      case "BEAULIEU" -> "Sur les ruines du centre commercial repose l'epave calcinee d'un avion.\n" +
              "Il reste peut-etre de la nourriture quelque part.\n" +
              "Vous apercevez quelques mouvements au loin.\n" +
              "Quoi que vous comptiez faire, mieux vaut faire vite.";
      case "MILETRIE" -> "On dirait que c'est plutot anime par ici.\n" +
              "L'hopital se tient encore sinistrement au coeur des ruines.\n" +
              "L'explorer serait risque mais pourrait rapporter gros.\n" +
              "Des rumeurs terrifiantes circulent sur les abominations y roderaint.\n";
      case "CITY_CENTER" -> "Vous vous souvenez que des survivants avaient utilise Notre-Dame comme base pendant un temps.\n" +
              "Il semble qu'ils l’aient abandonnee, mais peut-etre ont-ils laisse des objets utiles.\n" +
              "Une odeur de chair brulee vous monte au nez. Qu'a-t-il bien pu se passer ?\n";
      case "COURONNERIES" -> "La majeur partie des batiments sont en mauvais etat.\n"+
              "Quelques tours tiennent bon et abritent peut-etre des survivants.\n"+
              "Vous pouvez entendre le son de l'eau qui coule plus loin.\n";
      case "GIBAUDERIE" -> "Il y a de nombreux commerces et des batiments de la clinique.\n"+
              "Tout est calme et vous pouvez entendre vos propre pas sur la route poussiereuse.\n"+
              "Il y a probablement des choses interessantes cachees dans le coin.\n";
      case "WEST_POITIERS" -> "Tout est desormais recouvert d’une foret dense.\n"+
              "La vie... trouve toujours un chemin.\n"+
              "\n";
      case "SOUTH_POITIERS" -> "On dirait que tout a ete disperse par une grosse explosion.\n"+
              "La forte concentration de cendres et de produits chimiques vous brule les poumons.\n"+
              "Il vaudrait mieux quitter cet endroit.\n";
      case "PONT_NEUF" -> "L'odeur est attroce. Depuis les hauteurs, un glissement de boue a entraine une maree de cadavres en decomposition jusqu’a la riviere.\n"+
              "Des engins de chantier sont eparpilles un peu partout.\n"+
              "Vous souriez en pensant que personne ne verra jamais l'aboutissement de ces incessants travaux.\n";
      case "SAINT_ELOI" -> "Tout est si calme ici, c'est probablement le quartier le moins endommage de la ville.\n"+
              "L’aspect soigne des batiments recents vous donne une etrange sensation d’isolement.\n"+
              "\n";
      case "TROIS_CITES" -> "Le petit centre commercial a ete fortifie et vous savez qu’une communaute y vit.\n"+
              "Ils sont reputes pour aider les voyageurs de passage, mais vous ne faites plus confiance a personne.\n"+
              "\n";
      case "NORTH_POITIERS" -> "Depuis les hauteurs de la vallee du Clain, vous pouvez contempler le panorama.\n"+
              "La statue silencieuse vous fait face depuis l’autre rive.\n"+
              "Son cote gauche est noirci par les flammes, son bras droit est manquant.\n";
      case "MONTBERNAGE" -> "Le grand pont de la voie penetrante enjambe toujours le Clain.\n"+
              "Sa haute silhouette est un vestige du monde que vous avez connu.\n"+
              "\n";
      case "FINAL_EXIT" -> "La longue route semble deserte.\n"+
              "Vous etes enfin sorti de la ville.\n" + "\n";
      default -> "";
    };
  }

  //returns the description of the modelLocation in parameter by calling the switch case on top
  public static String locationDescription(LocationModel location) {
    String lang = GameModel.loadLanguage();
    String lName = location.getName();
    String desc = getDescription(location);
    if (lang.equals("EN")) {
      return "You're at "+ lName + ":\n" + desc + "\n";
    } else {
      return "Vous etes a"+ lName + ":\n" + desc + "\n";
    }
  }

  //Message used when failing to open a modelContainer
  public static String failOpening() {
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")) {
      return "You failed to open ";
    } else {
      return "Vous n'arrivez pas a ouvrir ";
    }
  }

  //Message used when you try to open a modelContainer (crate) that requires a tool(crowbar) you don't have
  public static String toolRequired() {
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")) {
      return "The crate remains closed. Maybe you'd have more success with a tool.";
    } else {
      return "La caisse reste fermee. Vous pourriez peut-etre l'ouvrir avec un outil.";
    }
  }

  //Message displayed to ask for validation to use an modelItem
  public static String useItem(ItemModel item) {
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")) {
      return "Do you want to use this " + item + "?\n Y or N\n";
    } else {
      return "Voulez vous utiliser ceci: " + item + "?\n Y(oui) ou N(non)\n";
    }
  }

  public static String itemUsed(ItemModel item) {
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")) {
      return "You used your " + item + "to " + MessageEnModel.itemEffect(item);
    } else {
      return "Vous avez utilise votre "+item+"pour "+ MessageEnModel.itemEffectfr(item);
    }
  }

  public static String itemEffect(ItemModel item) {
    if (item instanceof CrowbarModel) {
      return "to open ";
    } else if (item instanceof ConsumableModel) {
      return "to restore ";
    } else return "for an unknown reason";
  }

  public static String itemEffectfr (ItemModel item){
    if (item instanceof CrowbarModel) {
      return "ouvrir ceci : ";
    } else if (item instanceof ConsumableModel) {
      return "recuperer ceci : ";
    } else return "faire.. quelque chose avec ceci :";
  }

  public static String herosHP(CharacterModel c) {
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")) {
      return "You now have " + c.getHealth() + " HP left.";
    } else {
      return "Vous avez maintenant " + c.getHealth() + " PV.";
    }
  }

  public static String monsterHP(CharacterModel c) {
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")) {
      return "The " + c + "now have " + c.getHealth() + " HP left.";
    } else {
      return "Le " + c +  "a maintenant " + c.getHealth() + " PV.";
    }
  }

  public static String monsterAttack(CharacterModel attacker) {
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")) {
      return "A " + attacker + " has attacked you !\n";
    } else {
      return "Un " + attacker + " vous attaque!\n";
    }
  }

  public static String NoWeapon() {
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")) {
      return "Do you really think you can harm this monster with you bare hands ?";
    } else {
      return "Vous pensez vraiment pouvoir blesser ce monstre a mains nues ?";
    }
  }

  public static String InvalidItem() {
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")) {
    return "There is no modelItem like this here.";}
    else return MessageFrModel.InvalidItem();
  }

  public static String invalidCommand() {
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")) {
      return "The command is incorrect. Please write HELP to see the commands.";
    } else {
      return MessageFrModel.invalidCommand();
    }
  }

  public static String monsterApparition(CharacterModel c) {
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")) {
      return "A " + c + " has appeared !";
    } else {
      return MessageFrModel.monsterApparition(c);
    }
  }

  public static String displayExitsInLoc() {
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")) {
      return "Exits you can see :";
    }
    else {
      return MessageFrModel.displayExitsInLoc();
    }
  }

  public static String displayItemsInLoc() {
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")) {
      return "Items you can see :";
    }
    else return MessageFrModel.displayItemsInLoc();
  }

  public static String cantUseItem(ItemModel item1) {
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")) {
      return wrongItem("use ") + item1 + "on this!";
    } else {
      return MessageFrModel.cantUseItem(item1);
    }
  }

  public static String cantDo(CommandModel command) {
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")) {
      return "You can't " + command + "right now.";
    } else {
      return MessageFrModel.cantDo(command);
    }
  }

  public static String commandOnItem(String verb, ItemModel item) {
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")) {
      return "You " + verb + "this " + item;
    } else {
      return MessageFrModel.commandOnItem(verb, item);
    }
  }

  public static String heroDescription(HeroModel heroM) {
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")) {
      String wp = "Your hero :\n" + herosHP(heroM) + "\n";
      if (heroM.getWeapon() != null) {
        wp += "Your equipped modelWeapon is a " + heroM.getWeapon() + "\n";
      }
      if (heroM.getShield() != null) {
        wp += "Your protection is " + heroM.getShield() + "\n";
      }
      return wp;
    }
    else {
      return MessageFrModel.heroDescription(heroM);
    }
  }

  public static String randName() {
    String lang = GameModel.loadLanguage();
    if (lang.equals("EN")) {
      String name;
      Random rand = new Random();
      int i = rand.nextInt(3);
      switch (i) {
        case 0: {
          name = "Diamond";
          break;
        }
        case 1: {
          name = "Brutus";
          break;
        }
        case 2: {
          name = "Nyamba";
          break;
        }
        default: {
          name = "Teapot";
          break;
        }
      }
      return name;
    }
    else {
      return MessageFrModel.randName();
    }
  }
}
