package view.viewEditor;

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
 
   public SelectTypeElem(String types[], String listElem[][]) {
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

   private void detailsTab(Tab tab, VBox infoBox, String type, String listElem[][], int i){

      String[] listCurrType = listElem[i];
      SelectElem elems= new SelectElem(listCurrType, 8);

      switch (type) {
         case "Monstre / Monster":
            infoBox.getChildren().add(elems);
            otherInfoMonster(infoBox);
            tab.setContent(infoBox);
            break;
         case "Portes / Doors":
            otherInfoDoor(infoBox, listCurrType);
            tab.setContent(infoBox);
            break;
         default: tab.setContent(elems);
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

   private void otherInfoDoor(VBox infoBox, String[] listImgDoors){
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
            //image.setImage(new Image("file:../resources/image/door" + i + ".png"));
         }
         else{
            //image.setImage(new Image("file:../resources/image/noDoor.png"));
            image.createDraggableImage("noDoor", " ", 100, 100);
         }
      });
   }

   private int find(String[] listDoors, String elem, String[] listImages){
      int lenNbDoor = listDoors.length; //len - 1 car dans la liste on a aussi noDoor
      int i = lenNbDoor;
      for (String currDoor : listDoors){
         if (currDoor == elem){
            break;
         }
         i--;
      }

      int currI = i % (listImages.length - 1);
      return currI;
   }

   private String[] getDoors(){
      //recuperer le contenu via le controller
      String[] doors = {"Place Lepetit", "Beaulieu", "Notre-Dame", "Blossac"};
      return doors;
   }

}