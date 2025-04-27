package view.viewEditor;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import view.Cell;

/**
 * FrameGame corresponds to the modelGame board.
 * It inherits from a gridPane.
 *
 * @author C. Besançon
 */
public class FrameGame extends GridPane {

  /**preferred height - fixed height of a grid square */
  private double prefHeight = 60;
  /**preferred width - fixed width of a grid square */
  private double prefWidth = 60;

  //// Public ////

  /**
   * Constructor
   * @param col number of columns in the gridPane
   * @param row number of rows in the gridPane
   */
  public FrameGame(int col, int row) {
    super();
    int nbCol = col;
    int nbRow = row;
    // Définir la taille des colonnes
    for (int i = 0; i < col; i++) {
      ColumnConstraints colConst = new ColumnConstraints(prefWidth); // largeur fixe
      colConst.setHalignment(HPos.CENTER);
      this.getColumnConstraints().add(colConst);
    }

    // Définir la taille des lignes
    for (int i = 0; i < row; i++) {
      RowConstraints rowConst = new RowConstraints(prefHeight); // hauteur fixe
      rowConst.setValignment(VPos.CENTER);
      this.getRowConstraints().add(rowConst);
    }
    this.addStackPane(nbCol, nbRow);

    this.setHgap(1);
    this.setVgap(1);
    this.setAlignment(Pos.CENTER);
  }

  public FrameGame() {}

  /**
   * Added cells in which you can drag and drop and delete elements
   */
  public void setCellsDraggableInFrame() {
    for (Node node : this.getChildren()) {
      Cell cell = (Cell) node;
      cell.setCellDraggable();
    }
  }

  //// Private ////
  /**
   * adds the default cells in the gridPane (frameGame) by setting the sizes of the boxes.
   * @param nbCol number of columns in the gridPane
   * @param nbRow number of rows in the gridPane
   */
  private void addStackPane(int nbCol, int nbRow) {
    for (int i = 0; i < nbCol; i++) {
      for (int j = 0; j < nbRow; j++) {
        StackPane cell = new Cell();
        cell.setPrefSize(this.prefHeight, this.prefWidth);
        cell.setMaxSize(this.prefHeight, this.prefWidth);
        cell.setMinSize(this.prefHeight, this.prefWidth);
        this.add(cell, i, j);
      }
    }
  }
}
