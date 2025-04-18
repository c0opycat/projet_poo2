package view.viewEditor;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class SelectTypeElem extends TabPane {
 
   public SelectTypeElem(String types[], String listElem[][]) {
      super();

         int nbTypes = types.length;
         int i;
         
         for(i = 0; i < nbTypes; i++)
         {
            String type = types[i];

            Tab tab = new Tab(type);
            VBox infoTab = new VBox();
            detailsTab(tab, infoTab, type, listElem, i);

            this.getTabs().add(tab);
         }
   }

   private void detailsTab(Tab tab, VBox infoBox, String type, String listElem[][], int i){

      SelectElem elem = new SelectElem(listElem[i], 8);
      switch (type) {
         case "Monstre / Monster":
            infoBox.getChildren().add(elem);
            otherInfoMonster(infoBox);
            tab.setContent(infoBox);
            break;
         case "Portes / Doors":
            infoBox.getChildren().add(elem);
            otherInfoDoor(infoBox);
            tab.setContent(infoBox);
            break;
         default: tab.setContent(elem);
      }
      
   }

   private Region spring(){
      Region spring = new Region();
      VBox.setVgrow(spring, Priority.SOMETIMES);
      return spring;
   }
   
   private void otherInfoMonster(VBox infoBox){
      Label attention = new Label("Attention : vous ne pouvez mettre qu'un seul monstre par niveau");
      Label warning = new Label("Warning : you can put only one monster per level");
      infoBox.getChildren().addAll(spring(), attention, spring(), warning);
   }

   private void otherInfoDoor(VBox infoBox){
      
   }
}