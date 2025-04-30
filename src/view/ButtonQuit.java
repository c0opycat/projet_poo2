package view;

import javafx.application.Platform;
import javafx.scene.control.Button;

/**
 * A custom button that allows the user to quit the application.
 * Displays a confirmation dialog before exiting.
 * @author L. Cooper
 */
public class ButtonQuit extends Button {

  /**
   * The language object used to set the button's text based on the current language.
   */
  private Lang lang = new Lang();

  /**
   * Constructs a new ButtonQuit instance.
   * Sets the button's text based on the language and attaches an action handler
   * to confirm and exit the application.
   */
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
