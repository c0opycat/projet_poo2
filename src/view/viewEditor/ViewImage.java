package view.viewEditor;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import view.ImageCache;

public class ViewImage extends ImageView{

    public ViewImage(){
        super();
    }

    //Création d'une image (element) qu'on puisse prendre et déposer dans un gridPane
    public void createDraggableImage(String imageName, String elemName, double prefHeight, double prefWidth) {
        Image image = ImageCache.getImage(imageName + ".png", prefWidth, prefHeight);
        this.setImage(image);
        this.setPreserveRatio(true);

        if (prefHeight < prefWidth) {
            this.setFitHeight(prefHeight);
        } else {
            this.setFitWidth(prefWidth);
        }

        // Activer le drag and drop
        this.setOnDragDetected(event -> {
            Dragboard db = this.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(imageName + ";" + elemName);
            db.setContent(content);
            event.consume();
        });

    }

    public void addListenerOnClicked(Label msgFLabel, String msgF, Label msgELabel, String msgE ){
        this.setOnMouseClicked(event -> {
            msgFLabel.setText(msgF);
            msgELabel.setText(msgE);
        });
    }
}

