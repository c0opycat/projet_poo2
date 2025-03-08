package view.viewGameOver;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import view.BorderWithButtons;

//Game Over pane, that is shown when a game is finished.
public class GameOverView extends BorderWithButtons{
    public GameOverView()
    {
        super(createButtons());

        Label title = new Label("GAME OVER");

        EndGameInfos infos = new EndGameInfos();

        this.setTop(title);
        this.setCenter(infos);

        //Style
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 20px");
        title.setPadding(new Insets(10));
        title.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
        BorderPane.setAlignment(title, Pos.CENTER);
        infos.setAlignment(Pos.TOP_CENTER);
        BorderPane.setMargin(title, new Insets(10));
        BorderPane.setMargin(infos, new Insets(10));

    }

    private static ArrayList<Button> createButtons()
    {
        ArrayList<Button> buttons = new ArrayList<>();
        
        Button menu = new Button("Menu");
        Button hof = new Button("Hall of Fame");
        Button newGame = new Button("New Game");

        buttons.add(menu);
        buttons.add(hof);
        buttons.add(newGame);

        return buttons;
    }

}
