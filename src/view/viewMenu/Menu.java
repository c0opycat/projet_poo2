package view.viewMenu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
//import model.Main;
import view.mainDisplay.MainDisplay;
import view.viewConfig.ConfigView;
import view.viewHallOfFame.HOFView;

public class Menu extends VBox{
    


    public Menu(MainDisplay mainDisplay)
    {
        super();

        this.addcomp(mainDisplay);
    }


    private void addcomp(MainDisplay mainDisplay)
    {

        Label title = new Label("Menu");

        Button jouer = new Button("Play");
        Button cfg = new Button("Config");
        Button edit = new Button("Editor");
        Button hof = new Button("Hall of Fame");


        //style du menu
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 34px; -fx-text-fill: #373737");

        this.getStylesheets().add("file:../resources/style.css");

        //Met tous les button a la meme taille
        double buttonWidth = 150;
        double buttonHeight = 35;
        jouer.setPrefSize(buttonWidth, buttonHeight);
        cfg.setPrefSize(buttonWidth, buttonHeight);
        edit.setPrefSize(buttonWidth, buttonHeight);
        hof.setPrefSize(buttonWidth, buttonHeight);


        this.getChildren().addAll(jouer, cfg, edit, hof);

        this.setAlignment(Pos.CENTER);

        VBox.setMargin(jouer, new Insets(0, 10, 10, 10));
        VBox.setMargin(cfg, new Insets(10));
        VBox.setMargin(edit, new Insets(10));
        VBox.setMargin(hof, new Insets(10));

        BorderPane.setAlignment(title, Pos.CENTER);
        BorderPane.setMargin(title, new Insets(75, 0, 0, 0));

        //Listerner pour les boutons

        cfg.setOnAction(e -> { 
            mainDisplay.setCenter(new ConfigView());
            e.consume();
        });

        hof.setOnAction(e -> {
            mainDisplay.setCenter(new HOFView());
        });
        
    }


}
