package view.viewMenu;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import view.BorderWithButtons;
import view.MainScene;
import view.viewConfig.ConfigView;
import view.viewGame.GameView;
import view.viewGame.viewCommand.viewMenuCommand.QuitView;
import view.viewHallOfFame.HOFView;

public class Menu extends BorderWithButtons{
    


    public Menu()
    {
        super();

        this.addTitle("Menu");
        this.setButtons();
        this.addcomp();
    }

    //Add a quit button
    private void setButtons()
    {
        ArrayList<Button> buttons = new ArrayList<>();

        QuitView quit = new QuitView();
        buttons.add(quit);

        this.addButtons(buttons);
    }


    private void addcomp()
    {

        VBox vb = new VBox();

        Button jouer = new Button("Play");
        Button cfg = new Button("Config");
        Button edit = new Button("Editor");
        Button hof = new Button("Hall of Fame");

        //style du menu
        
        Image backgroundImage = new Image("file:../resources/assets/post_apocalyptic_city_background_600x600_new.jpg");
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.setBackground(new Background(background));

        //Met tous les button a la meme taille
        double buttonWidth = 150;
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

    }

    private Button getPlay(){
        return (Button) ((VBox)this.getCenter()).getChildren().get(0);
    }

    private Button getConfig(){
        return (Button) ((VBox)this.getCenter()).getChildren().get(1);
    }

    private Button getEditor(){
        return (Button) ((VBox)this.getCenter()).getChildren().get(2);
    }

    private Button getHOF(){
        return (Button) ((VBox)this.getCenter()).getChildren().get(3);
    }

    public void addHandlers()
    {
        MainScene scene = this.getMainScene();

        getPlay().setOnAction(e -> {
            GameView gameView = new GameView();
            scene.setContent(gameView);
            gameView.setButtons();
        });

        getConfig().setOnAction(e -> {
            scene.setContent(new ConfigView());
        });

        getEditor().setOnAction(e -> {
        });

        getHOF().setOnAction(e -> {
            HOFView hofView = new HOFView();
            scene.setContent(hofView);
            hofView.setButtons();
        });
    }

}
