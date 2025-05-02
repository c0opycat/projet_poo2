package view.viewGame.viewCommand.viewMenuCommand;

import controller.controllerGame.controllerCommand.controllerMenuCommand.PauseController;
import java.util.ArrayList;
import javafx.scene.control.Button;
import view.Lang;
import view.MyAlert;
import view.viewGame.GameView;

/**
 * The PauseView class represents a pause button in the game interface.
 * This class extends Button to provide pause functionality for the game.
 * When pressed, it pauses the game and displays a dialog that allows the player
 * to resume when ready. The pause state is managed through its controller.
 * @author L. Cooper
 */
public class PauseView extends Button {

  /** The controller that manages the pause functionality. */
  private final PauseController pauseController;

  /** Reference to the main game view for context and access to game state. */
  private final GameView gameView;

  /** Language handler for internationalization support. */
  private final Lang lang = new Lang();

  /**
   * Constructs a new PauseView with the specified game view.
   * Initializes the pause button with appropriate text and an action handler
   * that triggers the pause functionality when clicked. The button is linked
   * to its controller which manages the actual pause/resume logic.
   *
   * @param gameView the game view to associate with this pause button
   */
  public PauseView(GameView gameView) {
    super();
    this.gameView = gameView;
    this.pauseController = new PauseController(gameView, this);

    this.setText("Pause");
    this.setOnAction(e -> {
        this.getPauseController().execute();
        this.showPauseAlert();
        e.consume();
      });
  }

  /**
   * Gets the pause controller associated with this view.
   *
   * @return the PauseController for this pause button
   */
  public PauseController getPauseController() {
    return this.pauseController;
  }

  /**
   * Gets the game view associated with this pause button.
   *
   * @return the GameView instance
   */
  public GameView getGameView() {
    return this.gameView;
  }

  /**
   * Gets the language handler for this view.
   *
   * @return the Lang object used for text internationalization
   */
  public Lang getLang() {
    return this.lang;
  }

  /**
   * Displays a pause alert dialog.
   * Creates and shows a modal dialog informing the user that the game is paused.
   * The dialog cannot be closed except by clicking the "Resume" button, which
   * will unpause the game and continue gameplay. Text is displayed in the
   * appropriate language based on current settings.
   */
  public void showPauseAlert() {
    String title = this.getLang().getCurr_lang().equals("EN")
      ? "Game paused"
      : "Jeu en pause";
    String header = this.getLang().getCurr_lang().equals("EN")
      ? "The game is paused."
      : "Le jeu est en pause.";
    MyAlert pauseAlert = new MyAlert(title, header, null);

    ArrayList<String> optionList = new ArrayList<>();

    String resumeOption = this.getLang().getCurr_lang().equals("EN")
      ? "Resume"
      : "Reprendre";

    optionList.add(resumeOption);

    String choice = pauseAlert.showChoiceAlertNotCloseable(
      pauseAlert.createButtonTypes(optionList)
    );

    if (choice != null) {
      this.getPauseController().execute();
    }
  }
}
