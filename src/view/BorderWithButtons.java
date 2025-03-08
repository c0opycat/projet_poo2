package view;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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

    
    //Add each button of the list and a quit button at the bottom of the pane.
    private void addButtons(ArrayList<Button> buttons)
    {
        HBox buttonsBox = new HBox();
        
        //Adding a quit button in the list
        Button quitButton = new Button("Quit");
        buttons.addLast(quitButton);

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
