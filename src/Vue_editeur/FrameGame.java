package Vue_editeur;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class FrameGame extends GridPane {
    public FrameGame()
    {
        super();

        this.addLabels(15);

        this.setHgap(3);
        this.setVgap(3);
        this.setAlignment(Pos.CENTER);

        this.setCell('o', 4, 4, "#FF0000");
        this.resetCell(4, 4);
    }

    private void setCell(String val, int x, int y, String numberColor)
    {
        //Style du label en CSS
        String label_style = "-fx-background-color: " + numberColor;

        Label to_add = new Label(val);
        
        //Style
        to_add.setStyle(label_style);
        to_add.setMinHeight(20.0);
        to_add.setMinWidth(20.0);
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

    private void addLabels(int size)
    {
        //1e colonne à gauche
        for(int i = 1; i <= size; i++)
        {
            this.setCell("" + i, 0, i, "transparent");
        }

        //1e ligne en haut
        char c = 'A';
        for(int i = 1; i <= size; i++)
        {
            this.setCell(c, i, 0, "transparent");
            c++;
        }

        //Remplissage de la grid
        for(int i = 1; i <= size; i++)
        {
            for(int j = 1; j <= size; j++)
            this.setCell(' ', i, j, "#8A2BE2");
        }
    }
}

