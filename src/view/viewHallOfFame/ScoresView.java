package view.viewHallOfFame;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ScoresView extends VBox{
    public ScoresView()
    {
        super();

        this.addScores();
    }

    private void addScores()
    {
        //Récupérer les meilleurs scores (avec pseudo ?)
        ArrayList<Integer> scores = new ArrayList<>(); //Exemple

        scores.add(15);
        scores.add(11);
        scores.add(9);
        scores.add(7);
        scores.add(5);

        int ind = 1; //Place of the score 
        
        for(Integer score : scores)
        {
            
            if(ind > 10) //Peut être pas nécessaire selon les scores dans le model
            {
                break;
            }

            HBox scoreBox = new HBox();

            Label indLabel = new Label("#" + ind + " : ");
            Region spring = new Region();
            Label scoreLabel = new Label("" + score);

            scoreBox.getChildren().addAll(indLabel, spring, scoreLabel);

            //Style
            scoreBox.setMaxWidth(100);
            HBox.setHgrow(spring, Priority.ALWAYS);
            indLabel.setAlignment(Pos.CENTER_LEFT);
            scoreLabel.setAlignment(Pos.CENTER_RIGHT);
            HBox.setMargin(scoreLabel, new Insets(2));
            scoreBox.setAlignment(Pos.CENTER);

            this.getChildren().add(scoreBox);

            ind++;
        }
    }
}
