package view.viewConfig;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import view.mainDisplay.MainDisplay;
import view.viewMenu.Menu;

public class ConfigView extends VBox{
    
    public ConfigView(MainDisplay mainDisplay)
    {
        super();

        this.addComp(mainDisplay);
    }

    private void addComp(MainDisplay mainDisplay)
    {

        Button lang = new Button("Language");
        Button vol = new Button("Volume");
        Button res = new Button("Resolution");
        Button back = new Button("Back");

        this.setAlignment(Pos.CENTER);
        VBox.setMargin(lang, new Insets(0, 10, 10, 10));
        VBox.setMargin(vol, new Insets(10));
        VBox.setMargin(res, new Insets(10));
        VBox.setMargin(back, new Insets(10));

        this.getChildren().addAll(lang, vol, res, back);

        back.setOnAction(e -> { 
            mainDisplay.setCenter(new Menu(mainDisplay));
            e.consume();
        });

    }

}
