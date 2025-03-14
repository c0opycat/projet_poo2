package view.viewMenu;

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
import javafx.scene.layout.VBox;
import view.BorderWithButtons;

public class Menu extends BorderWithButtons{
    
    public Menu()
    {
        super();

        this.addcomp();

    }

    private void addcomp()
    {
        VBox vb = new VBox();

        Label title = new Label("Menu");

        Button jouer = new Button("Play");
        Button cfg = new Button("Config");
        Button edit = new Button("Editor");
        Button hof = new Button("Hall of Fame");


        //style du menu
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 34px; -fx-text-fill: #373737");

        this.getStylesheets().add("file:../resources/style.css");

        //Met tous les button a la meme taille
        double buttonWidth = 100;
        double buttonHeight = 35;
        jouer.setPrefSize(buttonWidth, buttonHeight);
        cfg.setPrefSize(buttonWidth, buttonHeight);
        edit.setPrefSize(buttonWidth, buttonHeight);
        hof.setPrefSize(buttonWidth, buttonHeight);


        vb.getChildren().addAll(jouer, cfg, edit, hof);

        vb.setAlignment(Pos.CENTER);

        VBox.setMargin(jouer, new Insets(0, 10, 10, 10));
        VBox.setMargin(cfg, new Insets(10));
        VBox.setMargin(edit, new Insets(10));
        VBox.setMargin(hof, new Insets(10));

        this.setCenter(vb);
        this.setTop(title);

        BorderPane.setAlignment(vb, Pos.CENTER);
        BorderPane.setAlignment(title, Pos.CENTER);
        BorderPane.setMargin(title, new Insets(75, 0, 0, 0));

        Image backgroundImage = new Image("file:../resources/assets/post_apocalyptic_city_background_600x600_new.jpg");
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.setBackground(new Background(background));




    }

}
