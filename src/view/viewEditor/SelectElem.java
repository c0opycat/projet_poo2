package view.viewEditor;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class SelectElem extends GridPane {
    private double prefHeight = 50;
    private double prefWidth = 50;
    private ArrayList<String> nomsItems;

    //A terme pour avoir des gridPane pour chaque type d'element il faudra ajouter la liste des nomsItems en paramètre du constructeur
    public SelectElem(ArrayList<String> listItem, int col)
    {
        super();

        this.nomsItems = listItem;

        int nbCol = col - 1;

        this.addItemsToTypeElem(nbCol);

        this.setHgap(1);
        this.setVgap(1);
        this.setAlignment(Pos.CENTER);

        //this.defineColRow(30, nbCol, nbRow);
    }

    //Add Items To TypeElem (ajouter un choix dans le gridPane des élèments)
    private void addItemsToTypeElem(int nbCol) {
        int nbItems = nomsItems.size();

        int j = 0, k = 0;
    
        for (int i = 0; i < nbItems; i++) {
            final String imageName = nomsItems.get(i);
            final String elemName = nomsItems.get(i);
            // Drag uniquement (pas de drop ici)
            ViewImage item = new ViewImage();
            item.createDraggableImage(imageName, elemName, this.prefHeight, this.prefWidth);
            this.add(item, j, k);
            j = (j + 1) % nbCol;
            if (j == 0) k++;
        }
    }
}