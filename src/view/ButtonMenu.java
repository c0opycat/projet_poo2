package view;

import javafx.scene.control.Button;
import view.viewMenu.MenuView;

/**
 * Button that redirects to the Menu after confirmation.
 * @author L. Cooper
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
        MyAlert menuAlert = new MyAlert(
          "Go to the menu",
          null,
          "You are about to go to the menu."
        );

        boolean isOk = menuAlert.showConfirmation();

        if (isOk) {
          MenuView menu = new MenuView();
          mainScene.setContent(menu);
          menu.addHandlers();
        }
      });
  }
}
