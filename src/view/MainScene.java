package view;

import javafx.scene.Scene;
import view.viewMenu.Menu;

public class MainScene extends Scene{
    public MainScene()
    {
        super(new Menu(), 600, 600);
    }
}
