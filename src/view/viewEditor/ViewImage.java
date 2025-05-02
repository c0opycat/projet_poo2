package view.viewEditor;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import view.ImageCache;

/**
 * A custom ImageView used to represent draggable images (elements)
 * in the editor. This component can be added to a GridPane and supports
 * drag-and-drop as well as click-based info display.
 * 
 * @author C. Besançon
 */
public class ViewImage extends ImageView {
  /// Public///
  /**
   * Default constructor
   */
  public ViewImage() {
    super();
  }

  /**
   * Initializes this ViewImage with a specific image and makes it draggable.
   * 
   * The image is scaled preserving its aspect ratio and will be
   * rescaled according to which dimension (height or width) is smaller.
   * 
   * When dragged, the dragboard will carry a string formatted as:
   * {@code imageName;elementName}
   * 
   * @param imageName  The base name of the image file (without ".png").
   * @param elemName   The name of the game element this image represents.
   * @param prefHeight The preferred height to fit the image.
   * @param prefWidth  The preferred width to fit the image.
   */
  public void createDraggableImage(
      String imageName,
      String elemName,
      double prefHeight,
      double prefWidth) {
    Image image = ImageCache.getImage(
        imageName + ".png",
        prefWidth,
        prefHeight);
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

  /**
   * Adds a mouse click listener that updates two labels with the provided
   * messages.
   *
   * @param msgFLabel The first label to update on click (e.g. function
   *                  description).
   * @param msgF      The message to set on {@code msgFLabel}.
   * @param msgELabel The second label to update on click (e.g. element
   *                  description).
   * @param msgE      The message to set on {@code msgELabel}.
   */
  public void addListenerOnClicked(
      Label msgFLabel,
      String msgF,
      Label msgELabel,
      String msgE) {
    this.setOnMouseClicked(event -> {
      msgFLabel.setText(msgF);
      msgELabel.setText(msgE);
    });
  }
}
