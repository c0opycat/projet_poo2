package view;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import view.viewMenu.Menu;

public class ButtonMenu extends Button{
    public ButtonMenu(MainScene mainScene)
    {
        super("Menu");

        this.setOnAction(e -> {
            //Creation of a dialog to confirm the exit of the application. 
            Alert quitAlert = new Alert(AlertType.CONFIRMATION);
            quitAlert.setTitle("Go to the menu");
            quitAlert.setContentText("You are about to go to the menu.");

            //Creation of the options (because the cancel one was in French).
            ButtonType bt1 = new ButtonType("OK");
            ButtonType bt2 = new ButtonType("Cancel");

            quitAlert.getButtonTypes().setAll(bt1, bt2);

            Optional<ButtonType> choice = quitAlert.showAndWait();
            if(choice.get() == bt1)
            {
                //Exit the application if the user choose OK.
                Menu menu = new Menu();
                mainScene.setContent(menu);
                menu.addHandlers();
                
            }
            else
            {
                //Close the dialog otherwise.
                quitAlert.close();
            }
        });

            
    }
}
