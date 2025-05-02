package view.viewCharacter;

import controller.controllerCharacter.HeroController;
import java.awt.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * HeroView is a class that represents the view of a hero in the game.
 * It extends ImageView and contains methods to manage the display of the hero.
 * It is responsible for displaying the hero's image and managing its position on the screen.
 * @author L. Cooper
 * @author A. Bertrand-Bernard
 */
public class HeroView extends ImageView {

  /**
   * The default starting coordinates for the hero.
   * This position is used as the initial placement when a hero is created
   * or when the hero's position needs to be reset.
   */
  private final Point default_coord;

  /**
   * The hero's current position in the game world.
   * These coordinates are updated whenever the hero moves to a new location.
   */
  private Point actual_coord;

  /**
   * The controller that manages the hero's behavior and game logic.
   * Handles operations related to the hero model like movement, combat, and status updates.
   */
  private final HeroController heroController;

  /**
   * Constructs a new HeroView with the specified controller.
   * Initializes the view with the hero's image and sets up the default position.
   * The hero is initially placed at coordinates (0,0) until moved by game logic.
   *
   * @param heroController the controller that manages the hero model this view will display
   */
  public HeroView(HeroController heroController) {
    super(new Image("file:./resources/image/hero.png", 25, 25, true, true));
    this.default_coord = new Point(0, 0);
    this.heroController = heroController;
    this.actual_coord = default_coord;
  }

  /**
   * getActualCoord is a method that returns the actual coordinates of the hero.
   * It is used to get the current position of the hero on the screen.
   * @return Point object representing the actual coordinates of the hero
   */
  public Point getActualCoord() {
    return this.actual_coord;
  }

  /**
   * setActualCoord is a method that sets the actual coordinates of the hero.
   * It is used to update the position of the hero on the screen.
   * @param point Point object representing the new coordinates of the hero
   */
  public void setActualCoord(Point point) {
    this.actual_coord = point;
  }

  /**
   * getHeroController is a method that returns the HeroController object.
   * It is used to get the controller of the hero.
   * @return HeroController object
   */
  public HeroController getHeroController() {
    return this.heroController;
  }

  /**
   * getDefaultCoord is a method that returns the default coordinates of the hero.
   * It is used to get the initial position of the hero on the screen.
   * @return Point object representing the default coordinates of the hero
   */
  public Point getDefaultCoord() {
    return this.default_coord;
  }

  /**
   * getName is a method that returns the name of the hero.
   * It is used to get the name of the hero for display purposes.
   * @return String representing the name of the hero
   */
  public String getName() {
    return this.getHeroController().getName();
  }

  public boolean isInAttackRange(MonsterView monsterView) {
    int heroX = (int) this.getActualCoord().getX();
    int heroY = (int) this.getActualCoord().getY();
    int monsterX = (int) monsterView.getCoord().getX();
    int monsterY = (int) monsterView.getCoord().getY();

    return (
      heroX >= monsterX - 2 &&
      heroX <= monsterX + 2 &&
      heroY >= monsterY - 2 &&
      heroY <= monsterY + 2
    );
  }
}
