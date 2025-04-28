package view.viewCharacter;

import controller.controllerCharacter.controllerHeros.HeroController;
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

  private final Point default_coord;
  private Point actual_coord;
  private final HeroController heroController;

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
}
