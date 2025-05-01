package view.viewGame.viewCommand.viewInteractCommand;

import controller.controllerGame.controllerCommand.controllerInterractCommand.TakeController;
import java.awt.Point;
import javafx.scene.control.Button;
import view.Lang;
import view.viewGame.GameView;

/**
 * The view class for handling the take command in the game.
 * Provides functionality to create take buttons and manage interactions.
 * @author L. Cooper
 * @author A. Bertrand-Bernard
 */
public class TakeView {

  private final Lang lang = new Lang();
  private final TakeController takeController;
  private final GameView gameView;

  /**
   * Constructs a new TakeView instance.
   *
   * @param gameView the GameView instance associated with this TakeView.
   */
  public TakeView(GameView gameView) {
    this.takeController = new TakeController(gameView, this);
    this.gameView = gameView;
  }

  /**
   * Retrieves the take controller for this view.
   *
   * @return the TakeController instance.
   */
  public TakeController getTakeController() {
    return this.takeController;
  }

  /**
   * Retrieves the game view associated with this TakeView.
   *
   * @return the GameView instance.
   */
  public GameView getGameView() {
    return this.gameView;
  }

  /**
   * Retrieves the language manager for this view.
   *
   * @return the Lang instance.
   */
  public Lang getLang() {
    return this.lang;
  }

  /**
   * Creates a take button for taking items.
   *
   * @param ind the index of the item to take.
   * @return the created Button instance.
   */
  public Button takeViewButton(int ind) {
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
