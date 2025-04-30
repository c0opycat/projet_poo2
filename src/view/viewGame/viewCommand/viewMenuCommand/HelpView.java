package view.viewGame.viewCommand.viewMenuCommand;

import controller.controllerGame.controllerCommand.controllerMenuCommand.HelpController;
import javafx.scene.control.Button;
import view.Lang;
import view.viewGame.GameView;

/**
 * The view class for the help button in the game menu.
 * Extends Button and provides functionality to display help information.
 * @author L. Cooper
 */
public class HelpView extends Button {

  private final HelpController helpController;
  private Lang lang = new Lang();

  /**
   * Constructs a new HelpView instance.
   *
   * @param gameView the GameView instance associated with this HelpView.
   */
  public HelpView(GameView gameView) {
    super();
    lang.setButtonLang(this, "Aide", "Help");
    this.helpController = new HelpController(this, gameView);

    this.setOnAction(e -> {
        this.getHelpController().help();

        e.consume();
      });
  }

  /**
   * Retrieves the help controller for this view.
   *
   * @return the HelpController instance.
   */
  public HelpController getHelpController() {
    return this.helpController;
  }
}
