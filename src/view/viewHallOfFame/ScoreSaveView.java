package view.viewHallOfFame;

import controller.controllerGame.ScoreSaveController;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import view.Lang;

public class ScoreSaveView extends VBox {

  private final ScoreSaveController scoreSaveController;
  private final Lang lang = new Lang();

  public ScoreSaveView() {
    super(10);
    this.scoreSaveController = new ScoreSaveController(this);

    this.addScores();
    this.setAlignment(Pos.CENTER);
    this.getStyleClass().add("score-view");
  }

  public ScoreSaveController getScoreSaveController() {
    return this.scoreSaveController;
  }

  public Lang getLang() {
    return this.lang;
  }

  private void addScores() {
    List<Pair<String, Long>> topScores =
      this.getScoreSaveController().getScores();

    int rank = 0;

    for (Pair<String, Long> score : topScores) {
      Label rankLabel = new Label("#" + rank + " ");
      Label nameLabel = new Label(score.getKey());
      Label scoreLabel = new Label(" : " + score.getValue());

      HBox rowBox = new HBox(5);
      rowBox.getChildren().addAll(rankLabel, nameLabel, scoreLabel);
      rowBox.setAlignment(Pos.CENTER);
      rowBox.getStyleClass().add("row-score-view");

      this.getChildren().add(rowBox);

      rank++;
    }

    // Add empty rows if less than 10 scores
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
