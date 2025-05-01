package view.viewCharacter;

import controller.controllerCharacter.MonsterController;
import java.awt.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.viewLocation.LocationView;

public class MonsterView extends ImageView {

  private final MonsterController monsterController;
  private Point coord;

  public MonsterView(LocationView locationView) {
    super();
    this.monsterController = new MonsterController(this, locationView);

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

  public String getType() {
    return this.getMonsterController().getType();
  }
}
