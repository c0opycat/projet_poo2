package view.viewGameOver;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import view.Lang;
import view.viewGame.GameView;

/**
 * The EndGameInfos class represents the component that displays the final game results.
 * This class extends VBox to provide a vertical layout for displaying end game information,
 * including whether the player won or lost, their final score, and a detailed description of
 * the game outcome. It's used within the GameOverView to present results to the player.
 */
public class EndGameInfos extends VBox {

  /** Reference to the game view containing the final game state and results. */
  private final GameView gameView;

  /** Language handler for internationalization support. */
  private Lang lang = new Lang();

  /**
   * Constructs a new EndGameInfos with the specified game view.
   * Initializes the component with the game view and adds all the result elements
   * to display the game outcome information.
   *
   * @param gameView the game view containing the final game state and results
   */
  public EndGameInfos(GameView gameView) {
    super();
    this.gameView = gameView;

    this.addElements();
  }

  /**
   * Gets the game view associated with this end game information display.
   *
   * @return the GameView instance
   */
  public GameView getGameView() {
    return this.gameView;
  }

  /**
   * Gets the language handler for this component.
   *
   * @return the Lang object used for text internationalization
   */
  public Lang getLang() {
    return this.lang;
  }

  /**
   * Adds all the elements needed to display the game outcome information.
   * Creates and configures labels for the win/loss message and score display,
   * as well as a text area for the detailed outcome description. The content
   * and styling varies depending on whether the player won or lost the game
   * and the current language setting.
   */
  private void addElements() {
    String endText = "";

    TextArea endDescription = new TextArea();
    endDescription.setEditable(false);
    endDescription.setWrapText(true);

    if (this.getGameView().getGameController().isWon()) {
      endText = lang.getCurr_lang().equals("EN")
        ? "You win !"
        : "Vous avez gagné !";
      endDescription.setText(
        this.getGameView().getGameController().getGameWonMessage()
      );
    } else {
      endText = lang.getCurr_lang().equals("EN")
        ? "You died !"
        : "Vous êtes mort !";
      endDescription.setText(
        this.getGameView().getGameController().getGameLostMessage()
      );
    }

    Label endLabel = new Label(endText);

    Region spring = new Region();

    //Get the score
    long score = this.getGameView().getGameController().getScore();
    Label scoreLabel = new Label(
      lang.getCurr_lang().equals("EN")
        ? "Your Score : " + score
        : "Votre score : " + score
    );

    this.getChildren().addAll(endLabel, scoreLabel, spring, endDescription);

    //Style
    spring.setMaxHeight(100);
    VBox.setVgrow(spring, Priority.ALWAYS);
    endLabel.setStyle(
      "-fx-font-weight: bold; -fx-font-size: 50px; -fx-text-fill: #380e0e"
    );
    scoreLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 25px");
    endLabel.setAlignment(Pos.CENTER);
    scoreLabel.setAlignment(Pos.CENTER);
    VBox.setMargin(endLabel, new Insets(30));
    VBox.setMargin(scoreLabel, new Insets(20));
  }
}
