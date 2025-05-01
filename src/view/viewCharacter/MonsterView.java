package view.viewCharacter;

import controller.controllerCharacter.MonsterController;
import java.awt.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.viewGame.GameView;
import view.viewLocation.LocationView;

public class MonsterView extends ImageView {

  private final MonsterController monsterController;
  private final GameView gameView;
  private Point coord;

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

  public Point getCoord() {
    return this.coord;
  }

  public void setCoord(Point point) {
    this.coord = point;
  }

  public MonsterController getMonsterController() {
    return this.monsterController;
  }

  public GameView getGameView() {
    return this.gameView;
  }

  public String getType() {
    return this.getMonsterController().getType();
  }

  public void attack() {
    this.getMonsterController().attack();
  }

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
