package view;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class FrameGame extends GridPane {
    private double prefHeight = 50;
    private double prefWidth = 50;
    
    public FrameGame(int col, int row)
    {
        super();

        int nbCol = col - 1;
        int nbRow = row - 1;
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

    private void addStackPane(int nbCol, int nbRow)
    {
        //Remplissage de la grid
        for(int i = 0; i < nbCol; i++)
        {
            for(int j = 0; j < nbRow; j++)
            {
                StackPane cell = new Cell();
                cell.setPrefSize(this.prefHeight, this.prefWidth);
                cell.setMaxSize(this.prefHeight, this.prefWidth);
                cell.setMinSize(this.prefHeight, this.prefWidth);
                this.add(cell, i, j);
            }
        }
    }


    //Ajout de cellule dans lesquels on peut faire du drag and drop, et supprimer des elements
    public void addCellsToFrame(GridPane gridTarget, int nbCol, int nbRow) {
        for (int i = 0; i < nbCol; i++) {
            for (int j = 0; j < nbRow; j++) {
                Cell cell = new Cell();
                cell.addCellDraggable(this.prefHeight, this.prefWidth, nbCol, nbRow);
                cell.setPrefSize(this.prefHeight, this.prefWidth);
                cell.setMaxSize(this.prefHeight, this.prefWidth);
                cell.setMinSize(this.prefHeight, this.prefWidth);
                gridTarget.add(cell, i, j);
            }
        }
    }

}

