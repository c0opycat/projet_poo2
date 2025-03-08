package view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

//Main scene
public class View extends Scene{
    public View(Parent root, double width, double height)
    {
        super(root, width, height);
    }

    //Returns the quit button of the root
    public Button getQuitButton()
    {
        return (Button)((BorderWithButtons)this.getRoot()).getQuitButton();
    }
}
