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

public class GameModel {

  public int timePaused;
  private long startTime;
  private int[] killedMonster;
  public boolean isRunning;
  public ScoreModel score;
  private final HeroModel HEROM;
  public GameMapModel map;
  private LocationModel currentLocation;
  private boolean isEnd;
  private boolean isWon;

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
  }

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
  }

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

  public void forfeit(boolean b) {
    this.isEnd = b;
    this.isWon = false;
  }

  public HeroModel getHero() {
    return this.HEROM;
  }

  //Displays the start of modelGame
  public void start() {
    this.startTime = System.currentTimeMillis();
    System.out.println(
      MessageEnModel.startGame(map.getStartLoc(), map.getEndLoc())
    );
    System.out.println(MessageEnModel.getDescription(this.getCurLocation()));
  }

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

  private int timeBonus() {
    int timeBonus =
      1000 -
      (int) (System.currentTimeMillis() - startTime) / 100 +
      this.timePaused;
    if (timeBonus > 0) {
      return timeBonus;
    } else return 0;
  }

  public void updateScore() {
    this.score.setScore(
        this.timeBonus() +
        this.killedMonster[0] * 10 +
        this.killedMonster[1] * 20 +
        this.killedMonster[2] * 50
      );
  }

  //Returns the current Location
  public LocationModel getCurLocation() {
    return this.currentLocation;
  }

  //Sets the current modelLocation to l
  public void setCurLocation(LocationModel l) {
    this.currentLocation = l;
  }

  //Returns true if the modelGame is won
  public boolean isWon() {
    return this.isWon;
  }

  //Checks is the modelGame is ended and returns if it is
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

  //Displays the end of modelGame
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

  public String getGameWonMessage() {
    return MessageEnModel.gameWon();
  }

  public String getGameLostMessage() {
    return MessageEnModel.gameLost();
  }
}
