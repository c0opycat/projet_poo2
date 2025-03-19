package view.viewConfig;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ConfigView extends VBox{
    
    public ConfigView()
    {
        super();

        this.addComp();
    }

    private void addComp()
    {

        Button lang = new Button("Language");
        Button vol = new Button("Volume");
        Button res = new Button("Resolution");

        this.setAlignment(Pos.CENTER);
        VBox.setMargin(lang, new Insets(0, 10, 10, 10));
        VBox.setMargin(vol, new Insets(10));
        VBox.setMargin(res, new Insets(10));

        this.getChildren().addAll(lang, vol, res);

        

    }

}
