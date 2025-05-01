package model.modelGame.modelScore;

/**
 * Represents a player's score.
 * <p>
 * This class stores a player's name and their associated score.
 * It provides methods to retrieve and update these values.
 * </p>
 */
public class ScoreModel {

  /** The name of the player. */
  public String name;

  /** The score of the player. */
  public long score;

  /**
   * Constructs a new ScoreModel with the given player name and score.
   * @param name  the name of the player
   * @param score the player's score
   */
  public ScoreModel(String name, long score) {
    this.name = name;
    this.score = score;
  }

  /**
   * Sets the score for this player.
   * @param score the new score to assign
   */
  public void setScore(long score) {
    this.score = score;
  }

  /**
   * Get the score of the player.
   * @return the player's score
   */
  public long getScore() {
    return score;
  }

  /**
   * Get the name of the player.
   * @return the player's name
   */
  public String getName() {
    return name;
  }
}
