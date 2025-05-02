package model.modelGame.modelScore;

import java.io.*;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The {@code ScoreSaveModel} class manages saving and loading of the best player scores to a JSON file.
 * <p>
 * It maintains a list of up to 10 top scores and handles:
 * <ul>
 *     <li>Loading scores from a file on creation</li>
 *     <li>Saving updated scores back to the file</li>
 *     <li>Adding new scores and keeping the top 10</li>
 *     <li>Displaying scores to the console</li>
 * </ul>
 */
public class ScoreSaveModel {

  /** The file path where scores are stored. */
  private static final String FILE_NAME = "./save/scores.json";

  /** The maximum number of best scores to keep. */
  private static final int MAX_SCORES = 10;

  /** The list of scores. */
  private List<ScoreModel> scores;

  /**
   * Constructs a new {@code ScoreSaveModel}, initializing and loading scores from the file if it exists.
   */
  public ScoreSaveModel() {
    this.scores = new ArrayList<>();
    loadScores();
  }

  /**
   * Loads scores from the JSON file.
   * If the file does not exist, this method does nothing.
   */
  private void loadScores() {
    File file = new File(FILE_NAME);
    if (!file.exists()) {
      return; // No scores yet
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      StringBuilder json = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        json.append(line);
      }

      JSONArray array = new JSONArray(json.toString());
      for (int i = 0; i < array.length(); i++) {
        JSONObject obj = array.getJSONObject(i);
        scores.add(new ScoreModel(obj.getString("name"), obj.getInt("score")));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Saves the current list of scores to the JSON file.
   */
  private void saveScores() {
    JSONArray array = new JSONArray();
    for (ScoreModel s : scores) {
      JSONObject obj = new JSONObject();
      obj.put("name", s.name);
      obj.put("score", s.score);
      array.put(obj);
    }

    try (FileWriter writer = new FileWriter(FILE_NAME)) {
      writer.write(array.toString(4)); // Pretty print with indent
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Adds a new score to the list.
   * <p>
   * Scores are sorted in descending order. If adding the new score exceeds the maximum allowed number of scores,
   * the lowest score is removed.
   * @param name the name of the player
   * @param score the score achieved
   */
  public void addScore(String name, long score) {
    scores.add(new ScoreModel(name, score));
    scores.sort((a, b) -> Long.compare(b.score, a.score));
    if (scores.size() > MAX_SCORES) {
      scores = scores.subList(0, MAX_SCORES);
    }
    saveScores();
  }

  /**
   * Returns the list of current scores.
   * @return a list of {@link ScoreModel} representing the scores
   */
  public List<ScoreModel> getScores() {
    return scores;
  }

  /**
   * Displays the best scores in the console.
   */
  public void displayScores() {
    System.out.println("Best Scores :\n");
    for (ScoreModel s : scores) {
      System.out.println(s.name + " - " + s.score + "\n");
    }
  }
}
