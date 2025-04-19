package view.viewEditor;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
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
         
         for(i = 0; i < nbTypes; i++)
         {
            String type = types[i];

            Tab tab = new Tab(type);
            VBox infoBox = new VBox();
            infoBox.setAlignment(Pos.CENTER);
            detailsTab(tab, infoBox, type, listElem, i);

            this.getTabs().add(tab);
         }
   }

   private void detailsTab(Tab tab, VBox infoBox, String type, ArrayList<ArrayList<String>> listElem, int i){

      ArrayList<String> listCurrType = listElem.get(i);
      SelectElem elems= new SelectElem(listCurrType, 8);

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
         default: 
            tab.setContent(elems);
            break;
      }
      
   }

   private Region spring(){
      Region spring = new Region();
      VBox.setVgrow(spring, Priority.ALWAYS);
      return spring;
   }
   
   //Attention seulement les labels ils faut faire une fonction pour empêcher de mettre plus d'un monstre
   //Sinon gestion initial des monstres via random et ne pas permettre à l'utilisateur de mettre des monstres
   private void otherInfoMonster(VBox infoBox){
      Label attention = new Label("Attention : vous ne pouvez mettre qu'un seul monstre par niveau");
      Label warning = new Label("Warning : you can put only one monster per level");
      infoBox.getChildren().addAll(spring(), attention, spring(), warning, spring());
   }

   private void otherInfoDoor(VBox infoBox, ArrayList<String> listImgDoors){
      String[] listDoors = getDoors();

      //Creation image drag & drop
      ViewImage image = new ViewImage();
      image.createDraggableImage("noDoor", " ", 100, 100);
      
      //Creation de la boîte choix
      ComboBox<String> choiceDoor = new ComboBox<>();
      choiceDoor.getItems().addAll(listDoors);
      choiceDoor.setValue(" "); // Valeur par défaut = rien

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
      choiceDoor.valueProperty().addListener((obs, oldVal, newVal) -> {
         int i = find(listDoors, newVal, listImgDoors);
         if (i > -1){
            image.createDraggableImage("door" + i, newVal, 100, 100);
         }
         else{
            image.createDraggableImage("noDoor", " ", 100, 100);
         }
      });
   }

   private int find(String[] listDoors, String elem, ArrayList<String> listImages){
      int lenNbDoor = listDoors.length; //len - 1 car dans la liste on a aussi noDoor
      int i = lenNbDoor;
      for (String currDoor : listDoors){
         if (currDoor == elem){
            break;
         }
         i--;
      }

      int currI = i % (listImages.size() - 1);
      return currI;
   }

   private String[] getDoors(){
      //recuperer le contenu via le controller
      String[] doors = {"Place Lepetit", "Beaulieu", "Notre-Dame", "Blossac"};
      return doors;
   }

   private void otherInfoItem(VBox infoBox, ArrayList<String> listInfoItem)
   {
      //Sous Tab choix entre Weapon / Container / Consommable / Other ????

      //Je pars du principe que chaque item est enregistré sous la même forme
      //nom de l'image/item ; type d'item ; paramètre1 : valeur / 1parameter : value ; paramètre2 : valeur / 2parameter : value
      //dans l'ordre de la classe la plus haute dont il hérite jusqu'à lui
      ArrayList<String> imgItems = new ArrayList<>();
      ArrayList<String> msgFList = new ArrayList<>();
      ArrayList<String> msgEList = new ArrayList<>();
      String msgF;
      String msgE;
      Label msgFLabel = new Label("Paramètres de l'objet");
      Label msgELabel = new Label("Item's parameters");

      for (String currItem : listInfoItem){
         String[] parts = currItem.split(";");

         String[] lang = parts[0].split("/");
         if (lang.length >= 2){
            msgF = "Cet objet est un/une " + lang[0] + ".\n";
            msgE = "This object is a/an " + lang[1] + ".\n";
         }
         else {
            msgF = "Sans nom \n";
            msgE = "No name \n";
         }

         imgItems.add(lang[0]);

         int len = parts.length;
         if ( len > 1 ){
            msgF += "Ses paramètres sont ";
            msgE += "Its parameters are ";
            for (int i = 1; i < len; i++)
            {
               lang = parts[i].split("/");
               if (lang.length >= 2){
                  msgF += lang[0];
                  msgE += lang[1];
               }
               else{
                  msgF += "Il n'a pas de paramètre particulier.";
                  msgE += "It has no particular parameter.";
               }
            }
         }
         else{
            msgF += "Il n'a pas de paramètre particulier.";
            msgE += "It has no particular parameter.";
         }
         msgFList.add(msgF);
         msgEList.add(msgE);
      }

      SelectElemItem listItem= new SelectElemItem(imgItems, 8, msgFList, msgEList, msgFLabel, msgELabel );
      infoBox.getChildren().addAll(listItem, spring(), msgFLabel, spring(), msgELabel);
   }

}