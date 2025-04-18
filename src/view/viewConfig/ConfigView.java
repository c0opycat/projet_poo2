package view.viewConfig;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import view.BorderWithButtons;
import view.ButtonMenu;
import view.ButtonQuit;
import view.viewGame.viewCommand.viewMenuCommand.SaveView;

public class ConfigView extends BorderWithButtons{
    
    public ConfigView()
    {
        super();

        this.addComp();
    }

    private void addComp()
    {
        this.addTitle("Configuration");

        VBox vb = new VBox();

        KeybindConfigView kb = new KeybindConfigView();

        vb.setAlignment(Pos.CENTER);
 

        vb.getChildren().addAll(kb);

        this.setContent(vb);
        this.addBackground();
    }


    public void setButtons()
    {
        ArrayList<Button> buttons = new ArrayList<>();

        ButtonMenu menu = new ButtonMenu(this.getMainScene());
        ButtonQuit quit = new ButtonQuit();
        SaveView save = new SaveView();

        buttons.add(menu);
        buttons.add(save);
        buttons.add(quit);

        this.addButtons(buttons);
    }
}
