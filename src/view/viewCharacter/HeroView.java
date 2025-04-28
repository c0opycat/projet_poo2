package view.viewCharacter;

import controller.controllerCharacter.controllerHeros.HeroController;
import java.awt.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

  public Point getActualCoord() {
    return this.actual_coord;
  }

  public void setActualCoord(Point point) {
    this.actual_coord = point;
  }

  public HeroController getHeroController() {
    return this.heroController;
  }

  public Point getDefaultCoord() {
    return this.default_coord;
  }

  public String getName() {
    return this.getHeroController().getName();
  }
}
