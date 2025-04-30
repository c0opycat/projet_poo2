package view.viewGame.viewCommand.viewMenuCommand;

import controller.controllerGame.controllerCommand.controllerMenuCommand.QuitController;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import view.Lang;
import view.viewGame.GameView;
import view.viewGameOver.GameOverView;

/**
 * View class for the "Quit" button.
 * This class handles the graphical representation and logic for quitting the game.
 * @author L. Cooper
 */
public class QuitView extends Button {

  private final QuitController quitController;
  private Lang lang = new Lang();

  /**
   * Constructs a QuitView button with the specified GameView.
   * Sets up the button's appearance and action logic for quitting the game.
   *
   * @param gameView the GameView instance associated with this button
   */
  public QuitView(GameView gameView) {
    super();
    lang.setButtonLang(this, "Quitter", "Quit");
    this.quitController = new QuitController(this, gameView);

    this.setOnAction(e -> {
        //Creation of a dialog to confirm the exit of the application.
        Alert quitAlert = new Alert(AlertType.CONFIRMATION);

        quitAlert.setHeaderText(
          lang.getCurr_lang().equals("EN") ? "Exit game" : "Quitter la partie"
        );
        quitAlert.setContentText(
          lang.getCurr_lang().equals("EN")
            ? "You are about to forfeit the game."
            : "Vous allez déclarer forfait."
        );
        //Creation of the options (because the cancel one was in French).
        ButtonType bt1 = new ButtonType("OK");
        ButtonType bt2 = new ButtonType(
          lang.getCurr_lang().equals("EN") ? "Cancel" : "Annuler"
        );

        quitAlert.getButtonTypes().setAll(bt1, bt2);

        Optional<ButtonType> choice = quitAlert.showAndWait();
        if (choice.get() == bt1) {
          //ExitModel the application if the user choose OK.
          this.getQuitController().forfeit();
          gameView.getGameController().end();
          GameOverView goView = new GameOverView(gameView);
          gameView.getMainScene().setContent(goView);
          goView.setButtons();
        } else {
          //Close the dialog otherwise.
          quitAlert.close();
        }
      });
  }

  /**
   * Gets the QuitController instance associated with this button.
   *
   * @return the QuitController instance
   */
  public QuitController getQuitController() {
    return this.quitController;
  }
}
