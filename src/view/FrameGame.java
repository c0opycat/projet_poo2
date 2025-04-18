package view;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;

public class FrameGame extends GridPane {
    private double prefHeight = 50;
    private double prefWidth = 50;
    
    public FrameGame(int col, int row)
    {
        super();

        int nbCol = col - 1;
        int nbRow = row - 1;
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
    
                gridTarget.add(cell, i, j);
            }
        }
    }

}

