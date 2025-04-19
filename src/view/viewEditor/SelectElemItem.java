package view.viewEditor;

import java.util.ArrayList;

import javafx.scene.control.Label;

public class SelectElemItem extends SelectElem {
    private ArrayList<String> msgF;
    private ArrayList<String> msgE;

    //A terme pour avoir des gridPane pour chaque type d'element il faudra ajouter la liste des nomsItems en paramètre du constructeur
    public SelectElemItem(ArrayList<String> listItem, int col, ArrayList<String> msgFItem, ArrayList<String> msgEItem, Label msgFLabel, Label msgELabel)
    {
        super(listItem, col);
        this.msgF = msgFItem;
        this.msgE = msgEItem;
        this.addsListenersOnClicked(msgFLabel, msgF, msgELabel, msgE);
    }

    public void addsListenersOnClicked(Label msgFLabel, ArrayList<String> msgF, Label msgELabel, ArrayList<String> msgE){
        int nbItems = msgF.size();

            
        for (int i = 0; i < nbItems; i++){
            final String currentFMsg = msgF.get(i);
            final String currentEMsg = msgE.get(i);
            ViewImage img = ((ViewImage)this.getChildren().get(i));
            img.addListenerOnClicked(msgFLabel, currentFMsg, msgELabel, currentEMsg);
        }
    }
}