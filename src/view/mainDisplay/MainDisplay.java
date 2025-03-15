package view.mainDisplay;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import view.BorderWithButtons;
import view.viewConfig.ConfigView;
import view.viewMenu.Menu;

public class MainDisplay extends BorderWithButtons{
    
    public MainDisplay()
    {
        super();

        this.init();
    }

    private void init(){

        Label title = new Label("Menu");

        this.getStylesheets().add("file:../resources/style.css");
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 34px; -fx-text-fill: #373737");

        this.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        BorderPane.setMargin(title, new Insets(75, 0, 0, 0));

        Image backgroundImage = new Image("file:../resources/assets/post_apocalyptic_city_background_600x600_new.jpg");
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.setBackground(new Background(background));

        Menu menu = new Menu(this);
        this.setCenter(menu);

    }

    public BorderWithButtons getBorderWithButtons()
    {
        return this;
    }

}
