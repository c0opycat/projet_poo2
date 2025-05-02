package view.viewHallOfFame;

import controller.controllerGame.ScoreSaveController;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import view.Lang;

/**
 * The ScoreSaveView class displays a leaderboard of player high scores.
 * This class creates a vertical layout that shows the top scores in the game,
 * with special styling for the top three ranks. It also includes placeholder
 * entries to always display exactly 10 rows in the score table.
 * @author L. Cooper
 */
public class ScoreSaveView extends VBox {

  /** Controller that manages score data retrieval and processing. */
  private final ScoreSaveController scoreSaveController;

  /** Language handler for internationalization support. */
  private final Lang lang = new Lang();

  /**
   * Constructs a new ScoreSaveView.
   * Initializes the view with vertical spacing of 20 pixels, creates its
   * controller, populates the score list, centers all content, and applies
   * the "score-view" style class for CSS styling.
   */
  public ScoreSaveView() {
    super(20);
    this.scoreSaveController = new ScoreSaveController(this);

    this.addScores();
    this.setAlignment(Pos.CENTER);
    this.getStyleClass().add("score-view");
  }

  /**
   * Gets the score save controller associated with this view.
   *
   * @return the ScoreSaveController handling data operations
   */
  public ScoreSaveController getScoreSaveController() {
    return this.scoreSaveController;
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
   * Populates the view with score entries.
   * Creates a row for each score in the top scores list retrieved from the controller.
   * Each row contains the rank, player name, and score value. Special styling is applied
   * to the top three ranks. If fewer than 10 scores exist, additional placeholder rows
   * are added to maintain consistent layout.
   */
  private void addScores() {
    List<Pair<String, Long>> topScores =
      this.getScoreSaveController().getScores();

    int rank = 1;

    for (Pair<String, Long> score : topScores) {
      Label rankLabel = new Label("#" + rank + " ");
      Label nameLabel = new Label(score.getKey());
      Label scoreLabel = new Label(" :   " + score.getValue());

      HBox rowBox = new HBox(10);
      rowBox.getChildren().addAll(rankLabel, nameLabel, scoreLabel);
      rowBox.setAlignment(Pos.CENTER);

      double setMaxWidth = 400;
      rowBox.setMaxWidth(setMaxWidth);

      rowBox.getStyleClass().add("row-score-view");

      switch (rank) {
        case 1:
          rowBox.getStyleClass().add("row-score-view-1");
          break;
        case 2:
          rowBox.getStyleClass().add("row-score-view-2");
          break;
        case 3:
          rowBox.getStyleClass().add("row-score-view-3");
          break;
        default:
          break;
      }

      this.getChildren().add(rowBox);

      rank++;
    }

    while (rank <= 10) {
      Label rankLabel = new Label("#" + rank);
      Label nameLabel = new Label("---");
      Label scoreLabel = new Label("");

      HBox rowBox = new HBox(5);
      rowBox.getChildren().addAll(rankLabel, nameLabel, scoreLabel);
      rowBox.setAlignment(Pos.CENTER);
      rowBox.getStyleClass().add("row-score-view");

      this.getChildren().add(rowBox);
      rank++;
    }
  }
}
