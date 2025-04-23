package view;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import view.viewMenu.Menu;

/**
 * Button that redirects to the Menu after confirmation.
 *
 */
public class ButtonMenu extends Button {

  //// Public ////

  /**
   * Constructor
   * @param mainScene The scene that contains every pane.
   */
  public ButtonMenu(MainScene mainScene) {
    super("Menu");
    this.setOnAction(e -> {
        //Creation of a dialog to confirm the exit of the application.
        MyAlert menuAlert = new MyAlert(
          "Go to the menu",
          null,
          "You are about to go to the menu."
        );

        boolean isOk = menuAlert.show(AlertType.CONFIRMATION);

        if (isOk) {
          //Exit the application if the user choose OK.
          Menu menu = new Menu();
          mainScene.setContent(menu);
          menu.addHandlers();
        }
      });
  }
}
