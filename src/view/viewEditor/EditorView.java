package view.viewEditor;

import java.util.ArrayList;

import javafx.scene.control.Button;
import view.BorderWithButtons;
import view.ButtonMenu;
import view.ButtonQuit;

public class EditorView extends BorderWithButtons{
    
    public EditorView()
    {
        super();

        this.addComp();
    }

    private void addComp()
    {
        EditorPane ep = new EditorPane();

        this.setContent(ep);
        this.addBackground("backgroundEditor.png");
    }

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
