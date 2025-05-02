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
    super(20);
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
