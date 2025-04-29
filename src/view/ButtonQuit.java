package view;

import javafx.application.Platform;
import javafx.scene.control.Button;

public class ButtonQuit extends Button {

  private Lang lang = new Lang();

  public ButtonQuit() {
    super();
    lang.setButtonLang(this, "Quitter", "Quit");
    this.setOnAction(e -> {
        //Creation of a dialog to confirm the exit of the application.
        MyAlert quitAlert = new MyAlert(
          "Exit game",
          null,
          "You are about to quit the application."
        );

        boolean isOk = quitAlert.showConfirmation();

        if (isOk) {
          //ExitModel the application if the user choose OK.
          Platform.exit();
        }
      });
  }
}
