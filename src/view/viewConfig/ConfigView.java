package view.viewConfig;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import view.BorderWithButtons;
import view.ButtonMenu;
import view.viewGame.viewCommand.viewMenuCommand.QuitView;
import view.viewGame.viewCommand.viewMenuCommand.SaveView;

public class ConfigView extends BorderWithButtons{
    
    public ConfigView()
    {
        super();

        this.addComp();
    }

    private void addComp()
    {
        VBox vb = new VBox();

        KeybindConfigView kb = new KeybindConfigView();
        Button lang = new Button("Language");
        Button vol = new Button("Volume");
        Button res = new Button("Resolution");

        vb.setAlignment(Pos.CENTER);
        VBox.setMargin(lang, new Insets(0, 10, 10, 10));
        VBox.setMargin(vol, new Insets(10));
        VBox.setMargin(res, new Insets(10));

        vb.getChildren().addAll(kb, lang, vol, res);

        this.setContent(vb);
        this.addBackground();
    }


    public void setButtons()
    {
        ArrayList<Button> buttons = new ArrayList<>();

        ButtonMenu menu = new ButtonMenu(this.getMainScene());
        QuitView quit = new QuitView();
        SaveView save = new SaveView();

        buttons.add(menu);
        buttons.add(save);
        buttons.add(quit);

        this.addButtons(buttons);
    }
}
