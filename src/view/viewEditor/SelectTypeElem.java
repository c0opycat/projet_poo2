package view.viewEditor;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

//OTHERINFODOOR A BESOIN DE RECUPERER LA LISTE DES LIEUX
//ENREGISTRÉ DANS LE MODEL VIA LE CONTROLLER
//A GERER LORS DE L'ENREGISTREMENT DE LA LISTE CORRESPONDANTE
//DANS listElem
public class SelectTypeElem extends TabPane {

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

  private void detailsTab(
    Tab tab,
    VBox infoBox,
    String type,
    ArrayList<ArrayList<String>> listElem,
    int i
  ) {
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

  //Attention seulement les labels ils faut faire une fonction pour empêcher de mettre plus d'un monstre
  //Sinon gestion initial des monstres via random et ne pas permettre à l'utilisateur de mettre des monstres
  private void otherInfoMonster(VBox infoBox) {
    Label attention = new Label(
      "Attention : vous ne pouvez mettre qu'un seul monstre par niveau"
    );
    Label warning = new Label(
      "Warning : you can put only one monster per level"
    );
    infoBox
      .getChildren()
      .addAll(attention, warning);
  }

  private void otherInfoDoor(VBox infoBox, ArrayList<String> listImgDoors) {
    String[] listDoors = getDoors();

    //Creation image drag & drop
    ViewImage image = new ViewImage();
    image.createDraggableImage("noDoor", "noDoor", 100, 100);

    //Creation de la boîte choix
    ComboBox<String> choiceDoor = new ComboBox<>();
    choiceDoor.getItems().addAll(listDoors);
    choiceDoor.setValue("noDoor"); // Valeur par défaut = rien

    //Creation et ajout des labels Vers:
    Label vers = new Label("Vers :");
    Label towards = new Label("Towards :");

    VBox labelBox = new VBox();
    labelBox.getChildren().addAll(vers, towards);

    //Ajout du choix de la direction
    HBox wayBox = new HBox();
    wayBox.getChildren().addAll(labelBox, choiceDoor);
    wayBox.setAlignment(Pos.CENTER);

    //Ajout dans la box principal
    infoBox.getChildren().addAll(image, wayBox);

    //Mis à jour de l'image
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

  private int find(
    String[] listDoors,
    String elem,
    ArrayList<String> listImages
  ) {
    int lenNbDoor = listDoors.length; //len - 1 car dans la liste on a aussi noDoor
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

  private String[] getDoors() {
    //recuperer le contenu via le controller
    String[] doors = {
      "noDoor",
      "Place Lepetit",
      "Beaulieu",
      "Notre-Dame",
      "Blossac",
    };
    return doors;
  }

    private void otherInfoItem(VBox infoBox, ArrayList<String> listInfoItem) {
        //Sous Tab choix entre WeaponModel / ContainerModel / Consommable / Other ????

        //Je pars du principe que chaque modelItem est enregistré sous la même forme
        //nom de l'image/modelItem ; type d'modelItem ; paramètre1 : valeur / 1parameter : value ; paramètre2 : valeur / 2parameter : value
        //dans l'ordre de la classe la plus haute dont il hérite jusqu'à lui
        Label msgFLabel = new Label("Paramètres de l'objet");
        Label msgELabel = new Label("ItemModel's parameters");
        String presFr = "Cet objet est un/une ";
        String presEn = "This object is a/an ";

        otherInfoData(infoBox, listInfoItem, msgFLabel, msgELabel, presFr, presEn);

    }

    // private LocationModel curLoc;
    // private LocationModel lastLoc;
    // public int posx;
    // public int posy;
    // protected int health;
    // public final int MAXHEALTH;
    // protected ProtectionModel shield; //Can be null
    // protected WeaponModel modelWeapon; //Can be null
    private void otherInfoCharactere(VBox infoBox, ArrayList<String> listInfoItem) {
        //Sous Tab choix entre WeaponModel / ContainerModel / Consommable / Other ????
    
        //Je pars du principe que chaque modelItem est enregistré sous la même forme
        //nom de l'image/modelItem ; type d'modelItem ; paramètre1 : valeur / 1parameter : value ; paramètre2 : valeur / 2parameter : value
        //dans l'ordre de la classe la plus haute dont il hérite jusqu'à lui
        Label msgFLabel = new Label("Information personnage : ");
        Label msgELabel = new Label("Charater's information: ");
        String presFr = "Ce personnage est un/une ";
        String presEn = "This character is a/an ";
    
        otherInfoData(infoBox, listInfoItem, msgFLabel, msgELabel, presFr, presEn);
    
    }

    public void otherInfoData(VBox infoBox, ArrayList<String> listInfoItem, Label msgFLabel, Label msgELabel, String presFr, String presEn){
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
                lang = parts[i].split("/");
                if (lang.length >= 2) {
                  String value = lang[1].split(":")[1];
                  msgF += lang[0] + ":" + value + "\n";
                  msgE += lang[1] + "\n";
                }
            }
            } else {
                msgF += "Pas d'autres informations";
                msgE += "No other data";
            }
            msgFList.add(msgF);
            msgEList.add(msgE);
        }
  
        SelectElemItem listItem = new SelectElemItem(imgItems,8, msgFList, msgEList, msgFLabel, msgELabel );
        infoBox.getChildren().addAll(listItem, msgFLabel, msgELabel);
    }
  
}