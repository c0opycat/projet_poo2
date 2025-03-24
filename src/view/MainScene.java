package view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import view.viewMenu.Menu;

public class MainScene extends Scene{

    public MainScene(){
        super(new Menu(), 600, 600);
        ((Menu) this.getRoot()).addHandlers();
    }

    //Set the root of the Scene with a instance of a class extending BorderWithButtons
    public void setContent(Parent root)
    {
        this.setRoot(root);
    }
}
