package controller.controllerGame;

import java.io.PrintStream;
import java.util.ArrayList;
import model.modelCharacter.modelHeros.JobModel;
import model.modelGame.GameModel;
import view.viewGame.GameInfosStream;
import view.viewGame.GameView;

public class GameController {

  private final GameModel gameModel;
  private final GameView gameView;

  public GameController(GameView gameView, String name, String jobChoice) {
    this.gameView = gameView;
    this.gameModel = new GameModel(name, jobChoice);
  }

  public GameModel getGameModel() {
    return this.gameModel;
  }

  public GameView getGameView() {
    return this.gameView;
  }

  public void start() {
    PrintStream newOut = new PrintStream(
      new GameInfosStream(this.getGameView().getGameInfos())
    );
    System.setOut(newOut);

    this.getGameModel().start();
  }

  public void end() {
    System.setOut(System.out);
  }

  public static ArrayList<String> getJobs() {
    ArrayList<String> jobs = new ArrayList<>();

    for (JobModel job : JobModel.values()) {
      jobs.add(job.name());
    }

    return jobs;
  }

  public static String getLang() {
    return GameModel.loadLanguage();
  }
}
