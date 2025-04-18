package view;

import javafx.scene.image.ImageView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;

public class Cell extends StackPane {
    private String elem;
    private Tooltip tooltip;

    public Cell(){
        super();

        this.tooltip = new Tooltip();
        Tooltip.install(this,tooltip);
        updateTooltip();

        this.setStyle("-fx-border-color: black; -fx-background-color: lightgray;");
    }

    public void addCellDraggable(double prefHeight, double prefWidth, int nbCol, int nbRow){
        this.setPrefSize(prefWidth, prefHeight);
        this.setStyle("-fx-border-color: black; -fx-background-color: lightgray;");

         // Accepter le dépôt
        this.setOnDragOver(event -> {
            if (event.getGestureSource() != this && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });
    
        // Gérer le dépôt
        this.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasString()) {
                String itemNom = db.getString();
                String[] parts = itemNom.split(";");

                if (parts.length == 2){
                    String imageName = parts[0];
                    String elemName = parts[1];
                    ImageView newItem = createDraggableImage(imageName, elemName, nbCol, nbRow, prefHeight, prefWidth);

                    this.getChildren().clear();
                    this.getChildren().add(newItem);
                    this.elem = elemName;

                    event.setDropCompleted(true);
                }
                else{
                    event.setDropCompleted(false);
                }
            } 
            else {
                event.setDropCompleted(false);
            }
            event.consume();
        });
        
    }

    //Création d'une image (element) qu'on puisse prendre et déposer dans un autre gridPane
    private ImageView createDraggableImage(String imageName, String elemName, int nbCol, int nbRow, double prefHeight, double prefWidth) {
        ImageView image = new ImageView(new Image("file:../resources/image/" + imageName + ".png"));
        image.setPreserveRatio(true);
        image.setSmooth(true);
        image.setCache(true);

        // Adapter la taille de l'image une fois qu'elle est posé dans la scène (pour éviter NullPointerException)
        image.sceneProperty().addListener((obs, oldScene, newScene) -> {
            double h = prefHeight;
            double w = prefWidth;
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
            content.putString(imageName + ";" + elemName);
            db.setContent(content);

            this.getChildren().remove(image);
            this.elem =  null;

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

    private void updateTooltip(){
        if (elem == null){
            tooltip.setText("");
        }
        else{
            tooltip.setText(this.elem);
        }
    }
}
