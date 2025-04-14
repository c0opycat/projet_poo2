package view.viewLocation;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class LocationView extends GridPane{
    

    public LocationView()
    {
        super();

        //a supp
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 6; j++)
            {
                this.add(new Label("(" + i + "," + j + ")"), i, j);
            }
        }
    }

    public void setContent()
    {
        //Récupérer le contenu par le controller
    }

    public void takeExit()
    {
        //Passer au niveau précédent/suivant
        //Repérer si c'est une exit avec les coordonnées
    }
}
