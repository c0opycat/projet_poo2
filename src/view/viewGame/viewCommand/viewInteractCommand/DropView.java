package view.viewGame.viewCommand.viewInteractCommand;

import controller.controllerGame.controllerCommand.controllerInterractCommand.DropController;
import javafx.scene.control.Button;
import view.Lang;
import view.viewGame.GameView;

public class DropView extends Button {

  private final Lang lang = new Lang();
  private final DropController dropController;
  private final GameView gameView;

  public DropView(GameView gameView, int ind) {
    super();
    this.dropController = new DropController(gameView, this, ind);
    this.gameView = gameView;

    this.getLang().setButtonLang(this, "Lâcher", "Drop");
    this.getStyleClass().add("button-Commande");

    this.setOnAction(e -> {
        if (this.getDropController().execute()) {
          this.getGameView().getCurrentLocationView().updateItems();
          this.getGameView().getContainerView().updateContainerView(true);
        }
      });
  }

  public DropController getDropController() {
    return this.dropController;
  }

  public GameView getGameView() {
    return this.gameView;
  }

  public Lang getLang() {
    return this.lang;
  }
}
