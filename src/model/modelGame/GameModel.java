package model.modelGame;

import java.io.FileReader;
import model.modelCharacter.modelHeros.HeroModel;
import model.modelCharacter.modelHeros.JobModel;
import model.modelCharacter.modelMonster.MonsterModel;
import model.modelGame.modelScore.ScoreModel;
import model.modelGame.modelScore.ScoreSaveModel;
import model.modelLocation.*;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Represents the main model of the game.
 * <p>
 * Handles the game state, current location, hero status, monster kills, time tracking,
 * and scoring system. It also manages saving of scores and loading of language preferences.
 * </p>
 */
public class GameModel {

  /** Total paused time during the game in milliseconds. */
  public int timePaused;

  /** Timestamp marking the game start. */
  private long startTime;

  /** Array storing killed monsters count by type (Dried, Angry, Colossus). */
  private int[] killedMonster;

  /** Flag indicating whether the game is currently running. */
  public boolean isRunning;

  /** The player's current score. */
  public ScoreModel score;

  /** The hero controlled by the player. */
  private final HeroModel HEROM;

  /** The game map including all locations. */
  public GameMapModel map;

  /** The current location of the player. */
  private LocationModel currentLocation;

  /** Flag indicating if the game has ended. */
  private boolean isEnd;

  /** Flag indicating if the player has won the game. */
  private boolean isWon;

  /**
   * Creates a new game with default hero.
   */
  public GameModel() {
    this.isRunning = true;
    this.timePaused = 0;
    this.HEROM = new HeroModel();
    this.killedMonster = new int[3];
    this.map = new GameMapModel();
    this.currentLocation = map.getStartLoc();
    this.isEnd = false;
    this.isWon = false;
    this.score = new ScoreModel(HEROM.getName(), 0);
    this.startTime = 0;
  }

  /**
   * Creates a new game with a hero of the specified name and job.
   *
   * @param name the name of the hero
   * @param jobChoice the job of the hero (must match JobModel enum)
   */
  public GameModel(String name, String jobChoice) {
    this.isRunning = true;
    this.timePaused = 0;
    this.HEROM = new HeroModel(name, JobModel.valueOf(jobChoice));
    this.killedMonster = new int[3];
    this.map = new GameMapModel();
    this.currentLocation = map.getStartLoc();
    this.isEnd = false;
    this.isWon = false;
    this.score = new ScoreModel(HEROM.getName(), 0);
    this.startTime = 0;
  }

  /**
   * Loads the current language setting from a JSON file.
   * @return the language as a String, or null if file does not exist
   */
  public static String loadLanguage() {
    String language = null;
    try {
      FileReader reader = new FileReader("./save/language.json");
      JSONObject jsonObject = new JSONObject(new JSONTokener(reader));
      language = (String) jsonObject.get("language");
    } catch (Exception e) {
      System.err.println("File 'language.json' not found.");
    }
    return language;
  }

  /**
   * Make the game end as forfeited.
   * @param b true to end the game
   */
  public void forfeit(boolean b) {
    this.isEnd = b;
    this.isWon = false;
  }

  /**
   * Returns the current hero.
   * @return the hero model
   */
  public HeroModel getHero() {
    return this.HEROM;
  }

  /**
   * Starts the game and displays the introduction.
   */
  public void start() {
    this.startTime = System.currentTimeMillis();
    System.out.println(
      MessageEnModel.startGame(map.getStartLoc(), map.getEndLoc())
    );
    System.out.println(MessageEnModel.getDescription(this.getCurLocation()));
  }

  /**
   * Registers a killed monster.
   * @param monster the killed monster
   */
  public void addKilledMonster(MonsterModel monster) {
    String name = monster.getClass().getSimpleName();
    switch (name.substring(0, name.length() - 5)) {
      case "Dried":
        this.killedMonster[0]++;
        break;
      case "Angry":
        this.killedMonster[1]++;
        break;
      case "Colossus":
        this.killedMonster[2]++;
        break;
    }
  }

  /**
   * Calculates and returns the time bonus.
   * @return the calculated time bonus
   */
  private long timeBonus() {
    long timeBonus =
      (System.currentTimeMillis() - startTime) / 100 - this.timePaused;
    if (timeBonus > 0) {
      return timeBonus;
    } else return 0;
  }

  /**
   * Updates the score based on monsters killed and time bonus.
   */
  public void updateScore() {
    this.score.setScore(
        this.timeBonus() +
        this.killedMonster[0] * 10 +
        this.killedMonster[1] * 20 +
        this.killedMonster[2] * 50
      );
  }

  /**
   * Returns the current location.
   * @return the current location
   */
  public LocationModel getCurLocation() {
    return this.currentLocation;
  }

  /**
   * Sets the current location.
   * @param l the new location
   */
  public void setCurLocation(LocationModel l) {
    this.currentLocation = l;
  }

  /**
   * Checks whether the game has been won.
   * @return true if the player has won
   */
  public boolean isWon() {
    return this.isWon;
  }

  /**
   * Checks whether the game has ended or not.
   * @return true if the game is over
   */
  public boolean isEnd() {
    if (this.getHero().isKO()) {
      this.isWon = false;
      this.isEnd = true;
    } else if (this.getCurLocation() == map.getEndLoc()) {
      this.isWon = true;
      this.isEnd = true;
    }

    return this.isEnd;
  }

  /**
   * Displays the end game message and saves the score.
   */
  public void displayEnd() {
    updateScore();
    ScoreSaveModel saveScore = new ScoreSaveModel();
    saveScore.addScore(this.score.getName(), this.score.getScore());

    if (this.isWon()) {
      System.out.println(MessageEnModel.gameWon());
    } else {
      System.out.println(MessageEnModel.gameLost());
    }
  }

  /**
   * Returns the victory message.
   * @return the victory message
   */
  public String getGameWonMessage() {
    return MessageEnModel.gameWon();
  }

  /**
   * Returns the lost game message.
   * @return the lost game message
   */
  public String getGameLostMessage() {
    return MessageEnModel.gameLost();
  }
}
