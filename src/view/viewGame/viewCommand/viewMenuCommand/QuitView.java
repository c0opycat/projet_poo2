package view.viewGame.viewCommand.viewMenuCommand;

import java.util.Optional;

import controller.controllerGame.controllerCommand.QuitController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import view.viewGame.GameView;
import view.viewGameOver.GameOverView;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class QuitView extends Button{
    private final QuitController quitController;

    public QuitView(GameView gameView){
        super("Quit");

        this.quitController = new QuitController(this, gameView);

        this.setOnAction(e -> {

            //Creation of a dialog to confirm the exit of the application. 
            Alert quitAlert = new Alert(AlertType.CONFIRMATION);
            quitAlert.setTitle("Exit game");
            quitAlert.setContentText("You are about to forfeit the game.");

            //Creation of the options (because the cancel one was in French).
            ButtonType bt1 = new ButtonType("OK");
            ButtonType bt2 = new ButtonType("Cancel");

            quitAlert.getButtonTypes().setAll(bt1, bt2);

            Optional<ButtonType> choice = quitAlert.showAndWait();
            if(choice.get() == bt1)
            {
                //Exit the application if the user choose OK.
                this.getQuitController().forfeit();

                GameOverView goView = new GameOverView(gameView);
                gameView.getMainScene().setContent(goView);
                goView.setButtons();
            }
            else
            {
                //Close the dialog otherwise.
                quitAlert.close();
            }
        });
    }

    public QuitController getQuitController()
    {
        return this.quitController;
    }
}
