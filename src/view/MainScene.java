package view;

import javafx.scene.Scene;
import view.mainDisplay.MainDisplay;

public class MainScene extends Scene{
    public MainScene()
    {
        super(new MainDisplay(), 600, 600);
    }
}
