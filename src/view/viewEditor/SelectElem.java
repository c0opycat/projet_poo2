package view.viewEditor;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

/**
 * A GridPane displaying a selection of draggable items (e.g. for use in a level
 * editor).
 * Each item is represented by an image and associated element name.
 * Items can be dragged (but not dropped) from this panel.
 * 
 * @author C. Besançon
 */
public class SelectElem extends GridPane {

  /** preferred height - fixed height of a grid square */
  private double prefHeight = 100;
  /** preferred width - fixed width of a grid square */
  private double prefWidth = 100;
  /** nomsItems - list of types of items for tabs */
  private ArrayList<String> nomsItems;

  ///Public///
  /**
   * Constructs a selection panel displaying items in a grid layout.
   * 
   * @param listItem List of element names (used for both display and image
   *                 association)
   * @param col      Number of columns to display (minimum 1)
   */
  public SelectElem(ArrayList<String> listItem, int col) {
    super();
    this.nomsItems = listItem;

    if (col < 1) {
      System.err.println("Erreur dans selectElem nombre de colonne null ou négative");
    } else {
      int nbCol = col - 1;

      this.addItemsToTypeElem(nbCol);

      this.setHgap(1);
      this.setVgap(1);
      this.setAlignment(Pos.CENTER);
    }
  }

  ///Private///
  /**
   * Adds all items to the grid layout as draggable images.
   * Each image corresponds to an element name from the list.
   * 
   * @param nbCol Number of columns to arrange items across
   */
  private void addItemsToTypeElem(int nbCol) {
    int nbItems = nomsItems.size();

    int j = 0, k = 0;

    for (int i = 0; i < nbItems; i++) {
      final String imageName = nomsItems.get(i);
      final String elemName = nomsItems.get(i);
      // Drag uniquement (pas de drop ici)
      ViewImage item = new ViewImage();
      item.createDraggableImage(
          imageName,
          elemName,
          this.prefHeight,
          this.prefWidth);
      this.add(item, j, k);
      j = (j + 1) % nbCol;
      if (j == 0)
        k++;
    }
  }
}
