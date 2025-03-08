package view.viewGameOver;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class EndGameInfos extends VBox{
    public EndGameInfos()
    {
        super();

        this.addElements();
    }

    private void addElements()
    {
        //Get the end of the game message
        //Récupérer ds Message le message de victoire ou défaite dans la bonne langue.
        String endText = "You died !"; //Exemple
        Label endLabel = new Label(endText);

        Region spring = new Region();

        //Get the score
        Integer score = 10; //Exemple
        Label scoreLabel = new Label("score : " + score);

        this.getChildren().addAll(endLabel, spring, scoreLabel);

        //Style
        spring.setMaxHeight(100);
        VBox.setVgrow(spring, Priority.ALWAYS);
        endLabel.setStyle("-fx-font-weight: bold;");
        endLabel.setAlignment(Pos.CENTER);
        scoreLabel.setAlignment(Pos.CENTER);
        VBox.setMargin(endLabel, new Insets(10));
        VBox.setMargin(scoreLabel, new Insets(10));
    }
}
