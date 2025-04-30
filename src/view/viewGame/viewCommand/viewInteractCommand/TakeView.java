package view.viewGame.viewCommand.viewInteractCommand;

import controller.controllerGame.controllerCommand.controllerInterractCommand.TakeController;
import java.awt.Point;
import javafx.scene.control.Button;
import view.Lang;
import view.viewGame.GameView;

public class TakeView {

  private final Lang lang = new Lang();
  private final TakeController takeController;
  private final GameView gameView;

  public TakeView(GameView gameView) {
    this.takeController = new TakeController(gameView, this);
    this.gameView = gameView;
  }

  public TakeController getTakeController() {
    return this.takeController;
  }

  public GameView getGameView() {
    return this.gameView;
  }

  public Lang getLang() {
    return this.lang;
  }

  public Button takeViewButton(String elem, int ind) {
    Button takeButton = new Button();
    this.getLang().setButtonLang(takeButton, "Prendre", "Take");
    takeButton.getStyleClass().add("button-Commande");

    int containerX = this.getGameView().getContainerView().getX();
    int containerY = this.getGameView().getContainerView().getY();
    Point containerPoint = new Point(containerX, containerY);

    takeButton.setOnAction(e -> {
      this.getTakeController().setTakeModel(String.valueOf(ind));
      if (this.getTakeController().execute(containerPoint)) {
        this.getGameView().getContainerView().updateContainerView(false);
      }
    });

    return takeButton;
  }
}
