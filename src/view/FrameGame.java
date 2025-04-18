package view;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;

public class FrameGame extends GridPane {
    private double prefHeight = 50;
    private double prefWidth = 50;
    
    public FrameGame(int col, int row)
    {
        super();

        int nbCol = col - 1;
        int nbRow = row - 1;
        this.addStackPane(nbCol, nbRow);

        this.setHgap(1);
        this.setVgap(1);
        this.setAlignment(Pos.CENTER);
    }

    private void addStackPane(int nbCol, int nbRow)
    {
        //Remplissage de la grid
        for(int i = 0; i < nbCol; i++)
        {
            for(int j = 0; j < nbRow; j++)
            {
                StackPane cell = new StackPane();
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
                        cell.getChildren().add(itemImage);                            
                        event.setDropCompleted(true);
                    } else {
                        event.setDropCompleted(false);
                    }
                    event.consume();
                });

                this.add(cell, i, j);
            }
        }
    }


    //Ajout de cellule dans lesquels on peut faire du drag and drop, et supprimer des elements
    public void addCellsToFrame(GridPane gridTarget, int nbCol, int nbRow) {
        for (int i = 0; i < nbCol; i++) {
            for (int j = 0; j < nbRow; j++) {
                StackPane cell = new StackPane();
                cell.setPrefSize(prefWidth, prefHeight);
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
                        ImageView newItem = createDraggableImage(cell, itemName, nbCol, nbRow);

                        cell.getChildren().clear();
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
    private ImageView createDraggableImage(StackPane cell, String imageName, int nbCol, int nbRow) {
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

            cell.getChildren().remove(image);

            event.consume();
        });

        image.setOnDragDone(event -> {
            if (!event.isDropCompleted()) {
                // L'image a été relâchée ailleurs → elle est supprimée (déjà retirée)
            }
            event.consume();
        });
    
        return image;
    }

}

