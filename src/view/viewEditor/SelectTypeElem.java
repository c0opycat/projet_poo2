package view.viewEditor;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Custom TabPane to select different categories of elements
 * (Doors, Items, Characters).
 * Each tab displays specific information and GUI components for the selected
 * type.
 *
 * @author C. Besançon
 */
public class SelectTypeElem extends TabPane {
  /// Public///
  /**
   * Constructs a TabPane with a tab per type, each displaying a list of elements
   * and extra info.
   *
   * @param types    List of element categories (ex : "Objets / Items")
   * @param listElem List of lists, each containing the elements for the
   *                 corresponding type
   */
  public SelectTypeElem(String types[], ArrayList<ArrayList<String>> listElem) {
    super();
    int nbTypes = types.length;
    int i;

    for (i = 0; i < nbTypes; i++) {
      String type = types[i];

      Tab tab = new Tab(type);
      VBox infoBox = new VBox();
      infoBox.setAlignment(Pos.CENTER);
      detailsTab(tab, infoBox, type, listElem, i);

      this.getTabs().add(tab);
      this.getStyleClass().add("transparent-layer");
    }
  }

  /// Private///
  /**
   * Fills the content of a tab depending on the element type.
   *
   * @param tab      The tab to configure.
   * @param infoBox  The main container for this tab’s content.
   * @param type     The type of element being handled (e.g., "Monsters").
   * @param listElem The full list of elements grouped by type.
   * @param i        The index of the current type in listElem.
   */
  private void detailsTab(
      Tab tab,
      VBox infoBox,
      String type,
      ArrayList<ArrayList<String>> listElem,
      int i) {
    ArrayList<String> listCurrType = listElem.get(i);
    SelectElem elems = new SelectElem(listCurrType, 8);

    switch (type) {
      case "Monstres / Monsters":
        infoBox.getChildren().add(elems);
        otherInfoMonster(infoBox);
        tab.setContent(infoBox);
        break;
      case "Portes / Doors":
        otherInfoDoor(infoBox, listCurrType);
        tab.setContent(infoBox);
        break;
      case "Objets / Items":
        otherInfoItem(infoBox, listCurrType);
        tab.setContent(infoBox);
        break;
      case "Personnages / Characters":
        otherInfoCharactere(infoBox, listCurrType);
        tab.setContent(infoBox);
        break;
      default:
        tab.setContent(elems);
        break;
    }
  }

  /**
   * Adds a warning about placing only one monster per level.
   * 
   * @deprecated We choose another way to manage monster
   * @param infoBox The container to fill.
   */
  private void otherInfoMonster(VBox infoBox) {
    Label attention = new Label(
        "Attention : vous ne pouvez mettre qu'un seul monstre par niveau");
    Label warning = new Label(
        "Warning : you can put only one monster per level");
    infoBox
        .getChildren()
        .addAll(attention, warning);
  }

  /**
   * Adds UI controls for selecting and configuring doors.
   *
   * @param infoBox      The container to hold UI elements.
   * @param listImgDoors List of image references for available doors.
   */
  private void otherInfoDoor(VBox infoBox, ArrayList<String> listImgDoors) {
    String[] listDoors = getDoors();

    // Creation image drag & drop
    ViewImage image = new ViewImage();
    image.createDraggableImage("noDoor", "noDoor", 100, 100);

    // Creation de la boîte choix
    ComboBox<String> choiceDoor = new ComboBox<>();
    choiceDoor.getItems().addAll(listDoors);
    choiceDoor.setValue("noDoor"); // Valeur par défaut = rien

    // Creation et ajout des labels Vers:
    Label vers = new Label("Vers :");
    Label towards = new Label("Towards :");

    VBox labelBox = new VBox();
    labelBox.getChildren().addAll(vers, towards);

    // Ajout du choix de la direction
    HBox wayBox = new HBox();
    wayBox.getChildren().addAll(labelBox, choiceDoor);
    wayBox.setAlignment(Pos.CENTER);

    // Ajout dans la box principal
    infoBox.getChildren().addAll(image, wayBox);

    // Mis à jour de l'image
    choiceDoor
        .valueProperty()
        .addListener((obs, oldVal, newVal) -> {
          int i = find(listDoors, newVal, listImgDoors);
          if (i > -1) {
            image.createDraggableImage("door" + i, newVal, 100, 100);
          } else {
            image.createDraggableImage("noDoor", "noDoor", 100, 100);
          }
        });
  }

  /**
   * Finds the index of a selected door name in the list of available images.
   *
   * @param listDoors  Array of door names.
   * @param elem       The selected element name.
   * @param listImages List of image names or identifiers.
   * @return The index of the matching image, or -1 if not found.
   */
  private int find(
      String[] listDoors,
      String elem,
      ArrayList<String> listImages) {
    int lenNbDoor = listDoors.length; // len - 1 car dans la liste on a aussi noDoor
    int i = lenNbDoor;
    for (String currDoor : listDoors) {
      if (currDoor == elem) {
        break;
      }
      i--;
    }

    int currI = i % (listImages.size() - 1);
    return currI;
  }

  /**
   * Retrievec the names of all levels
   * 
   * @return an array with the level names
   */
  private String[] getDoors() {
    // recuperer le contenu via le controller
    String[] doors = {
        "noDoor",
        "Place Lepetit",
        "Beaulieu",
        "Notre-Dame",
        "Blossac",
    };
    return doors;
  }

  /**
   * Displays extra information about item parameters.
   *
   * @param infoBox      The container to fill
   * @param listInfoItem The list of item descriptions
   */
  private void otherInfoItem(VBox infoBox, ArrayList<String> listInfoItem) {
    // Sous Tab choix entre WeaponModel / ContainerModel / Consommable / Other ????

    // Je pars du principe que chaque modelItem est enregistré sous la même forme
    // nom de l'image/modelItem ; type d'modelItem ; paramètre1 : valeur /
    // 1parameter : value ; paramètre2 : valeur / 2parameter : value
    // dans l'ordre de la classe la plus haute dont il hérite jusqu'à lui
    Label msgFLabel = new Label("Paramètres de l'objet");
    Label msgELabel = new Label("ItemModel's parameters");
    String presFr = "Cet objet est un/une ";
    String presEn = "This object is a/an ";

    otherInfoData(infoBox, listInfoItem, msgFLabel, msgELabel, presFr, presEn);

  }

  /**
   * Displays extra information about character parameters.
   *
   * @param infoBox      The container to fill
   * @param listInfoItem The list of character descriptions
   */
  private void otherInfoCharactere(VBox infoBox, ArrayList<String> listInfoItem) {
    // Sous Tab choix entre WeaponModel / ContainerModel / Consommable / Other ????

    // Je pars du principe que chaque modelItem est enregistré sous la même forme
    // nom de l'image/modelItem ; type d'modelItem ; paramètre1 : valeur /
    // 1parameter : value ; paramètre2 : valeur / 2parameter : value
    // dans l'ordre de la classe la plus haute dont il hérite jusqu'à lui
    Label msgFLabel = new Label("Information personnage : ");
    Label msgELabel = new Label("Charater's information: ");
    String presFr = "Ce personnage est un/une ";
    String presEn = "This character is a/an ";

    otherInfoData(infoBox, listInfoItem, msgFLabel, msgELabel, presFr, presEn);

  }

  /**
   * Shared function to display bilingual info for items or characters.
   *
   * @param infoBox      The container to fill
   * @param listInfoItem Items or characters info
   * @param msgFLabel    French label
   * @param msgELabel    English label
   * @param presFr       French presentation
   * @param presEn       English presentation
   */
  private void otherInfoData(VBox infoBox,
      ArrayList<String> listInfoItem,
      Label msgFLabel,
      Label msgELabel,
      String presFr, String presEn) {

    ArrayList<String> imgItems = new ArrayList<>();
    ArrayList<String> msgFList = new ArrayList<>();
    ArrayList<String> msgEList = new ArrayList<>();
    String msgF;
    String msgE;

    for (String currItem : listInfoItem) {
      String[] parts = currItem.split(";");

      String[] lang = parts[0].split("/");
      if (lang.length >= 2) {
        msgF = presFr + lang[0] + ".\n";
        msgE = presEn + lang[1] + ".\n";
      } else {
        msgF = "Sans nom \n";
        msgE = "No name \n";
      }

      imgItems.add(lang[0]);

      int len = parts.length;
      if (len > 1) {
        for (int i = 1; i < len; i++) {
          String[] langVal = parts[i].split(":");
          lang = langVal[0].split("/");
          String[] value = langVal[1].split("/");
          msgF += lang[0] + ":";
          if (lang.length == 1) {
            msgE += lang[0] + ":";
          } else {
            msgE += lang[1] + ":";
          }

          msgF += value[0] + "\n";
          if (value.length == 1) {
            msgE += value[0] + "\n";
          } else {
            msgE += value[1] + "\n";
          }
        }
      } else {
        msgF += "Pas d'autres informations";
        msgE += "No other data";
      }
      msgFList.add(msgF);
      msgEList.add(msgE);
    }

    SelectElemItem listItem = new SelectElemItem(imgItems, 8, msgFList, msgEList, msgFLabel, msgELabel);
    infoBox.getChildren().addAll(listItem, msgFLabel, msgELabel);
  }

}