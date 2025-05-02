package view.viewCharacter;

import controller.controllerCharacter.MonsterController;
import java.awt.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.viewGame.GameView;
import view.viewLocation.LocationView;

/**
 * The MonsterView class represents the visual component of a monster in the game.
 * This class extends ImageView to provide a graphical representation of different monster types.
 * Each monster has a specific image based on its type (Colossus, Angry, or Dried).
 * The class also manages the monster's position in the game world and provides methods
 * for monster-related actions like attacking and checking attack range.
 * @author L. Cooper
 * @author A. Bertrand-Bernard
 */
public class MonsterView extends ImageView {

  /** Controller that manages the monster's behavior and game logic. */
  private final MonsterController monsterController;

  /** Reference to the main game view for accessing game state and UI elements. */
  private final GameView gameView;

  /** Current coordinates of the monster in the game grid. */
  private Point coord;

  /**
   * Constructs a new MonsterView with the specified game and location views.
   * Initializes the monster controller and sets the appropriate image based on
   * the monster type determined by the controller. Different monster types
   * (Colossus, Angry, Dried) have distinct visual representations.
   *
   * @param gameView the game view to associate with this monster
   * @param locationView the location view where this monster will appear
   */
  public MonsterView(GameView gameView, LocationView locationView) {
    super();
    this.monsterController = new MonsterController(
      gameView,
      this,
      locationView
    );
    this.gameView = gameView;

    if (this.getMonsterController().hasMonsterModel()) {
      String type = this.getMonsterController().getType();

      if (type.equals("Colossus")) {
        this.setImage(
            new Image("file:./resources/image/colossus.png", 25, 25, true, true)
          );
      } else if (type.equals("Angry")) {
        this.setImage(
            new Image("file:./resources/image/angry.png", 25, 25, true, true)
          );
      } else {
        this.setImage(
            new Image("file:./resources/image/dried.png", 25, 25, true, true)
          );
      }
    }
  }

  /**
   * Gets the current coordinates of this monster.
   *
   * @return a Point object containing the monster's x,y position
   */
  public Point getCoord() {
    return this.coord;
  }

  /**
   * Sets the coordinates for this monster.
   *
   * @param point the Point object specifying the new x,y position
   */
  public void setCoord(Point point) {
    this.coord = point;
  }

  /**
   * Gets the monster controller associated with this view.
   *
   * @return the MonsterController managing this monster
   */
  public MonsterController getMonsterController() {
    return this.monsterController;
  }

  /**
   * Gets the game view associated with this monster.
   *
   * @return the GameView instance
   */
  public GameView getGameView() {
    return this.gameView;
  }

  /**
   * Gets the type of this monster (Colossus, Angry, or Dried).
   *
   * @return a string representing the monster's type
   */
  public String getType() {
    return this.getMonsterController().getType();
  }

  /**
   * Executes an attack action for this monster.
   * Delegates to the monster controller to handle the attack logic.
   */
  public void attack() {
    this.getMonsterController().attack();
  }

  /**
   * Checks if the hero is within this monster's attack range.
   * A monster can attack if the hero is within a 5x5 grid centered on the monster
   * (i.e., at most 2 cells away horizontally and vertically).
   *
   * @return true if the hero is within attack range, false otherwise
   */
  public boolean isInAttackRange() {
    HeroView heroView = this.getGameView().getHeroView();

    int heroX = (int) heroView.getActualCoord().getX();
    int heroY = (int) heroView.getActualCoord().getY();
    int monsterX = (int) this.getCoord().getX();
    int monsterY = (int) this.getCoord().getY();

    return (
      monsterX >= heroX - 2 &&
      monsterX <= heroX + 2 &&
      monsterY >= heroY - 2 &&
      monsterY <= heroY + 2
    );
  }
}
