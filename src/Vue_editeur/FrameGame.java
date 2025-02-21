package Vue_editeur;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.beans.binding.Bindings;

public class FrameGame extends GridPane {
    public FrameGame(int col, int row)
    {
        super();

        int nbCol = col - 1;
        int nbRow = row - 1;
        this.addLabels(nbCol, nbRow);

        this.setHgap(1);
        this.setVgap(1);
        this.setAlignment(Pos.CENTER);

        this.defineColRow(30, nbCol, nbRow);

        //this.resetCell(4, 4);
    }

    private void setCell(String val, int x, int y, String numberColor)
    {

        //Style du label en CSS
        String label_style = "-fx-background-color: " + numberColor;

        Label to_add = new Label(val);
        
        //Style
        to_add.setStyle(label_style);
        //taille des cases
        to_add.setMaxHeight(Double.MAX_VALUE);
        to_add.setMaxWidth(Double.MAX_VALUE);
        to_add.setAlignment(Pos.CENTER);

        this.add(to_add, x, y);
    }

    public void setCell(char val, int x, int y, String numberColor)
    {
        String label_text = "" + val;
        
        this.setCell(label_text, x, y, numberColor);
    }

    public void resetCell(int x, int y)
    {
        this.setCell(" ", x, y, "#8A2BE2");
    }

    private void addLabels(int nbCol, int nbRow)
    {
        /*/
        //1e colonne à gauche
        for(int i = 1; i <= nbRow; i++)
        {
            this.setCell("" + i, 0, i, "transparent");
        }

        //1e ligne en haut
        char c = 'A';
        for(int i = 1; i <= nbCol; i++)
        {
            this.setCell(c, i, 0, "transparent");
            c++;
        }
        */

        //Remplissage de la grid
        for(int i = 0; i <= nbCol; i++)
        {
            for(int j = 0; j <= nbRow; j++)
            this.setCell(' ', i, j, "#8A2BE2");
        }
    }

    private void defineColRow(int size, int nbCol, int nbRow)
    {
        for (int i = 0; i <= nbCol; i++)
        {
            this.getColumnConstraints().add(new ColumnConstraints(size)); 
        }
        
        for (int i = 0; i <= nbRow; i++)
        {
            this.getRowConstraints().add(new RowConstraints(size));
        }
        
    }
}

