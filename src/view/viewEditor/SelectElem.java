package view.viewEditor;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;

public class SelectElem extends GridPane {
    public SelectElem(int col, int row)
    {
        super();

        int nbCol = col - 1;
        int nbRow = row - 1;

        addItemsToTypeElem(this, nbCol, nbRow);
        this.setHgap(1);
        this.setVgap(1);
        this.setAlignment(Pos.CENTER);

        this.defineColRow(30, nbCol, nbRow);
    }

    private void setCell(String val, int x, int y, String numberColor)
    {

        //Style du label en CSS
        String label_style = "-fx-background-color: " + numberColor;

        Label to_add = new Label(val);
        
        //Style
        to_add.setStyle(label_style);
        //taille des cases
        to_add.setMaxHeight(Double.MAX_VALUE);
        to_add.setMaxWidth(Double.MAX_VALUE);
        to_add.setAlignment(Pos.CENTER);

        this.add(to_add, x, y);
    }

    public void setCell(char val, int x, int y, String numberColor)
    {
        String label_text = "" + val;
        
        this.setCell(label_text, x, y, numberColor);
    }

    public void resetCell(int x, int y)
    {
        this.setCell(" ", x, y, "#8A2BE2");
    }
    
    private void defineColRow(int size, int nbCol, int nbRow)
    {
        for (int i = 0; i <= nbCol; i++)
        {
            this.getColumnConstraints().add(new ColumnConstraints(size)); 
        }
        
        for (int i = 0; i <= nbRow; i++)
        {
            this.getRowConstraints().add(new RowConstraints(size));
        }
        
    }

    //Add Items To TypeElem (ajouter un choix dans le gridPane des élèments)
    private void addItemsToTypeElem(GridPane gridSource, int nbCol, int nbRow) {
        String[] nomsItems = {"maison_bleu", "maison_jaune"};
        int nbItems = nomsItems.length;
        int j = 0, k = 0;
    
        for (int i = 0; i < nbItems; i++) {
            final String itemName = nomsItems[i];
            ImageView item = createDraggableImage(itemName, nbCol, nbRow);
    
            // Drag uniquement (pas de drop dans la source)
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
    
        image.fitWidthProperty().bind(image.getScene().widthProperty().divide(nbCol));  // adaptatif
        image.fitHeightProperty().bind(image.getScene().heightProperty().divide(nbRow));
    
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