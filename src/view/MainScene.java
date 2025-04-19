package view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import view.viewMenu.Menu;

public class MainScene extends Scene{

    public MainScene(){
        //Taille d'origine 1024 * 768
        super(new Menu(), 1331, 998);
        ((Menu) this.getRoot()).addHandlers();
    }

    //Set the root of the Scene with a instance of a class extending BorderWithButtons
    public void setContent(Parent root)
    {
        this.setRoot(root);
    }
}
