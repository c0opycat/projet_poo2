package view;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;

/**
 * View of a cell on the modelGame board.
 * This class manages the image displayed on the board.
 * It manages:
 * - The displayed element (name and image)
 * - Tooltip text when hovering
 * - Drag-and-drop behaviors for element movement
 *
 * @author C. Besançon
 */
public class Cell extends StackPane {

  /** Name of the element save on the cell */
  private String elem;
  /** Name of the image */
  private String img;
  /** Information show about the element */
  private Tooltip tooltip;
  /** ImageView associated with the elem */
  private ImageView imageView;

  //// Public ////

  /**
   * Default constructor.
   * Creates an empty cell with tooltip and styling.
   */
  public Cell() {
    super();
    this.tooltip = new Tooltip();
    Tooltip.install(this, tooltip);
    updateTooltip();

    this.setStyle("-fx-border-color: black;");
    this.getStyleClass().add("transparent-layer");
  }

  /**
   * Constructor with an element name.
   * 
   * @param elem Name of the element stored in this cell
   */
  public Cell(String elem) {
    super();
    this.elem = elem;
    this.tooltip = new Tooltip();
    Tooltip.install(this, tooltip);
    updateTooltip();

    this.setStyle("-fx-border-color: black;");
    this.getStyleClass().add("transparent-layer");
  }

  /**
   * Constructor with an element and an image name.
   * 
   * @param elem Name of the element
   * @param img  Image file name (without extension)
   */
  public Cell(String elem, String img) {
    super();
    this.elem = elem;
    this.img = img;
    this.tooltip = new Tooltip();
    Tooltip.install(this, tooltip);
    updateTooltip();

    this.setStyle("-fx-border-color: black;");
    this.getStyleClass().add("transparent-layer");
  }

  /**
   * Removes the given image from the cell.
   * 
   * @param image The ImageView to remove
   */
  public void deleteImage(ImageView image) {
    this.getChildren().remove(image);
    this.setImageView(null);
  }

  /**
   * Add the given image from the cell.
   * 
   * @param image The ImageView to add
   */
  public void addImage(ImageView image) {
    fitSize(image);
    this.getChildren().add(image);
    this.setImageView(image);
  }

  /**
   * Adds an image to the cell based on the stored element name.
   */
  public void addImage() {
    String imageName = Cell.getImageName(this.getElement());

    ImageView imageView = new ImageView(
        new Image(imageName, 25, 25, true, true));

    imageView.setCache(true);

    fitSize(imageView);

    this.getChildren().add(imageView);
    this.setImageView(imageView);
  }

  /**
   * Returns the file path of the image corresponding to a given element.
   * 
   * @param nameItem Name of the element
   * @return File path to the image
   */
  public static String getImageName(String nameItem) {
    String path = "file:./resources/image/";
    String ext = ".png";
    String imageElement = null;
    switch (nameItem) {
      case "Protection":
        imageElement = "armure";
        break;
      case "Crowbar":
        imageElement = "pied de biche";
        break;
      case "BaseballBat":
        imageElement = "batte";
        break;
      case "Doggo":
        imageElement = "grand chien";
        break;
      case "Gun":
        imageElement = "pistolet";
        break;
      case "Sword":
        imageElement = "épée";
        break;
      case "Backpack":
        imageElement = "sac à dos";
        break;
      case "Crate":
        imageElement = "caisse2";
        break;
      case "Chest":
        imageElement = "coffre";
        break;
      case "Medicine":
        imageElement = "flacon de gellules";
        break;
      case "Meat":
        imageElement = "steak";
        break;
      case "Fruit":
        imageElement = "pommes";
        break;
      case "Cake":
        imageElement = "gateau";
        break;
    }

    return path + imageElement + ext;
  }

  /**
   * Set the cell to recognize drag and drop
   * and change the cell to a new modelItem in it
   */
  public void setCellDraggable() {
    this.setDragOver();
    this.setDragDropped();
  }

  //// Private ////

  /**
   * Set the cell to recognize drag over
   */
  private void setDragOver() {
    this.setOnDragOver(event -> {
      if (event.getGestureSource() != this && event.getDragboard().hasString()) {
        event.acceptTransferModes(TransferMode.MOVE);
      }
      event.consume();
    });
  }

  /**
   * Defines drag and drop: when dropping, retrieves the name of the image and the
   * element
   * and creates a cell (image view) containing the element (displays its image
   * and in the tooltip its name).
   * Empty the old cell and replace it with the new one.
   */
  private void setDragDropped() {
    this.setOnDragDropped(event -> {
      Dragboard db = event.getDragboard();
      if (db.hasString()) {
        String itemNom = db.getString();
        String[] parts = itemNom.split(";");

        if (parts.length == 2) {
          String oldElem = getElement();
          String oldImg = getImage();

          setElement(parts);

          String[] oldElement = { oldImg, oldElem };

          String[] newElement = parts;

          view.viewEditor.viewHistory.HistoryManager.getInstance()
              .recordAction(
                  new view.viewEditor.viewHistory.CellAction(
                      this,
                      oldElement,
                      newElement));

          event.setDropCompleted(true);
        } else {
          event.setDropCompleted(false);
        }
      } else {
        event.setDropCompleted(false);
      }
      event.consume();
    });
  }

  /**
   * Creating an image (element) that can be taken and dropped into another
   * gridPane
   * 
   * @param imageName name of the image to display
   * @param elemName  name of the element to display
   * @return
   */
  private ImageView createDraggableImage(String imageName, String elemName) {
    ImageView image = new ImageView(
        new Image("file:./resources/image/" + imageName + ".png"));
    image.setPreserveRatio(true);
    image.setSmooth(true);
    image.setCache(true);

    fitSize(image);

    setDragDetected(image, imageName, elemName);

    return image;
  }

  /**
   * Resize the image once it is placed in the scene to avoid NullPointerException
   * 
   * @param image ImageView that contains the image to display in the grid cell
   */
  private void fitSize(ImageView image) {
    image
        .sceneProperty()
        .addListener((obs, oldScene, newScene) -> {
          image.fitWidthProperty().bind(this.widthProperty());
          image.fitHeightProperty().bind(this.heightProperty());
        });
  }

  /**
   * Set the ability to retrieve the image and element name
   * and then remove the image from the cell when a drag is detected
   * 
   * @param image     ImageView that contains the image to display in the grid
   *                  cell
   * @param imageName Name of the image show on the cell
   * @param elemName  Name of the element save on the cell
   */
  private void setDragDetected(
      ImageView image,
      String imageName,
      String elemName) {
    image.setOnDragDetected(event -> {
      Dragboard db = image.startDragAndDrop(TransferMode.MOVE);
      ClipboardContent content = new ClipboardContent();
      content.putString(imageName + ";" + elemName);
      db.setContent(content);

      this.removeElement(image);

      event.consume();
    });
  }

  /// Public Utilitaire///
  /**
   * Changes the name of the element to be displayed when hovering over a box in
   * the tooltip
   */
  private void updateTooltip() {
    if (elem == null) {
      Tooltip.uninstall(this, tooltip);
    } else {
      Tooltip.install(this, tooltip);
      tooltip.setText(this.elem);
    }
  }

  /**
   * Sets the element and its image from a string array.
   * The array should contain the image name and element name, in this order.
   * Replaces the current content of the cell with the new draggable image.
   *
   * @param elem Array of strings: [image name, element name]
   */
  public void setElement(String[] elem) {
    String imageName = elem[0];
    String elemName = elem[1];
    ImageView newItem = createDraggableImage(imageName, elemName);

    this.getChildren().clear();
    this.getChildren().add(newItem);
    this.elem = elemName;
    this.img = imageName;
    updateTooltip();
  }

  /**
   * Gets the name of the element stored in the cell.
   *
   * @return Name of the element
   */
  public String getElement() {
    return elem;
  }

  /**
   * Gets the name of the element stored in the cell (for saving purposes).
   *
   * @return Name of the element
   */
  public String getElementSave() {
    return elem;
  }

  /**
   * Removes the element from the cell (but does not remove the image).
   * Only updates internal references and tooltip.
   */
  public void removeElement() {
    this.elem = null;
    this.updateTooltip();
  }

  /**
   * Removes the specified image from the cell, and clears element and image name
   * references.
   *
   * @param image The ImageView to remove from the cell
   */
  public void removeElement(ImageView image) {
    this.deleteImage(image);
    this.img = null;
    this.elem = null;
    this.updateTooltip();
  }

  /**
   * Sets the name of the element stored in the cell.
   * This does not add or modify the image.
   *
   * @param elem Name of the element
   */
  public void setElement(String elem) {
    this.elem = elem;
    updateTooltip();
  }

  /**
   * Gets the name of the image associated with the element.
   *
   * @return Image file name (without path or extension)
   */
  public String getImage() {
    return this.img;
  }

  /**
   * Gets the tooltip text shown on hover (usually the element name).
   *
   * @return Tooltip text
   */
  public String getTooltip() {
    return this.tooltip.getText();
  }

  /**
   * Gets the ImageView associated with the cell.
   *
   * @return The current ImageView (can be null if no image is present)
   */
  public ImageView getImageView() {
    return this.imageView;
  }

  /**
   * Sets the ImageView to be associated with the cell.
   *
   * @param imageView The ImageView to set
   */
  public void setImageView(ImageView imageView) {
    this.imageView = imageView;
  }
}
