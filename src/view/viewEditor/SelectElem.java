package view.viewEditor;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;

public class SelectElem extends GridPane {
    private double prefHeight = 50;
    private double prefWidth = 50;
    private String[] nomsItems;

    //A terme pour avoir des gridPane pour chaque type d'element il faudra ajouter la liste des nomsItems en paramètre du constructeur
    public SelectElem(String[] listItem, int col)
    {
        super();

        this.nomsItems = listItem;

        int nbCol = col - 1;

        addItemsToTypeElem(this, nbCol);

        this.setHgap(1);
        this.setVgap(1);
        this.setAlignment(Pos.CENTER);

        //this.defineColRow(30, nbCol, nbRow);
    }

    //Add Items To TypeElem (ajouter un choix dans le gridPane des élèments)
    private void addItemsToTypeElem(GridPane gridSource, int nbCol) {
        int nbItems = nomsItems.length;
        int nbRow = nbItems % nbCol;

        int j = 0, k = 0;
    
        for (int i = 0; i < nbItems; i++) {
            final String itemName = nomsItems[i];
            // Drag uniquement (pas de drop ici)
            ImageView item = createDraggableImage(itemName, nbCol, nbRow);
            gridSource.add(item, j, k);
            j = (j + 1) % nbCol;
            if (j == 0) k++;
        }
    }

    //Création d'une image (element) qu'on puisse prendre et déposer dans un autre gridPane
    private ImageView createDraggableImage(String imageName, int nbCol, int nbRow) {
        ImageView image = new ImageView(new Image("file:../resources/image/" + imageName + ".jpg"));
        image.setPreserveRatio(true);
        image.setSmooth(true);
        image.setCache(true);
    
        // Adapter la taille de l'image une fois qu'elle est posé dans la scène (pour éviter NullPointerException)
        image.sceneProperty().addListener((obs, oldScene, newScene) -> {
            double h = this.prefHeight;
            double w = this.prefWidth;
            if (h < w){
                image.setFitHeight(h);
            }
            else{
                image.setFitWidth(w);
            }
            
        });

        // Activer le drag and drop
        image.setOnDragDetected(event -> {
            Dragboard db = image.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(imageName);
            db.setContent(content);
            event.consume();
        });
    
        return image;
    }

}