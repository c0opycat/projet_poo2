package controller.controllerGame;

import controller.controllerCharacter.HeroController;
import java.io.PrintStream;
import java.util.ArrayList;
import model.modelCharacter.modelHeros.JobModel;
import model.modelGame.GameModel;
import view.viewGame.GameInfosStream;
import view.viewGame.GameView;
import view.viewLocation.LocationView;

/**
 * Controller class for managing the game logic.
 * It acts as an intermediary between the GameModel and GameView.
 * @author L. Cooper
 * @author A. Bertrand-Bernard
 */
public class GameController {

  /** The model containing all game logic, state, and data structures. */
  private GameModel gameModel;

  /** The view responsible for displaying the game's visual interface and handling user interactions. */
  private final GameView gameView;

  /**
   * Constructs a GameController with the specified GameView, hero name, and job choice.
   *
   * @param gameView the GameView instance associated with this controller
   * @param name the name of the hero
   * @param jobChoice the job choice of the hero
   */
  public GameController(GameView gameView, String name, String jobChoice) {
    this.gameView = gameView;
    this.gameModel = new GameModel(name, jobChoice);
  }

  /**
   * Gets the GameModel instance managed by this controller.
   *
   * @return the GameModel instance
   */
  public GameModel getGameModel() {
    return this.gameModel;
  }

  /**
   * Gets the GameView instance associated with this controller.
   *
   * @return the GameView instance
   */
  public GameView getGameView() {
    return this.gameView;
  }

  /**
   * Starts the game by initializing the game model and updating the view.
   * Redirects the standard output to the game information stream.
   */
  public void start() {
    PrintStream newOut = new PrintStream(
      new GameInfosStream(this.getGameView().getGameInfos())
    );
    System.setOut(newOut);

    this.getGameModel().start();

    this.getGameView()
      .getLevelBox()
      .getChildren()
      .add(
        this.getGameModel()
          .getCurLocation()
          .getLocationController()
          .getLocationView()
      );
  }

  /**
   * Ends the game by resetting the standard output and removing command handlers.
   */
  public void end() {
    System.setOut(System.out);

    this.getGameView()
      .getCommandsView()
      .removeHandlers(this.getGameView().getMainScene());

    this.getGameModel().displayEnd();
  }

  /**
   * Checks if the game has ended.
   * Delegates to the game model to determine if the game has reached
   * an end state (either won or lost).
   *
   * @return true if the game has ended, false otherwise
   */
  public boolean isEnd() {
    return this.getGameModel().isEnd();
  }

  /**
   * Checks if the game has been won by the player.
   * Delegates to the game model to determine if the winning conditions
   * have been satisfied.
   *
   * @return true if the player has won the game, false otherwise
   */
  public boolean isWon() {
    return this.getGameModel().isWon();
  }

  /**
   * Gets the message to display when the player wins the game.
   * Retrieves the winning message from the game model, typically
   * congratulating the player on their victory.
   *
   * @return a string containing the game won message
   */
  public String getGameWonMessage() {
    return this.getGameModel().getGameWonMessage();
  }

  /**
   * Gets the message to display when the player loses the game.
   * Retrieves the losing message from the game model, typically
   * explaining the cause of defeat.
   *
   * @return a string containing the game lost message
   */
  public String getGameLostMessage() {
    return this.getGameModel().getGameLostMessage();
  }

  /**
   * Gets the player's final score.
   * Retrieves the calculated score from the game model based on
   * the player's performance throughout the game.
   *
   * @return the player's score as a long value
   */
  public long getScore() {
    return this.getGameModel().score.getScore();
  }

  /**
   * Updates the current location in the game.
   * Loads the location and updates the corresponding view.
   */
  public void updateCurrentLocation() {
    this.getGameView().getLevelBox().getChildren().clear();

    if (this.getGameView().getCommandsView() != null) {
      this.getGameView()
        .getCommandsView()
        .removeHandlers(this.getGameView().getMainScene());
    }

    this.getGameModel().getCurLocation().getLocationController().loadLocation();

    LocationView newLocation =
      this.getGameModel()
        .getCurLocation()
        .getLocationController()
        .getLocationView();

    this.getGameView().setCurrentLocationView(newLocation);

    this.getGameView()
      .getLevelLabel()
      .setText(newLocation.getLocationController().getName());
    this.getGameView().getLevelBox().getChildren().add(newLocation);

    this.getGameView().addHandlers(this.getGameView().getMainScene());
  }

  /**
   * Gets the list of available jobs in the game.
   *
   * @return a list of job names
   */
  public static ArrayList<String> getJobs() {
    ArrayList<String> jobs = new ArrayList<>();

    for (JobModel job : JobModel.values()) {
      jobs.add(job.name());
    }

    return jobs;
  }

  /**
   * Gets the HeroController instance associated with the game's hero.
   *
   * @return the HeroController instance
   */
  public HeroController getHeroController() {
    return this.getGameModel().getHero().getHeroController();
  }
}
