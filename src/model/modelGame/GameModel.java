package model.modelGame;

import java.io.FileReader;
import model.modelCharacter.modelHeros.HeroModel;
import model.modelCharacter.modelHeros.JobModel;
import model.modelLocation.*;
import org.json.JSONObject;
import org.json.JSONTokener;

public class GameModel {

  private long startTime;
  public static int Score;
  private final HeroModel HEROM;
  public GameMapModel map;
  private LocationModel currentLocation;
  private boolean isEnd;
  private boolean isWon;

  public GameModel() {
    this.HEROM = new HeroModel();
    this.map = new GameMapModel();
    this.currentLocation = map.getStartLoc();
    this.isEnd = false;
    this.isWon = false;
  }

  public GameModel(String name, String jobChoice) {
    this.HEROM = new HeroModel(name, JobModel.valueOf(jobChoice));
    this.map = new GameMapModel();
    this.currentLocation = map.getStartLoc();
    this.isEnd = false;
    this.isWon = false;
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
    System.out.println(
      MessageEnModel.startGame(map.getStartLoc(), map.getEndLoc())
    );
    System.out.println(MessageEnModel.getDescription(this.getCurLocation()));
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
      return true;
    } else if (this.getCurLocation() == map.getEndLoc()) {
      this.isWon = true;
      this.isEnd = true;
    }

    return this.isEnd;
  }

  //Displays the end of modelGame
  public void displayEnd() {
    if (this.isWon()) {
      System.out.println(MessageEnModel.gameWon());
    } else {
      System.out.println(MessageEnModel.gameLost());
    }
  }
}
