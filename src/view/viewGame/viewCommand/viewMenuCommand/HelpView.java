package view.viewGame.viewCommand.viewMenuCommand;

import controller.controllerGame.controllerCommand.controllerMenuCommand.HelpController;
import javafx.scene.control.Button;
import view.viewGame.GameView;

public class HelpView extends Button {

  private final HelpController helpController;

  public HelpView(GameView gameView) {
    super("Help");
    this.helpController = new HelpController(this, gameView);

    this.setOnAction(e -> {
        this.getHelpController().help();

        e.consume();
      });
  }

  public HelpController getHelpController() {
    return this.helpController;
  }
}
