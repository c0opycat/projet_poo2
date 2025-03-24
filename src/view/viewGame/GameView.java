package view.viewGame;

import java.util.ArrayList;

import javafx.scene.control.Button;
import view.BorderWithButtons;
import view.ButtonMenu;
import view.viewGame.viewCommand.viewMenuCommand.QuitView;
import view.viewGame.viewCommand.viewMenuCommand.SaveView;

public class GameView extends BorderWithButtons{
    public GameView()
    {
        super();
        
        this.addTitle("Game");

        //Ajouter la FrameGame
    }

    //Set the bottom of the pane with buttons
    public void setButtons()
    {
        ArrayList<Button> buttons = new ArrayList<>();
        
        ButtonMenu menu = new ButtonMenu(this.getMainScene());
        SaveView save = new SaveView();
        QuitView quit = new QuitView();

        buttons.add(menu);
        buttons.add(save);
        buttons.add(quit);

        this.addButtons(buttons);
    }
}
