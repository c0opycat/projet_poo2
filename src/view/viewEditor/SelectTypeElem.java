package view.viewEditor;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class SelectTypeElem extends TabPane {
   public SelectTypeElem(String Types[]) {
      super();

        for(String s : Types)
        {
            Tab tab = new Tab(s);
            tab.setContent(new SelectElem(8));
            this.getTabs().add(tab);
        }
   }

}