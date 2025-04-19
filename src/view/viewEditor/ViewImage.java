package view.viewEditor;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class ViewImage extends ImageView{

    public ViewImage(){
        super();
    }

    //Création d'une image (element) qu'on puisse prendre et déposer dans un gridPane
    public void createDraggableImage(String imageName, String elemName, double prefHeight, double prefWidth) {
        Image image = new Image("file:../resources/image/" + imageName + ".png");
        this.setImage(image);
        this.setPreserveRatio(true);
        this.setSmooth(true);
        this.setCache(true);

        this.sceneProperty().addListener((obs, oldScene, newScene) -> {
            double h = prefHeight;
            double w = prefWidth;
            if (h < w){
                this.setFitHeight(h);
            }
            else{
                this.setFitWidth(w);
            }
        });

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

