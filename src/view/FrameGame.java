package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;

public class FrameGame extends GridPane {
    public FrameGame(int col, int row)
    {
        super();

        int nbCol = col - 1;
        int nbRow = row - 1;
        this.addLabels(nbCol, nbRow);

        this.setHgap(1);
        this.setVgap(1);
        this.setAlignment(Pos.CENTER);

        this.defineColRow(30, nbCol, nbRow);

        //this.resetCell(4, 4);
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

    private void addLabels(int nbCol, int nbRow)
    {
        //Remplissage de la grid
        for(int i = 0; i <= nbCol; i++)
        {
            for(int j = 0; j <= nbRow; j++)
            {
                StackPane cell = new StackPane();
                cell.setPrefSize(50, 50);
                cell.setStyle("-fx-border-color: black; -fx-background-color: lightgray;");

                // Accepter le dépôt
                cell.setOnDragOver(event -> {
                    if (event.getGestureSource() != cell && event.getDragboard().hasString()) {
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                    event.consume();
                });

                // Gérer le dépôt
                cell.setOnDragDropped(event -> {
                    Dragboard db = event.getDragboard();
                    if (db.hasString()) {
                        String itemNom = db.getString();
                        ImageView itemImage = new ImageView(new Image("file:../resources/image/"+ itemNom+".jpg"));
                        itemImage.setFitWidth(50);
                        itemImage.setFitHeight(50);
                            cell.getChildren().add(itemImage);                            event.setDropCompleted(true);
                    } else {
                        event.setDropCompleted(false);
                    }
                    event.consume();
                });

                this.add(cell, i, j);
            }
        }
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

    //Ajout de cellule dans lesquels on peut faire du drag and drop, et supprimer des elements
    public void addCellsToFrame(GridPane gridTarget, int nbCol, int nbRow) {
        for (int i = 0; i <= nbCol; i++) {
            for (int j = 0; j <= nbRow; j++) {
                StackPane cell = new StackPane();
                cell.setPrefSize(50, 50);
                cell.setStyle("-fx-border-color: black; -fx-background-color: lightgray;");
    
                // Accepter le dépôt
                cell.setOnDragOver(event -> {
                    if (event.getGestureSource() != cell && event.getDragboard().hasString()) {
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                    event.consume();
                });
    
                // Gérer le dépôt
                cell.setOnDragDropped(event -> {
                    Dragboard db = event.getDragboard();
                    if (db.hasString()) {
                        String itemName = db.getString();
                        ImageView newItem = createDraggableImage(itemName, nbCol, nbRow);
                        
                        newItem.setOnDragDetected(ev -> {
                            Dragboard dragboard = newItem.startDragAndDrop(TransferMode.MOVE);
                            ClipboardContent content = new ClipboardContent();
                            content.putString(itemName);
                            dragboard.setContent(content);
    
                            // Retirer l'image de la cellule source
                            cell.getChildren().remove(newItem);
                            ev.consume();
                        });
    
                        newItem.setOnDragDone(ev -> {
                            if (!ev.isDropCompleted()) {
                                // L'image a été relâchée ailleurs → elle est supprimée (déjà retirée)
                            }
                            ev.consume();
                        });

                        cell.getChildren().clear(); // ou garder plusieurs si besoin
                        cell.getChildren().add(newItem);
                        event.setDropCompleted(true);
                    } else {
                        event.setDropCompleted(false);
                    }
                    event.consume();
                });
    
                gridTarget.add(cell, i, j);
            }
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

