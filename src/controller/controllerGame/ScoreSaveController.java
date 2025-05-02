package controller.controllerGame;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import model.modelGame.modelScore.ScoreModel;
import model.modelGame.modelScore.ScoreSaveModel;
import view.viewHallOfFame.ScoreSaveView;

public class ScoreSaveController {

  private final ScoreSaveView scoreSaveView;
  private final ScoreSaveModel scoreSaveModel;

  public ScoreSaveController(ScoreSaveView scoreSaveView) {
    this.scoreSaveView = scoreSaveView;
    this.scoreSaveModel = new ScoreSaveModel();
  }

  public ScoreSaveView getScoreSaveView() {
    return this.scoreSaveView;
  }

  public ScoreSaveModel getScoreSaveModel() {
    return this.scoreSaveModel;
  }

  public List<Pair<String, Long>> getScores() {
    List<Pair<String, Long>> scoresPair = new ArrayList<>();

    List<ScoreModel> scores = this.scoreSaveModel.getScores();

    for (ScoreModel score : scores) {
      scoresPair.add(new Pair<String, Long>(score.getName(), score.getScore()));
    }

    return scoresPair;
  }
}
