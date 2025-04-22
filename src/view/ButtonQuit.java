package view;

import javafx.application.Platform;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class ButtonQuit extends Button {

  public ButtonQuit() {
    super("Quit");
    this.setOnAction(e -> {
        //Creation of a dialog to confirm the exit of the application.
        MyAlert quitAlert = new MyAlert(
          "Exit game",
          null,
          "You are about to quit the game."
        );

        boolean isOk = quitAlert.show(AlertType.CONFIRMATION);

        if (isOk) {
          //Exit the application if the user choose OK.
          Platform.exit();
        }
      });
  }
}
