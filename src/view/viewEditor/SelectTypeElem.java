package view.viewEditor;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class SelectTypeElem extends TabPane {
 
   public SelectTypeElem(String types[], String listElem[][]) {
      super();

         int nbTypes = types.length;
         int i;
         
         for(i = 0; i < nbTypes; i++)
         {
            Tab tab = new Tab(types[i]);
            tab.setContent(new SelectElem(listElem[i], 8));
            this.getTabs().add(tab);
         }
   }

}