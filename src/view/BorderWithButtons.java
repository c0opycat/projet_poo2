package view;

import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

//Main pane.
//Subclass of BorderPane that has buttons at the bottom.
public class BorderWithButtons extends BorderPane{
    //Default constructor that place a quit button at the bottom of the pane.
    public BorderWithButtons()
    {
        super();

        this.addButtons(new ArrayList<>());
    }

    //Constructor that takes a list of buttons and place them and a quit button at the bottom of the pane.
    //Hypothesis : the list is not null (but it can be empty)
    public BorderWithButtons(ArrayList<Button> buttons)
    {
        super();

        this.addButtons(buttons);
    }

    //Returns the quit button
    public Button getQuitButton()
    {
        return (Button)((HBox)this.getBottom()).getChildren().getLast();
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

    
    //Add each button of the list and a quit button at the bottom of the pane.
    private void addButtons(ArrayList<Button> buttons)
    {
        HBox buttonsBox = new HBox();
        
        //Adding a quit button in the list
        Button quitButton = new Button("Quit");
        buttons.addLast(quitButton);

        quitButton.setOnAction(e -> {
            //Creation of a dialog to confirm the exit of the application. 
            Alert quitAlert = new Alert(AlertType.CONFIRMATION);
            quitAlert.setTitle("Exit game");
            quitAlert.setContentText("You are about to quit the game.");

            //Creation of the options (because the cancel one was in French).
            ButtonType bt1 = new ButtonType("OK");
            ButtonType bt2 = new ButtonType("Cancel");

            quitAlert.getButtonTypes().setAll(bt1, bt2);

            Optional<ButtonType> choice = quitAlert.showAndWait();
            if(choice.get() == bt1)
            {
                //Exit the application if the user choose OK.
                Platform.exit();
            }
            else
            {
                //Close the dialog otherwise.
                quitAlert.close();
            }
        });

        //Adding each button of the list
        for(Button button : buttons)
        {
            buttonsBox.getChildren().add(button);
            HBox.setMargin(button, new Insets(5));
        }

        buttonsBox.setAlignment(Pos.CENTER_RIGHT);
        this.setBottom(buttonsBox);
    }
}
