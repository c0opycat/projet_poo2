

import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import view.View;
import view.viewHallOfFame.HOFView;

public class Main extends Application{
    @Override
    public void start (Stage stage) throws Exception{
        
        //Scene
        final View scene = new View(new HOFView(), 300, 300);

        stage.setScene(scene);
        stage.setTitle("OOP 2 Project");
        stage.show();

        //Add an eventhandler to the quit Button of the root of the scene to exit the application if the button is clicked.
        scene.getQuitButton().setOnAction(e -> {
        
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
    }
    
    public static void main (String args []) {
		launch(args);
    }    
}
