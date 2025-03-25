package view;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

//Main pane.
//Subclass of BorderPane that has buttons at the bottom.
public class BorderWithButtons extends BorderPane{
    //Default constructor
    public BorderWithButtons()
    {
        super();
    }

    //Returns the scene
    public MainScene getMainScene()
    {
        return (MainScene)this.getScene();
    }


    public void setContent(Node cotent)
    {
        this.setCenter(cotent);
    }

    //Returns the button that has a specific text
    //Returns null and print a message if no button was found
    public Button getSpecButton(String buttonText)
    {
        Button res = null;

        ObservableList<Node> nodes = ((HBox)this.getBottom()).getChildren();
        for(Node node : nodes)
        {
            Button button = (Button)node;

            if(button.getText() == buttonText)
            {
                res = button;
                break;
            }
        }

        if(res == null)
        {
            System.err.println("Error getSpecButton : No button was found.");
        }

        return res;
    }

    public void addTitle(String title)
    {
        Label titleLabel = new Label(title);

        titleLabel.getStyleClass().add("title");

        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        BorderPane.setMargin(titleLabel, new Insets(75, 0, 0, 0));

        this.setTop(titleLabel);
    }

    
    //Add each button of the list and a quit button at the bottom of the pane.
    public void addButtons(ArrayList<? extends Button> buttons)
    {
        HBox buttonsBox = new HBox();

        //Adding each button of the list
        for(Button button : buttons)
        {
            buttonsBox.getChildren().add(button);
            HBox.setMargin(button, new Insets(5));
            button.setAlignment(Pos.CENTER_RIGHT);
        }

        buttonsBox.setAlignment(Pos.BOTTOM_RIGHT);
        this.getStylesheets().add("file:../resources/style.css");

        this.setBottom(buttonsBox);
    }

    public void addBackground(){
        Image backgroundImage = new Image("file:../resources/assets/background.jpeg");
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.setBackground(new Background(background));
    }
}
