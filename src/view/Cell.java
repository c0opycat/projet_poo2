package view;

import javafx.scene.image.ImageView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;

/**
 * View of a cell on the modelGame board.
 * This class manages the image displayed on the board.
 *
 * @author C. Besançon
 */
public class Cell extends StackPane {
    /** Name of the element save on the cell */
    private String elem;
    /**Information show about the element */
    private Tooltip tooltip;

    //// Public ////
    
    /**
     * Constructor
     */
    public Cell(){
        super();

        this.tooltip = new Tooltip();
        Tooltip.install(this,tooltip);
        updateTooltip();

        this.setStyle("-fx-border-color: black;");
        this.getStyleClass().add("transparent-layer");
    }


    /**
     * Set the cell to recognize drag and drop and change the cell to a new modelItem in it
     * @param prefHeight preferred height - fixed height of a grid square
     * @param prefWidth preferred width - fixed width of a grid square
     */
    public void setCellDraggable(){
        this.setDragOver();
        this.setDragDropped();
        
    }

    //// Private ////
    
    /**
     * Set the cell to recognize drag over
     */
    private void setDragOver(){
         this.setOnDragOver(event -> {
            if (event.getGestureSource() != this && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });
    }

    /**
     * Defines drag and drop: when dropping, retrieves the name of the image and the element 
     * and creates a cell (image view) containing the element (displays its image and in the tooltip its name). 
     * Empty the old cell and replace it with the new one.
     * @param prefHeight preferred height - fixed height of a grid square
     * @param prefWidth preferred width - fixed width of a grid square
     */
    private void setDragDropped(){
                this.setOnDragDropped(event -> {
                    Dragboard db = event.getDragboard();
                    if (db.hasString()) {
                        String itemNom = db.getString();
                        String[] parts = itemNom.split(";");
        
                        if (parts.length == 2){
                            setElement(parts);
        
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

    
    /**
     * Creating an image (element) that can be taken and dropped into another gridPane
     * @param imageName name of the image to display
     * @param elemName name of the element to display
     * @param prefHeight preferred height - fixed height of a grid square
     * @param prefWidth preferred width - fixed width of a grid square
     * @return
     */
    private ImageView createDraggableImage(String imageName, String elemName) {
        ImageView image = new ImageView(new Image("file:../resources/image/" + imageName + ".png"));
        image.setPreserveRatio(true);
        image.setSmooth(true);
        image.setCache(true);

        fitSize(image);

        setDragDetected(image, imageName, elemName);
    
        return image;
    }
    
    /**
     * Resize the image once it is placed in the scene to avoid NullPointerException
     * @param image ImageView that contains the image to display in the grid cell
     */
    private void fitSize(ImageView image){
        image.sceneProperty().addListener((obs, oldScene, newScene) -> {
            image.fitWidthProperty().bind(this.widthProperty());
            image.fitHeightProperty().bind(this.heightProperty());
        });
    }

    /**
     * Set the ability to retrieve the image and element name 
     * and then remove the image from the cell when a drag is detected
     * @param image ImageView that contains the image to display in the grid cell
     * @param imageName Name of the image show on the cell
     * @param elemName Name of the element save on the cell
     */
    private void setDragDetected(ImageView image, String imageName, String elemName){
        image.setOnDragDetected(event -> {
            Dragboard db = image.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(imageName + ";" + elemName);
            db.setContent(content);

            this.getChildren().remove(image);
            this.elem =  null;

            event.consume();
        });
    }

    /**
     * Changes the name of the element to be displayed when hovering over a box in the tooltip
     */
    private void updateTooltip(){
        if (elem == null){
            tooltip.setText("");
        }
        else{
            tooltip.setText(this.elem);
        }
    }

    public void setElement(String[] elem) {

        String imageName = elem[0];
        String elemName = elem[1];
        ImageView newItem = createDraggableImage(imageName, elemName);
        
        this.getChildren().clear();
        this.getChildren().add(newItem);
        this.elem = elemName;
        updateTooltip();
    }

    public String getElement() {
        return elem;
    }
}
