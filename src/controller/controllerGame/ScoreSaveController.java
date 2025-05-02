package controller.controllerGame;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import model.modelGame.modelScore.ScoreModel;
import model.modelGame.modelScore.ScoreSaveModel;
import view.viewHallOfFame.ScoreSaveView;

/**
 * Controller class for managing game score retrieval and display.
 * This class acts as an intermediary between the ScoreSaveModel and ScoreSaveView.
 * It handles operations related to high score data access and formatting
 * for presentation in the Hall of Fame screen.
 * @author L. Cooper
 */
public class ScoreSaveController {

  /** The view that displays the score leaderboard in the game interface. */
  private final ScoreSaveView scoreSaveView;

  /** The model containing the score data and saving/loading logic. */
  private final ScoreSaveModel scoreSaveModel;

  /**
   * Constructs a new ScoreSaveController with the specified score save view.
   * Initializes the controller with a reference to the score save view
   * and creates a new ScoreSaveModel to handle data operations.
   *
   * @param scoreSaveView the view that displays the high scores
   */
  public ScoreSaveController(ScoreSaveView scoreSaveView) {
    this.scoreSaveView = scoreSaveView;
    this.scoreSaveModel = new ScoreSaveModel();
  }

  /**
   * Gets the score save view associated with this controller.
   *
   * @return the ScoreSaveView instance
   */
  public ScoreSaveView getScoreSaveView() {
    return this.scoreSaveView;
  }

  /**
   * Gets the score save model associated with this controller.
   *
   * @return the ScoreSaveModel instance
   */
  public ScoreSaveModel getScoreSaveModel() {
    return this.scoreSaveModel;
  }

  /**
   * Retrieves the list of high scores as paired player names and score values.
   * Converts the internal ScoreModel objects from the model layer into
   * more view-friendly Pair objects containing just the name and score value.
   *
   * @return a list of Pair objects where each pair contains a player name and score
   */
  public List<Pair<String, Long>> getScores() {
    List<Pair<String, Long>> scoresPair = new ArrayList<>();

    List<ScoreModel> scores = this.scoreSaveModel.getScores();

    for (ScoreModel score : scores) {
      scoresPair.add(new Pair<String, Long>(score.getName(), score.getScore()));
    }

    return scoresPair;
  }
}
