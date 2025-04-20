package view;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class FrameGame extends GridPane {
    private double prefHeight = 60;
    private double prefWidth = 60;
    
    public FrameGame(int col, int row)
    {
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
    public void addCellsToFrame() {
        for (Node node : this.getChildren()) {
                Cell cell = (Cell)node;
                cell.addCellDraggable(this.prefHeight, this.prefWidth);
            }
    }

}

