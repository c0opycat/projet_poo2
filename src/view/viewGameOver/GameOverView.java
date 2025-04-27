package view.viewGameOver;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import view.BorderWithButtons;
import view.ButtonMenu;
import view.ButtonQuit;
import view.viewGame.GameView;

//Game Over pane, that is shown when a modelGame is finished.
public class GameOverView extends BorderWithButtons{

    public GameOverView(GameView gameView)
    {
        super();

        this.addTitle("Game Over");

        EndGameInfos infos = new EndGameInfos(gameView);

        this.setCenter(infos);

        //Style
        infos.setAlignment(Pos.TOP_CENTER);
        BorderPane.setMargin(infos, new Insets(10));

    }

    //Set the bottom of the pane with buttons
    public void setButtons()
    {
        ArrayList<Button> buttons = new ArrayList<>();
        
        ButtonMenu menu = new ButtonMenu(this.getMainScene());
        ButtonQuit quit = new ButtonQuit();

        buttons.add(menu);
        buttons.add(quit);

        this.addButtons(buttons);
    }

}
