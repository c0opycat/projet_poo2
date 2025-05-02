package view.viewGameOver;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import view.BorderWithButtons;
import view.ButtonMenu;
import view.ButtonQuit;
import view.Lang;
import view.viewGame.GameView;

/**
 * The GameOverView class represents the game over screen displayed when a game session ends.
 * This view extends BorderWithButtons to provide a consistent layout with navigation buttons.
 * It displays a "Game Over" title and the end game results including the player's final score
 * and whether they won or lost the game.
 */
public class GameOverView extends BorderWithButtons {

  /** Language handler for internationalization support. */
  private Lang lang = new Lang();

  /**
   * Constructs a new GameOverView with the specified game view.
   * Initializes the view with a localized "Game Over" title and creates
   * an EndGameInfos component to display the final results of the game session.
   * The component is centered in the view with appropriate margins.
   *
   * @param gameView the game view containing the final game state and results
   */
  public GameOverView(GameView gameView) {
    super();
    if (lang.getCurr_lang().equals("EN")) {
      this.addTitle("Game Over", false);
    } else {
      this.addTitle("Fin de partie", false);
    }

    EndGameInfos infos = new EndGameInfos(gameView);

    this.setCenter(infos);

    infos.setAlignment(Pos.TOP_CENTER);
    BorderPane.setMargin(infos, new Insets(10));
  }

  /**
   * Sets up the navigation buttons for the game over screen.
   * Adds a menu button to return to the main menu and a quit button
   * to exit the application. These buttons are added to the button bar
   * at the bottom of the view.
   */
  public void setButtons() {
    ArrayList<Button> buttons = new ArrayList<>();

    ButtonMenu menu = new ButtonMenu(this.getMainScene());
    ButtonQuit quit = new ButtonQuit();

    buttons.add(menu);
    buttons.add(quit);

    this.addButtons(buttons);
  }
}
