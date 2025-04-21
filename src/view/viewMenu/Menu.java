package view.viewMenu;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import view.BorderWithButtons;
import view.ButtonQuit;
import view.MainScene;
import view.viewConfig.ConfigView;
import view.viewEditor.EditorView;
import view.viewGame.GameView;
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

        ButtonQuit quit = new ButtonQuit();
        buttons.add(quit);

        this.addButtons(buttons);
    }


    private void addcomp()
    {

        VBox vb = new VBox(20);

        Button jouer = new Button("Play");
        Button cfg = new Button("Config");
        Button edit = new Button("Editor");
        Button hof = new Button("Hall of Fame");

        //style du menu
        
       this.addBackground("background.jpeg");

        //Met tous les button a la meme taille
        double buttonWidth = 150;
        double buttonHeight = 35;
        jouer.setPrefSize(buttonWidth, buttonHeight);
        cfg.setPrefSize(buttonWidth, buttonHeight);
        edit.setPrefSize(buttonWidth, buttonHeight);
        hof.setPrefSize(buttonWidth, buttonHeight);

        vb.setMaxHeight(350);
        vb.setMaxWidth(450);

        vb.getChildren().addAll(jouer, cfg, edit, hof);

        vb.setAlignment(Pos.CENTER);

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
            ConfigView configView = new ConfigView();
            scene.setContent(configView);
            configView.setButtons();
        });

        getEditor().setOnAction(e -> {
            EditorView editorView = new EditorView();
            scene.setContent(editorView);
            editorView.setButtons();
        });

        getHOF().setOnAction(e -> {
            HOFView hofView = new HOFView();
            scene.setContent(hofView);
            hofView.setButtons();
        });
    }

}
