package view.viewGame.viewCommand.viewMenuCommand;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class SaveView extends Button{
    public SaveView()
    {
        super("Save");

        this.setOnAction(e -> {
            //Creation of a dialog to confirm saving.
            Alert quitAlert = new Alert(AlertType.CONFIRMATION);
            quitAlert.setTitle("Save");
            quitAlert.setContentText("Do you want to save ?");

            //Creation of the options (because the cancel one was in French).
            ButtonType bt1 = new ButtonType("Yes");
            ButtonType bt2 = new ButtonType("Cancel");

            quitAlert.getButtonTypes().setAll(bt1, bt2);

            Optional<ButtonType> choice = quitAlert.showAndWait();
            if(choice.get() == bt1)
            {
                //Save if the user choose Yes
                
                //Save
            }
            else
            {
                //Close the dialog otherwise.
                quitAlert.close();
            }
        });
    }
}
